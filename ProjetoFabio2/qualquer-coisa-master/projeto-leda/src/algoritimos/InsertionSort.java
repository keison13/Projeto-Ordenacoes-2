package algoritimos;

import comparadores.Comparador;

public class InsertionSort extends Algoritmo {
    @Override
    public String[] sort(String[] array, Comparador comparador) {

        for (int i = 1; i < array.length; ++i) {
            String chave = array[i];
            int j = i - 1;

            while (j >= 0 && comparador.exec(array[j], chave) > 0) {
                array[j + 1] = array[j];
                j = j - 1;
            }

            array[j + 1] = chave;
        }

        return array;
    }

    @Override
    public String toString() {
        return "insertionSort";
    }

}
