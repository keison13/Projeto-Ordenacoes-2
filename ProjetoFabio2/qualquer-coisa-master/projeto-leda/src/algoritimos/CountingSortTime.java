package algoritimos;

import comparadores.Comparador;

import java.util.Arrays;

public class CountingSortTime extends Algoritmo {
    @Override
    public String[] sort(String[] array, Comparador comparador) {

        int[] times = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            String[] fields = array[i].split(";");
            times[i] = Integer.parseInt(fields[2].replace(".", ""));
        }

        int max = Arrays.stream(times).max().orElse(0);

        int[] count = new int[max + 1];
        String[] sortedArray = new String[array.length];

        for (int time : times) {
            count[time]++;
        }

        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }

        for (int i = array.length - 1; i >= 0; i--) {
            sortedArray[count[times[i]] - 1] = array[i];
            count[times[i]]--;
        }

        return sortedArray;
    }

    @Override
    public String toString() {
        return "countingSortTime";
    }

}
