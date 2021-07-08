import sys

a = float(sys.argv[1])
b = float(sys.argv[2])
c = float(sys.argv[3])

discriminant = b*b-4*a*c
if discriminant > 0:
    r1 = (-b+discriminant**(1/2))/(2*a)
    r1 = round(r1, 3)
    r2 = (-b-discriminant**(1/2))/(2*a)
    print("There are two solutions")
    print("Solutions:", "%.2f" % r1, " ", "%.2f" % r2)
if discriminant == 0:
    r1 = -b/(2*a)
    print("There is one solutions")
    print("Solution:", "%.2f" % r1)
if discriminant < 0:
    print("The solution is not real")