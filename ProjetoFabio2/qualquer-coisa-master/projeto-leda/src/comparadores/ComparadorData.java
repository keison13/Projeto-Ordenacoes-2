package comparadores;

import csv.ProcessadorCsv;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ComparadorData extends Comparador {
    private static final SimpleDateFormat FORMATADOR =
            new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public int exec(String dado1, String dado2) {
        String data1 = dado1.split(ProcessadorCsv.DELIMITER)[1];
        String data2 = dado2.split(ProcessadorCsv.DELIMITER)[1];

        try {
            Date data1Formatada = FORMATADOR.parse(data1);
            Date data2Formatada = FORMATADOR.parse(data2);

            return data2Formatada.compareTo(data1Formatada);
        } catch (ParseException e) {

            throw new RuntimeException(
                    "Erro Formatação Data: [%s] e [%s]\nError: %s".formatted(data1, data2, e)
            );
        }

    }

    @Override
    public String toString() {
        return "date";
    }

}

