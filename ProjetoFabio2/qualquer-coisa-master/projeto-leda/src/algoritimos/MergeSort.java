package algoritimos;

import comparadores.Comparador;

import java.util.Arrays;

public class MergeSort extends Algoritmo {
    @Override
    public String[] sort(String[] array, Comparador comparador) {
         String[] arrayOrdenado = mergeSort(array, comparador);

         return arrayOrdenado;
    }

    private String[] mergeSort(String[] arr, Comparador comparador) {
        if (arr.length <= 1) {
            return arr;
        }

        int meio = arr.length / 2;

        String[] esquerda = Arrays.copyOfRange(arr, 0, meio);
        String[] direita = Arrays.copyOfRange(arr, meio, arr.length);

        mergeSort(esquerda, comparador);
        mergeSort(direita, comparador);

        return merge(arr, esquerda, direita, comparador);
    }

    private String[] merge(String[] arr, String[] esquerda, String[] direita, Comparador comparador) {
        int i = 0, j = 0, k = 0;

        while (i < esquerda.length && j < direita.length) {
            if (comparador.exec(esquerda[i], direita[j]) <= 0) {
                arr[k++] = esquerda[i++];
            } else {
                arr[k++] = direita[j++];
            }
        }

        while (i < esquerda.length) {
            arr[k++] = esquerda[i++];
        }

        while (j < direita.length) {
            arr[k++] = direita[j++];
        }

        return arr;
    }

    @Override
    public String toString() {
        return "mergeSort";
    }

}
