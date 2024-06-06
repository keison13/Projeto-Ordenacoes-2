package algoritimos;

import comparadores.Comparador;

public class HeapSort extends Algoritmo {

    @Override
    public String[] sort(String[] array, Comparador comparador) {
        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i, comparador);
        }

        for (int i = n - 1; i > 0; i--) {
            String temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            heapify(array, i, 0, comparador);
        }

        return array;
    }

    @Override
    public String toString() {
        return "heapSort";
    }

    private static void heapify(String[] arr, int n, int i, Comparador comparador) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && comparador.exec(arr[left], arr[largest]) > 0) {
            largest = left;
        }

        if (right < n && comparador.exec(arr[right], arr[largest]) > 0) {
            largest = right;
        }

        if (largest != i) {
            String swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest, comparador);
        }
    }

}
