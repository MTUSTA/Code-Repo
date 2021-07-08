import sys

input = open(sys.argv[1], "r")
output = open(sys.argv[2], "w")
list1 = []
for i in input:
    i = i.rstrip("\n")
    i = i.split("\t")
    i[0] = int(i[0])
    i[1] = int(i[1])
    list1.append(i)
list1.sort()

Message = list1[0][0]
print("Message 1", file=output)
yer = 2
for k in list1:
    if Message != k[0]:
        print("Message", yer, file=output)
        yer += 1
        Message = k[0]
    print(k[0], k[1], k[2], file=output)

input.close()
output.close()