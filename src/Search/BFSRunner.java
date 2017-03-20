package Search;
import java.util.ArrayList;
import java.util.Arrays;

public class BFSRunner {

    public static void main(String args[]){

        //Create our nodes
        Node NYC = new Node("New York City", null);
        Node CHI = new Node("Chicago", null);
        Node DEN = new Node("Denver", null);
        Node MIA = new Node("Miami", null);
        Node DAL = new Node("Dallas", null);
        Node SD = new Node("San Diego", null);
        Node LA = new Node("Los Angeles", null);
        Node SF = new Node("San Francisco", null);

        //Add edges between appropriate cities
        NYC.setChildren(new ArrayList<Node>(Arrays.asList(CHI,DEN,MIA,DAL)));
        CHI.setChildren(new ArrayList<Node>(Arrays.asList(NYC,SF,DEN)));
        DEN.setChildren(new ArrayList<Node>(Arrays.asList(CHI,NYC,LA,SF)));
        MIA.setChildren(new ArrayList<Node>(Arrays.asList(NYC,DAL)));
        DAL.setChildren(new ArrayList<Node>(Arrays.asList(NYC,SD,MIA,LA)));
        SD.setChildren(new ArrayList<Node>(Arrays.asList(LA,DAL)));
        LA.setChildren(new ArrayList<Node>(Arrays.asList(SD,SF,DEN,DAL)));
        SF.setChildren(new ArrayList<Node>(Arrays.asList(LA,DEN,CHI)));

        //Test our BreadthFirstSearch
        AbstractSearch bfs = new BreadthFirstSearch(NYC, SD);
        AbstractSearch dfs = new DepthFirstSearch(NYC, SD);

        if(bfs.search())
            System.out.print("Path Found!");
        else
        {
            System.out.print("No Path Found");
        }
        
        System.out.println();
        System.out.println();
        
        if(dfs.search())
            System.out.print("Path Found!");
        else
        {
            System.out.print("No Path Found");
        }
    }
}