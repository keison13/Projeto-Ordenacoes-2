import algoritimos.*;
import casos.Caso;
import casos.CasoMedio;
import casos.CasoMelhor;
import casos.CasoPior;
import comparadores.*;
import csv.ProcessadorCsv;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        ProcessadorCsv processadorCsv = new ProcessadorCsv();

        List<Algoritmo> algoritmos = List.of(
                new MergeSort(),
                new InsertionSort(),
                new SelectionSort(),
                new HeapSort(),
                new QuickSort(),
                new QuickSortMedianOfThree(),
                new CountingSortDate(),
                new CountingSortTime()
        );

        List<Comparador> comparadores = List.of(
                new ComparadorComunicacao(),
                new ComparadorData(),
                new ComparadorTime()
        );

        List<Caso> casos = List.of(
                new CasoPior(),
                new CasoMedio(),
                new CasoMelhor()
        );

        System.out.println("--------------------------------------------------------------------------");
        System.out.println("----------- Iniciando Transformações --------");
        System.out.println();

        processadorCsv.filtrarAcidentesComAlcool();
        processadorCsv.filtrarNaoColisoesEntreVeiculosEmMovimento();
        processadorCsv.filtrarColisoesComAnimaisDeFloresta();
        processadorCsv.filtrarPedestresSobInfluencia();

        System.out.println();
        System.out.println("---- Transformações finalizadas");
        System.out.println();

        for (Algoritmo algoritmo : algoritmos) {

            System.out.println("--------------------------------------------------------------------------");
            System.out.println("----------- Algoritmo: [" + algoritmo + "]-----------------");
            System.out.println();

            for (Caso caso : casos) {

                System.out.println("--------- Caso: [" + caso + "] --------------");
                System.out.println();

                for (Comparador comparador : comparadores) {

                    if (algoritmo instanceof CountingSortDate && !comparador.toString().equals("date")) {
                        continue;
                    }

                    if (algoritmo instanceof CountingSortTime && !comparador.toString().equals("time")) {
                        continue;
                    }

                    String[] arrayOriginal = processadorCsv.obterArrayOrdenacao();
                    String[] arrayCaso = caso.obterArray(arrayOriginal, comparador);

                    long inicio = System.currentTimeMillis();
                    String[] arrayOrdenado = algoritmo.sort(arrayCaso, comparador);
                    long fim = System.currentTimeMillis();

                    String nomeArquivo = String.format(
                            "accidents_NCBMV_%s_%s_%s.csv",
                            comparador, algoritmo, caso
                    );

                    processadorCsv.writeCSV(
                            "%s/dataset/resultado/%s".formatted(ProcessadorCsv.CAMINHO_BASE, nomeArquivo),
                            arrayOrdenado
                    );

                    System.out.printf(
                            "Arquivo [%s] (%s) Ordenado com Tempo de %.3f\n",
                            nomeArquivo, comparador, (double) (fim - inicio)
                    );

                }

                System.out.println();
            }

            System.out.println();
        }


    }


}