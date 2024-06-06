package casos;

import comparadores.Comparador;

import java.util.Arrays;

public class CasoMelhor extends Caso {

    @Override
    public String[] obterArray(String[] array, Comparador comparador) {
        Arrays.sort(array, comparador::exec);

        return array;
    }

    @Override
    public String toString() {
        return "melhorCaso";
    }

}
