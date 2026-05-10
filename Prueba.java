
public class Prueba {
    public static void main(String[] args) {
        LinkedBST<Integer> tree1 = new LinkedBST<>();
        LinkedBST<Integer> tree2 = new LinkedBST<>();

        int[] datos1 = {15, 8, 22, 5, 12};
        int[] datos2 = {20, 10, 30, 5, 15};

        for (int x : datos1) tree1.insert(x);
        for (int x : datos2) tree2.insert(x);

        if (sameArea(tree1, tree2)) {
            System.out.println("Ambos arboles tienen la misma area.");
        } else {
            System.out.println("Los arboles tienen areas diferentes.");
        }
    }

    // retorna true si dos arboles tienen el mismo area 
    public static boolean sameArea(LinkedBST<?> t1, LinkedBST<?> t2) {
        return t1.areaBST() == t2.areaBST();
    }
}