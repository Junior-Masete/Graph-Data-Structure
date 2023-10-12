public class Main {
    public static void main(String[] args) {
        Graph g = new Graph("graph.txt");
        g.printt();
        System.out.println();

        System.out.println("-------Testing toString-------");
        System.out.println(g);
        System.out.println();

        System.out.println("-------Testing DepthFirst-------");
        System.out.println("DFS: "+g.depthFirstTraversal());;
        System.out.println();
        System.out.println("-------Testing BreadthFirst-------");
        System.out.println("BFS:" + g.breadthFirstTraversal());
        System.out.println();

        System.out.println("-------Testing ShortestPath(S, E)-----");
        System.out.println("The Shortest distance between A and L is: " + g.shortestPath("A", "L"));
        System.out.println("Executed");
        System.out.println();
        System.out.println("-------Testing cycleDetection()-----");
        boolean isCycle;
        isCycle = g.cycleDetection();
        if(isCycle){
            System.out.println("The Cycle is Detected");
        }
        else{
            System.out.println("The Cycle is Not Detected");
        }
        System.out.println("Executed");
        System.out.println();

        System.out.println("-------Testing All-All ShortestPath()-----");
        Double[][] myArr = g.shortestPaths();
        for(int i = 0; i < myArr.length; i++){
            for(int x = 0; x < myArr.length; x++){
                System.out.print(myArr[i][x] + " - ");
            
            }
            System.out.println();
        }
        System.out.println("Executed");
        System.out.println();

        System.out.println("-------Testing StronglyConnectedComponenets()-----");
        //System.out.println(g.stronglyConnectedComponents());
        
        /*System.out.println("DFS:" + g.depthFirstTraversal());
        System.out.println();
        System.out.println("BFS:" + g.breadthFirstTraversal());


        System.out.println("-------Testing InsertVertices-----");
        g.insertVertex("K");
        g.printt();

        System.out.println("-------Testing InsertEdge-----");
        g.insertEdge("J", "K", 6);
        g.printt();

        System.out.println("-------Testing removeEdge-----");
        g.removeEdge("A", "J");
        g.printt();

        System.out.println("-------Testing toString-------");
        System.out.println(g);

        System.out.println("-------Testing DepthFirst-------");
        g.depthFirstTraversal();
        System.out.println();*/
        





        //System.out.println(g);


        /*System.out.println("DFS:" + g.depthFirstTraversal());
        System.out.println("BFS:" + g.breadthFirstTraversal());
        System.out.println("Strongly Connected:\n" + g.stronglyConnectedComponents());
        */
    }
}