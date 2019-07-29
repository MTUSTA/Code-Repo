# ------------------------------------------------------------ #
# Student Name:Mehmet Taha USTA
# Student ID:21527472
# BBM103 Introduction to Programming Laboratory I, Fall 2016
# Assignment 3: Mission: Save the Earth
# ------------------------------------------------------------ #
import sys

dictionary1 = open(sys.argv[1], "r")
binarian_transmission1 = open(sys.argv[2], "r")
peace_message1 = open(sys.argv[3], "r")

my_dict1 = {}
my_dict2 = {}
satirlar = []


for binarian_lines in binarian_transmission1.readlines():
    binarian_lines = binarian_lines.rstrip("\n")
    satirlar.append(binarian_lines)


def create_dictionary():
    for dictionary_lines in dictionary1.readlines():
        liste = dictionary_lines.split(" ")
        my_dict1[liste[0]] = liste[1]
        my_dict2[liste[1]] = liste[0]


def binarian_to_english(satirlar):
    for binarian_lines in satirlar:
        if binarian_lines[0] != "#" and binarian_lines[0] != "+":
            binarian_lines = binarian_lines.split(" ")
            for i in binarian_lines:
                try:
                    print(my_dict1[i], end=" ")
                except KeyError as msg1:
                    print(str(msg1)[1:-1], end=" ")
            print("\n", end="")


def binary_to_decimal(satirlar):
    print("\nData about Binarian planet:")
    uzaklik = 0
    decimal_number_temperature = 0
    decimal_number_orbital_speed = 0
    for binarian_lines in satirlar:
        binarian_lines = binarian_lines.split(" ")
        if binarian_lines[0] == "+" and binarian_lines[2].isdigit():
            if binarian_lines[1] == my_dict2["temperature"]:

                power_temperature = 0
                binarian_lines[2] = int(binarian_lines[2])
                while binarian_lines[2] > 0:
                    decimal_number_temperature += 2 ** power_temperature * (binarian_lines[2] % 10)
                    binarian_lines[2] //= 10
                    power_temperature += 1
            if binarian_lines[1] == my_dict2["orbital-speed"]:
                power_orbital_speed = 0
                binarian_lines[2] = int(binarian_lines[2])
                while binarian_lines[2] > 0:
                    decimal_number_orbital_speed += 2 ** power_orbital_speed * (binarian_lines[2] % 10)
                    binarian_lines[2] //= 10
                    power_orbital_speed += 1
            if binarian_lines[1] == my_dict2["distance"]:
                decimal_number_distance = 0
                power_distance = 0
                binarian_lines[2] = int(binarian_lines[2])
                one_light_year = 9.4607*(10**12)
                while binarian_lines[2] > 0:
                    decimal_number_distance += 2 ** power_distance * (binarian_lines[2] % 10)
                    binarian_lines[2] //= 10
                    power_distance += 1
                    uzaklik = one_light_year*decimal_number_distance
    print("Distance from the Earth:", '%e'%float(uzaklik), "km")
    print("Planet temperature:", float(decimal_number_temperature), "degrees Celsius")
    print("Orbital speed:", float(decimal_number_orbital_speed), "km/s\n")



def english_to_binarian():

    for peace_message_lines in peace_message1.readlines():
        peace_message_lines = peace_message_lines.rstrip("\n")
        peace_message_lines = peace_message_lines.split(" ")
        for word in peace_message_lines:

            try:
                if len(word) > 0:
                    if word[-1] == "," or word[-1] == "!" or word[-1] == "?" or word[-1] == ".":
                        print(my_dict2[word[:-1].lower()], end=" ")
                    else:
                        print(my_dict2[word.lower()], end=" ")

            except KeyError:
                if len(word) > 0:
                    #word[:-1].isalpha()
                    if (word[-1] == "!" or word[-1] == "?" or word[-1] == "." or word[-1] == ",") and word.isalpha():
                        print(word[:-1], end=" ")
                    if word[-1] != "," and word[-1] != "." and word[-1] != "?" and word[-1] != "!" and word.isalpha():
                        print(word, end=" ")
                    if word[-1] != "," and word[-1] != "." and word[-1] != "?" and word[-1] != "!" and word.isdigit():
                        word = int(word)
                        binNum = 0
                        power = 0
                        while word > 0:
                            binNum += 10 ** power * (word % 2)
                            word //= 2
                            power += 1
                        print(int(binNum), end=" ")
        print("\n", end="")


create_dictionary()
binarian_to_english(satirlar)
binary_to_decimal(satirlar)
english_to_binarian()

dictionary1.close()
binarian_transmission1.close()
peace_message1.close()

