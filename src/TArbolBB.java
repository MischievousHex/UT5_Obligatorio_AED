public class TArbolBB<T> implements IArbolBB<T> {

    private TElementoAB<T> raiz;

    /**
     * Separador utilizado entre elemento y elemento al imprimir la lista
     */
    public static final String SEPARADOR_ELEMENTOS_IMPRESOS = "-";

    public TArbolBB() {
        raiz = null;
    }

    /**
     * @param unElemento
     * @return
     */
    public boolean insertar(TElementoAB<T> unElemento) {
        if (esVacio()) {
            raiz = unElemento;
            return true;
        } else {
            return raiz.insertar(unElemento);
        }
    }

    /**
     * @param unaEtiqueta
     * @return
     */
    @SuppressWarnings("unchecked")
    public TElementoAB<T> buscar(Comparable unaEtiqueta) {
        if (esVacio()) {
            return null;
        } else {
            return raiz.buscar(unaEtiqueta);
        }
    }

    /**
     * @return recorrida en inorden del arbol, null en caso de ser vacío
     */
    public String inOrden() {
        if (esVacio()) {
            return null;
        } else {
            return raiz.inOrden();
        }
    }

    @Override
    public Lista<T> inorden() {
        Lista <T> listaInorden = null;
        if (!esVacio()) {
            listaInorden = new Lista<T>();
            raiz.inOrden(listaInorden);
        }
        return listaInorden;
    }

    public String postOrden(){
        if(esVacio())
            return null;
        return raiz.postOrden();
    }

    /**
     * @return recorrida en preOrden del arbol, null en caso de ser vacío
     */
    /**
     * @return
     */
    public boolean esVacio() {
        return (raiz == null);
    }

    /**
     * @return True si habían elementos en el árbol, false en caso contrario
     */
    public boolean vaciar() {
        if (!esVacio()) {
            raiz = null;
            return true;
        }
        return false;
    }

    @Override
    public void eliminar(Comparable unaEtiqueta) {
        if (!esVacio()) {
            this.raiz = this.raiz.eliminar(unaEtiqueta);
        }
    }

    @Override
    public int obtenerAltura() {
        if(esVacio())
            return 0;
        return raiz.obtenerAltura();
    }

    @Override
    public int obtenerTamanio() {
        if(esVacio())
            return 0;
        return obtenerTamanio();
    }

    @Override
    public int obtenerNivel(Comparable unaEtiqueta) {
        if(esVacio())
            return 0;
        return raiz.obtenerNivel(unaEtiqueta);
    }

    @Override
    public int obtenerCantidadHojas() {
        if(esVacio())
            return 1;
        return raiz.obtenerCantidadHojas();
    }

    // ----------------------------------------------------------------------
    //  Implementaciones para trabajo obligatorio:
    // ----------------------------------------------------------------------

    public TElementoAB<T> ObtenerMenor(){
        TElementoAB<T> auxiliar = raiz;
        while(auxiliar != null){
            if(auxiliar.getHijoIzq() == null)
                return auxiliar;
            auxiliar = auxiliar.getHijoIzq();
        }
        return raiz;
    }

    public TElementoAB<T> ObtenerMayor(){
        TElementoAB<T> auxiliar = raiz;
        while(auxiliar != null){
            if(auxiliar.getHijoDer() == null)
                return auxiliar;
            auxiliar = auxiliar.getHijoDer();
        }
        return raiz;
    }

    // Implementación recursiva de los métodos anteriores. Se recomienda utilizar sus versiones no-recursivas,
    // ya que son más eficientes.
    public TElementoAB<T> ObtenerMenorRecursivo(){
        return raiz.ObtenerMenor();
    }

    public TElementoAB<T> ObtenerMayorRecursivo(){
        return raiz.ObtenerMayor();
    }

    public TElementoAB<T> ObtenerInmediatoAnterior(Comparable unaEtiqueta){
        return raiz.ObtenerInmediatoAnterior(unaEtiqueta);
    }

    public int CantidadNodosNivel(int nivel){
        return raiz.ObtenerCantidadNodosNivel(nivel, 1, 0);
    }

    public void imprimirHojas() {
        raiz.ImprimirHojas(1);
    }

    public boolean VerificarBusqueda(){
        return this.raiz.VerificarBusqueda(this.ObtenerMenor().getEtiqueta(), this.ObtenerMayor().getEtiqueta());
    }

}
