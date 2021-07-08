import sys
import string

dict_hall = {}
ABC = {}
reverse_ABC = {}
yer = 0
for letter in string.ascii_uppercase:
    ABC[yer] = letter
    reverse_ABC[letter] = yer
    yer += 1


def Check_Sell_Ticket(find, placement, place, element, sell_input, output):
    #ex:B3
    if len(placement) == 1:
        if len(dict_hall[find][0]) < int(placement[0]):
            print("Error: The hall '{}' has less column than the specified index {}!".format(find, element),file=output)
            return False
        if dict_hall[find][place][int(placement[0])] != "X":
            print("Warning: The seats {} cannot be sold to {} due some of them have already been sold".format(element, sell_input), file=output)
            return False
    #ex:C2-10
    elif len(placement) == 2:
        if len(dict_hall[find][0]) < int(placement[1]):
            print("Error: The hall '{}' has less column than the specified index {}!".format(find, element), file=output)
            return False
        yer_kont = 0
        for i in range(int(placement[0]), int(placement[1])):
            if dict_hall[find][place][i] != "X":
                yer_kont += 1
        if yer_kont != 0:
            print("Warning: The seats {} cannot be sold to {} due some of them have already been sold".format(element, sell_input), file=output)
            return False
    return True


def Check_Cancel_Ticket(find, placement, place, element, output):
    if len(placement) == 1:
        if len(dict_hall[find][0]) < int(placement[0]):
            print("Error: The hall '{}' has less column than the specified index {}!".format(find, element), file=output)
            return False
        if dict_hall[find][place][int(placement[0])] == "X":
            print("Error: The seat {} at {} has already been free! Nothing to cancel".format(element, find), file=output)
            return False
    elif len(placement) == 2:
        if len(dict_hall[find][0]) < int(placement[1]):
            print("Error: The hall '{}' has less column than the specified index {}!".format(find, element), file=output)
            return False
    return True


def createhall(hall, rowxcolumns, output):
    #checking hall, if it exists, funtion will give error
    give_error = 0
    for check1 in dict_hall:
        if check1 == hall:
            give_error = 1
            print("Warning: Cannot create the hall for the second time. The cinema has already {}".format(hall), file=output)
            break
    #creates hall(2d) with list comprehension
    if give_error == 0:
        #split word for set row and column
        rowxcolumns = rowxcolumns.split("x")
        #here is list comprehension
        Matrix = [["X" for x in range(int(rowxcolumns[0]))] for y in range(int(rowxcolumns[1]))]
        # data hide with dictionary
        dict_hall[hall] = Matrix
        print("Hall '{}' having {} seats has been created".format(hall, int(rowxcolumns[0])*int(rowxcolumns[1])), file=output)


def SELLTİCKET(sell_input, output):
    #finding data hall
    for find in dict_hall:
        if find == sell_input[2]:
            for element in sell_input[3:]:
                #obtain number
                placement = element[1:].split("-")
                #finding row
                place = reverse_ABC[element[0]] * (-1) - 1
                #ex:B3
                if len(placement) == 1 and Check_Sell_Ticket(find, placement,place,element,sell_input[0], output) == True:
                    dict_hall[find][place][int(placement[0])] = sell_input[1][0].upper()
                    print("Success: {} has bought {} at {}".format(sell_input[0], element, find), file=output)
                #ex:C9-12
                elif len(placement) == 2 and Check_Sell_Ticket(find, placement,place,element,sell_input[0], output) == True:
                    for deneme in range(int(placement[0]), int(placement[1])):
                        dict_hall[find][place][deneme] = sell_input[1][0].upper()
                    print("Success: {} has bought {} at {}".format(sell_input[0], element, find), file=output)
            break


def CANCELTİCKET(cancel_input, output):
    #finding data hall
    for find in dict_hall:
        if find == cancel_input[0]:
            for element in cancel_input[1:]:
                # obtain number
                placement = element[1:].split("-")
                # finding row
                place = reverse_ABC[element[0]] * (-1) - 1
                # ex:B3
                if len(placement) == 1 and Check_Cancel_Ticket(find, placement, place, element, output) == True:
                    place = reverse_ABC[element[0]] * (-1) - 1
                    dict_hall[find][place][int(placement[0])] = "X"
                    print("Success: The seat {} at {} has been canceled and now ready to sell again".format(cancel_input[1], find), file=output)
                # ex:C9-12
                elif len(placement) == 2 and Check_Cancel_Ticket(find, placement, place, element, output) == True:
                    for deneme in range(int(placement[0]), int(placement[1])):
                        place = reverse_ABC[element[0]] * (-1) - 1
                        if dict_hall[find][place][deneme] != "X":
                            dict_hall[find][place][deneme] = "X"
                            print("Success: The seat {} at {} has been canceled and now ready to sell again".format(cancel_input[1], find), file=output)
                        elif dict_hall[find][place][deneme] == "X":
                            print("Error: The seat {}{} at {} has already been free! Nothing to cancel".format(cancel_input[1][0], deneme, cancel_input[0]), file=output)
            break


def BALANCE(balance_input, output):
    #students=5 ,full=10
    for i in balance_input:
        print("Hall report of '{}'".format(i), file=output)
        print("-------------------------", file=output)
        Sum_of_student = 0
        Sum_of_full_fares = 0
        for j in dict_hall:
            if i == j:
                for k in dict_hall[j]:
                    for L in k:
                        if L == "S":
                            Sum_of_student += 5
                        if L == "F":
                            Sum_of_full_fares += 10
                print("Sum of students = {}, Sum of full fares = {}, Overall = {}".format(Sum_of_student, Sum_of_full_fares, Sum_of_student+Sum_of_full_fares), file=output)
                break

#prints 2d array in output
def SHOWHALL(showhall, output):
    print("Printing hall layout of {}".format(showhall), file=output)
    for find in dict_hall:
        if find == showhall:
            row_num = len(dict_hall[find])-1
            for row in dict_hall[find]:
                print(ABC[row_num], end=" ", file=output)
                for rows in row:
                    print(rows, end="  ", file=output)
                print("", file=output)
                row_num -= 1
            print("  ", end="", file=output)
            for sutun in range(len(dict_hall[find])):
                if sutun < 9:
                    print(sutun, end="  ", file=output)
                elif sutun >= 9:
                    print(sutun, end=" ", file=output)
            print("", file=output)
            break

def Main():
    #open input file
    input = open(sys.argv[1], "r")
    #create outputfile
    output = open("out.txt", "w")
    #reads input file line by line
    for i in input.readlines():
        #delete newline character
        i = i.rstrip("\n")
        #split lines
        i = i.split(" ")
        # if input line is empty, if statement will discart it
        if len(i) != 1:
            if i[0] == "CREATEHALL":
                if len(i) == 3:
                    createhall(i[1], i[2], output)
                elif len(i) < 3:
                    print("Error: Not enough parameters for creating a hall!", file=output)
                elif len(i) > 3:
                    print("Error: Too much parameters for creating a hall!", file=output)
            elif i[0] == "SELLTICKET":
                SELLTİCKET(i[1:], output)
            elif i[0] == "CANCELTICKET":
                CANCELTİCKET(i[1:], output)
            elif i[0] == "BALANCE":
                BALANCE(i[1:], output)
            elif i[0] == "SHOWHALL":
                SHOWHALL(i[1], output)
    input.close()
    output.close()

Main()