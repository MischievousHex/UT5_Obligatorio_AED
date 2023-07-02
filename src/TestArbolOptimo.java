import org.junit.jupiter.api.Test;

public class TestArbolOptimo {

    @Test
    public void TestPalabras(){

        // Arrange
        String[] palabras = ManejadorArchivosGenerico.leerArchivo("input/palabras.txt");
        String[] nopalabras = ManejadorArchivosGenerico.leerArchivo("input/nopalabras.txt");
        String[] claves = new String[palabras.length];
        int[] p = new int[palabras.length+1];
        int[] q = new int[palabras.length+1];

        p[0] = 0;

        for(int i = 0; i < palabras.length; i++){
            String[] kvp = palabras[i].split(" ");
            claves[i] = kvp[0];
            p[i+1] = Integer.parseInt(kvp[1]);

            q[i] = Integer.parseInt(nopalabras[i]);
        }
        q[palabras.length] = Integer.parseInt(nopalabras[palabras.length]);

        // Act
        TCalculadorMatricesOptimo<String> calculadorMatricesOptimo = new TCalculadorMatricesOptimo<>();
        calculadorMatricesOptimo.EncontrarOptimo(palabras.length, p, q);

        TArbolBB<String> unArbol = new TArbolBB<>();
        calculadorMatricesOptimo.ArmarArbol(0, palabras.length, claves, unArbol);

        // Assert

        assert (unArbol.obtenerNivel("if") == 0);
        assert (unArbol.obtenerNivel("super") > unArbol.obtenerNivel("class"));

    }

    @Test
    public void TestPalabras2(){
        // Arrange
        String[] palabras = ManejadorArchivosGenerico.leerArchivo("input/palabras2.txt");
        String[] nopalabras = ManejadorArchivosGenerico.leerArchivo("input/nopalabras2.txt");
        String[] claves = new String[palabras.length];
        int[] p = new int[palabras.length+1];
        int[] q = new int[palabras.length+1];

        p[0] = 0;

        for(int i = 0; i < palabras.length; i++){
            String[] kvp = palabras[i].split(" ");
            claves[i] = kvp[0];
            p[i+1] = Integer.parseInt(kvp[1]);

            q[i] = Integer.parseInt(nopalabras[i]);
        }
        q[palabras.length] = Integer.parseInt(nopalabras[palabras.length]);

        // Act
        TCalculadorMatricesOptimo<String> calculadorMatricesOptimo = new TCalculadorMatricesOptimo<>();
        calculadorMatricesOptimo.EncontrarOptimo(palabras.length, p, q);

        TArbolBB<String> unArbol = new TArbolBB<>();
        calculadorMatricesOptimo.ArmarArbol(0, palabras.length, claves, unArbol);

        // Assert

        TElementoAB<String> raiz = unArbol.buscar("raton");
        assert (unArbol.obtenerNivel("raton") == 0);
        assert (raiz.getHijoIzq().getEtiqueta().equals("gato"));
        assert (raiz.getHijoDer().getEtiqueta().equals("zorro"));
        assert (raiz.getHijoIzq().getHijoDer().getEtiqueta().equals("perro"));
    }

}
