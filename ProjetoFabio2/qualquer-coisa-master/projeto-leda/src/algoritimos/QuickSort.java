package algoritimos;

import comparadores.Comparador;

public class QuickSort extends Algoritmo {

    @Override
    public String[] sort(String[] array, Comparador comparador) {
        quickSort(array, 0, array.length - 1, comparador);

        return array;
    }

    @Override
    public String toString() {
        return "quickSort";
    }

    private void quickSort(String[] arr, int low, int high, Comparador comparador) {
        if (low < high) {
            int pi = partition(arr, low, high, comparador);

            quickSort(arr, low, pi - 1, comparador);
            quickSort(arr, pi + 1, high, comparador);
        }
    }

    private static int partition(String[] arr, int low, int high, Comparador comparador) {
        String pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (comparador.exec(arr[j], pivot) <= 0) {
                i++;
                String temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        String temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }


}
