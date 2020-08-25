import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.lang.*;

public class SearchTree {
    class Instance {
        private Graph graph;
        private int count;

        public Instance (Graph g, int k) {
            this.graph = g;
            this.count = k;
        }
    }
    private boolean solve (Instance i){
        if( i.count < 0) {
            return false;
        }
        if(i.graph.getEdgeCount() == 0) {
            return true;
        }
        // G besitzt mindestens eine Kante
        // Kante in adListe suchen
        Set<Integer> vertices = i.graph.getVertices();
        Iterator<Integer> it = vertices.iterator();
        int vertex1 = -1;
        int vertex2 = -1;
        while (it.hasNext()) {
            vertex1 = it.next();
            if(i.graph.degree(vertex1) > 0) {
                vertex2 = i.graph.getNeighbors(vertex1).iterator().next();
                break;
            }
        }
        // Kante gefunden
        // Lösche u oder v und rufe solve rek auf
        Graph graphOhneU = i.graph.getCopy();
        graphOhneU.deleteVertex(vertex1);
        graphOhneU = removeSingletons(graphOhneU);
        Instance ohneU = new Instance(graphOhneU, i.count -1);
        ohneU = removeDegOne(ohneU);
        ohneU = removeHighDeg(ohneU);
        if( solve(ohneU) == true) {
            return true;
        }

        Graph graphOhneV = i.graph.getCopy();
        graphOhneV.deleteVertex(vertex2);
        graphOhneV = removeSingletons(graphOhneV);
        Instance ohneV = new Instance(graphOhneV, i.count -1);
        ohneV = removeDegOne(ohneV);
        ohneV = removeHighDeg(ohneV);
        if(solve(ohneV) == true) {
            return true;
        }
        return false;
    }
    public int solve(Graph g){
        for(int k = 0; k < g.getEdgeCount(); k ++) {
            Instance inst = new Instance(g, k);
            if(solve(inst)) {
                return k;
            }
        }
        return -1;
    }
    public void printSolve(Graph g) {
        System.out.println("Vertex count " + g.size());
        System.out.println("Edgecount " + g.getEdgeCount());
        long startTime = System.nanoTime();
        int k = solve(g);
        long estimatedTime = System.nanoTime() - startTime;
        System.out.println("Lösungsgröße " + k);
        System.out.println("Laufzeit " + estimatedTime);
    }
    public static void main(String[] args) {
        MyGraph graph0 = new MyGraph("C:\\Users\\t-jen\\OneDrive\\Desktop\\sample");
        SearchTree st = new SearchTree();
        st.printSolve(graph0);
    }
    public Graph removeSingletons(Graph g) {
        HashSet<Integer> singletons = new HashSet<>();
        for (int vertex: g.getVertices()) {
            if (g.degree(vertex) == 0) {
                singletons.add(vertex);
            }
        }
        for (int singleton : singletons) {
            g.deleteVertex(singleton);
        }
        return g;
    }
    public Instance removeDegOne(Instance inst) {
        HashSet<Integer> degOne = new HashSet<>();
        for (int vertex: inst.graph.getVertices()) {
            if (inst.graph.degree(vertex) == 1) {
                degOne.add(vertex);
            }
        }
        HashSet<Integer> neighbors = new HashSet<>();
        for (int vertex: degOne) {
            //int neighbor = inst.graph.getNeighbors(vertex).iterator().next();
            for (int neighbor: inst.graph.getNeighbors(vertex)) {
                inst.graph.deleteVertex(neighbor);

            }

        }
        inst.count --;
        return inst;
    }
    public Instance removeHighDeg(Instance inst) {
        HashSet<Integer> highDeg = new HashSet<>();
        for (int vertex: inst.graph.getVertices()) {
            if (inst.graph.degree(vertex) > inst.count) {
                highDeg.add(vertex);
            }
        }
        for (int vertex: highDeg) {
            inst.graph.deleteVertex(vertex);
        }
        inst.count --;
        return inst;
    }
}
