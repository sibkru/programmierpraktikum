import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class alt implements Graph {
    private HashSet<Integer> vertexIds;
    private HashMap<Integer, LinkedList<Integer>> adjacencyList;

    public void addVertex (Integer v) {
        this.vertexIds.add(v);
    }
    public void addEdge (Integer v, Integer w) {
        if (!(adjacencyList.containsKey(v))) {
            LinkedList<Integer> values = new LinkedList<>();
            values.add(w);
            adjacencyList.put(v, values);
        } else {
            LinkedList<Integer> values = new LinkedList<>();
         values = adjacencyList.get(v);
         values.add(w);
         adjacencyList.remove(v);
         adjacencyList.put(v, values);
        }
        if (!(adjacencyList.containsKey(w))) {
            LinkedList<Integer> values = new LinkedList<>();
            values.add(v);
            adjacencyList.put(w, values);
        } else {
            LinkedList<Integer> values = new LinkedList<>();
            values = adjacencyList.get(w);
            values.add(v);
            adjacencyList.remove(w);
            adjacencyList.put(w, values);
        }
    }
    public void deleteVertex (Integer v) {
        vertexIds.remove(v);
        adjacencyList.remove(v);
        for (int i: adjacencyList.keySet()) {
            LinkedList<Integer> values = new LinkedList<>();
            values = adjacencyList.get(i);
            if(values.getFirst() == v){
                values.removeFirst();
                adjacencyList.remove(i);
                adjacencyList.put(i, values);
            }
            for (Iterator k = values.iterator(); k.hasNext();){
                if(k.next() == v){
                    values.removeFirst();
                    adjacencyList.remove(i);
                    adjacencyList.put(i, values);
                }
            }

        }
    }
    public void deleteEdge (Integer w, Integer v){
        LinkedList<Integer> values = new LinkedList<>();
        values = adjacencyList.get(w);
        values.remove(v);
        adjacencyList.remove(w);
        adjacencyList.put(w, values);
        values = adjacencyList.get(v);
        values.remove(w);
        adjacencyList.remove(v);
        adjacencyList.put(v, values);
    }
    public boolean contains (Integer v){
        return vertexIds.contains(v);
    }
    public int degree (Integer v){
        if(this.contains(v)){
            return adjacencyList.get(v).size();
        }
        else {
            return 0;
            }
        }
    public boolean adjacent (Integer v, Integer w){
        if (this.contains(v) && this.contains(w)) {
            LinkedList<Integer> values = new LinkedList<>();
            values = adjacencyList.get(v);
            return values.contains(w);
        }
        return false;
    }
    public Graph getCopy(){
       alt copy = new alt();
        for (int id: this.vertexIds) {
            copy.addVertex(id);
        }
        //for (int i: adjacencyList.keySet()){
            //LinkedList<Integer> values = new LinkedList<>();
            //values = adjacencyList.get(i);
            //for (int v: values) {
                //copy.addEdge(i,v);
            //}
        //}
        return copy;
    }
    public Set<Integer> getNeighbors (Integer v){
        LinkedList<Integer> values = new LinkedList<>();
        HashSet<Integer> neighbors = new HashSet<>();
        values = adjacencyList.get(v);
        for (int i: values) {
            neighbors.add(i);
        }
        return neighbors;
    }
    public int size (){
        return vertexIds.size();
    }
    public int getEdgeCount (){
        int counter = 0;
        for (LinkedList<Integer> i: adjacencyList.values()) {
            counter = counter + i.size();
        }
        return counter / 2;
    }
    public Set<Integer> getVertices (){
        return vertexIds;
    }
    public alt (){
        this.vertexIds = new HashSet<>();
        this.adjacencyList = new HashMap<>();
    }
    public alt (String path){
        BufferedReader reader;
        this.vertexIds = new HashSet<>();
        this.adjacencyList = new HashMap<>();
        HashSet<String> lines = new HashSet<>();
        try {
            reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            while(line != null){
                lines.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Iterator<String> it = lines.iterator();
        String[] parts = new String[2];
        int v, w;
        //HashSet<String[]> parts = new HashSet<>();
        while (it.hasNext()){
            parts = it.next().split(" ");
            v = Integer.parseInt(parts[0]);
            w = Integer.parseInt(parts[1]);
            this.addVertex(v);
            this.addVertex(w);
            this.addEdge(v, w);
        }
    }
    public static void main (String[] args){
        alt graph0 = new alt("C:\\Users\\t-jen\\OneDrive\\Desktop\\bio-dmela.mtx");
        Set<Integer> vertices = graph0.getVertices();
        Iterator it = vertices.iterator();
        while( it.hasNext()){
            System.out.println(it.next());
        }
        System.out.println(graph0.getEdgeCount());
    }
}
