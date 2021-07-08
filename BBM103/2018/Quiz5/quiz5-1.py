import sys

girdi = int(sys.argv[1])


def diamond(dongu, durum):
    if durum == 0:
        for k in range(girdi-1-dongu):
            print("", end=" ")
        for i in range(dongu*2+1):
            print("*", end="")
        if girdi != 1:
            print()
        if dongu == girdi-1:
            durum += 1
            dongu -= 1
        dongu += 1
    if durum == 1:
        dongu -= 1
        if dongu != -1:
            for j in range(girdi-1-dongu):
                print("", end=" ")
        for l in range(dongu*2+1):
            print("*", end="")
        print()
        if dongu <= 0:
            durum += 1
    if durum != 2:
        diamond(dongu, durum)

diamond(0,0)