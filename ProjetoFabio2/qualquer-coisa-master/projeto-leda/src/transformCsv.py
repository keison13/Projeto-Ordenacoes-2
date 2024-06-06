import pandas as pd
import os
def selecionar_colunas_e_salvar(input_file, output_file, new_delimiter, columns):
    df = pd.read_csv(input_file, delimiter=",")
    df_selecionado = df.iloc[:, columns]
    df_selecionado.to_csv(output_file, sep=new_delimiter, index=False)

print("Gerandos CSV formatados....")

delimiter = ";"

selecionar_colunas_e_salvar(
    os.getcwd() + './projeto-leda/src/road_accidents_czechia_2016_2022.csv',
    os.getcwd() + './projeto-leda/src/dataset/road_accidents_czechia_2016_2022.csv',
    delimiter,
    [1,2,3,4,5,9]
)

selecionar_colunas_e_salvar(
    os.getcwd() + './projeto-leda/src/pedestrian.csv',
    os.getcwd() + './projeto-leda/src/dataset/pedestrian.csv',
    delimiter,
    [1,3]
)

print("Formatação completa!")
