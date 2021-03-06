import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class MyGraph implements Graph {
    private HashSet<Integer> vertexIds;
    private HashMap<Integer, HashSet<Integer>> adjacencyList;

    public void addVertex (Integer v) {
        this.vertexIds.add(v);
    }
    public void addEdge (Integer v, Integer w) {
        if (!(adjacencyList.containsKey(v))) {
            HashSet<Integer> values = new HashSet<>();
            values.add(w);
            adjacencyList.put(v, values);
        } else {
            adjacencyList.get(v).add(w);
            }
        if (!(adjacencyList.containsKey(w))) {
            HashSet<Integer> values = new HashSet<>();
            values.add(v);
            adjacencyList.put(w, values);
        } else {
            adjacencyList.get(w).add(v);
            }
    }
    public void deleteVertex (Integer v) {
        vertexIds.remove(v);
        adjacencyList.remove(v);
        Iterator it = adjacencyList.keySet().iterator();
        HashSet<Integer> values = new HashSet<>();
        while (it.hasNext()) {
            int key = (int) it.next();
            values = adjacencyList.get(key);
            HashSet<Integer> values2 = new HashSet<>();
            for (Iterator it2 = values.iterator();it2.hasNext();) {
                if(it2.next() == v) {
                    values2.add(key);
                }
            }
            for (int value: values2) {
                adjacencyList.get(value).remove(v);
            }
        }
    }
    public void deleteEdge (Integer w, Integer v){
        adjacencyList.get(w).remove(v);
        adjacencyList.get(v).remove(w);
    }
    public boolean contains (Integer v){
        return vertexIds.contains(v);
    }
    public int degree (Integer v){
        if(this.contains(v) && adjacencyList.containsKey(v)){
            return adjacencyList.get(v).size();
        }
        else {
            return 0;
        }
    }
    public boolean adjacent (Integer v, Integer w){
        if (this.contains(v) && this.contains(w)) {
           HashSet<Integer> values = adjacencyList.get(v);
            return values.contains(w);
        }
        return false;
    }
    public Graph getCopy(){
        MyGraph copy = new MyGraph();
        for (int id: this.vertexIds) {
            copy.addVertex(id);
        }
        for (int i: adjacencyList.keySet()){
            HashSet<Integer> values = adjacencyList.get(i);
            for (int v: values) {
                copy.addEdge(i,v);
            }
        }
        return copy;
    }
    public Set<Integer> getNeighbors (Integer v){
        HashSet<Integer> neighbors = new HashSet<>();
        if (adjacencyList.containsKey(v)) {
            neighbors = adjacencyList.get(v);
        }
        return neighbors;
    }
    public int size (){
        return vertexIds.size();
    }
    public int getEdgeCount (){
        int counter = 0;
        for (HashSet<Integer> i: adjacencyList.values()) {
            counter = counter + i.size();
        }
        return counter / 2;
    }
    public Set<Integer> getVertices (){
        return vertexIds;
    }
    public MyGraph(){
        this.vertexIds = new HashSet<>();
        this.adjacencyList = new HashMap<>();
    }
    public MyGraph (String path){
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
        MyGraph graph0 = new MyGraph("C:\\Users\\t-jen\\OneDrive\\Desktop\\bio-dmela.mtx");
        Set<Integer> vertices = graph0.getVertices();
        Iterator it = vertices.iterator();
        while( it.hasNext()){
            System.out.println(it.next());
        }
        System.out.println(graph0.getEdgeCount());
        Graph graph1 = graph0.getCopy();
        System.out.println(graph1.getEdgeCount());
        graph1.addEdge(10001, 10002);
        System.out.println(graph1.getEdgeCount());

    }
}

