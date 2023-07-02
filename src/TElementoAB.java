public class TElementoAB<T> implements IElementoAB<T> {

    private Comparable etiqueta;
    private TElementoAB<T> hijoIzq;
    private TElementoAB<T> hijoDer;
    private T datos;

    /**
     * @param unaEtiqueta
     * @param unosDatos 
     */
    public TElementoAB(Comparable unaEtiqueta, T unosDatos) {
        etiqueta = unaEtiqueta;
        datos = unosDatos;
    }

    public TElementoAB<T> getHijoIzq() {
        return hijoIzq;
    }

    public TElementoAB<T> getHijoDer() {
        return hijoDer;
    }

    /**
     * @param unElemento
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean insertar(TElementoAB<T> unElemento) {
        if (unElemento.getEtiqueta().compareTo(etiqueta) < 0) {
            if (hijoIzq != null) {
                return getHijoIzq().insertar(unElemento);
            } else {
                hijoIzq = unElemento;
                return true;
            }
        } else if (unElemento.getEtiqueta().compareTo(etiqueta) > 0) {
            if (hijoDer != null) {
                return getHijoDer().insertar(unElemento);
            } else {
                hijoDer = unElemento;
                return true;
            }
        } else {
            // ya existe un elemento con la misma etiqueta.-
            return false;
        }
    }

    /**
     * @param unaEtiqueta
     * @return
     */
    @Override
    public TElementoAB<T> buscar(Comparable unaEtiqueta) {

        if (unaEtiqueta.equals(etiqueta)) {
            return this;
        } else if (unaEtiqueta.compareTo(etiqueta) < 0) {
            if (hijoIzq != null) {
                return getHijoIzq().buscar(unaEtiqueta);
            } else {
                return null;
            }
        } else if (hijoDer != null) {
            return getHijoDer().buscar(unaEtiqueta);
        } else {
            return null;
        }
    }

    /**
     * @return recorrida en inorden del subArbol que cuelga del elemento actual
     */
    @Override
    public String inOrden() {
        Lista<T> unaLista = new Lista<>();
        this.inOrden(unaLista);
        return unaLista.toString();
    }

   @Override
    public void inOrden(Lista<T> unaLista) {
        if(hijoIzq != null)
            hijoIzq.inOrden(unaLista);
        unaLista.insertar(new Nodo<>(this.etiqueta, this.datos));
        if(hijoDer != null)
            hijoDer.inOrden(unaLista);
    }

    public String postOrden(){
        Lista<T> unaLista = new Lista<>();
        this.postOrden(unaLista);
        return unaLista.toString();
    }

    public void postOrden(Lista<T> unaLista){
        if(hijoDer != null)
            hijoDer.postOrden(unaLista);
        if(hijoIzq != null)
            hijoIzq.postOrden(unaLista);
        unaLista.insertar(new Nodo<>(this.etiqueta, this.datos));
    }

    @Override
    public Comparable getEtiqueta() {
        return etiqueta;
    }

    /**
     * @return
     */
    public String imprimir() {
        return (etiqueta.toString());
    }

    @Override
    public T getDatos() {
        return datos;
    }

    @Override
    public void setHijoIzq(TElementoAB<T> elemento) {
        this.hijoIzq = elemento;

    }

    @Override
    public void setHijoDer(TElementoAB<T> elemento) {
        this.hijoDer = elemento;
    }

    

    @Override
    public int obtenerAltura() {
        int altIzq = 0;
        int altDer = 0;
        if(hijoIzq != null){
            altIzq = hijoIzq.obtenerAltura();
        }
        if(hijoDer != null){
            altDer = hijoDer.obtenerAltura();
        }
        return (Math.max(altIzq, altDer) + 1);
    }

    @Override
    public int obtenerTamanio() {
        int size = 1;
        if(hijoIzq != null)
            size += hijoIzq.obtenerTamanio();
        if(hijoDer != null)
            size += hijoDer.obtenerTamanio();
        return size;    }

    @Override
    public int obtenerNivel(Comparable unaEtiqueta) {
        if(this.etiqueta.compareTo(unaEtiqueta) == 0)
            return 0;
        int nivIzq = -1;
        int nivDer = -1;
        if(hijoIzq != null) {
            nivIzq = hijoIzq.obtenerNivel(unaEtiqueta);
            if(nivIzq != -1)
                return nivIzq + 1;
        }
        if(hijoDer != null) {
            nivDer = hijoDer.obtenerNivel(unaEtiqueta);
            if(nivDer != -1)
                return nivDer + 1;
        }
        return -1;
    }

    @Override
    public TElementoAB eliminar(Comparable unaEtiqueta) {
        if (unaEtiqueta.compareTo(etiqueta) < 0) {
            if (hijoIzq != null) {
                this.hijoIzq = hijoIzq.eliminar(unaEtiqueta);
            }
            return this;
        }

        else if (unaEtiqueta.compareTo(etiqueta) > 0) { //por descarte >0
            if (hijoDer != null) {
                this.hijoDer = hijoDer.eliminar(unaEtiqueta);
            }
            return this;
        }

        else
        {
            return this.quitaElNodo();
        }
    }

    private TElementoAB quitaElNodo() {
        if (hijoIzq == null) { //solo tiene un hijo
            return hijoDer;
        }

        else if (hijoDer == null) {
            return hijoIzq;
        }
        else {
            TElementoAB<T> elHijo = hijoIzq;
            TElementoAB<T> elPadre = this;

            while (elHijo.hijoDer != null) {
                elPadre = elHijo;
                elHijo = elHijo.hijoDer;
            }
            if (elPadre != this) {
                elPadre.setHijoDer(elHijo.getHijoIzq());
                elHijo.setHijoIzq(hijoIzq);
            }
            elHijo.setHijoDer(hijoDer);
            setHijoIzq(null); // para mantener el órden, sino se nos rompe el árbol. la raíz fue para abajo y ahora la acomodamos arriba
            setHijoDer(null);
            return elHijo;
        }
    }

    @Override
    public int obtenerCantidadHojas() {
        if (hijoIzq == null){
            if (hijoDer == null){
                // si los dos no existen
                return 1;
            } else {
                // si el izquierdo no existe pero el derecho si
                return hijoDer.obtenerCantidadHojas();
            }
        } else {
            if(hijoDer == null){
                // si el izquierdo existe pero el derecho no
                return hijoIzq.obtenerCantidadHojas();
            } else {
                // si ambos existen
                return (hijoIzq.obtenerCantidadHojas() + hijoDer.obtenerCantidadHojas());
            }
        }
    }

    public TElementoAB<T> ObtenerMenor(){
        TElementoAB<T> auxiliar = this;
        while(auxiliar != null){
            if(auxiliar.getHijoIzq() == null)
                return auxiliar;
            auxiliar = auxiliar.getHijoIzq();
        }
        return this;
    }

    public TElementoAB<T> ObtenerMayor(){
        TElementoAB<T> auxiliar = this;
        while(auxiliar != null){
            if(auxiliar.getHijoDer() == null)
                return auxiliar;
            auxiliar = auxiliar.getHijoDer();
        }
        return this;
    }

    public TElementoAB<T> ObtenerMenorRecursivo(){
        if(this.hijoIzq == null)
            return this;
        return this.hijoIzq.ObtenerMenor();
    }

    public TElementoAB<T> ObtenerMayorRecursivo(){
        if(this.hijoDer == null)
            return this;
        return this.hijoDer.ObtenerMayor();
    }

    public TElementoAB<T> ObtenerInmediatoAnterior(Comparable unaEtiqueta) {

        // Precondición: La etiqueta debe estar en el árbol
        if(buscar(unaEtiqueta) == null)
            return null;

        TElementoAB<T> anterior = null;
        TElementoAB<T> actual = this;

        while(true) {

            if (actual.etiqueta.equals(unaEtiqueta)) {
                if (actual.getHijoIzq() == null)
                    return anterior;
                else return actual.getHijoIzq().ObtenerMayor();
            }
            else if (actual.etiqueta.compareTo(unaEtiqueta) > 0) {
                if (actual.getHijoIzq() != null)
                    actual = actual.getHijoIzq();
            }

            else if (actual.etiqueta.compareTo(unaEtiqueta) < 0) {
                if (actual.getHijoDer() != null) {
                    anterior = actual;
                    actual = actual.getHijoDer();
                }
            }

        }

    }

    public int ObtenerCantidadNodosNivel(int nivel, int actual, int contador) {
        if(actual == nivel){
            return contador+1;
        } // else ...

        if(this.hijoIzq != null)
            contador = this.hijoIzq.ObtenerCantidadNodosNivel(nivel, actual+1, contador);
        if(this.hijoDer != null)
            contador = this.hijoDer.ObtenerCantidadNodosNivel(nivel, actual+1, contador);
        return contador;
    }

    public void ImprimirHojas(int nivelActual){
        if(hijoIzq == null && hijoDer == null)
            System.out.println("Hoja: " + this.getEtiqueta().toString() + ", " + this.getDatos().toString() +
                    ", Nivel: " + nivelActual);
        if(hijoIzq != null){
            hijoIzq.ImprimirHojas(nivelActual + 1);
        }
        if(hijoDer != null){
            hijoDer.ImprimirHojas(nivelActual + 1);
        }
    }

    public boolean VerificarBusqueda(Comparable minimo, Comparable maximo){
        boolean verificar = true;
        if(this.etiqueta.compareTo(minimo) < 0 || this.etiqueta.compareTo(maximo) > 0){
            verificar = false;
        }
        if(hijoIzq != null) {
            verificar = verificar && hijoIzq.VerificarBusqueda(minimo, this.etiqueta);
        }
        if(hijoDer != null) {
            verificar = verificar && hijoDer.VerificarBusqueda(this.etiqueta, maximo);
        }
        return verificar;
    }

}
