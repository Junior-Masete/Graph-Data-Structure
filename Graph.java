import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Graph {
    private String[] vertices;
    private Integer[][] adjacencyMatrix; /////////////////////////////
    private int numVertices;
    private int numEdges;
    

    public Graph(String fileName) {
        if(fileName == ""){
            numEdges = 0;
            numVertices = 0;
            vertices = new String[numVertices];
            adjacencyMatrix = new Integer[numVertices][numVertices];
        }

       File f = new File(fileName);
       try(Scanner reader = new Scanner(f)){
            String numV = reader.nextLine();
            numVertices = Integer.parseInt(numV);
            vertices = new String[numVertices];
            numEdges = 0;
            adjacencyMatrix = new Integer[numVertices][numVertices];

            String vtcs = reader.nextLine();
           // Scanner mysVtcs = new Scanner(vtcs);
           vertices = vtcs.split(" ");
           String myStr;
           String mStarr[];
           int count = 0;
           
           while(reader.hasNext()){
                myStr = reader.nextLine();
                mStarr = myStr.split(" ");
                for(int i = 0; i < numVertices; i++){
                    adjacencyMatrix[count][i] = Integer.parseInt(mStarr[i]);
                }
                
                count++;
           }

           for(int x=0;x < numVertices; x++){
            for(int y =0; y < numVertices; y++){
                
                if(adjacencyMatrix[x][y] != 0){
                    numEdges++;
                }
            }
            
        }


            reader.close();    
        //------------------------
        
            
       }
       catch(FileNotFoundException e){
            
       }
    }

    public void insertVertex(String name) {
        

        int updNumV = numVertices + 1;
        Integer [][] theArr = new Integer[numVertices][numVertices];

        for(int z=0;z < numVertices; z++){
            for(int y =0; y < numVertices; y++){
                theArr[z][y] = adjacencyMatrix[z][y];
            }
        }

        adjacencyMatrix = new Integer[updNumV][updNumV];
        for(int z=0;z < numVertices; z++){
            for(int y =0; y < numVertices; y++){
                adjacencyMatrix[z][y] = theArr[z][y];
            }
        }

        for(int y =0; y < updNumV; y++){
            adjacencyMatrix[y][updNumV-1] = 0;
        }

        for(int y =0; y < updNumV; y++){
            adjacencyMatrix[updNumV-1][y] = 0;
        }

        

        String [] myArr = new String[numVertices];
        for(int i=0; i < numVertices; i++){
            myArr[i] = vertices[i];
        }

        vertices = new String[numVertices+1];
        for(int x =0; x < numVertices;x++){
            vertices[x] = myArr[x];
        }

        vertices[numVertices] = name;
        numVertices++;
        
        
    }

    public void insertEdge(String start, String end, int weight) {
        int r, c;
        r = validVertices(start);
        c = validVertices(end);

        if(r != -1 && c != -1 && weight != 0){
            adjacencyMatrix[r][c] = weight;
            numEdges++;
        }
    }

    private int validVertices(String a){ //check if both vertices exists

        for(int i=0; i < numVertices; i++){
            if(vertices[i].equals(a)){
                return i;
            }
        }

        return -1;
    }

    public void removeVertex(String name) {
        if(numVertices == 0){
            return;
        }

        int index = validVertices(name);

        int count=0;
        for(int r=0; r < adjacencyMatrix.length; r++){
            if(adjacencyMatrix[index][r] != 0){
                count++;
            }
        }

        String [] myArr = new String[numVertices-1];
        for(int i = 0, x=0 ; i < numVertices-1; i++, x++){
            if(vertices[i].equals(name)){
                myArr[i] = vertices[x+1];
                x++;
            }else{
                myArr[i] = vertices[x];
            }
            
        }

        numVertices--;
        //numEdges--;
        vertices = new String[numVertices];
        for(int i = 0 ; i < numVertices; i++){
            vertices[i] = myArr[i];
        }

        //Integer[][] adjMat = new Integer[numVertices][numVertices];
        
        if (index == -1) {
            
            return;
        }

       

        for (int i = index; i < adjacencyMatrix.length - 1; i++) {
            adjacencyMatrix[i] = adjacencyMatrix[i + 1];
        }
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = index; j < adjacencyMatrix.length - 1; j++) {
                adjacencyMatrix[i][j] = adjacencyMatrix[i][j + 1];
            }
        }
        

        numEdges -= count;

        Integer[][] adjMat = new Integer[adjacencyMatrix.length - 1][adjacencyMatrix.length - 1];

        for(int i = 0; i < adjMat.length; i++){
            for (int j = 0; j < adjMat.length; j++) {
                adjMat[i][j] = adjacencyMatrix[i < index ? i : i + 1][j < index ? j : j + 1];
            }
        }

        
        adjacencyMatrix = adjMat;
    }

   

    public void removeEdge(String start, String end) {

        int r = validVertices(start);
        int c = validVertices(end);

        if(r != -1 && c != -1){
            if(adjacencyMatrix[r][c] != 0){
                adjacencyMatrix[r][c] = 0;
                numEdges--;
            }
            
        }
    }

    @Override
    public String toString() {
        String myStr;
        myStr = "(" + numVertices +"," + numEdges + ")" + "\t";
        for(int i=0; i < numVertices; i++){
            myStr += vertices[i];
            if(i < numVertices-1){
                myStr += "\t";
            }
        }

        String myStr2="";

        for(int i = 0; i < numVertices;i++){
            myStr2 += vertices[i] + "\t";
            for(int j = 0; j < numVertices;j++){
                
                myStr2 += adjacencyMatrix[i][j];
                if(j < numVertices-1){
                    myStr2 += "\t";
                }
            }
            myStr2 += "\n";
        }



        return String.format( myStr + "\n" +  myStr2);
        //return myStr;
    }

    public String depthFirstTraversal() {
        if(numVertices == 0){
            return "";
        }

        boolean [] already = new boolean[numVertices];
        //int [] numV = new int[numVertices];
        //String myStrrr= "";
        StringBuilder myStrrr = new StringBuilder();
        
        DFC(0, already, myStrrr);
        //String mStr = "";
        
        return myStrrr.toString();
    }

    private void DFC(int v, boolean visited[], StringBuilder myStrrr){ //recursive function
        visited[v] = true;
        //String myStr = "";
        
        //myStrrr += "[" + vertices[v] + "]";
        myStrrr.append("[");
        myStrrr.append(vertices[v].charAt(0));
        myStrrr.append("]");
      
        //System.out.print("I am adding");
        //System.out.print("[" + vertices[v] + "]");
        

        for(int i=0; i < numVertices; i++){
            if(adjacencyMatrix[v][i] != 0 && !visited[i]){
                DFC(i, visited, myStrrr);
            }
        }

    }

    public String breadthFirstTraversal() {
        if(numVertices == 0){
            return "";
        }

        int startV = 0;
        String myStr = "";
        boolean [] visited = new boolean[numVertices];
        int [] queue = new int[numVertices];
        int front = 0;
        int rear = 0;

        visited[startV] = true;
        queue[rear++] = startV;

        while (front != rear) {
            int currIndex = queue[front++];
            myStr += "[" + vertices[currIndex] + "]";
            //System.out.print("[" + vertices[currIndex] + "]");

            for (int i = 0; i < numVertices; i++) {
                if (adjacencyMatrix[currIndex][i] != 0 && !visited[i]) {
                    visited[i] = true;
                    queue[rear++] = i;
                }
            }
        }
        
        return myStr;
    }

    public Double[][] shortestPaths() {
        if(numVertices == 0){
            Double[][] dists = new Double[0][0];
            return dists;
        }

        Double unreachable = Double.POSITIVE_INFINITY;
        Double[][] dists = new Double[numVertices][numVertices];
        for(int i = 0; i < numVertices; i++){
            for(int x = 0; x < numVertices; x++){
                dists[i][x] = Double.valueOf(adjacencyMatrix[i][x]);
            }
        }

        for (int k = 0; k < numVertices; k++) {
            for (int i = 0; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    if (dists[i][k] != unreachable && dists[k][j] != unreachable && dists[i][k] + dists[k][j] < dists[i][j]) {
                        dists[i][j] = dists[i][k] + dists[k][j];
                    }
                }
            }
        }

        return dists;

    }

    public Double shortestPath(String start, String end) {

        if(validVertices(start) == -1 || validVertices(end) == -1){
            return null;
        }

        double[] dists = new double[numVertices];
        for(int i=0; i < numVertices; i++){
            dists[i] = Double.POSITIVE_INFINITY;
        }

        int[] prev = new int[numVertices]; //stores indices
        int indOfStart, indOfEnd;
        indOfStart = findInd(start);
        indOfEnd = findInd(end);

        dists[0] = 0.0;
        int[] queue = new int[numVertices];
        int queueSize = 1;
        queue[0] = indOfStart;

        int queueInd = 0;
        while(queueSize > 0){
            int curr = queue[queueInd];
            queueInd = (queueInd + 1)%numVertices;
            queueSize--;

            if(curr == indOfEnd){
                break;
            }

            for(int u=0; u < numVertices; u++){
                double weight = adjacencyMatrix[curr][u];

                if(weight > 0){
                    double newDist = dists[curr] + weight;

                    if(newDist < dists[u]){
                        dists[u] = newDist;
                        prev[u] = curr;

                        queue[(queueInd + queueSize) % numVertices] = u;
                        queueSize++;
                    }

                }

            }
        }

        /*String [] shortestPath = new String[numVertices];
        int curr = indOfEnd;
        int shortestPathInd = 0;

        while(curr != indOfStart){
            shortestPath[shortestPathInd] = vertices[curr];
            curr = prev[curr];
            shortestPathInd++;

        }

        shortestPath[shortestPathInd] = start;*/


        return dists[indOfEnd];

    }

    private int findInd(String vertex){
        for(int i = 0; i < numVertices-1; i++){
            if(vertices[i].equals(vertex)){
                return i;
            }
            
        }

        return -1;

    }

    public boolean cycleDetection() {
        boolean [] visited = new boolean[numVertices];
        //int [] visited = new int[numVertices];
        
        for(int i = 0; i < numVertices; i++){
            if(!visited[i] && checkCycle(i, visited, -1)){
                return true;
            }
    
        }
        return false;
    }

    private boolean checkCycle(int v, boolean [] visited, int parent){
    
        int b=0;
        visited[v] = true;
        for (int u = 0; u < numVertices; u++) {
            if (adjacencyMatrix[v][u] != 0) {
                if (!visited[u]) {
                    if (checkCycle(u, visited, v)) {
                        return true;
                    }
                } else if (u != parent) {
                    return true; // Found a back edge (cycle)
                }
            }
        }
        return false;

    }

    public String stronglyConnectedComponents() {
        if (numVertices == 0) {
            return "";
        }

        return "";
    }

  

    public void printt(){
        for(int z =0; z < numVertices; z++){
            System.out.print(vertices[z] + " ");
        }
        System.out.println();
        for(int x=0;x < numVertices; x++){
            for(int y =0; y < numVertices; y++){
                System.out.print(adjacencyMatrix[x][y] + " ");
            }
            System.out.println();
        }
    }
}