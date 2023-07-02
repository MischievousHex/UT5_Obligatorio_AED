public class TCalculadorMatricesOptimo<T> implements ICalculadorMatricesOptimo
{

    int[][] C;
    int[][] R;
    int[][] W;

    @Override
    public void EncontrarOptimo(int cantidadElementos, int[] frecuenciaExito, int[] frecuenciaNoExito) {

        this.C = new int[cantidadElementos+1][cantidadElementos+1];
        this.W = new int[cantidadElementos+1][cantidadElementos+1];
        this.R = new int[cantidadElementos+1][cantidadElementos+1];

        // Calcular C y W de la primera "linea" (00, 11, 22, 33, ...), todos tienen C = 0, W = q y R = 0
        for(int i = 0; i <= cantidadElementos; i++){
            C[i][i] = 0;
            W[i][i] = frecuenciaNoExito[i];
            R[i][i] = 0;
        }

        // Calcular W
        for(int m = 0; m <= cantidadElementos; m++){ // m= 0, 1, 2, 3, 4
            for(int i = 0; i <= (cantidadElementos - m); i++){   // i = 0:4, 0:3, 0:2, 0:1, 0
                int j = i + m;                                  // j = 0:4, 1:4, 2:4, 3:4, 4
                if(i == j)      // ya están calculados
                    continue;
                // Calculo de W
                W[i][j] = W[i][j-1] + frecuenciaExito[j] + frecuenciaNoExito[j];

        // Calcular C y R
                // Obtener todos los Cs necesarios
                int[] PosiblesC = new int[j-i];
                int x = 0; // iterador
                for(int k = i+1; k <= j; k++){
                    PosiblesC[x] = C[i][k-1] + C[k][j];
                    x++;
                }
                // Obtener el minimo
                int minimo = PosiblesC[0];
                int kElegido = i+1;
                for(int valor = 0; valor < PosiblesC.length; valor++){
                    if(PosiblesC[valor] < minimo) {
                        minimo = PosiblesC[valor];
                        // el valor elegido es de i<k<=j, asi que sumamos i+1 para que sea un k valido
                        kElegido = valor + 1 + i;
                    }
                }
                // Calculo de C
                C[i][j] = minimo + W[i][j];

                // Calculo de R
                R[i][j] = kElegido;
            }
        }

    }

    @Override
    public void ArmarArbol(int i, int j, Comparable[] claves, TArbolBB elArbol) {

        // para normalizar las claves, asumimos que 0 es nulo, para no forzar al usuario a typear un "null" en cada
        // array de claves, lo automatizamos
        if(claves[0] != null){
            Comparable[] fixeado = new Comparable[claves.length + 1];
            fixeado[0] = null;
            for(int z = 0; z < claves.length; z++){
                fixeado[z+1] = claves[z];
            }
            claves = fixeado;
        }

        // código del método
        if(i == j)
            return;
        // La raíz es R(i, j), obtenemos su clave
        Comparable clave = claves[R[i][j]];
        if(clave == null)
            return;
        // Insertamos el elemento actual
        TElementoAB<T> Elemento = new TElementoAB<T>(clave, (T) clave);
        elArbol.insertar(Elemento);
        ArmarArbol(i, R[i][j]-1, claves, elArbol); // El hijo izquierdo va desde el menor valor a el de su padre -1
        ArmarArbol(R[i][j], j, claves, elArbol); // El hijo derecho va desde su padre hasta el mayor valor.

        // El error es cuando toma el 0 como un valor de key valido
    }
}
