a = 1
b = 1
c = 1


def cal(x, y, z):
    print(x,y,z,)
    if (a == x and b == y and c == z):
        return 1
    elif (a < x or b < y or c < z):
        return 0
    return cal(x + 1, y, z) + cal(x, y + 1, z) + cal(x, y, z + 1)


print(cal(0, 0, 0))