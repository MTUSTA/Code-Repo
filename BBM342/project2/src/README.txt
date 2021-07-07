You can run the Makefile using the make command to compile it

main.c = sserver
The line required to compile on the server side
gcc -o sserver main.c -lpthread

cmd arguments that must be entered to run the server file
./sserver -p 8080 -s 1 -ch1 short_intro.txt
./sserver -p 8080 -s 2 -ch1 short_intro.txt -ch2 short_intro1.txt
./sserver -p 8080 -s 3 -ch1 short_intro.txt -ch2 short_intro1.txt -ch3 short_intro2.txt

The data sent by the server is printed to the console for the client.

local adress = 0.0.0.0 
port depends on parameter

sserver file running on dev system
I couldn't test it with java file because there are no two sessions. I am getting results on my own computer.