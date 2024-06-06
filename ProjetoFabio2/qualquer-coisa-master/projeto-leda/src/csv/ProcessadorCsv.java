package csv;

import java.io.*;
import java.rmi.server.ExportException;
import java.util.*;
import java.util.function.Function;


public class ProcessadorCsv {

    private static String[] arrayOrdenacao;

    public static String DELIMITER = ";";

    public static String CAMINHO_BASE = "./projeto-leda/src";

    public static String ARQUIVO_PRINCIPAL =
            String.format("%s/dataset/road_accidents_czechia_2016_2022.csv", CAMINHO_BASE);

    public static String ARQUIVO_PEDESTRIAN =
            String.format("%s/dataset/pedestrian.csv", CAMINHO_BASE);
    public static String ARQUIVO_ORDENACAO =
            String.format("%s/dataset/filtrados/accidents_NCBMV.csv", CAMINHO_BASE);

    public static String ARQUIVO_ALCOOL_ACIDENTES =
            String.format("%s/dataset/filtrados/alcohol_accidents.csv", CAMINHO_BASE);

    public static String ARQUIVO_ACIDENTE_NCBMV =
            String.format("%s/dataset/filtrados/accidents_NCBMV.csv", CAMINHO_BASE);

    public static String ARQUIVO_ACIDENTE_CWFA =
            String.format("%s/dataset/filtrados/accidents_CWMFA.csv", CAMINHO_BASE);

    public static String ARQUIVO_ACCIDENTS_DRUNK_PEDESTRIANS =
            String.format("%s/dataset/filtrados/drunk_pedestrians.csv", CAMINHO_BASE);

    public void filtrarAcidentesComAlcool() throws IOException {
        filtrarData(
                "alcohol",
                ARQUIVO_PRINCIPAL,
                ARQUIVO_ALCOOL_ACIDENTES,
                (String value) ->value.toLowerCase().contains("yes blood")
        );

        System.out.println("--- Filtro por Acidentes de alcool finalizado");

    }

    public void filtrarNaoColisoesEntreVeiculosEmMovimento() throws IOException {
        filtrarData(
                "crash_kind",
                ARQUIVO_PRINCIPAL,
                ARQUIVO_ACIDENTE_NCBMV,
                (String value) -> Objects.equals(value, "not an option It is not a collision between moving vehicles")
        );

        System.out.println("--- Filtro por não Colisoes Entre Veiculos Em Movimento finalizado");

    }

    public void filtrarColisoesComAnimaisDeFloresta() throws IOException {
        filtrarData(
                "accident_kind",
                ARQUIVO_ACIDENTE_NCBMV,
                ARQUIVO_ACIDENTE_CWFA,
                (String value) -> Objects.equals(value.toLowerCase(), "collision with forest animals")
        );

        System.out.println("--- Filtro por Colisoes ComAnimais De Floresta finalizado");
    }

    public void filtrarPedestresSobInfluencia() throws IOException {
        filtrarData(
                "pedestrian_condition",
                ARQUIVO_PEDESTRIAN,
                ARQUIVO_ACCIDENTS_DRUNK_PEDESTRIANS,
                (String value) -> value.contains("under the influence of alcohol")
        );

        System.out.println("--- Filtro por Pedestres Sob Influencia finalizado");

    }

    public String[] obterArrayOrdenacao() throws IOException {

        if (arrayOrdenacao == null) {
            var data =  readCsv(ARQUIVO_ORDENACAO);

            data.poll();
            data.poll();

            arrayOrdenacao = data.toArray(new String[0]);
        }

        return arrayOrdenacao.clone();
    }


    private void filtrarData(String column, String inputFile, String outputFile, Function<String, Boolean> compararString) throws IOException {
        Queue<String> data = readCsv(inputFile);
        HashMap<String, Integer> colunas = new HashMap<>();

        try (FileWriter writer = new FileWriter(outputFile)) {

            if (!data.isEmpty()) {

                String header = data.poll();
                String[] columns = header.split(DELIMITER);

                writer.write(String.join(DELIMITER, columns) + "\n");

                for (int i = 0; i < columns.length; i++) {
                    colunas.put(columns[i], i);
                }

                Integer columnIndex = colunas.get(column);

                if (columnIndex == null) {
                    throw new IllegalArgumentException("Column not found: " + column);
                }

                while (!data.isEmpty()) {
                    String line = data.poll();
                    String[] fields = line.split(DELIMITER);

                    String value = fields[columnIndex];

                    if (compararString.apply(value)) {
                        writer.write(line + "\n");
                    }
                }
            }
        }

    }

    public static Queue<String> readCsv(String inputFile) throws IOException {
        Queue<String> data = new LinkedList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;

            String header = reader.readLine();
            String[] columns = header.split(DELIMITER);

            data.add(header);

            while ((line = reader.readLine()) != null) {

                if (columns.length != line.split(DELIMITER).length) {
                    continue;
                }

                data.add(line);
            }

        }

        return data;
    }


    public void writeCSV(String fileName, String[] data) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

        for (String line : data) {
            writer.write(line);
            writer.newLine();
        }

        writer.close();
    }

}
