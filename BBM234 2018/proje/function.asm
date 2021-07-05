
main:
addi $sp,$sp,-8#make space on stack
addi $s0,$s0,5#a
addi $s1,$s1,3#b
addi $s2,$s2,0#result=0

bne $s0,$s1,else# if(a==b)
add $t1,$s0,$s1#a+b
addi $s3,$zero,8#define 8
mul $s2, $s3,$t1#result=8(a+b)
jal exit

else:
jal compare
jal exit

compare:
add $a0,$zero,$s0#Function Arguments a
add $a1,$zero,$s1#Function Arguments b
bge $a0,$a1,else2 #if(a<b)
sw $ra,0($sp)
jal punish
lw $ra,0($sp)
jr $ra

else2:
sw $ra,4($sp)
jal award
lw $ra,4($sp)
jr $ra

punish:
add $a0,$zero,$a0#Function Arguments a
add $a1,$zero,$a1#Function Arguments b
sub $t2,$a0,$a1#a-b
addi $t3,$zero,2 #define 2 in punish
mul $s2,$t3,$t2#result =(a-b)*2
jr $ra#return adress

award:
add $a0,$zero,$a0#Function Arguments a
add $a1,$zero,$a1#Function Arguments b
add $t4,$a0,$a1#a+b
addi $t5,$zero,4 #define 4 in punish
mul $s2,$t4,$t5#result =(a+b)*4
jr $ra#return adress

exit:#finish
addi $sp,$sp,+8#deallocated

