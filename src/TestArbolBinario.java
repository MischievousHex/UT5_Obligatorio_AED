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


}