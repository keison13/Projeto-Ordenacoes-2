package casos;

import comparadores.Comparador;

public class CasoMedio extends Caso {

    @Override
    public String[] obterArray(String[] array, Comparador comparador) {
        return array;
    }

    @Override
    public String toString() {
        return "medioCaso";
    }

}
