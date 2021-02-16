import sys

infile = open(sys.argv[1], "r")
listIntegers = infile.readline().split(";")


def avgfirstthreedigit(listint):
    empty_list = []
    reverse_list = []

    for i in listint:
        average = (int(i[0])+int(i[1])+int(i[2]))/3
        empty_list.append(int(average))
    #reverse list witout reverse function
    while empty_list:
        reverse_list.append(empty_list.pop())

    return reverse_list


output = avgfirstthreedigit(listIntegers)
print(output)
infile.close()
