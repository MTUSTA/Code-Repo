.data
A: .word 2,2,6,4
.text

main:
la $t1, A #define array
la $s1, 0 # i=0
la $s2, 0 # diff=0
li $s4,3 # limit of for loop

Loop:
	bge  $s1, $s4, Exit #at 3 we go to Exit, defined below
	lw $t2,0($t1)#A[i]
	add $t5,$t1,4#increase address value of A
	lw $t3,($t5)#A[i+1]
	sub $t4,$t3,$t2#A[i+1]-A[i]
	ble $t4,$zero,else#if(diff>0)
	
	li $t6,0
mul1:	beq $t6,5,store#5*A[i] with for loop 0<5
	add $t7,$t7,$t2
	addi $t6,$t6,1#+t6++
	j mul1
store:	sw $t7,($t1)
	sub $t7,$t7,$t7#$t7 equal zero
	j cont

cont:
	add $t1,$t1,4#increase adress array
	add $s1,$s1,1 #i++
	j Loop #jumps back to the top of loop
else:
	li $t0,0
mul2:	beq  $t0,-5,store2#-5*A[i] with for loop 0<5
	sub $t8,$t8,$t2
	addi $t0,$t0,-1#$t0++
	j mul2
store2:	sw $t8,4($t1)#save result A[i+1]
	sub $t8,$t8,$t8#$t8 equal zero
	j cont
Exit:#finish
