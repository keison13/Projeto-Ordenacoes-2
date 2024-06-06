package algoritimos;

import comparadores.Comparador;

public class QuickSortMedianOfThree extends Algoritmo {

    private void medianOfThree(String[] array, Comparador comparador) {
        int low = 0;
        int mid = array.length / 2;
        int high = array.length - 1;

        if (comparador.exec(array[low], array[mid]) > 0) {
            swap(array, low, mid);
        }
        if (comparador.exec(array[low], array[high]) > 0) {
            swap(array, low, high);
        }
        if (comparador.exec(array[mid], array[high]) > 0) {
            swap(array, mid, high);
        }
        swap(array, mid, high);
    }

    private static void swap(String[] array, int i, int j) {
        String temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    @Override
    public String[] sort(String[] array, Comparador comparador) {
        medianOfThree(array, comparador);

        quickSort(array, 0, array.length - 1, comparador);

        return array;
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

    @Override
    public String toString() {
        return "quickSortMedianOfThree";
    }

}
