import java.util.*;
import java.io.File;    
import java.io.BufferedReader;  
import java.io.IOException; 
import java.io.FileReader; 




//Informed search
public class find_route {
    //creating a virtual map
    
    Hashtable<String, ArrayList<String[]>> city_map = new Hashtable<String, ArrayList<String[]>>();
   //similar city_map but for hueristics file/
    Hashtable<String, Integer> hueristics = new Hashtable<String,Integer>();
    //route is to store the  shortest path form souce to destination/
    Hashtable<String, Object[]> route = new Hashtable<String, Object[]>();
    int store_expnodes=0;
    int popped_nodes=0;
    int nodes_generated=0;

    //function to separate from file to [row]][col]
    private void sepFile(String inputFile) throws IOException
    {

        File file = new File(inputFile);    //reading the file
        BufferedReader br = new BufferedReader(new FileReader(file.getPath()));
        String line;
        while (!(line=br.readLine()).equals("END OF INPUT"))    //reading till END OF INPUT is encountered
        {
            String start = line.split(" ")[0];
            String end = line.split(" ")[1];
            String dist = line.split(" ")[2];
            generateCityMap(start, end,dist);     //generating the node
            generateCityMap(end, start, dist);    
        }
    }


    private void sepHeuFile(String h_file) throws IOException
    {

        File file = new File(h_file);
        Scanner sc = new Scanner(new FileReader(file.getPath()));
        String line;
        while (!(line = sc.nextLine()).equals("END OF INPUT"))
        {
            hueristics.put(line.split(" ")[0].toString(),Integer.parseInt(line.split(" ")[1].toString()));
        }
    }
    // Function stores the input file information in city_map.
    private void generateCityMap(String start, String end, String dist)
    {
        String[] entry = {end, dist};
        if (city_map.containsKey(start))
            city_map.get(start).add(entry);
        else {
            ArrayList<String[]> temp = new ArrayList<String[]>();
            temp.add(entry);
            city_map.put(start,temp);
        }
        nodes_generated=city_map.size()-1
    ;
    }

    
    public void determineRoute(Node node)
    {
        if (!route.containsKey(node.city) || (Integer) route.get(node.city)[1]>node.path_cost){
            Object[] valueRoute = {node.con_city!=null ? node.con_city.city : null, node.path_cost};
            route.put(node.city,valueRoute);
        }
    }
    
    //printing the route 
    private void pathTraceBack(String destination)
    {

        String cumulativeDistance = "infinity";
        Stack<String> finalRoute = new Stack<String>();
        if (route.containsKey(destination)){
            cumulativeDistance = route.get(destination)[1]+" km";
            String con_city = (String) route.get(destination)[0];
            while (con_city!=null){
                int dist = (Integer)route.get(destination)[1] - (Integer)route.get(con_city)[1];
                finalRoute.push(con_city+" to "+destination+ ", "+dist+" km");
                destination=con_city;
                con_city = (String)route.get(destination)[0];
            }
           
        }
        StringBuffer sb = new StringBuffer();
        sb.append("nodes popped: "+popped_nodes+"\n");
        sb.append("nodes expanded: "+store_expnodes  +"\n");
        sb.append("nodes generated: "+nodes_generated+"\n");
        sb.append("distance: "+cumulativeDistance+"\n");
        sb.append("route:\n");
        if (finalRoute.isEmpty()) sb.append("none");
        else {
            while (!finalRoute.isEmpty()){
                sb.append(finalRoute.pop());
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
    }
     //informed search
     private void findRoute(String inputFile, String source, String destination, String h_file) throws IOException
     {   

        sepHeuFile(h_file);

        sepFile(inputFile);

        
         HashSet<String> visited_node = new HashSet<String>();
         PriorityQueue<Node> fringe = new PriorityQueue<Node>(1,new hComp());//stores the data on the basis of path cost
         fringe.add(new Node(source,null,0,0)); //destination as null, path as 0
         while (!fringe.isEmpty())
         {   
             Node currentNode = fringe.poll(); // store the node from fringe
           
             popped_nodes++;
             
             determineRoute(currentNode);
             
             if (currentNode.city.equals(destination)) 
                 break;
             //if the current city is in visited then skip the process
             if(visited_node.contains(currentNode.city))
                 continue;
             ArrayList<String[]> childrenCityInfo = city_map.get(currentNode.city);
             Iterator<String[]> cCIIterator = childrenCityInfo.iterator();
             while (cCIIterator.hasNext()){
                 String[] str = cCIIterator.next();
                 Node node = new Node(str[0],currentNode, currentNode.path_cost+Integer.parseInt(str[1]),currentNode.path_cost+Integer.parseInt(str[1])+hueristics.get(str[0]));
                 fringe.add(node);
             }
             //Adding the city to visited the node
             visited_node.add(currentNode.city);
             store_expnodes = visited_node.size();
         }
         //prints the elements of the route
         pathTraceBack(destination);
         
     }

    //for uninformed search
    private void findRoute(String inputFile, String source, String destination) throws IOException
    {
        sepFile(inputFile);
        HashSet<String> visited_node = new HashSet<String>();
        PriorityQueue<Node> fringe = new PriorityQueue<Node>(1,new nComp()); //queue on the basis of path cost
        fringe.add(new Node(source,null,0)); //destination as null, path as 0
        while (!fringe.isEmpty())
        {   //storing the node from fringe at index 0 to currentNode
            Node currentNode = fringe.poll();
            popped_nodes++;
           
            determineRoute(currentNode);

            //If the destination is reached then break the loop
            if (currentNode.city.equals(destination)) 
                break;
            //if the current city is in visited then skip the process
            if(visited_node.contains(currentNode.city))
                continue;
            ArrayList<String[]> childrenCityInfo = city_map.get(currentNode.city);
            Iterator<String[]> iter = childrenCityInfo.iterator();
            while (iter.hasNext()){
                String[] s = iter.next();
                Node n = new Node(s[0],currentNode,currentNode.path_cost+Integer.parseInt(s[1]));
                fringe.add(n);
            }
            //Adding the city to visited the node
            visited_node.add(currentNode.city);
           store_expnodes = visited_node.size();
          
        }
        //prints the nodes of the path
        pathTraceBack(destination);
    }

   

    //main function
    //check which search is to be executed
    public static void main(String[] args) throws IOException
    {
        find_route findRoute = new find_route();
        // finds the shortest path for uninformed search without using hueristics file.
        if (args.length==3 && (args[0]!=null || args[1]!=null || args[2]!=null))
            findRoute.findRoute(args[0],args[1],args[2]);
        // finds the shortest path for informed search using hueristics file.
        else if (args.length==4 && (args[0]!=null || args[1]!=null || args[2]!=null || args[3]!=null))
            findRoute.findRoute(args[0],args[1],args[2],args[3]);
        else
            System.out.println("Please enter valid input!");
    }
}