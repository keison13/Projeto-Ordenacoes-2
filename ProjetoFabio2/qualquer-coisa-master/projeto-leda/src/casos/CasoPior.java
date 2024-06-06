package casos;

import comparadores.Comparador;

import java.util.Arrays;

public class CasoPior extends Caso {

    @Override
    public String[] obterArray(String[] array, Comparador comparador) {
        Arrays.sort(array, (dado1, dado2) -> comparador.exec(dado2, dado1));

        return array;
    }

    @Override
    public String toString() {
        return "piorCaso";
    }

}
