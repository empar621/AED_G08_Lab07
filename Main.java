
public class Main {
    public static void main(String[] args) {
        LinkedBST<Integer> bst = new LinkedBST<>();
        
        // Datos del Ejercicio 01
        int[] datos = {15, 8, 22, 5, 12, 18, 30};
        for (int n : datos) bst.insert(n);

        System.out.println("Recorrido InOrden (debe estar ordenado):");
        bst.inOrder(); // 5 8 12 15 18 22 30

        System.out.println("Nodos no-hojas: " + bst.countNodes()); // 3 (15, 8, 22)
        System.out.println("Amplitud máxima: " + bst.amplitude()); // 4 (Nivel 2)
        System.out.println("Altura desde nodo 15 (raíz): " + bst.height(15)); // 2
        System.out.println("Altura desde nodo 8: " + bst.height(8)); // 1
    }
}