import ast

maze_input = input("Please enter feeding map as a list:\n")

# convert input to matrix
maze_input = ast.literal_eval(maze_input)
# rabbit_coordinat = [x,y], -1 means undefined
rabbit_coordinat = [-1, -1]

# find initial rabbit coordinates
for i in range(len(maze_input)):
    if '*' in maze_input[i]:
        rabbit_coordinat = [i, maze_input[i].index('*')]

direction_list = input("Please enter direction of movements as a list:\n")
# convert input to list
direction_list = ast.literal_eval(direction_list)

# User Score
score = 0

# Condition: Rabbit dies and cannot move if it eats poison
poisened = 0
# check coordinates -> if not valid return false,else return true
def checker(rabbit_x, rabbit_y):
    row = len(maze_input)
    col = len(maze_input[0])
    # size checking
    if (rabbit_x == -1) or (rabbit_y == -1) or (row == rabbit_x) or (col == rabbit_y):
        return False
    # Wall checking
    if maze_input[rabbit_x][rabbit_y] == "W":
        return False
    if maze_input[rabbit_x][rabbit_y] == "P":
        global poisened
        poisened = 1
    return True


# helper function to print maze
def printer():
    for i in maze_input:
        for j in i:
            print(j, end=" ")
        print()



#  C=Carrot, A=Apple, P=Poison, M=Meat
def increase_point(x, y):
    global score
    # Rabbit earns 10 points if rabbit eats carrot
    if maze_input[x][y] == "C":
        score += 10
    # Rabbit earns 5 points if rabbit eats apple
    elif maze_input[x][y] == "A":
        score += 5
    # Rabbit loses 5 points if rabbit eats meats
    elif maze_input[x][y] == "M":
        score -= 5


# *=Rabbit, C=Carrot, A=Apple, P=Poison, M=Meat,W=Wall and X=Empty
# helper function
print("Your board is:")
printer()
for i in direction_list:
    if poisened == 0 :
        if i == "U" and checker(rabbit_coordinat[0] - 1, rabbit_coordinat[1]):
            # swapping area
            maze_input[rabbit_coordinat[0]][rabbit_coordinat[1]] = "X"
            increase_point(rabbit_coordinat[0] - 1, rabbit_coordinat[1])
            maze_input[rabbit_coordinat[0] - 1][rabbit_coordinat[1]] = "*"
            # x = x-1
            rabbit_coordinat[0] -= 1

        elif i == "L" and checker(rabbit_coordinat[0], rabbit_coordinat[1] - 1):
            # swapping area
            maze_input[rabbit_coordinat[0]][rabbit_coordinat[1]] = "X"
            increase_point(rabbit_coordinat[0], rabbit_coordinat[1] - 1)
            maze_input[rabbit_coordinat[0]][rabbit_coordinat[1] - 1] = "*"
            # y = y-1
            rabbit_coordinat[1] -= 1

        elif i == "R" and checker(rabbit_coordinat[0], rabbit_coordinat[1] + 1):
            # swapping area
            maze_input[rabbit_coordinat[0]][rabbit_coordinat[1]] = "X"
            # increase score
            increase_point(rabbit_coordinat[0], rabbit_coordinat[1] + 1)
            maze_input[rabbit_coordinat[0]][rabbit_coordinat[1] + 1] = "*"
            # y = y+1
            rabbit_coordinat[1] += 1

        elif i == "D" and checker(rabbit_coordinat[0] + 1, rabbit_coordinat[1]):
            # swapping area
            maze_input[rabbit_coordinat[0]][rabbit_coordinat[1]] = "X"
            # increase score
            increase_point(rabbit_coordinat[0] + 1, rabbit_coordinat[1])
            maze_input[rabbit_coordinat[0] + 1][rabbit_coordinat[1]] = "*"
            # x = x+1
            rabbit_coordinat[0] += 1
        # (Condition: Rabbit dies and cannot move if it eats poison) -> finisher
    elif poisened:
        break
print("\nYour output should be like this:")
printer()

print("Your score is:", score)
