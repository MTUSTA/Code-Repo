makeYacc: project.l project.y
	lex project.l
	yacc project.y
	gcc -o project_result lex.yy.c -lfl -w
	./project_result < test.txt
