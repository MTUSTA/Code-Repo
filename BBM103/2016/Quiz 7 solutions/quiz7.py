import sys
girdi1 = open(sys.argv[1], "r")
#çarp ayır
f = 2
def primeFact (i, f):
    if i < f:
        return []
    if i % f == 0:
        return [f] + primeFact (i / f, 2)
    return primeFact (i, f + 1)

for a in girdi1.readlines():
    a = a.rstrip("\n")
    splitlenmiş_a = a.split(",")
    for b in splitlenmiş_a:
        print(b, "=", "{}".format(primeFact(int(b), f)))