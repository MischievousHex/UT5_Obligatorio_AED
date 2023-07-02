
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {

        Integer[] claves = {10, 20, 30, 40};
        int[] p = {0, 3, 3, 1, 1};
        int[] q = {2, 3, 1, 1, 1};
        TArbolBB<Integer> arbolOptimo = new TArbolBB<>();

        TCalculadorMatricesOptimo<Integer> Calculador = new TCalculadorMatricesOptimo<>();
        Calculador.EncontrarOptimo(4, p, q);
        Calculador.ArmarArbol(0,4, claves, arbolOptimo);

    }
}
