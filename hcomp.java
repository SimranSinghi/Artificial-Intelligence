
import java.util.*;

public class hcomp { //class for sorting in desccending order of the fringe on path cost/
    class hComp implements Comparator<Node>{

        @Override
        public int compare(Node n1, Node n2) {
    
            if (n1.heuristic_cost>n2.heuristic_cost) return 1;
            else  if (n1.heuristic_cost<n2.heuristic_cost) return -1;
            return 0;
        }
    }
}
