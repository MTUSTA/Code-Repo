import sys
print()
start = 1

if start == 1 and len(sys.argv) != 4:
    start = 0
    print("Please run with 3 arguments")

if start == 1 and len(sys.argv) == 4:
    for i in sys.argv[1:4]:
        if i.isalpha() == False:
            print("Argument ",i , "is not a word. All arguments should be word")
            start = 0

if start == 1:
    if len(sys.argv[1]) == len(sys.argv[2]) or len(sys.argv[1]) == len(sys.argv[3]) or len(sys.argv[2]) == len(sys.argv[3]):
        start = 0
        print("Arguments should be a different length")

if start == 1:

    characterlist = []
    for i in sys.argv[1:4]:
        for j in i:
            characterlist.append(j)
    characterlist.sort()
    print("Find longest word using letters given below\n")
    a = 0
    while a < len(characterlist):
        if a == len(characterlist) - 1:
            print("'{}'".format(characterlist[a]), end="\n\n")
            a += 1
        else:
            print("'{}'".format(characterlist[a]), end=",")
            a += 1

    x = input("Guess a longest word: ")
    finish = 0
    for k in sys.argv[1:4]:
        if x == k:
            finish = 1
            break

    if finish == 1:
        puan = 10
        print("\nYou found a word from list\n")
        for k in sys.argv[1:4]:
            if len(x) > len(k):
                puan += 20
        print("You won", puan, "points !\n")

    else:
        print("\nThe word you guessed is not in the list\n")
        print("You lost !")