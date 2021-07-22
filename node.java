
class Node{
    
    int heuristic_cost;  
    String city;    
    Node con_city;    
    int path_cost;  

    Node(String city, Node con_city, int path_cost)   //for uninformed search
    {
        this.path_cost = path_cost;
        this.con_city = con_city;
        this.city = city;
    }
    Node(String city, Node con_city, int path_cost, int heuristicCost)    //for informed search
    {
        this.path_cost = path_cost;
        this.con_city = con_city;
        this.city = city;
        this.heuristic_cost = heuristicCost;
    }
    
}
