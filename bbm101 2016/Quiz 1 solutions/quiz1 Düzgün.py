print("Quiz: Average of odd numbers")

#Open a new file for output
output=open("outputFile.txt","w")

total = 0
count = 0

for i in range(10):
	print("Process ", i+1)
	number = int(input("Please enter a number: "))
	if number%2 != 0:
		total = total + number
		count = count + 1

average = total / count				#Calculate the average

out="{} is the average of the odd numbers"
print(out)
print(out.format(average))			#print to the screen
print(out.format(sum),file=output)	#print to the file

#Close the file
output.close()