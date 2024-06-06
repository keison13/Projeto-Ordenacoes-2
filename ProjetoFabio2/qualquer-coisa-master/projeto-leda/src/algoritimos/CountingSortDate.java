package algoritimos;

import comparadores.Comparador;

import java.util.Arrays;

public class CountingSortDate extends Algoritmo {
    @Override
    public String[] sort(String[] array, Comparador comparador) {
        int[] dates = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            String[] fields = array[i].split(";");
            String[] dateFields = fields[1].split("-");
            dates[i] = Integer.parseInt(dateFields[0] + dateFields[1] + dateFields[2]);
        }

        int max = Arrays.stream(dates).max().orElse(0);

        int[] count = new int[max + 1];
        String[] sortedArray = new String[array.length];

        for (int date : dates) {
            count[date]++;
        }

        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }

        for (int i = array.length - 1; i >= 0; i--) {
            sortedArray[count[dates[i]] - 1] = array[i];
            count[dates[i]]--;
        }

        for (int i = 0; i < sortedArray.length / 2; i++) {
            String temp = sortedArray[i];
            sortedArray[i] = sortedArray[sortedArray.length - 1 - i];
            sortedArray[sortedArray.length - 1 - i] = temp;
        }

        return sortedArray;
    }

    @Override
    public String toString() {
        return "countingSortDate";
    }

}

