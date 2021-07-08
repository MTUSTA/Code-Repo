import sys

list = []

even_number_rate = 0
split1 = sys.argv[1].split(",")
for i in split1:
    i = int(i)
    if i > 0:
        even_number_rate += i
        if i % 2 == 0:
            list.append(i)

print("Even Numbers:", list)
sum = 0
for i in list:
    sum += i
print("Sum of Even Numbers:", sum)
print("Even Number Rate:", round(sum/even_number_rate, 3))