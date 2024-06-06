

# Execução

1. Baixe os arquivos `road_accidents_czechia_2016_2022.csv` e `pedestrian.csv` e coloque dentro a pasta `src`
2. Abra o terminal, na que engloba a pasta projeto-leda, e execute `python ./projeto-leda/src/transformCsv.py` e aguarde 2 novos arquivos serem gerados.
3. Execute o Main.java
4. A pasta `dataset.restulados` contém os csv resultantes dos algoritmos e `dataset.filtrados` contém as filtragem por campos.

## Observações

- o Script python serve para trocar o delimitador do arquivo CSV e para remover colunas inutilizaveis
- Não é possivel ordenar o campo `communication` usando o CoutingSort
- Caso dê erro de FileNotFound, execute o projeto na pasta que engloba a pasta `projeto-leda`
# Estrutura de Dados

1. Queue

Arquivo: ProcessadorCsv

### Motivo
- Processar dados de um arquivo CSV
- A Queue crescem dinamicamente conforme necessário,
- Preserva a ordem dos elemento

2. Tabela Hash

Arquivo: ProcessadorCsv

### Motivo

- Armazenar as colunas numa tabela hash para depois procura-lá uma em especifica
- As buscas são mais rapida que uma busca numa lista

3. ArrayList

- Arquivo: Main

###
- Motivo: Armazenar Algoritmos, Casos e Comparadores dinamicamente sem precisar definir tamanho fixo.

