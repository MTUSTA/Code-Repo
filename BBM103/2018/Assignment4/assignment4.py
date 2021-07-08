import sys
# 2d matrix
input_list = []


# function to prints 2d matrix
def print_matrix():
    for k in range(len(input_list)):
        for l in input_list[k]:
            print(l, end=" ")
        print()


# function to reads input.txt and fills 2d matrix
def read_file():
    input1 = open(sys.argv[1], "r")
    for i in input1.readlines():
        i = i.rstrip("\n")
        i = i.split(" ")
        # change string to integer
        for j in range(len(i)):
            i[j] = int(i[j])
        input_list.append(i)
    # close input file
    input1.close()


# calculate fibonacci with recursion method
def fib(n):
    if n < 2:
        return n
    return fib(n-2) + fib(n-1)


# checking coordinates for out of matrix
def check_matrix(row, column):
    if row < 0 or row >= len(input_list) or column < 0 or column >= len(input_list[row]):
        return False
    return True


del_cord_ls = []


# This game is a collect game where of each turn you should collect two or more numbers based on spatial relationship.
def find_and_delete(row, column):
    # first coordinates
    del_cord_ls.append([row, column])
    # for fib_num*fib(number)
    fib_num = 1
    durum = 0
    while durum < 1:
        # for element in del_cord_ls:
        i = len(del_cord_ls)-1
        while i > -1:
            # Every cell has four neighbors left, right, above and below.
            # left
            if check_matrix(del_cord_ls[i][0], del_cord_ls[i][1] - 1) == True and input_list[del_cord_ls[i][0]][del_cord_ls[i][1]] == input_list[del_cord_ls[i][0]][del_cord_ls[i][1] - 1] and del_cord_ls.__contains__([del_cord_ls[i][0], del_cord_ls[i][1] - 1]) == False:
                del_cord_ls.append([del_cord_ls[i][0], del_cord_ls[i][1] - 1])
                fib_num += 1
            # right
            if check_matrix(del_cord_ls[i][0], del_cord_ls[i][1] + 1) == True and input_list[del_cord_ls[i][0]][del_cord_ls[i][1]] == input_list[del_cord_ls[i][0]][del_cord_ls[i][1] + 1] and del_cord_ls.__contains__([del_cord_ls[i][0], del_cord_ls[i][1] + 1]) == False:
                del_cord_ls.append([del_cord_ls[i][0], del_cord_ls[i][1] + 1])
                fib_num += 1
            # above
            if check_matrix(del_cord_ls[i][0] - 1, del_cord_ls[i][1]) == True and input_list[del_cord_ls[i][0]][del_cord_ls[i][1]] == input_list[del_cord_ls[i][0] - 1][del_cord_ls[i][1]] and del_cord_ls.__contains__([del_cord_ls[i][0] - 1, del_cord_ls[i][1]]) == False:
                del_cord_ls.append([del_cord_ls[i][0]-1, del_cord_ls[i][1]])
                fib_num += 1
            # below
            if check_matrix(del_cord_ls[i][0] + 1, del_cord_ls[i][1]) == True and input_list[del_cord_ls[i][0]][del_cord_ls[i][1]] == input_list[del_cord_ls[i][0] + 1][del_cord_ls[i][1]] and del_cord_ls.__contains__([del_cord_ls[i][0] + 1, del_cord_ls[i][1]]) == False:
                del_cord_ls.append([del_cord_ls[i][0] + 1, del_cord_ls[i][1]])
                fib_num += 1
            if fib_num != 1:
                input_list[del_cord_ls[i][0]][del_cord_ls[i][1]] = " "
            del_cord_ls.remove(del_cord_ls[i])
            # decrease i
            i -= 1
        if len(del_cord_ls) == 0:
            durum = 1
    # return true to delete_column()and swap()
    return fib_num


# checking number and game terminating function
def gameover():
    duplicate = 0
    for k in range(len(input_list)):
        for l in range(len(input_list[k])):
            # Every cell has four neighbors left, right, above and below.
            # left
            if check_matrix(k, l-1) == True and input_list[k][l] != " " and input_list[k][l] == input_list[k][l-1]:
                    duplicate += 1
            # right
            if check_matrix(k, l+1) == True and input_list[k][l] != " " and input_list[k][l] == input_list[k][l+1]:
                    duplicate += 1
            # above
            if check_matrix(k-1, l) == True and input_list[k][l] != " " and input_list[k][l] == input_list[k-1][l]:
                    duplicate += 1
            # below
            if check_matrix(k+1, l) == True and input_list[k][l] != " " and input_list[k][l] == input_list[k+1][l]:
                    duplicate += 1
    return duplicate


# if a column disappears completely,
# all the cells which are at the right side of that blank column should move left to fill the empty space.
def delete_column_and_row():
    delete_row = 0
    # row
    for row in range(len(input_list)-1, -1, -1):
        # column
        for element in input_list[row]:
            if element != " ":
                delete_row += 1
        # delete row
        if delete_row == 0:
            del input_list[row]
        # reset variable
        delete_row = 0

    delete_col = 0
    # column
    for i in range(len(input_list[0])-1, -1, -1):
        # row
        for j in range(len(input_list)):
            if input_list[j][i] == " ":
                delete_col += 1
        if delete_col == len(input_list):
            # delete column
            for k in input_list:
                del k[i]
        # reset variable
        delete_col = 0


def swap():
    # row
    for i in range(len(input_list)-2, -1, -1):
        # column
        for j in range(len(input_list[0])):
            # swapp cell
            if input_list[i][j] != " " and input_list[i+1][j] == " ":
                row = i
                while row < len(input_list)-1 and input_list[row+1][j] == " ":
                    temp = input_list[row][j]
                    input_list[row][j] = input_list[row+1][j]
                    input_list[row + 1][j] = temp
                    row += 1


# program starts
def main():
    read_file()
    score = 0
    i = 0
    while i < 1:
        print_matrix()
        print("\nYour score is:", score, "\n")
        # no duplicate, finish game
        finish = gameover()
        if finish == 0:
            i = 1
            print("Game over", end="")
        else:
            chk = 0
            while chk < 1:
                user = input("Please enter a row and column number: ")
                print()
                # split user input
                yerler = user.split(" ")
                # converting integer
                yerler[0] = int(yerler[0])
                yerler[1] = int(yerler[1])
                if check_matrix(yerler[0]-1, yerler[1]-1) == False or input_list[yerler[0]-1][yerler[1]-1] == " ":
                    print("Please enter a correct size!\n")
                else:
                    chk = 1
            # declare integer
            area_number = input_list[yerler[0]-1][yerler[1]-1]
            # Note that the selected cell must include at least one neighboring cell with the same value
            result = find_and_delete(yerler[0]-1, yerler[1]-1)
            if result != 1:
                # swap column
                swap()
                # if column or row is empty, this function will delete this column
                delete_column_and_row()
                score += fib(result)*area_number
main()