import java.util.*;
import java.io.File;    
import java.io.BufferedReader;  
import java.io.IOException; 
import java.io.FileReader;


class nComp implements Comparator<Node>{
    public int compare(Node n1,Node n2)
    {
        if (n1.path_cost>n2.path_cost) return 1;
        else if (n1.path_cost<n2.path_cost) return -1;
        else return 0;
    }
}
