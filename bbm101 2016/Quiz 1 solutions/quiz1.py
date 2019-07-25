import math
output=open("newFile.txt", "w")
for i in range(10)
    number=int(input("please enter a number"))
    result=0
    numbercounter=0
    if number %2 !=0:
        result=result+number
        numbercounter=numbercounter+1
avg=result/numbercounter
out="{}+{}={}"
print(out.format(result,numbercounter,result/numbercounter))
print(out.format(result,numbercounter,result/numbercounter),file=output)

output.close()

Düzenlenmiş benimki
import math
output=open("newFile.txt", "w")
result=0
numbercounter=0
for i in range(10):
    number=int(input("please enter a number"))
    if number %2 !=0:
        result=result+number
        numbercounter=numbercounter+1
avg= result/numbercounter
out="{}+{}={}"
print(out.format(result,numbercounter,result/numbercounter))
print(out.format(result,numbercounter,result/numbercounter),file=output)

output.close()

