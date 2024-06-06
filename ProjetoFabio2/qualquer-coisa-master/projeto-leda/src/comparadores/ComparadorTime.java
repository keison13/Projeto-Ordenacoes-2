package comparadores;


import csv.ProcessadorCsv;

public class ComparadorTime extends Comparador {

    @Override
    public int exec(String dado1, String dado2) {
        String time1 = dado1.split(ProcessadorCsv.DELIMITER)[2];
        String time2 = dado2.split(ProcessadorCsv.DELIMITER)[2];

        Double time1Double = Double.parseDouble(time1);
        Double time2Double = Double.parseDouble(time2);

        return time1Double.compareTo(time2Double);
    }

    @Override
    public String toString() {
        return "time";
    }

}
