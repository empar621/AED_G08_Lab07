import java.util.LinkedList;
import java.util.Queue;

public class LinkedBST<E extends Comparable<E>> {

    protected class Node {
        E data;
        Node left;
        Node right;

        public Node(E data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    protected Node root;

    public LinkedBST() {
        this.root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    // OPERACIONES BASICAS 

    public void insert(E x) {
        this.root = insert(x, this.root);
    }

    protected Node insert(E x, Node node) {
        if (node == null) return new Node(x);
        int resC = x.compareTo(node.data);
        if (resC < 0) node.left = insert(x, node.left);
        else if (resC > 0) node.right = insert(x, node.right);
        return node;
    }

    public boolean search(E x) {
        return search(x, this.root);
    }

    protected boolean search(E x, Node node) {
        if (node == null) return false;
        int resC = x.compareTo(node.data);
        if (resC < 0) return search(x, node.left);
        else if (resC > 0) return search(x, node.right);
        return true;
    }

    // EJERCICIO 2

    // a) Eliminar todos los nodos
    public void destroyNodes() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("El árbol ya está vacío.");
        this.root = null;
    }

    // b y c) Contar nodos no-hojas 
    public int countNodes() {
        return countNodes(this.root);
    }

    private int countNodes(Node node) {
        if (node == null || (node.left == null && node.right == null)) {
            return 0;
        }
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    // d) Altura de un nodo x de forma iterativa
    public int height(E x) {
        Node current = this.root;
        // busqueda iterativa del nodo x
        while (current != null && !current.data.equals(x)) {
            if (x.compareTo(current.data) < 0) current = current.left;
            else current = current.right;
        }

        if (current == null) return -1; 

        // calculo de altura por niveles BFS usando una cola
        Queue<Node> queue = new LinkedList<>();
        queue.add(current);
        int height = -1;

        while (!queue.isEmpty()) {
            int nodesInLevel = queue.size();
            height++;
            for (int i = 0; i < nodesInLevel; i++) {
                Node node = queue.poll();
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return height;
    }

    // e) Amplitud (maximo numero de nodos en cualquier nivel)
    public int amplitude() {
        if (isEmpty()) return 0;

        Queue<Node> queue = new LinkedList<>();
        queue.add(this.root);
        int maxAmplitude = 0;

        while (!queue.isEmpty()) {
            int currentLevelSize = queue.size();
            if (currentLevelSize > maxAmplitude) {
                maxAmplitude = currentLevelSize;
            }

            for (int i = 0; i < currentLevelSize; i++) {
                Node node = queue.poll();
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return maxAmplitude;
    }
    
    // EJERCICIO 3 //
    
    // 1)    
    public int areaBST() {
        if (isEmpty()) return 0;

        Queue<Node> queue = new LinkedList<>();
        queue.add(this.root);
        
        int leafCount = 0;
        int height = -1;

        while (!queue.isEmpty()) {
            int nodesInLevel = queue.size();
            height++; 
            
            for (int i = 0; i < nodesInLevel; i++) {
                Node node = queue.poll();
                
                // verificacion de nodo hoja (grado 0) 
                if (node.left == null && node.right == null) {
                    leafCount++;
                }
                
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        
        // Area = numero de nodos hojas * altura 
        return leafCount * height;
    }
    
    // 2)
    public void drawBST() {
        System.out.println("Estructura del Arbol (Raiz a la izquierda):");
        drawBST(this.root, "", true);
    }

    private void drawBST(Node node, String prefix, boolean isLeft) {
        if (node != null) {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + node.data);
            drawBST(node.left, prefix + (isLeft ? "│   " : "    "), true);
            drawBST(node.right, prefix + (isLeft ? "│   " : "    "), false);
        }
    }
    
    // 4) metodo parenthesize para visualización con sangria y parentesis
    public void parenthesize() {
        parenthesize(this.root, 0);
    }

    private void parenthesize(Node nodo, int nivel) {
        if (nodo != null) {
            // generar la sangria segun el nivel actual
            for (int i = 0; i < nivel; i++) {
                System.out.print("    "); // 4 espacios por nivel
            }
            System.out.print(nodo.data);

            // si el nodo es una hoja no se abre parentesis de subarbol
            if (nodo.left == null && nodo.right == null) {
                System.out.println();
            } else {
                // si tiene hijos se abre parentesis y se procesa subarboles
                System.out.println(" (");
                
                // hijo izquierdo
                if (nodo.left != null) {
                    parenthesize(nodo.left, nivel + 1);
                } else {
                    // para hijo nulo si el otro existe
                    for (int i = 0; i <= nivel; i++) System.out.print("    ");
                    System.out.println("-"); 
                }

                // hijo derecho
                if (nodo.right != null) {
                    parenthesize(nodo.right, nivel + 1);
                } else {
                    for (int i = 0; i <= nivel; i++) System.out.print("    ");
                    System.out.println("-");
                }

                // cerrar el parentesis del nivel actual
                for (int i = 0; i < nivel; i++) {
                    System.out.print("    ");
                }
                System.out.println(")");
            }
        }
    }

    // 4)
    public boolean isValidBST() {
        return isValidBST(this.root, null, null);
    }

    private boolean isValidBST(Node nodo, E min, E max) {
        // un arbol vacio es un BST valido por definicion.
        if (nodo == null) {
            return true;
        }

        // el dato actual no puede ser menor o igual al minimo permitido
        if (min != null && nodo.data.compareTo(min) <= 0) {
            return false;
        }

        // el dato actual no puede ser mayor o igual al maximo permitido
        if (max != null && nodo.data.compareTo(max) >= 0) {
            return false;
        }

        // Recursion: 
        // Para el subarbol izquierdo, el maximo ahora es el valor del nodo actual
        // Para el subarbol derecho, el minimo ahora es el valor del nodo actual
        return isValidBST(nodo.left, min, nodo.data) && 
               isValidBST(nodo.right, nodo.data, max);
    }
    
    // EJERCICIO 5
    
    // b)
    public void searchRange(E min, E max) {
        System.out.println("Productos en el rango [" + min + ", " + max + "]:");
        searchRange(this.root, min, max);
        System.out.println();
    }

    private void searchRange(Node nodo, E min, E max) {
        if (nodo == null) {
            return;
        }

        if (nodo.data.compareTo(min) > 0) {
            searchRange(nodo.left, min, max);
        }

        if (nodo.data.compareTo(min) >= 0 && nodo.data.compareTo(max) <= 0) {
            System.out.print(nodo.data + " ");
        }

        if (nodo.data.compareTo(max) < 0) {
            searchRange(nodo.right, min, max);
        }
    }
    
    // c)
    public int countLeaves() {
        return countLeaves(this.root);
    }

    private int countLeaves(Node nodo) {
        if (nodo == null) {
            return 0;
        }
        // condición de nodo hoja grado 0 sin hijos
        if (nodo.left == null && nodo.right == null) {
            return 1;
        }
        // suma recursiva 
        return countLeaves(nodo.left) + countLeaves(nodo.right);
    }
    
     // d)
    public void printDescending() {
        System.out.println("Inventario en orden descendente:");
        printDescending(this.root);
        System.out.println();
    }

    private void printDescending(Node nodo) {
        if (nodo != null) {
            // Recorrido: Derecha -> Raiz -> Izquierda
            printDescending(nodo.right);
            System.out.print(nodo.data + " ");
            printDescending(nodo.left);
        }
    }
    
    // RECORRIDOS 
    public void inOrder() {
        inOrder(this.root);
        System.out.println();
    }

    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.data + " ");
            inOrder(node.right);
        }
    }
}