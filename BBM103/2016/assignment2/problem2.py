import sys

inputfile = open(sys.argv[1], "r")
print("The total cost of each house :")


def calculatetotalcost(list1):
    result_list = []
    for i in list1:
        result_total_cost = int(i[0]) + int(i[1]) * 10 + int(i[0]) * float(i[2]) * 10
        result_list.append(result_total_cost)
    return result_list


def displaycosts(list1):
    display_list = calculatetotalcost(list1)
    total = 0
    for item in display_list:
        print(total + 1, ". house's total cost is ", item)


def selectbestbuy(list1):
    display_list = calculatetotalcost(list1)
    min_number = display_list[0]
    for k in range(1, len(display_list)):
        if display_list[k] < min_number:
            min_number = display_list[k]
    number = int(display_list.index(min_number))+1

    print("You should select ", str(number) + ".", "house whose total cost is ", min_number, ".")


bulletlist = []
for i in inputfile.readlines():
    i = i.rstrip("\n")
    bulletlist.append(i.split(' '))

displaycosts(bulletlist)
selectbestbuy(bulletlist)
inputfile.close()
