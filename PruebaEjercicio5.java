
public class PruebaEjercicio5 {
    public static void main(String[] args) {
        // arbol para la gestion de productos 
        LinkedBST<Integer> inventario = new LinkedBST<>();

        System.out.println("=== SISTEMA DE GESTION DE INVENTARIO (BST) ===");

        // a. Insertar productos respetando la propiedad de orden 
        int[] codigosProductos = {50, 30, 70, 20, 40, 60, 80};
        
        System.out.println("Insertando codigos de productos...");
        for (int codigo : codigosProductos) {
            inventario.insert(codigo);
        }
        System.out.println("Productos insertados exitosamente.\n");

        // Visualizacion de la estructura cargada
        System.out.println("Estructura jerarquica del inventario:");
        inventario.drawBST(); 
        System.out.println();

        // b. Se buscas productos con codigos entre 35 y 65
        int min = 35;
        int max = 65;
        System.out.println("Probando busqueda por rango:");
        inventario.searchRange(min, max); 
        System.out.println();

        // c. Cuenta los productos que están en las "puntas" del arbol (sin hijos)
        int hojas = inventario.countLeaves();
        System.out.println("Numero de productos en nodos hoja: " + hojas); 
        System.out.println();

        // d. Muestra el inventario de mayor a menor codigo
        System.out.println("Probando listado en orden descendente:");
        inventario.printDescending(); 
        
        System.out.println("\n=== FIN DE LA PRUEBA ===");
    }
}