
public class Main {
    public static void main(String[] args) {
        LinkedBST<Integer> bst = new LinkedBST<>();
        
        // ejercicio 01
        int[] datos = {15, 8, 22, 5, 12, 18, 30};
        for (int n : datos) bst.insert(n);

        System.out.println("Recorrido InOrden (debe estar ordenado):");
        bst.inOrder(); 

        System.out.println("Nodos no-hojas: " + bst.countNodes()); 
        System.out.println("Amplitud máxima: " + bst.amplitude()); 
        System.out.println("Altura desde nodo 15 (raíz): " + bst.height(15)); 
        System.out.println("Altura desde nodo 8: " + bst.height(8)); 
    }
}