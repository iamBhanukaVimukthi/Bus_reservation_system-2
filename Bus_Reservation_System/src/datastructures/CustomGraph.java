package datastructures;

public class CustomGraph<T> {

    // Helper class to represent a weighted edge (connection)
    public static class Edge<T> {
        public T target;
        public double weight; // e.g., distance (km), travel time (mins), or fare

        public Edge(T target, double weight) {
            this.target = target;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return target + " (Weight: " + weight + ")";
        }
    }

    // Node wrapper to store a vertex and its list of outgoing edges
    public static class VertexNode<T> {
        public T vertex;
        public CustomLinkedList<Edge<T>> edges;
        public VertexNode<T> next;

        public VertexNode(T vertex) {
            this.vertex = vertex;
            this.edges = new CustomLinkedList<>();
            this.next = null;
        }
    }

    private VertexNode<T> head;
    private int vertexCount;

    public CustomGraph() {
        this.head = null;
        this.vertexCount = 0;
    }

    // --- ADD VERTEX ---
    public void addVertex(T vertex) {
        if (findVertexNode(vertex) != null) {
            return; // Avoid duplicates
        }

        VertexNode<T> newNode = new VertexNode<>(vertex);
        if (head == null) {
            head = newNode;
        } else {
            VertexNode<T> temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        vertexCount++;
    }

    // --- ADD EDGE ---
    public void addEdge(T source, T destination, double weight, boolean isBidirectional) {
        if (findVertexNode(source) == null) addVertex(source);
        if (findVertexNode(destination) == null) addVertex(destination);

        VertexNode<T> srcNode = findVertexNode(source);
        srcNode.edges.add(new Edge<>(destination, weight));

        if (isBidirectional) {
            VertexNode<T> destNode = findVertexNode(destination);
            destNode.edges.add(new Edge<>(source, weight));
        }
    }

    // Overloaded convenience method for bidirectional connections
    public void addEdge(T source, T destination, double weight) {
        addEdge(source, destination, weight, true);
    }

    // --- HELPER LOOKUP ---
    private VertexNode<T> findVertexNode(T vertex) {
        VertexNode<T> current = head;
        while (current != null) {
            if (current.vertex.equals(vertex)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    // --- DISPLAY GRAPH ---
    public void displayGraph() {
        if (head == null) {
            System.out.println("Graph is empty.");
            return;
        }

        VertexNode<T> current = head;
        while (current != null) {
            System.out.print("[" + current.vertex + "] -> ");
            if (current.edges.isEmpty()) {
                System.out.println("No outgoing connections");
            } else {
                current.edges.display();
            }
            current = current.next;
        }
    }

    public int getVertexCount() {
        return vertexCount;
    }
}