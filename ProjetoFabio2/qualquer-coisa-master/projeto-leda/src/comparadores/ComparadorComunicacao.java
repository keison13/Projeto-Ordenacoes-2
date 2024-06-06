package comparadores;

import csv.ProcessadorCsv;

public class ComparadorComunicacao extends Comparador {

    @Override
    public int exec(String dado1, String dado2) {
        String cominucacao1 = dado1.split(ProcessadorCsv.DELIMITER)[0];
        String cominucacao2 = dado2.split(ProcessadorCsv.DELIMITER)[0];

        return cominucacao1.compareTo(cominucacao2);
    }

    @Override
    public String toString() {
        return "communication_kind";
    }


}
