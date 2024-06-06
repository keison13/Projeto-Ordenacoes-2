package algoritimos;

import comparadores.Comparador;

public class SelectionSort extends Algoritmo {

    @Override
    public String[] sort(String[] array, Comparador comparador) {

        for (int i = 0; i < array.length - 1; i++) {
            int indexMinimo = i;

            for (int j = i + 1; j < array.length; j++) {
                if (comparador.exec(array[j], array[indexMinimo]) < 0) {
                    indexMinimo = j;
                }
            }

            String temp = array[indexMinimo];
            array[indexMinimo] = array[i];
            array[i] = temp;
        }

        return array;
    }

    @Override
    public String toString() {
        return "selectionSort";
    }

}
