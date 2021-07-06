import sys

setA = []
setB = []

for i in sys.argv[1].split(","):
    if setA.count(i) == 0:
        setA.append(i)

for i in sys.argv[2].split(","):
    if setB.count(i) == 0:
        setB.append(i)

Intersection = []
difference = []

check = 0
for i in setA:
    for j in setB:
        if i == j:
            check += 1
            Intersection.append(i)
            break
    if check == 0:
        difference.append(i)
    check = 0

union = []
union.extend(setA)

for i in setB:
    if union.count(i) == 0:
        union.append(i)


print("Set A: ", setA)
print("Set B: ", setB)
print("Intersection of A and B:", Intersection)
print("Union of A and B:", union)
print("Difference of A and B:", difference)