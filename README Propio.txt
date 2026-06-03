# Árbol Binario de Búsqueda (BST) — Java

**Curso:** Programación 3  
**Tema:** Estructuras de datos no lineales — Árboles  
**Estudiante:** [Tu nombre aquí]  
**Carné:** [Tu carné aquí]  

---

## ¿Qué es este proyecto?

Implementación manual de un **Árbol Binario de Búsqueda (BST)** en Java puro,
sin usar `java.util` ni librerías externas. Se parte de una base funcional
(inserción, eliminación, búsqueda y recorridos) y se extiende con 5 métodos
nuevos más 4 ejercicios extra.

### Estructura del proyecto

```
arboles/
├── pom.xml
└── src/main/java/umg/edu/progra/arboles/
    ├── Nodo.java                   ← nodo del árbol (sin modificar)
    ├── ArbolBinarioBusqueda.java   ← lógica principal + métodos nuevos
    └── Principal.java              ← pruebas y demostraciones
```

---

## Cómo compilar y ejecutar

### Requisitos
- Java 8 o superior
- Maven 3.x

### Pasos

```bash
# 1. Entrar a la carpeta del proyecto
cd arboles

# 2. Compilar
mvn compile

# 3. Ejecutar
java -cp target/classes umg.edu.progra.arboles.Principal

# 4. Extra E4: pasar valores por consola
java -cp target/classes umg.edu.progra.arboles.Principal 15 8 22 4 11
```

---

## Métodos nuevos implementados

### Problema 1 — `contarNodos()`

Cuenta el total de nodos del árbol usando **recursividad pura**, sin usar el
campo interno `tamanio`.

**Lógica:** caso base es nodo `null` → retorna 0. Caso recursivo: `1 + izquierda + derecha`.

```java
public int contarNodos()
```

**Ejemplo de entrada:**
```
Árbol con: 50, 30, 70, 20, 40, 60, 80, 10
```

**Salida esperada:**
```
tamanio()     = 8
contarNodos() = 8
Tras insertar 5 -> contarNodos() = 9
Tras eliminar 5 -> contarNodos() = 8
```

---

### Problema 2 — `esBalanceado()`

Verifica si el árbol está **balanceado**: para cada nodo, la diferencia de
altura entre su subárbol izquierdo y derecho es `<= 1`.

**Lógica:** calcula altura y verifica balance en un solo recorrido. Usa `-2`
como centinela para propagar el fallo hacia arriba sin recorrer de más.

```java
public boolean esBalanceado()
```

**Ejemplo — árbol balanceado (50,30,70,20,40,60,80,10):**
```
esBalanceado() = true
```

**Ejemplo — árbol degenerado (1,2,3,4,5 en orden):**
```
-> 5
     -> 4
          -> 3
               -> 2
                    -> 1

esBalanceado() = false
```

---

### Problema 3 — `esBSTValido()`

Verifica que el árbol cumple la propiedad de BST: todo el subárbol izquierdo
es **menor** que la raíz y todo el derecho es **mayor**.

**Lógica:** pasa un rango `(min, max)` permitido en cada llamada recursiva.
Es la técnica más eficiente y correcta para validar un BST.

```java
public boolean esBSTValido()
```

**Ejemplo — árbol correcto:**
```
esBSTValido() = true
```

**Ejemplo — árbol roto (nodo izquierdo con valor 12 debajo de raíz 10):**
```
esBSTValido() = false
```

---

### Problema 4 — `ancestroComunMasBajo(int a, int b)`

Devuelve el dato del nodo que es el **Ancestro Común más Bajo** (LCA) de
los valores `a` y `b`.

**Lógica:** aprovecha la propiedad del BST:
- Si ambos valores < nodo actual → buscar en subárbol izquierdo
- Si ambos valores > nodo actual → buscar en subárbol derecho
- Si se separan (uno a cada lado) → el nodo actual es el LCA

Lanza `IllegalArgumentException` si alguno de los valores no existe.

```java
public int ancestroComunMasBajo(int a, int b)
```

**Árbol de prueba:** `10, 20, 30, 40, 50, 60, 70, 80`

| Llamada | Resultado esperado |
|---------|-------------------|
| `lca(10, 40)` | `30` |
| `lca(10, 80)` | `50` |
| `lca(60, 80)` | `70` |
| `lca(10, 999)` | `IllegalArgumentException` |

---

### Problema 5 — `invertir()`

Invierte el árbol completo: intercambia `izquierdo` y `derecho` en **todos**
los nodos (reflejo / espejo).

**Lógica:** intercambia los hijos del nodo actual, luego llama recursivamente
al subárbol izquierdo y al derecho (ya intercambiados).

```java
public void invertir()
```

**Antes de invertir — inOrden:**
```
10 20 30 40 50 60 70 80
```

**Después de invertir — inOrden:**
```
80 70 60 50 40 30 20 10
```

---

## Ejercicios extra

### E1 — `kEsimoMenor(int k)`

Devuelve el k-ésimo valor más pequeño del árbol (índice desde 1).
Usa un recorrido InOrden con un contador; el k-ésimo nodo visitado es la respuesta.

```java
public int kEsimoMenor(int k)
```

| k | Resultado (árbol 10,20,30,40,50,60,70,80) |
|---|------------------------------------------|
| 1 | 10 |
| 3 | 30 |
| 8 | 80 |

---

### E2 — `imprimirRangoOrdenado(int min, int max)`

Imprime en orden todos los valores en el rango `[min, max]`, podando
subárboles que están fuera del rango para no recorrerlos innecesariamente.

```java
public void imprimirRangoOrdenado(int min, int max)
```

**Ejemplo:**
```
Rango [20, 60] → 20 30 40 50 60
Rango [10, 10] → 10
```

---

### E3 — `diametro()`

Devuelve el diámetro del árbol: el número de aristas en el **camino más largo**
entre dos nodos cualesquiera (puede o no pasar por la raíz).

Para cada nodo, el diámetro que pasa por él es `(alturaIzq + 1) + (alturaDer + 1)`.
Se conserva el máximo global.

```java
public int diametro()
```

**Ejemplo — árbol original:**
```
diametro() = 6
```

---

### E4 — BST desde argumentos de consola

Construye un BST a partir de los valores enteros pasados como argumentos al programa.

```bash
java -cp target/classes umg.edu.progra.arboles.Principal 15 8 22 4 11
```

**Salida:**
```
Valores insertados: 15 8 22 4 11
InOrden (ordenado): 4 8 11 15 22
Tamanio: 5
Altura:  2
Es BST valido:   true
Esta balanceado: true
```

---

## Reglas respetadas

- ✅ Prohibido `java.util.*` — ningún import de esa librería en todo el proyecto
- ✅ Sin librerías externas
- ✅ Estructuras auxiliares implementadas manualmente (`ColaNodos` dentro de `ArbolBinarioBusqueda`)
- ✅ Toda la lógica nueva en `ArbolBinarioBusqueda`
- ✅ Todas las pruebas en `Principal`
- ✅ Compila con `mvn compile` sin errores ni warnings

---

## Historial de commits

| Commit | Descripción |
|--------|-------------|
| `chore: proyecto base inicial` | Código base proporcionado por el cátedra |
| `feat: problema 1 contarNodos recursivo` | Cuenta nodos sin usar campo tamanio |
| `feat: problema 2 esBalanceado` | Verifica balance con centinela -2 |
| `feat: problema 3 esBSTValido con rango min-max` | Valida propiedad BST recursivamente |
| `feat: problema 4 ancestroComunMasBajo LCA` | LCA aprovechando propiedad del BST |
| `feat: problema 5 invertir arbol espejo` | Reflejo completo del árbol |
