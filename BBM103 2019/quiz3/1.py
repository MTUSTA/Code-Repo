# My First Data Miner Program

'''
This program
a) performs data cleaning process to remove missing attribute values present in the database.
b) calculates probability of being breast cancer of an imaginary patient
   by evaluationg his/her sample results provided as command-line argument.
'''

# Starter code that reads database named 'WBC.data' and loads it into a dictionary 'dataDic'

# Reads the datafile. Note: WBC.data should be located where this file belongs.
dataFile = open('WBC.data', 'r').read()

# Makes data file ready to use by assigning every record to a dictionary class name dataDic.
dataDic = {i.split(',')[0]: i.split(',')[1:] for i in dataFile.split('\n')}

# Do not alter any upper lines so that you do not get trouble in loading data file

# Performs data cleaning process, design the content and arguments depending on your design


def funDataClean():
      missingvalue = []
      loop = 0
      while(loop < 9):
            benign = 0
            benign_num = 0
            malignant = 0
            malignant_num = 0
            for i in dataDic:
                  if(dataDic[i][loop] != "?"):
                        if(dataDic[i][-1] == "benign"):
                              benign += int(dataDic[i][loop])
                              benign_num += 1
                        elif(dataDic[i][-1] == "malignant"):
                              malignant += int(dataDic[i][loop])
                              malignant_num += 1
            for i in dataDic:
                  if dataDic[i][loop] == "?":
                        if dataDic[i][-1] == "benign":
                              dataDic[i][loop] = str(round(benign/benign_num))
                              missingvalue.append(round(benign/benign_num))
                        if dataDic[i][-1] == "malignant":
                              dataDic[i][loop] = str(round(malignant/malignant_num))
                              missingvalue.append(round(malignant/malignant_num))
            loop += 1

      average_missin_value = 0
      for i in missingvalue:
            average_missin_value += i
      # 1st phase: Cleaning WBC Database

      print('The average of all missing values is  : ' + '{0:.4f}'.format(average_missin_value / len(missingvalue)))

funDataClean()

# Performas step-wise search in WBC database, design the content and arguments depending on your design
import sys

def performStepWiseSearch():
      input1 = sys.argv[1]
      input1 = input1.split(",")
      dataDic_list = []
      for mtu in dataDic:
            dataDic_list.append(dataDic[mtu])

      yer = 0
      for deneme in input1:
            split_deneme = deneme.split(":")
            if split_deneme[0] == "<":
                  tekrar_dene = 0
                  while (tekrar_dene < 1):
                      for k in dataDic_list:
                          if int(k[yer]) >= int(split_deneme[1]):
                              dataDic_list.remove(k)
                      tekrar_dene = 1
                      for k in dataDic_list:
                          if int(k[yer]) >= int(split_deneme[1]):
                              tekrar_dene = 0
                              break

            elif split_deneme[0] == "<=":
                  tekrar_dene = 0
                  while(tekrar_dene<1):
                      for k in dataDic_list:
                          if int(k[yer]) > int(split_deneme[1]):
                              dataDic_list.remove(k)
                      tekrar_dene = 1
                      for k in dataDic_list:
                          if int(k[yer]) > int(split_deneme[1]):
                              tekrar_dene = 0
                              break

            elif split_deneme[0] == ">":
                  tekrar_dene = 0
                  while(tekrar_dene < 1):
                        for k in dataDic_list:
                              if int(k[yer]) <= int(split_deneme[1]):
                                  dataDic_list.remove(k)
                        tekrar_dene = 1
                        for k in dataDic_list:
                              if int(k[yer]) <= int(split_deneme[1]):
                                  tekrar_dene = 0
                                  break

            elif split_deneme[0] == ">=":
                  tekrar_dene = 0
                  while (tekrar_dene < 1):
                      for k in dataDic_list:
                          if int(k[yer]) < int(split_deneme[1]):
                              dataDic_list.remove(k)
                      tekrar_dene = 1
                      for k in dataDic_list:
                          if int(k[yer]) < int(split_deneme[1]):
                              tekrar_dene = 0
                              break

            elif split_deneme[0] == "!=":
                  tekrar_dene = 0
                  while (tekrar_dene < 1):
                      for k in dataDic_list:
                            if int(k[yer]) == int(split_deneme[1]):
                                  dataDic_list.remove(k)
                      tekrar_dene = 1
                      for k in dataDic_list:
                          if int(k[yer]) == int(split_deneme[1]):
                              tekrar_dene = 0
                              break

            elif split_deneme[0] == "=":
                  tekrar_dene = 0
                  while (tekrar_dene < 1):
                      for k in dataDic_list:
                          if int(k[yer]) != int(split_deneme[1]):
                              dataDic_list.remove(k)
                      tekrar_dene = 1
                      for k in dataDic_list:
                          if int(k[yer]) != int(split_deneme[1]):
                              tekrar_dene = 0
                              break
            elif split_deneme[0] == "?":
                  pass
            yer += 1

      positive_case = 0
      negative_case = 0
      for i in dataDic_list:
            if i[-1] == "benign":
                  negative_case += 1
            elif i[-1] == "malignant":
                  positive_case += 1

# 2nd phase: Retrieving knowledge from WBC dataset

      print('\nTest Results:\n'
            '----------------------------------------------'
            '\nPositive (malignant) cases            : ' + str(positive_case) +
            '\nNegative (benign) cases               : ' + str(negative_case) +
            '\nThe probability of being positive     : ' + '{0:.4f}'.format(positive_case/(positive_case+negative_case))+
            '\n----------------------------------------------')

performStepWiseSearch()

