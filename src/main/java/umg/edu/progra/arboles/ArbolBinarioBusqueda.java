package umg.edu.progra.arboles;

/**
 * Arbol Binario de Busqueda (BST) implementado manualmente,
 * sin utilizar java.util ni librerias externas.
 *
 * Reglas del BST:
 *  - Para cada nodo N, todos los valores del subarbol izquierdo
 *    son MENORES que N.dato.
 *  - Para cada nodo N, todos los valores del subarbol derecho
 *    son MAYORES que N.dato.
 *  - No se permiten duplicados (se ignoran al insertar).
 *
 * @author Walter Cordova
 */
public class ArbolBinarioBusqueda {

    private Nodo raiz;
    private int tamanio;

    public ArbolBinarioBusqueda() {
        this.raiz = null;
        this.tamanio = 0;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    public int tamanio() {
        return tamanio;
    }

    /**
     * Inserta un valor en el arbol respetando la propiedad del BST.
     * Si el valor ya existe se ignora (no se insertan duplicados).
     */
    public void insertar(int valor) {
        if (raiz == null) {
            raiz = new Nodo(valor);
            tamanio++;
            return;
        }
        raiz = insertarRecursivo(raiz, valor);
    }

    private Nodo insertarRecursivo(Nodo actual, int valor) {
        if (actual == null) {
            tamanio++;
            return new Nodo(valor);
        }
        if (valor < actual.dato) {
            actual.izquierdo = insertarRecursivo(actual.izquierdo, valor);
        } else if (valor > actual.dato) {
            actual.derecho = insertarRecursivo(actual.derecho, valor);
        }
        return actual;
    }

     /**
     * Busca un valor dentro del arbol. Devuelve el Nodo si existe
     * o null si no se encuentra.
     */
    public Nodo buscar(int valor) {
        return buscarRecursivo(raiz, valor);
    }

    private Nodo buscarRecursivo(Nodo actual, int valor) {
        if (actual == null) {
            return null;
        }
        if (valor == actual.dato) {
            return actual;
        }
        if (valor < actual.dato) {
            return buscarRecursivo(actual.izquierdo, valor);
        }
        return buscarRecursivo(actual.derecho, valor);
    }

    public boolean contiene(int valor) {
        return buscar(valor) != null;
    }

    /**
     * Elimina un valor del arbol. Cubre los 3 casos clasicos:
     *  1. Nodo hoja (sin hijos)
     *  2. Nodo con un solo hijo
     *  3. Nodo con dos hijos (se reemplaza por el sucesor inorden:
     *     el menor del subarbol derecho).
     */
    public boolean eliminar(int valor) {
        int tamanioPrevio = tamanio;
        raiz = eliminarRecursivo(raiz, valor);
        return tamanio < tamanioPrevio;
    }

    private Nodo eliminarRecursivo(Nodo actual, int valor) {
        if (actual == null) {
            return null;
        }
        if (valor < actual.dato) {
            actual.izquierdo = eliminarRecursivo(actual.izquierdo, valor);
        } else if (valor > actual.dato) {
            actual.derecho = eliminarRecursivo(actual.derecho, valor);
        } else {
            // Nodo encontrado
            if (actual.izquierdo == null && actual.derecho == null) {
                tamanio--;
                return null;
            }
            if (actual.izquierdo == null) {
                tamanio--;
                return actual.derecho;
            }
            if (actual.derecho == null) {
                tamanio--;
                return actual.izquierdo;
            }
            // Nodo con dos hijos: se reemplaza con el sucesor inorden
            int sucesor = minimo(actual.derecho);
            actual.dato = sucesor;
            actual.derecho = eliminarRecursivo(actual.derecho, sucesor);
        }
        return actual;
    }

    /**
     * Devuelve el valor minimo del arbol (el nodo mas a la izquierda).
     */
    public int minimo() {
        if (raiz == null) {
            throw new IllegalStateException("El arbol esta vacio");
        }
        return minimo(raiz);
    }

    private int minimo(Nodo nodo) {
        Nodo actual = nodo;
        while (actual.izquierdo != null) {
            actual = actual.izquierdo;
        }
        return actual.dato;
    }

    /**
     * Devuelve el valor maximo del arbol (el nodo mas a la derecha).
     */
    public int maximo() {
        if (raiz == null) {
            throw new IllegalStateException("El arbol esta vacio");
        }
        Nodo actual = raiz;
        while (actual.derecho != null) {
            actual = actual.derecho;
        }
        return actual.dato;
    }

    /**
     * Altura del arbol: cantidad de aristas del camino mas largo
     * desde la raiz hasta una hoja. Un arbol vacio tiene altura -1.
     * Un arbol con solo raiz tiene altura 0.
     */
    public int altura() {
        return alturaRecursiva(raiz);
    }

    private int alturaRecursiva(Nodo nodo) {
        if (nodo == null) {
            return -1;
        }
        int izq = alturaRecursiva(nodo.izquierdo);
        int der = alturaRecursiva(nodo.derecho);
        return 1 + (izq > der ? izq : der);
    }

    /**
     * Cuenta cuantos nodos hoja (sin hijos) tiene el arbol.
     */
    public int contarHojas() {
        return contarHojasRecursivo(raiz);
    }

    private int contarHojasRecursivo(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        if (nodo.izquierdo == null && nodo.derecho == null) {
            return 1;
        }
        return contarHojasRecursivo(nodo.izquierdo) + contarHojasRecursivo(nodo.derecho);
    }

    // ============================================================
    // RECORRIDOS DEL ARBOL
    // ============================================================

    /**
     * Recorrido InOrden: Izquierdo -> Raiz -> Derecho.
     * En un BST imprime los valores ordenados de menor a mayor.
     */
    public void inOrden() {
        inOrdenRecursivo(raiz);
        System.out.println();
    }

    private void inOrdenRecursivo(Nodo nodo) {
        if (nodo == null) {
            return;
        }
        inOrdenRecursivo(nodo.izquierdo);
        System.out.print(nodo.dato + " ");
        inOrdenRecursivo(nodo.derecho);
    }

    /**
     * Recorrido PreOrden: Raiz -> Izquierdo -> Derecho.
     * Util para clonar el arbol.
     */
    public void preOrden() {
        preOrdenRecursivo(raiz);
        System.out.println();
    }

    private void preOrdenRecursivo(Nodo nodo) {
        if (nodo == null) {
            return;
        }
        System.out.print(nodo.dato + " ");
        preOrdenRecursivo(nodo.izquierdo);
        preOrdenRecursivo(nodo.derecho);
    }

    /**
     * Recorrido PostOrden: Izquierdo -> Derecho -> Raiz.
     * Util para liberar/eliminar el arbol.
     */
    public void postOrden() {
        postOrdenRecursivo(raiz);
        System.out.println();
    }

    private void postOrdenRecursivo(Nodo nodo) {
        if (nodo == null) {
            return;
        }
        postOrdenRecursivo(nodo.izquierdo);
        postOrdenRecursivo(nodo.derecho);
        System.out.print(nodo.dato + " ");
    }

    /**
     * Recorrido por niveles (BFS) implementado con una cola casera
     * (sin usar java.util). Imprime el arbol por anchura.
     */
    public void recorridoPorNiveles() {
        if (raiz == null) {
            System.out.println();
            return;
        }
        ColaNodos cola = new ColaNodos();
        cola.encolar(raiz);
        while (!cola.estaVacia()) {
            Nodo actual = cola.desencolar();
            System.out.print(actual.dato + " ");
            if (actual.izquierdo != null) {
                cola.encolar(actual.izquierdo);
            }
            if (actual.derecho != null) {
                cola.encolar(actual.derecho);
            }
        }
        System.out.println();
    }

    /**
     * Imprime el arbol de forma jerarquica y visual en consola
     * (rotado 90 grados: la raiz queda a la izquierda).
     */
    public void imprimirArbol() {
        if (raiz == null) {
            System.out.println("(arbol vacio)");
            return;
        }
        imprimirArbolRecursivo(raiz, 0);
    }

    private void imprimirArbolRecursivo(Nodo nodo, int nivel) {
        if (nodo == null) {
            return;
        }
        imprimirArbolRecursivo(nodo.derecho, nivel + 1);
        for (int i = 0; i < nivel; i++) {
            System.out.print("     ");
        }
        System.out.println("-> " + nodo.dato);
        imprimirArbolRecursivo(nodo.izquierdo, nivel + 1);
    }
    
    // ============================================================
    // PROBLEMA 1 — contarNodos recursivo
    // ============================================================

    /**
     * Devuelve la cantidad total de nodos del arbol usando recursividad.
     * NO usa el campo 'tamanio'.
     * Caso base: nodo null => 0 nodos.
     * Caso recursivo: 1 (nodo actual) + nodos izquierda + nodos derecha.
     */
    public int contarNodos() {
        return contarNodosRecursivo(raiz);
    }

    private int contarNodosRecursivo(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + contarNodosRecursivo(nodo.izquierdo) + contarNodosRecursivo(nodo.derecho);
    }

    
    // ============================================================
    // PROBLEMA 2 — esBalanceado
    // ============================================================

    /**
     * Devuelve true si el arbol esta balanceado: para cada nodo la
     * diferencia de altura entre subarbol izquierdo y derecho es <= 1.
     * Estrategia: calcular altura y verificar balance en un solo recorrido
     * usando -2 como centinela de "no balanceado".
     */
    public boolean esBalanceado() {
        return alturaBalanceada(raiz) != -2;
    }

    /**
     * Retorna la altura del subarbol si esta balanceado,
     * o -2 si en cualquier nodo la diferencia de alturas supera 1.
     */
    private int alturaBalanceada(Nodo nodo) {
        if (nodo == null) {
            return -1;
        }
        int altIzq = alturaBalanceada(nodo.izquierdo);
        if (altIzq == -2) return -2; // propagar fallo

        int altDer = alturaBalanceada(nodo.derecho);
        if (altDer == -2) return -2; // propagar fallo

        int diferencia = altIzq - altDer;
        if (diferencia < -1 || diferencia > 1) {
            return -2; // este nodo ya no esta balanceado
        }
        return 1 + (altIzq > altDer ? altIzq : altDer);
    	}
    
    		// ============================================================
    	    // PROBLEMA 3 — esBSTValido
    	    // ============================================================

    	    /**
    	     * Verifica que el arbol cumple la propiedad de BST:
    	     * todo el subarbol izquierdo < raiz, todo el derecho > raiz.
    	     * Usa rango (min, max) para validar cada nodo en un solo recorrido.
    	     */
    	    public boolean esBSTValido() {
    	        return esBSTValidoRecursivo(raiz, Integer.MIN_VALUE, Integer.MAX_VALUE);
    	    }

    	    private boolean esBSTValidoRecursivo(Nodo nodo, int min, int max) {
    	        if (nodo == null) {
    	            return true;
    	        }
    	        if (nodo.dato <= min || nodo.dato >= max) {
    	            return false;
    	        }
    	        return esBSTValidoRecursivo(nodo.izquierdo, min, nodo.dato)
    	            && esBSTValidoRecursivo(nodo.derecho, nodo.dato, max);
    	    }
    	    
    	    // ============================================================
    	    // PROBLEMA 4 — ancestroComunMasBajo (LCA)
    	    // ============================================================

    	    /**
    	     * Devuelve el dato del nodo que es el Ancestro Comun mas Bajo (LCA)
    	     * de los valores 'a' y 'b'.
    	     * Aprovecha la propiedad del BST:
    	     *  - Si ambos < actual -> LCA en subarbol izquierdo.
    	     *  - Si ambos > actual -> LCA en subarbol derecho.
    	     *  - Si uno es <= y otro es >= -> actual es el LCA.
    	     * Lanza IllegalArgumentException si alguno no existe en el arbol.
    	     */
    	    public int ancestroComunMasBajo(int a, int b) {
    	        if (!contiene(a)) {
    	            throw new IllegalArgumentException("El valor " + a + " no existe en el arbol.");
    	        }
    	        if (!contiene(b)) {
    	            throw new IllegalArgumentException("El valor " + b + " no existe en el arbol.");
    	        }
    	        return lcaRecursivo(raiz, a, b);
    	    }

    	    private int lcaRecursivo(Nodo nodo, int a, int b) {
    	        if (nodo == null) {
    	            throw new IllegalStateException("No se encontro el LCA (arbol invalido).");
    	        }
    	        if (a < nodo.dato && b < nodo.dato) {
    	            return lcaRecursivo(nodo.izquierdo, a, b);
    	        }
    	        if (a > nodo.dato && b > nodo.dato) {
    	            return lcaRecursivo(nodo.derecho, a, b);
    	        }
    	        // Uno a cada lado (o uno es igual al actual) => este es el LCA
    	        return nodo.dato;
    	    }
    	            // ============================================================
    	    	    // PROBLEMA 5 — invertir (espejo)
    	    	    // ============================================================

    	    	    /**
    	    	     * Invierte el arbol: intercambia izquierdo y derecho en todos los nodos.
    	    	     * Despues de invertir, inOrden imprimira los valores en orden DESCENDENTE.
    	    	     */
    	    	    public void invertir() {
    	    	        invertirRecursivo(raiz);
    	    	    }

    	    	    private void invertirRecursivo(Nodo nodo) {
    	    	        if (nodo == null) {
    	    	            return;
    	    	        }
    	    	        // Intercambiar hijos
    	    	        Nodo temp = nodo.izquierdo;
    	    	        nodo.izquierdo = nodo.derecho;
    	    	        nodo.derecho = temp;
    	    	        // Invertir recursivamente ambos subarboles
    	    	        invertirRecursivo(nodo.izquierdo);
    	    	        invertirRecursivo(nodo.derecho);
    	    	    }
    	    	    // ============================================================
    	    	    // EXTRA E1 — kEsimoMenor
    	    	    // ============================================================

    	    	    /**
    	    	     * Devuelve el k-esimo valor mas pequeno del arbol (1-indexado).
    	    	     * Usa un recorrido InOrden con un contador implementado con arreglo
    	    	     * de un elemento (truco para pasar el estado por referencia sin java.util).
    	    	     * Lanza IllegalArgumentException si k es invalido.
    	    	     */
    	    	    public int kEsimoMenor(int k) {
    	    	        if (k < 1 || k > tamanio) {
    	    	            throw new IllegalArgumentException("k=" + k + " fuera de rango [1," + tamanio + "]");
    	    	        }
    	    	        int[] contador = { 0 };
    	    	        int[] resultado = { Integer.MIN_VALUE };
    	    	        kEsimoMenorRecursivo(raiz, k, contador, resultado);
    	    	        return resultado[0];
    	    	    }

    	    	    private void kEsimoMenorRecursivo(Nodo nodo, int k, int[] contador, int[] resultado) {
    	    	        if (nodo == null || contador[0] >= k) {
    	    	            return;
    	    	        }
    	    	        kEsimoMenorRecursivo(nodo.izquierdo, k, contador, resultado);
    	    	        contador[0]++;
    	    	        if (contador[0] == k) {
    	    	            resultado[0] = nodo.dato;
    	    	            return;
    	    	        }
    	    	        kEsimoMenorRecursivo(nodo.derecho, k, contador, resultado);
    	    	    }
    	    	    
    	    	    // ============================================================
    	    	    // EXTRA E2 — imprimirRangoOrdenado
    	    	    // ============================================================

    	    	    /**
    	    	     * Imprime en orden todos los valores del arbol en el rango [min, max],
    	    	     * recorriendo lo menos posible (poda de subarboles fuera del rango).
    	    	     */
    	    	    public void imprimirRangoOrdenado(int min, int max) {
    	    	        imprimirRangoRecursivo(raiz, min, max);
    	    	        System.out.println();
    	    	    }

    	    	    private void imprimirRangoRecursivo(Nodo nodo, int min, int max) {
    	    	        if (nodo == null) {
    	    	            return;
    	    	        }
    	    	        // Poda izquierda: solo explorar si puede haber valores >= min
    	    	        if (nodo.dato > min) {
    	    	            imprimirRangoRecursivo(nodo.izquierdo, min, max);
    	    	        }
    	    	        // Imprimir si esta en rango
    	    	        if (nodo.dato >= min && nodo.dato <= max) {
    	    	            System.out.print(nodo.dato + " ");
    	    	        }
    	    	        // Poda derecha: solo explorar si puede haber valores <= max
    	    	        if (nodo.dato < max) {
    	    	            imprimirRangoRecursivo(nodo.derecho, min, max);
    	    	        }
    	    	    }
    	    	    // ============================================================
    	    	    // EXTRA E3 — diametro
    	    	    // ============================================================

    	    	    /**
    	    	    * Devuelve el diametro del arbol: el numero de aristas en el camino
    	    	    * mas largo entre dos nodos cualesquiera.
    	    	    * El camino puede o no pasar por la raiz.
    	    	    * Estrategia: para cada nodo el diametro maximo que pasa por el es
    	    	    * alturaIzq + alturaDer + 2. Se elige el maximo global.
    	    	    */
    	    	    public int diametro() {
    	    	    	    int[] maxDiametro = { 0 };
    	    	    	    diametroRecursivo(raiz, maxDiametro);
    	    	    	   return maxDiametro[0];
    	    	    	  }

    	    	     private int diametroRecursivo(Nodo nodo, int[] maxDiametro) {
    	    	    	     if (nodo == null) {
    	    	    	          return -1;
    	    	    	       }
    	    	    	     int altIzq = diametroRecursivo(nodo.izquierdo, maxDiametro);
    	    	    	     int altDer = diametroRecursivo(nodo.derecho, maxDiametro);

    	    	    	     // Diametro que pasa por este nodo: (altIzq+1) aristas hacia izq + (altDer+1) hacia der
    	    	    	      int diametroLocal = (altIzq + 1) + (altDer + 1);
    	    	    	      if (diametroLocal > maxDiametro[0]) {
    	    	    	       maxDiametro[0] = diametroLocal;
    	    	    	      }
    	    	    	      return 1 + (altIzq > altDer ? altIzq : altDer);
    	    	    	    }
    	    	     // ============================================================
    	    	     // EXTRA E4 — construir BST desde args (array de ints)
    	    	     // ============================================================

    	    	     /**
    	    	      * Construye un BST insertando todos los valores del arreglo recibido.
    	    	      * Se usa desde Principal pasando args[] convertido a int[].
    	    	      */
    	    	     public static ArbolBinarioBusqueda desdearreglo(int[] valores) {
    	    	         ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();
    	    	         for (int v : valores) {
    	    	             arbol.insertar(v);
    	    	         }
    	    	         return arbol;
    	    	     }
   
    // ============================================================
    // COLA INTERNA (lista enlazada simple) usada para BFS.
    // Se implementa aqui para NO depender de java.util.
    // ============================================================

    private static class NodoCola {
        Nodo valor;
        NodoCola siguiente;

        NodoCola(Nodo valor) {
            this.valor = valor;
        }
    }

    private static class ColaNodos {
        private NodoCola frente;
        private NodoCola fondo;

        boolean estaVacia() {
            return frente == null;
        }

        void encolar(Nodo n) {
            NodoCola nuevo = new NodoCola(n);
            if (frente == null) {
                frente = fondo = nuevo;
            } else {
                fondo.siguiente = nuevo;
                fondo = nuevo;
            }
        }

        Nodo desencolar() {
            if (frente == null) {
                throw new IllegalStateException("Cola vacia");
            }
            Nodo valor = frente.valor;
            frente = frente.siguiente;
            if (frente == null) {
                fondo = null;
            }
            return valor;
        }
    }
}
