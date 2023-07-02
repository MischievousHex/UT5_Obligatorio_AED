public interface ICalculadorMatricesOptimo<T>
{
    public void EncontrarOptimo(int cantidadElementos, int[] frecuenciaExito, int[] frecuenciaNoExito);
    public void ArmarArbol(int i, int j, Comparable[] claves, TArbolBB<T> elArbol);

}
