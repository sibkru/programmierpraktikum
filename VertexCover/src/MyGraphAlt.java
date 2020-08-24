import java.util.HashSet;
import java.util.*;



public class MyGraph {
    public LinkedList<Integer>[] adjazenzListe;

    public MyGraph() {
        this.adjazenzListe = new LinkedList[5];
        for (int i = 0; i < this.adjazenzListe.length; i++) {
            if (!(this.adjazenzListe[i] instanceof LinkedList)) {
                this.adjazenzListe[i] = new LinkedList<Integer>();
            }
            this.adjazenzListe[i].add(i);
        }
    }

    public void addVertex (Integer v) {
        LinkedList<Integer>[] newAdjazenzListe = new LinkedList[this.adjazenzListe.length + 1];
        for (int i = 0; i < this.adjazenzListe.length; i ++) {
            newAdjazenzListe[i] = this.adjazenzListe[i];
        }
        newAdjazenzListe[this.adjazenzListe.length] = new LinkedList<Integer>();
        newAdjazenzListe[this.adjazenzListe.length].add(v);

               this.adjazenzListe = newAdjazenzListe;
    }
    public static void main (String[] args) {
        MyGraph graph1 = new MyGraph();
        for (LinkedList l: graph1.adjazenzListe) {
            if (l.getFirst() != null) {
                int x = (int)l.getFirst();
                Iterator i = l.iterator();
                while( i.hasNext()){
                    System.out.println(i.next());
                }



            }
        }
        graph1.addVertex(7);
        for (LinkedList l: graph1.adjazenzListe) {
            if (l.getFirst() != null) {
                int x = (int)l.getFirst();
                Iterator i = l.iterator();
                while (i.hasNext()){
                    System.out.println(i.next());
                }



            }
        }
        graph1.getVertices();

    }
    public HashSet<Integer> getVertices (){
        HashSet<Integer> vertices = new HashSet<>();
        for (LinkedList l: this.adjazenzListe) {
            if (l.getFirst() != null) {
                vertices.add((int) l.getFirst());
                System.out.println(l.getFirst());
            }


        }
    return vertices;
    }
}
    public void addEdge (Integer v, Integer w) {
        if (!(adjacencyList.containsKey(v))) {
            Integer[] values = new Integer[1];
            values[0] = w;
            adjacencyList.put(v, values);
        } else {
            Integer[] values = new Integer[adjacencyList.get(v).length + 1];
            values = adjacencyList.get(v);
            values[values.length - 1] = w;
            adjacencyList.remove(v);
            adjacencyList.put(v, values);
        }
        if(!(adjacencyList.containsKey(w))) {
            Integer[] values = new Integer[1];
            values[0] = v;
            adjacencyList.put(w, values);
        } else {
            Integer[] values = new Integer[adjacencyList.get(w).length + 1];
            values = adjacencyList.get(w);
            values[values.length - 1] = v;
            adjacencyList.remove(w);
            adjacencyList.put(w, values);
        }
    }
    public void deleteVertex (Integer v) {
        vertexIds.remove(v);
        adjacencyList.remove(v);
        for (int i: adjacencyList.keySet()) {
            Integer[] values = new Integer[adjacencyList.get(i).length];
            values = adjacencyList.get(i);
            for (int j: values) {
                if (j == v){

                }
            }

        }

    }
}
