import csv
import sys
import numpy as np
import random
import matplotlib.pyplot as plt
import matplotlib.pyplot as plt2
import matplotlib.pyplot as plt3
import matplotlib.pyplot as plt4
import matplotlib.pyplot as plt5
import matplotlib.pyplot as plt6
import matplotlib.pyplot as plt7
import matplotlib.pyplot as plt8
from matplotlib.backends.backend_pdf import PdfPages

csvfile = open(sys.argv[1], newline="")
sys2modül = sys.argv[2]
csvreader = csv.reader(csvfile)
output1 = open("retriedData.txt", "w")

csv_list = []
bölgeler = []
bölgeler1 = []
total_votes = []
total_votes1 = []
electoral_list = []
electoral_list1 = []
isimler_oylar = []
isimler = []
sözlük = {}
sözlük2 = {}

def retrieveData(csvreader):
    for sentence in csvreader:
        csv_list.append(sentence)
    b = 0
    for a in range(len(csv_list[0])):
        if csv_list[0][a][0:5].lower() == "state":
            b = a
            for c in range(len(csv_list)):
                bölgeler.append(csv_list[c][b])
    e = 0
    for d in range(len(csv_list[0])):
        if csv_list[0][d][0:5].lower() == "total":
            e = d
            for f in range(len(csv_list)):
                total_votes.append(csv_list[f][e])

    h = 0
    for g in range(len(csv_list[0])):
        if csv_list[0][g].lower() == "electoral":
            h = g
            for i in range(len(csv_list)):
                electoral_list.append(csv_list[i][h])
    k = 0
    for j in range(len(csv_list[0])):
        if csv_list[0][j].lower() != "electoral" and csv_list[0][j][0:5].lower() != "total" and csv_list[0][j][0:5].lower() != "state":
            isimler.append(csv_list[0][j])
            k = j
            for l in range(len(csv_list)):
                if csv_list[l][j].isdigit():
                    isimler_oylar.append(int(csv_list[l][j]))
retrieveData(csvreader)
output1.write(str(isimler_oylar))

bölgeler1 = bölgeler[1:]
total_votes1 = total_votes[1:]
electoral_list1 = electoral_list[1:]
csv_list1 = csv_list[1:]



toplam_oylar = []
def DispBarPlot():
    o = 0
    p = 1
    q = 0
    r = 0
    while p < (len(isimler)+1):
        for n in range(len(bölgeler1)):
            o = o + isimler_oylar[n+q]
        toplam_oylar.append(o)
        sözlük[o] = isimler[r]
        sözlük2[isimler[r]] = o
        q = len(bölgeler1)*p
        o = 0
        p = p + 1
        r = r+1

    toplam_oylar.sort()
    toplam_oylar.remove(sözlük2["Others"])

    N = len(bölgeler1)
    bar1 = [int(csv_list1[s][csv_list[0].index(sözlük[toplam_oylar[-2]])]) for s in range(len(bölgeler1))]

    ind = np.arange(N)
    width = 0.35

    fig, ax = plt.subplots()
    rects1 = ax.bar(ind, bar1, width, color='r')

    bar2 = [int(csv_list1[s][csv_list[0].index(sözlük[toplam_oylar[-1]])]) for s in range(len(bölgeler1))]
    rects2 = ax.bar(ind + width, bar2, width, color='b')

    ax.set_ylabel('Vote Count')
    ax.set_xlabel('States')
    ax.set_title('Scores by group and gender')
    ax.set_xticks(ind + width)
    ax.set_xticklabels(bölgeler1, rotation=90)

    ax.legend((rects1[0], rects2[0]), (sözlük[toplam_oylar[-2]],sözlük[toplam_oylar[-1]]))

    plt.savefig("ComparativeVotes.pdf")
    plt.show()

DispBarPlot()


ilk_en_iyi = (isimler.index(sözlük[toplam_oylar[-1]]))
ikinci_en_iyi = isimler.index(sözlük[toplam_oylar[-2]])

oranlar = []
oranlar_string = []
oranlar_string_kısaltılmış = []
oranlar_string_kısaltılmış2 = []
oranlar_string_kısaltılmış3 = []
oranlar1 = [0, 10, 20, 30, 40]


def compareVoteonBar():

    toplam_oylar.reverse()
    toplanmış_oy = 0
    for vote_sum in range(len(toplam_oylar)):
        toplanmış_oy = toplanmış_oy+toplam_oylar[vote_sum]

    for oran in range(len(toplam_oylar)):
        yüzde_oy = (toplam_oylar[oran]/toplanmış_oy*100)
        oranlar.append(yüzde_oy)
        oranlar_string.append(str(yüzde_oy))

    for kısaltma in range(len(oranlar)):
        oranlar_string_kısaltılmış.append(oranlar_string[kısaltma][0:5])
    for kısaltma2 in range(len(oranlar)):
        oranlar_string_kısaltılmış2.append(float(oranlar_string_kısaltılmış[kısaltma2]))
    for kısaltma3 in range(len(oranlar)):
        oranlar_string_kısaltılmış3.append(oranlar_string_kısaltılmış[kısaltma3]+"%")


    x_pos = [x for x in range(len(oranlar))]

    ccc = ("r", "b", "yellow", "purple")

    plt2.bar(x_pos, oranlar_string_kısaltılmış2, align="center", color=ccc, alpha=0.8)
    plt2.xticks(x_pos, oranlar_string_kısaltılmış3)
    plt2.ylabel("Vote Percentages")
    plt2.xlabel("Nominees")
    plt2.savefig("CompVotePercs.pdf")
    plt2.show()
compareVoteonBar()#sag üst köşe eklencek

#4 step
obhis_list = []
icerik_list = []
frequency = []


def obtainHistogram():
    rakam = 0
    contain = 0
    for obhis in range(len(isimler_oylar)-51):
        if str(isimler_oylar[obhis]) == "0" or str(isimler_oylar[obhis]) == "1" or str(isimler_oylar[obhis]) == "2" or str(isimler_oylar[obhis]) == "3" or str(isimler_oylar[obhis]) == "4" or str(isimler_oylar[obhis]) == "5" or str(isimler_oylar[obhis]) == "6" or str(isimler_oylar[obhis]) == "7" or str(isimler_oylar[obhis]) == "8" or str(isimler_oylar[obhis]) == "9":
            obhis_list.append("0"+str(isimler_oylar[obhis]))
        else:
            isimler_oylar[obhis] = str(isimler_oylar[obhis])
            obhis_list.append(isimler_oylar[obhis][-2:])

    while rakam < 10:
        for frekans in range(len(obhis_list)):
            obhis_list[frekans].count(str(rakam))
            contain = contain+(obhis_list[frekans].count(str(rakam)))

        icerik_list.append(contain)
        contain = 0
        rakam = rakam + 1

    for icerik_list_öge in range(len(icerik_list)):
        sonuc = icerik_list[icerik_list_öge]/(len(obhis_list)*2)
        frequency.append(sonuc)


obtainHistogram()

grafik_rakam=[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

#5 step
def plotHistogram():
    mean=[0.1]
    plt3.plot(mean*10,linestyle="--", color="g",label="Mean")
    plt3.title("Histogram of least sign,digits")
    plt3.ylabel("Distribution")
    plt3.xlabel("Digits")
    plt3.plot(grafik_rakam, frequency, color="r", label="Digits Dist")
    plt3.savefig("Histogram.pdf")
    plt3.show()

plotHistogram()#other dinamikle legende ekle

#step6
rastgele_liste1 = []
rastgele_liste2 = []
rastgele_liste3 = []
rastgele_liste4 = []
rastgele_liste5 = []
rastgele_liste1_str = []
rastgele_liste2_str = []
rastgele_liste3_str = []
rastgele_liste4_str = []
rastgele_liste5_str = []
rastgele_liste_değişik = []
rasthis_list6 = []

def plotHistogramWithSample():

    rastgele_liste1_number = 0
    rastgele_liste2_number = 0
    rastgele_liste3_number = 0
    rastgele_liste4_number = 0
    rastgele_liste5_number = 0


    while rastgele_liste1_number < 10:
        number1 = random.randint(0, 100)
        rastgele_liste1.append(number1)
        rastgele_liste1_str.append(str(number1))
        rastgele_liste1_number = rastgele_liste1_number + 1

    while rastgele_liste2_number < 50:
        number2 = random.randint(0, 100)
        rastgele_liste2_number = rastgele_liste2_number + 1
        rastgele_liste2.append(number2)
        rastgele_liste2_str.append(str(number2))

    while rastgele_liste3_number < 100:
        number3 = random.randint(0, 100)
        rastgele_liste3_number = rastgele_liste3_number + 1
        rastgele_liste3.append(number3)
        rastgele_liste3_str.append(str(number3))

    while rastgele_liste4_number < 1000:
        number4 = random.randint(0, 100)
        rastgele_liste4_number = rastgele_liste4_number + 1
        rastgele_liste4.append(number4)
        rastgele_liste4_str.append(str(number4))

    while rastgele_liste5_number < 10000:
        number5 = random.randint(0, 100)
        rastgele_liste5_number = rastgele_liste5_number + 1
        rastgele_liste5.append(number5)
        rastgele_liste5_str.append(str(number5))

    rastgele_liste_değişik.append(rastgele_liste1_str)
    rastgele_liste_değişik.append(rastgele_liste2_str)
    rastgele_liste_değişik.append(rastgele_liste3_str)
    rastgele_liste_değişik.append(rastgele_liste4_str)
    rastgele_liste_değişik.append(rastgele_liste5_str)

    rastgele_rakam = 0
    rastgele_rakam1 = 0
    rastgele_rakam2 = 0
    rastgele_rakam3 = 0
    rastgele_rakam4 = 0

    rastgele_contain = 0
    rastgele_contain1 = 0
    rastgele_contain2 = 0
    rastgele_contain3 = 0
    rastgele_contain4 = 0
    rastgele_liste_değişik_numara = 0
    rasthis_list = []

    rast_list_öge0 = []
    rast_list_öge1 = []
    rast_list_öge2 = []
    rast_list_öge3 = []
    rast_list_öge4 = []

    while rastgele_liste_değişik_numara < 5:
        for rasthis in range(len(rastgele_liste_değişik[rastgele_liste_değişik_numara])):
            if str(rastgele_liste_değişik[rastgele_liste_değişik_numara][rasthis]) == "0" or str(rastgele_liste_değişik[rastgele_liste_değişik_numara][rasthis]) == "1" or str(rastgele_liste_değişik[rastgele_liste_değişik_numara][rasthis]) == "2" or str(rastgele_liste_değişik[rastgele_liste_değişik_numara][rasthis]) == "3" or str(rastgele_liste_değişik[rastgele_liste_değişik_numara][rasthis]) == "4" or str(rastgele_liste_değişik[rastgele_liste_değişik_numara][rasthis]) == "5" or str(rastgele_liste_değişik[rastgele_liste_değişik_numara][rasthis]) == "6" or str(rastgele_liste_değişik[rastgele_liste_değişik_numara][rasthis]) == "7" or str(rastgele_liste_değişik[rastgele_liste_değişik_numara][rasthis]) == "8" or str(rastgele_liste_değişik[rastgele_liste_değişik_numara][rasthis]) == "9":
                rasthis_list.append("0"+str(rastgele_liste_değişik[rastgele_liste_değişik_numara][rasthis]))
            else:
                rastgele_liste_değişik[rastgele_liste_değişik_numara][rasthis] = str(rastgele_liste_değişik[rastgele_liste_değişik_numara][rasthis])
                rasthis_list.append(rastgele_liste_değişik[rastgele_liste_değişik_numara][rasthis][-2:])
        rastgele_liste_değişik_numara = rastgele_liste_değişik_numara+1
    rasthis_liste0 = rasthis_list[0:10]
    rasthis_liste1 = rasthis_list[10:60]
    rasthis_liste2 = rasthis_list[60:160]
    rasthis_liste3 = rasthis_list[160:1160]
    rasthis_liste4 = rasthis_list[1160:]

    frequency1 = []
    frequency2 = []
    frequency3 = []
    frequency4 = []
    frequency5 = []
    grafik_rakam_ufaklık = [0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9]

    while rastgele_rakam < 10:
        for frekans1 in range(len(rasthis_liste0)):
            rasthis_liste0[frekans1].count(str(rastgele_rakam))
            rastgele_contain = rastgele_contain+(rasthis_liste0[frekans1].count(str(rastgele_rakam)))

        rast_list_öge0.append(rastgele_contain)
        rastgele_rakam = rastgele_rakam + 1

    for icerik_list_öge1 in range(len(rast_list_öge0)):
        sonuc1 = rast_list_öge0[icerik_list_öge1]/(len(rasthis_liste0)*2)
        frequency1.append(sonuc1)

    while rastgele_rakam1 < 10:
        for frekans1 in range(len(rasthis_liste1)):
            rasthis_liste1[frekans1].count(str(rastgele_rakam1))
            rastgele_contain1 = rastgele_contain1+(rasthis_liste1[frekans1].count(str(rastgele_rakam1)))

        rast_list_öge1.append(rastgele_contain)
        rastgele_rakam1 = rastgele_rakam1+1

    for icerik_list_öge2 in range(len(rast_list_öge1)):
        sonuc2 = rast_list_öge1[icerik_list_öge2]/(len(rasthis_liste1)*2)
        frequency2.append(sonuc2)

    while rastgele_rakam2 < 10:
        for frekans2 in range(len(rasthis_liste2)):
            rasthis_liste2[frekans2].count(str(rastgele_rakam2))
            rastgele_contain2 = rastgele_contain2+(rasthis_liste2[frekans2].count(str(rastgele_rakam2)))

        rast_list_öge2.append(rastgele_contain)
        rastgele_rakam2 = rastgele_rakam2+1

    for icerik_list_öge3 in range(len(rast_list_öge2)):
        sonuc3 = rast_list_öge2[icerik_list_öge3]/(len(rasthis_liste2)*2)
        frequency3.append(sonuc3)

    while rastgele_rakam3 < 10:
        for frekans3 in range(len(rasthis_liste3)):
            rasthis_liste3[frekans3].count(str(rastgele_rakam3))
            rastgele_contain3 = rastgele_contain3+(rasthis_liste3[frekans3].count(str(rastgele_rakam3)))

        rast_list_öge3.append(rastgele_contain)
        rastgele_rakam3 = rastgele_rakam3+1

    for icerik_list_öge4 in range(len(rast_list_öge3)):
        sonuc4 = rast_list_öge3[icerik_list_öge4]/(len(rasthis_liste3)*2)
        frequency4.append(sonuc4)

    while rastgele_rakam4 < 10:
        for frekans4 in range(len(rasthis_liste4)):
            rasthis_liste4[frekans4].count(str(rastgele_rakam4))
            rastgele_contain4 = rastgele_contain4+(rasthis_liste4[frekans4].count(str(rastgele_rakam4)))

        rast_list_öge4.append(rastgele_contain)
        rastgele_rakam4 = rastgele_rakam4+1

    for icerik_list_öge5 in range(len(rast_list_öge4)):
        sonuc5 = rast_list_öge4[icerik_list_öge5]/(len(rasthis_liste4)*2)
        frequency5.append(sonuc5)

    mean1 =[1]
    plt4.plot(mean1*10, linestyle="--", color="g",label="Mean")
    x = [i for i in range(10)]
    y = [k for k in range(len(frequency1))]
    plt4.title("Histogram of least sign.digits-Sample:1")
    plt4.ylabel("Distribution")
    plt4.xlabel("Digits")
    plt4.plot(x, frequency1, color="r", label="Digits Dist")
    plt4.xticks(x ,grafik_rakam)
    plt4.yticks(y ,grafik_rakam_ufaklık)
    plt4.savefig("HistogramofSample1.pdf")
    plt4.show()

    mean2 =[0.1]
    plt5.plot(mean2*10, linestyle="--", color="g",label="Mean")
    plt5.title("Histogram of least sign.digits-Sample:2")
    plt5.ylabel("Distribution")
    plt5.xlabel("Digits")
    plt5.plot(grafik_rakam_ufaklık, frequency2, color="r", label="Digits Dist")
    plt5.savefig("HistogramofSample2.pdf")
    plt5.show()

    mean3 =[0.1]
    plt6.plot(mean3*10, linestyle="--", color="g",label="Mean")
    plt6.title("Histogram of least sign.digits-Sample:3")
    plt6.ylabel("Distribution")
    plt6.xlabel("Digits")
    plt6.plot(grafik_rakam_ufaklık, frequency3, color="r", label="Digits Dist")
    plt6.savefig("HistogramofSample3.pdf")
    plt6.show()

    mean4 = [0.1]
    plt7.plot(mean4*10, linestyle="--", color="g",label="Mean")
    plt7.title("Histogram of least sign.digits-Sample:4")
    plt7.ylabel("Distribution")
    plt7.xlabel("Digits")
    plt7.plot(grafik_rakam_ufaklık, frequency4, color="r", label="Digits Dist")
    plt7.savefig("HistogramofSample4.pdf")
    plt7.show()

    mean5 = [0.1]
    plt8.plot(mean5*10, linestyle="--", color="g",label="Mean")
    plt8.title("Histogram of least sign.digits-Sample:5")
    plt8.ylabel("Distribution")
    plt8.xlabel("Digits")
    plt8.plot(grafik_rakam_ufaklık, frequency5, color="r", label="Digits Dist")
    plt8.savefig("HistogramofSample5.pdf")
    plt8.show()
plotHistogramWithSample()


def calculateMSE(calculate_list1,calculate_list2):
    anssss = 0
    for calmse in range(len(calculate_list1)):
        sonucmse = (calculate_list1[calmse]-calculate_list2[calmse])*(calculate_list1[calmse]-calculate_list2[calmse])
        anssss += sonucmse
    return anssss


uzunluk_01 = len(frequency)
mse_uz = 0
def MSEcalculationof(frequency,uzunluk_01):
    uzunluk_001=[0.1 for uzunluk_0001 in range(uzunluk_01)]
    return calculateMSE(frequency,uzunluk_001)

print(MSEcalculationof(frequency,uzunluk_01))

#9step9


def compareMSEs():
    salla_list = []
    salla_list_204 = []
    salla_no = 0
    while salla_no < 10000:
        for ikiyüzdört in range(204):
            salla = random.randint(0, 100)
            salla_list_204.append(salla)
        salla_list.append(salla_list_204)
        salla_list_204 = []
        salla_no = salla_no+ 1


    for i in range(len(salla_list)):
        print(MSEcalculationof(frequency,uzunluk_01))



compareMSEs()


#yapmıyorum

