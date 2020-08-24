import java.util.*;

public class MyGraph implements Graph {
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
        MyGraph copy = new MyGraph();
        for (int id: this.vertexIds) {
            copy.addVertex(id);
        }
        LinkedList<Integer> values = new LinkedList<>();
        for (int i: adjacencyList.keySet()){
            values = adjacencyList.get(i);
            for (int v: values) {
                addEdge(i,v);
            }
        }
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
}
