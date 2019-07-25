import matplotlib.pyplot as plot
import matplotlib.pyplot as plt
import csv

csvfile = open("weather_2012.csv", newline="")
csvreader = csv.reader(csvfile)


liste1 = []
grades = []

for sentence in csvreader:
    liste1.append(sentence)
liste2 = liste1[1:]

max_number1 = float(liste2[0][1])

print("Month       ", "Max Temperature")

for max_bulma01 in range(1, len(liste2)):
    if liste2[max_bulma01][0][5:7] == "01" and float(liste2[max_bulma01][1]) > max_number1:
        max_number1 = float(liste2[max_bulma01][1])
grades.append(max_number1)
print("January     ", max_number1)

max_number2 = float(liste2[0][1])
for max_bulma02 in range(1, len(liste2)):
    if liste2[max_bulma02][0][5:7] == "02" and float(liste2[max_bulma02][1]) > max_number2:
        max_number2 = float(liste2[max_bulma02][1])
grades.append(max_number2)
print("February    ", max_number2)

max_number3 = float(liste2[0][1])
for max_bulma03 in range(1, len(liste2)):
    if liste2[max_bulma03][0][5:7] == "03" and float(liste2[max_bulma03][1]) > max_number3:
        max_number3 = float(liste2[max_bulma03][1])
grades.append(max_number3)
print("March       ", max_number3)

max_number4 = float(liste2[0][1])
for max_bulma04 in range(1, len(liste2)):
    if liste2[max_bulma04][0][5:7] == "04" and float(liste2[max_bulma04][1]) > max_number4:
        max_number4 = float(liste2[max_bulma04][1])
grades.append(max_number4)
print("April       ", max_number4)

max_number5 = float(liste2[0][1])
for max_bulma05 in range(1, len(liste2)):
    if liste2[max_bulma05][0][5:7] == "05" and float(liste2[max_bulma05][1]) > max_number5:
        max_number5 = float(liste2[max_bulma05][1])
grades.append(max_number5)
print("May         ", max_number5)

max_number6 = float(liste2[0][1])
for max_bulma06 in range(1, len(liste2)):
    if liste2[max_bulma06][0][5:7] == "06" and float(liste2[max_bulma06][1]) > max_number6:
        max_number6 = float(liste2[max_bulma06][1])
grades.append(max_number6)
print("June        ", max_number6)

max_number7 = float(liste2[0][1])
for max_bulma07 in range(1, len(liste2)):
    if liste2[max_bulma07][0][5:7] == "07" and float(liste2[max_bulma07][1]) > max_number7:
        max_number7 = float(liste2[max_bulma07][1])
grades.append(max_number7)
print("July        ", max_number7)

max_number8 = float(liste2[0][1])
for max_bulma08 in range(1, len(liste2)):
    if liste2[max_bulma08][0][5:7] == "08" and float(liste2[max_bulma08][1]) > max_number8:
        max_number8 = float(liste2[max_bulma08][1])
grades.append(max_number8)
print("August      ", max_number8)

max_number9 = float(liste2[0][1])
for max_bulma09 in range(1, len(liste2)):
    if liste2[max_bulma09][0][5:7] == "09" and float(liste2[max_bulma09][1]) > max_number9:
        max_number9 = float(liste2[max_bulma09][1])
grades.append(max_number9)
print("September   ", max_number9)

max_number10 = float(liste2[0][2])
for max_bulma10 in range(1, len(liste2)):
    if liste2[max_bulma10][0][5:7] == "10" and float(liste2[max_bulma10][1]) > max_number10:
        max_number10 = float(liste2[max_bulma10][1])
grades.append(max_number10)
print("October     ", max_number10)

max_number11 = float(liste2[0][2])
for max_bulma11 in range(1, len(liste2)):
    if liste2[max_bulma11][0][5:7] == "11" and float(liste2[max_bulma11][1]) > max_number11:
        max_number11 = float(liste2[max_bulma11][1])
grades.append(max_number11)
print("November    ", max_number11)

max_number12 = float(liste2[0][2])
for max_bulma12 in range(1, len(liste2)):
    if liste2[max_bulma12][0][5:7] == "12" and float(liste2[max_bulma12][1]) > max_number12:
        max_number12 = float(liste2[max_bulma12][1])
grades.append(max_number12)
print("December    ", max_number12, "\n")

Aylar = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"]
x_pos1 = [x for x in range(len(Aylar))]
plot.bar(x_pos1, grades, align="center", color="g", alpha=0.8)
plot.xticks(x_pos1, Aylar, rotation=45)
plot.title("Max Temperature For Months")

plot.savefig("maxtemp.png", bbox_inches='tight')
plot.show()



print("Weather                                   ", "Mean Temperature")

liste3 = []

for b in range(len(liste2)):
    id = liste2[b][-1]
    if id not in [x for x in liste3]:
        liste3.append(id)

liste5 = []
d = 0
e = 0

while d < len(liste3):
    c = 0
    b = 0
    for a in range(len(liste2)):
        if liste2[a][-1] == liste3[e]:
            c = c + float(liste2[a][1])
            b = b+1
    liste5.append(liste3[e]+"!"+str((c/b)))
    d = d+1
    e = e+1

liste3.sort()
liste5.sort()
liste6 = []
liste7 = []
for y in range(len(liste5)):
    split1 = liste5[y].split("!")
    liste6.append(float(split1[1]))
    liste7.append((split1[0]))
for j in range(len(liste5)):
    print("{:<40}\t{:<40}".format(liste7[j], liste6[j]))


x_pos2 = [r for r in range(len(liste3))]
plt.bar(x_pos2, liste6, align="center", color="b", alpha=0.8)
plt.xticks(x_pos2, liste3, rotation=45)
plt.title("Mean Temperature For Months")

plt.savefig("meantemp.png", bbox_inches='tight')
plt.show()

csvfile.close()
