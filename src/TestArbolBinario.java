import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestArbolBinario {

    public void PopularArbol(TArbolBB<Integer> arbolBB){
        arbolBB.insertar(new TElementoAB<>(10, 10));
        arbolBB.insertar(new TElementoAB<>(20, 20));
        arbolBB.insertar(new TElementoAB<>(5, 5));
        arbolBB.insertar(new TElementoAB<>(99, 99));
        arbolBB.insertar(new TElementoAB<>(1, 1));
        arbolBB.insertar(new TElementoAB<>(190, 190));
        arbolBB.insertar(new TElementoAB<>(2, 2));
        arbolBB.insertar(new TElementoAB<>(8, 8));
        arbolBB.insertar(new TElementoAB<>(170, 170));
        arbolBB.insertar(new TElementoAB<>(53, 53));
    }

    @Test
    void verificarBusqueda() {

        // Arrange
        TArbolBB<Integer> arbolBB = new TArbolBB<>();
        PopularArbol(arbolBB);
        boolean expected = true;

        // Act
        boolean result = arbolBB.VerificarBusqueda();

        // Assert
        assertEquals(expected, result);

    }

    @Test
    void verificarBusquedaFalla(){

        // Arrange
        TArbolBB<Integer> arbolBB = new TArbolBB<>();
        PopularArbol(arbolBB);
        boolean expected = false;

        // Act
        TElementoAB<Integer> elementoMalo = new TElementoAB<>(15, 15);
        TElementoAB<Integer> hijoIzqMalo = new TElementoAB<>(25, 25);
        TElementoAB<Integer> hijoDerMalo = new TElementoAB<>(8, 8);
        elementoMalo.setHijoIzq(hijoIzqMalo);
        elementoMalo.setHijoDer(hijoDerMalo);
        arbolBB.insertar(elementoMalo);
        boolean result = arbolBB.VerificarBusqueda();

        // Assert
        assertEquals(expected, result);

    }

    @Test
    void imprimirHojas() {

        // Arrange
        TArbolBB<Integer> arbolBB = new TArbolBB<>();
        PopularArbol(arbolBB);

        // Act
        arbolBB.imprimirHojas();

        // Assert
        assert true;

    }

    @Test
    void cantidadNodosNivel() {

        // Arrange
        TArbolBB<Integer> arbolBB = new TArbolBB<>();
        PopularArbol(arbolBB);
        Integer expected = 3;

        // Act
        Integer result = arbolBB.CantidadNodosNivel(4);

        // Assert
        assertEquals(expected, result);

    }

    @Test
    void obtenerMayor() {

        // Arrange
        TArbolBB<Integer> arbolBB = new TArbolBB<>();
        PopularArbol(arbolBB);
        Integer expected = 190;

        // Act
        Integer result = arbolBB.ObtenerMayor().getDatos();

        // Assert
        assertEquals(expected, result);

    }

    @Test
    void obtenerMenor() {

        // Arrange
        TArbolBB<Integer> arbolBB = new TArbolBB<>();
        Integer expected = 1;

        // Act
        PopularArbol(arbolBB);
        Integer result = arbolBB.ObtenerMenor().getDatos();

        // Assert
        assertEquals(expected, result);

    }

    @Test
    void obtenerInmediatoAnterior() {

        // Arrange
        TArbolBB<Integer> arbolBB = new TArbolBB<>();
        PopularArbol(arbolBB);
        Integer expected = 170;

        // Act
        Integer result = arbolBB.ObtenerInmediatoAnterior(190).getDatos();

        // Result
        assertEquals(expected, result);

    }
}