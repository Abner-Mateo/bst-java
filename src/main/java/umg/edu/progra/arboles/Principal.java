package umg.edu.progra.arboles;

/**
 * Clase principal que demuestra el uso del Arbol Binario de Busqueda (BST)
 * implementado manualmente, sin usar librerias como java.util.
 *
 * Ejecucion sugerida:
 *   1. mvn compile
 *   2. mvn exec:java -Dexec.mainClass="umg.edu.progra.arboles.Principal"
 *
 * @author Walter Cordova
 */
public class Principal {

    public static void main(String[] args) {

        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();

        /*
         * Insertamos estos valores para formar el siguiente BST:
         *
         *               50
         *              /  \
         *            30    70
         *           /  \   / \
         *          20  40 60  80
         *         /
         *        10
         */
        int[] valores = { 50, 30, 70, 20, 40, 60, 80, 10 };
        for (int v : valores) {
            arbol.insertar(v);
        }

        System.out.println("===== Arbol Binario de Busqueda =====");
        System.out.println("Tamanio: " + arbol.tamanio());
        System.out.println("Altura:  " + arbol.altura());
        System.out.println("Minimo:  " + arbol.minimo());
        System.out.println("Maximo:  " + arbol.maximo());
        System.out.println("Hojas:   " + arbol.contarHojas());

        System.out.println("\n--- Representacion visual (rotada 90 grados) ---");
        arbol.imprimirArbol();

        System.out.println("\n--- Recorridos ---");
        System.out.print("InOrden    (ascendente): ");
        arbol.inOrden();

        System.out.print("PreOrden   (raiz primero): ");
        arbol.preOrden();

        System.out.print("PostOrden  (raiz al final): ");
        arbol.postOrden();

        System.out.print("Por niveles (BFS):         ");
        arbol.recorridoPorNiveles();

        System.out.println("\n--- Busquedas ---");
        System.out.println("Contiene 40? " + arbol.contiene(40));
        System.out.println("Contiene 99? " + arbol.contiene(99));

        System.out.println("\n--- Eliminacion ---");
        System.out.println("Eliminando 20 (nodo con 1 hijo)...");
        arbol.eliminar(20);
        System.out.print("InOrden tras eliminar 20: ");
        arbol.inOrden();

        System.out.println("Eliminando 30 (nodo con 2 hijos)...");
        arbol.eliminar(30);
        System.out.print("InOrden tras eliminar 30: ");
        arbol.inOrden();

        System.out.println("Eliminando 50 (raiz)...");
        arbol.eliminar(50);
        System.out.print("InOrden tras eliminar la raiz: ");
        arbol.inOrden();

        System.out.println("\n--- Estado final ---");
        arbol.imprimirArbol();
        System.out.println("Tamanio final: " + arbol.tamanio());
        System.out.println("Altura final:  " + arbol.altura());
        
        		// ============================================================
                // Reconstruimos el arbol original para los nuevos problemas
                // ============================================================
                arbol = new ArbolBinarioBusqueda();
                for (int v : valores) {
                    arbol.insertar(v);
                }

                // ============================================================
                // PROBLEMA 1 — contarNodos recursivo
                // ============================================================
                System.out.println("\n========================================");
                System.out.println("PROBLEMA 1 — contarNodos recursivo");
                System.out.println("========================================");
                System.out.println("tamanio()    (campo interno): " + arbol.tamanio());
                System.out.println("contarNodos() (recursivo):    " + arbol.contarNodos());
                // Verificar tras insercion
                arbol.insertar(5);
                System.out.println("Tras insertar 5:");
                System.out.println("  tamanio()    = " + arbol.tamanio());
                System.out.println("  contarNodos()= " + arbol.contarNodos());
                // Verificar tras eliminacion
                arbol.eliminar(5);
                System.out.println("Tras eliminar 5:");
                System.out.println("  tamanio()    = " + arbol.tamanio());
                System.out.println("  contarNodos()= " + arbol.contarNodos());
       
    }
}
