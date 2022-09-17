#	Yahtzee.s
#
###########################
#	Gameplay
#__________________________
# print the gameplay board
#
# 	loop
# roll the dice
# compute the dice
# update the board
# print the gameplay board
# print the dice roll
# prompt for which dice to re-roll
# 
# #########################
# s0 = random die value
# s1 = dice array
# s2 = score array
# s3 = temp word during updating score
# s4 = final score
# s5 = gameplay loop counter
# t0 = loop variable
# t1 = array index for printing
# t2 = loaded dice from array to check during scoring
# t3 = count var for scoring
# t4 = category score
# t6 = session high-score
# t7 = category prompt
# t9 = user input for re-rolling
#
#

	.data
dice_array1:		.word 0,0,0,0,0
score_array:		.word 0,0,0,0,0,0,0,0,0,0,0,0,0,0

upper:		.asciiz	"\t\t\t\t\t\t\t______________UPPER______________"
lower:		.asciiz "\t\t\t\t\t\t\t______________LOWER______________"
underlines:	.asciiz "=========================================================================================================================================="
few_line:	.asciiz " _____________"
fewer_line:	.asciiz " ___________"
new:		.asciiz "\n"
tab:		.asciiz "\t"
bars:		.asciiz "|"
spaces:		.asciiz "      "
a_spaces:	.asciiz " "

prompt_hold:		.asciiz "Which die would you like to re-roll? Enter 0 to quit.\t"
prompt_category:	.asciiz "Which category would you like to put your score into?\t"
score_tally:		.asciiz "Your Final Score is:\t"
yes_no:			.asciiz "Do you actually have this combination, or are you dumping in a 0? 1 for yes // 0 for no. \t"
play_again:		.asciiz "Would you like to play again? 1 for yes, 0 to end game.\t"
highscore:		.asciiz "The session high-score is:\t"
new_high:		.asciiz "New session highscore!!!"
not_new:		.asciiz "You did not beat the session high-score. Try again"


ones:		.asciiz "\t\t\t\t\t\t\t[ 1 ]   Ones:\t"
twos:		.asciiz "\t\t\t\t\t\t\t[ 2 ]   Twos:\t"
threes:		.asciiz "\t\t\t\t\t\t\t[ 3 ] Threes:\t"
fours:		.asciiz "\t\t\t\t\t\t\t[ 4 ]  Fours:\t"
fives:		.asciiz "\t\t\t\t\t\t\t[ 5 ]  Fives:\t"
sixes:		.asciiz "\t\t\t\t\t\t\t[ 6 ]  Sixes:\t"

bon:		.asciiz "\t\t\t\t\t\t\tBonus < Needs an Upper score of at least 63 > :     "
us:		.asciiz "\t\t\t\t\t\t\t\t away from the Bonus."
reached:	.asciiz "Reached! Your Upper total is currently:\t"

threekind:	.asciiz "\t\t\t\t\t\t\t[ 7 ] Three of a Kind:\t"
fourkind:	.asciiz "\t\t\t\t\t\t\t[ 8 ]  Four of a Kind:\t"
fullhouse:	.asciiz "\t\t\t\t\t\t\t[ 9 ]      Full House:\t"
smallst:	.asciiz "\t\t\t\t\t\t\t[ 10 ] Small Straight:\t"
largest:	.asciiz "\t\t\t\t\t\t\t[ 11 ] Large Straight:\t"
chanc:		.asciiz "\t\t\t\t\t\t\t[ 12 ]         Chance:\t"
yaht:		.asciiz "\t\t\t\t\t\t\t[ 13 ]        Yahtzee:\t"

	.text
main:	
	li $t0, 0	# initialize offest to 0
	li $t1, 0	# initialize dice printing offset to be 0
	li $s5, 0	#initialize gameplay loop counter to be 0
	la $s1, dice_array1	# load address of first dice array
	la $s2, score_array	# load address of hold-die array

	
###############################################
#  			                      #
#			                      #
#	  	  GAMEPLAY                    #
#			                      #
#			                      #
###############################################
gameplay:

#------------Rolls the Dice--------------#
	jal roll1
	jal print_dice1
	
	jal update_array
	jal roll2
	jal print_dice1
	
	jal update_array
	jal roll3
	jal print_dice1
#-----------Prints the Board-------------#
	jal print
#--------------Score Selection--------------#
	jal update_score
	
	jal print
	
	addi $s5, $s5, 1
	blt $s5, 13, gameplay
	
	jal final_count
	jal print_final_score
	j replay

###############################################
#  			                      #
#			                      #
#	      Replay Game	              #
#			                      #
#			                      #
###############################################	
replay:
	li $t0, 0	#reset t0
	
	jal new_line
	jal new_line
	jal line
	jal new_line
	jal new_line
	
        li $v0, 4
        la $a0, play_again
        syscall
        
        li $v0, 5
        syscall
        move $t0, $v0
        
        beqz $t0, end_game
        j main

high_score:
	sw $ra, 0($sp)	# save the address
	addi $sp, $sp, -4
	
	li $v0, 4
        la $a0, highscore
        syscall
        
        li $v0, 1
        move $a0, $t6	# print current high score (t6)
        syscall
        
        jal new_line
        
        blt $s4, $t6, not_new_score	# if new high score, fall through
        
        move $t6, $s4
        li $v0, 4
        la $a0, new_high
        syscall
        
        jal new_line
        
	addi $sp, $sp, 4 # restore the address
	lw $ra, 0($sp)
	
	
	jr $ra	# return
        
not_new_score:

	li $v0, 4
        la $a0, not_new
        syscall
        
        jal new_line
        
	addi $sp, $sp, 4 # restore the address
	lw $ra, 0($sp)
	
	
	jr $ra	# return	

###############################################
#  			                      #
#			                      #
#	      Score Tally	              #
#			                      #
#			                      #
###############################################	

final_count:
	sw $ra, 0($sp)	# save the address
	addi $sp, $sp, -4
	
	li $s3, 0
	li $s4, 0
	li $t0, 0
	li $t2, 0
	li $t3, 0
	li $t4, 0
	
	add $t2, $t2, $s2
upper_tally:
	lw $s3, 0($s2)
	add $s4, $s4, $s3
	lw $s3, 4($s2)
	add $s4, $s4, $s3
	lw $s3, 8($s2)
	add $s4, $s4, $s3
	lw $s3, 12($s2)
	add $s4, $s4, $s3
	lw $s3, 16($s2)
	add $s4, $s4, $s3
	lw $s3, 20($s2)
	add $s4, $s4, $s3

	
bonus_check:
	ble $s4, 63, lower_tally
	add $s4, $s4, 35
lower_tally:
	lw $s3, 28($s2)
	add $s4, $s4, $s3
	lw $s3, 32($s2)
	add $s4, $s4, $s3
	lw $s3, 36($s2)
	add $s4, $s4, $s3
	lw $s3, 40($s2)
	add $s4, $s4, $s3
	lw $s3, 44($s2)
	add $s4, $s4, $s3
	lw $s3, 48($s2)
	add $s4, $s4, $s3
	lw $s3, 52($s2)
	add $s4, $s4, $s3
	
	
	addi $sp, $sp, 4 # restore the address
	lw $ra, 0($sp)
	
	jr $ra	# return
	
###############################################
#  			                      #
#			                      #
#	      Score Selection	              #
#			                      #
#			                      #
###############################################			
update_score:
	sw $ra, 0($sp)	# save the address
	addi $sp, $sp, -4
	
	li $s3, 0
	li $t0, 0
	li $t2, 0
	li $t3, 0
	li $t4, 0
	
	add $t2, $t2, $s1
	
	
	jal prompt_for_category
	
	mul $t4, $t7, 4

	beq $t7, 1, a_ones
	beq $t7, 2, a_twos
	beq $t7, 3, a_threes
	beq $t7, 4, a_fours
	beq $t7, 5, a_fives
	beq $t7, 6, a_sixes
	beq $t7, 7, three_of_a_kind
	beq $t7, 8, four_of_a_kind
	beq $t7, 9, a_full_house
	beq $t7, 10, a_small_straight
	beq $t7, 11, a_large_straight
	beq $t7, 12, a_chance
	beq $t7, 13, a_yahtzee
	j update_score

###########
a_ones:
	lw $s3, ($t2)
	bne $s3, 1, not_one
	add $t3, $t3, $s3
not_one:
	addi $t2, $t2, 4
	addi $t0, $t0, 1
	blt $t0, 5, a_ones
	sw $t3, 0($s2)
	
	j return
#############
a_twos:
	lw $s3, ($t2)
	bne $s3,2, not_two
	add $t3, $t3, $s3
not_two:
	addi $t2, $t2, 4
	addi $t0, $t0, 1
	blt $t0, 5, a_twos
	sw $t3, 4($s2)
	
	j return
##############
a_threes:
	lw $s3, ($t2)
	bne $s3, 3, not_three
	add $t3, $t3, $s3
not_three:
	addi $t2, $t2, 4
	addi $t0, $t0, 1
	blt $t0, 5, a_threes
	sw $t3, 8($s2)
	
	j return
##############
a_fours:
	lw $s3, ($t2)
	bne $s3, 4, not_four
	add $t3, $t3, $s3
not_four:
	addi $t2, $t2, 4
	addi $t0, $t0, 1
	blt $t0, 5, a_fours
	sw $t3, 12($s2)
	
	j return
##############
a_fives:
	lw $s3, ($t2)
	bne $s3, 5, not_five
	add $t3, $t3, $s3
not_five:
	addi $t2, $t2, 4
	addi $t0, $t0, 1
	blt $t0, 5, a_fives
	sw $t3, 16($s2)
	
	j return
##############
a_sixes:
	lw $s3, ($t2)
	bne $s3, 6, not_six
	add $t3, $t3, $s3
not_six:
	addi $t2, $t2, 4
	addi $t0, $t0, 1
	blt $t0, 5, a_sixes
	sw $t3, 20($s2)
	
	j return
##############
three_of_a_kind:
	jal do_you_have_this
	beqz $s6, a_zero
yes_three:
	lw $s3, ($t2)
	add $t3, $t3, $s3
	
	add $t2, $t2, 4
	addi $t0, $t0, 1
	blt $t0, 5, yes_three
	sw $t3, 28($s2)
	
	j return
##############
four_of_a_kind:
	jal do_you_have_this
	beqz $s6, a_zero
	
yes_four:
	lw $s3, ($t2)
	add $t3, $t3, $s3
	
	add $t2, $t2, 4
	addi $t0, $t0, 1
	blt $t0, 5, yes_four
	sw $t3, 32($s2)
	
	j return
##############
a_full_house:
	jal do_you_have_this
	beqz $s6, a_zero
	
	li $t3, 25
	sw $t3, 36($s2)
	
	j return
##############
a_small_straight:
	jal do_you_have_this
	beqz $s6, a_zero
	
	li $t3, 30
	sw $t3, 40($s2)
	
	j return
##############
a_large_straight:
	jal do_you_have_this
	beqz $s6, a_zero
	
	li $t3, 40
	sw $t3, 44($s2)
	
	j return
##############
a_chance:
	lw $s3, ($t2)
	add $t3, $t3, $s3
	
	add $t2, $t2, 4
	addi $t0, $t0, 1
	blt $t0, 5, a_chance
	sw $t3, 48($s2)
	
	j return
##############
a_yahtzee:
	lw $s7, ($t2)
yup:		
	lw $s3, ($t2)
	bne $s7, $s3, a_zero
	move $s7, $s3
	
	add $t2, $t2, 4
	addi $t0, $t0, 1
	blt $t0, 5, yup
	
	li $t3, 50
	sw $t3, 52($s2)
	
	j return
################
a_zero:
	sw $zero, ($t2)
return:
	addi $sp, $sp, 4 # restore the address
	lw $ra, 0($sp)
	
	
	jr $ra	# return

#------------- Prompt for Score Category ----------#
prompt_for_category:

	sw $ra, 0($sp)	#save return address
	addi $sp, $sp, -4
	li $t7, 0 #reset value in t7
	jal new_line
	jal new_line

        li $v0, 4
        la $a0, prompt_category
        syscall
        
        li $v0, 5
        syscall
        move $t7, $v0
         
        addi $sp, $sp, 4
        lw $ra, 0($sp)	#restore return address
        
	jr $ra
	
###############################################
#  			                      #
#			                      #
#	      ROLL DICE METHOD                #
#			                      #
#			                      #
###############################################	
roll:
	sw $ra, 0($sp)	# save the address
	addi $sp, $sp, -4
	
	
	addi $a1, $zero, 6	# between 0-5
	addi $v0, $zero, 42	# syscall for random int	
	syscall		# syscall
	
	move $s0, $a0	# move random into into s0
	addi $s0, $s0, 1	#add one 
	
	addi $sp, $sp, 4 # restore the address
	lw $ra, 0($sp)
	
	
	jr $ra	# return
	
#------------------- FIRST ROLL ------------------------------
roll1:	# first roll, uses dice1 array { $s1 }
	sw $ra, 0($sp)	# save the address
	addi $sp, $sp, -4
	
	j d11

d11:
	jal roll
	sw $s0, 0($s1)
	j d21
	
d21:
	jal roll
	sw $s0, 4($s1)
	j d31
	
d31:
	jal roll
	sw $s0, 8($s1)
	j d41
	
d41:
	jal roll
	sw $s0, 12($s1)
	j d51
	
d51:
	jal roll
	sw $s0, 16($s1)
	
	addi $sp, $sp, 4 # restore the address
	lw $ra, 0($sp)
	
	
	jr $ra	# return
# -------------------- ^ FIRST ROLL ^ --------------------------

# ----------------------- REL-ROLL ONE / ROLL TWO ---------------------
roll2:
	sw $ra, 0($sp)	# save the address
	addi $sp, $sp, -4
	
	j d12

d12:
	lw $t9, 0($s1)	# array index is 0
	bnez $t9, d22

	jal roll
	sw $s0, 0($s1)
	j d22
	
d22:
	lw $t9, 4($s1)
	bnez $t9, d32

	jal roll
	sw $s0, 4($s1)
	j d32
	
d32:
	
	lw $t9, 8($s1)
	bnez $t9, d42

	jal roll
	sw $s0, 8($s1)
	j d42
	
d42:
	
	lw $t9, 12($s1)
	bnez $t9, d52

	jal roll
	sw $s0, 12($s1)
	j d52
	
d52:

	lw $t9, 16($s1)
	bnez $t9, exit_reroll_one
	
	jal roll
	sw $s0, 16($s1)
	j exit_reroll_one
	
exit_reroll_one:
	addi $sp, $sp, 4 # restore the address
	lw $ra, 0($sp)
	
	
	jr $ra	# return


# ----------------------- ^ REL-ROLL ONE / ROLL TWO ^ ---------------------
##########################################################################
# ----------------------- REL-ROLL TWO / ROLL THREE -----------------------
roll3:
	sw $ra, 0($sp)	# save the address
	addi $sp, $sp, -4
	
	j d13

d13:
	lw $t9, 0($s1)	# array index is 0
	bnez $t9, d23

	jal roll
	sw $s0, 0($s1)
	j d23
	
d23:
	lw $t9, 4($s1)
	bnez $t9, d33

	jal roll
	sw $s0, 4($s1)
	j d33
	
d33:
	
	lw $t9, 8($s1)
	bnez $t9, d43

	jal roll
	sw $s0, 8($s1)
	j d43
	
d43:
	
	lw $t9, 12($s1)
	bnez $t9, d53

	jal roll
	sw $s0, 12($s1)
	j d53
	
d53:

	lw $t9, 16($s1)
	bnez $t9, exit_reroll_two
	
	jal roll
	sw $s0, 16($s1)
	j exit_reroll_two
	
exit_reroll_two:
	addi $sp, $sp, 4 # restore the address
	lw $ra, 0($sp)
	
	
	jr $ra	# return


# ----------------------- ^ REL-ROLL TWO / ROLL THREE ^ ---------------------

										
###############################################
#  			                      #
#			                      #
#	 	HOLDING DICE	              #
#			                      #
#			                      #
###############################################	
hold_dice_input:

	sw $ra, 0($sp)	#save return address
	addi $sp, $sp, -4

        li $v0, 4
        la $a0, prompt_hold
        syscall
        
        li $v0, 5
        syscall
        move $t9, $v0
         
        addi $sp, $sp, 4
        lw $ra, 0($sp)	#restore return address
        
	jr $ra
#------------- takes user input and changes the value in the dice2 array --------------------
update_array:
	sw $ra, 0($sp)	#save return address
	addi $sp, $sp, -4

	jal hold_dice_input
	beq $t9, 0, done_inputting
	
	j die1

die1:
	bne $t9, 1, die2
	sw $0, 0($s1)
	j die2
	
die2:
	bne $t9, 2, die3
	sw $0, 4($s1)
	j die3
	
die3:
	bne $t9, 3, die4
	sw $0, 8($s1)
	j die4
	
die4:
	bne $t9, 4, die5
	sw $0, 12($s1)
	j die5
	
die5:
	bne $t9, 5, loop_update
	sw $0, 16($s1)
	
loop_update:
	j update_array

done_inputting:

	addi $sp, $sp, 4 # restore the address
	lw $ra, 0($sp)
	

	jr $ra	# return
##---------------------- ^ takes input and updates value of dice2 array ^ ------------------
print:
	sw $ra, 0($sp)	# save the address
	addi $sp, $sp, -4
	jal new_line
	jal new_line
	jal new_line
	jal new_line
	jal new_line
	jal new_line
	jal new_line
	jal new_line
	jal new_line
	jal line
	
	jal up
	
	jal one
	jal two
	jal three
	jal four
	jal five
	jal six
	
	jal bonus
	
	jal low
	
	jal three_kind
	jal four_kind
	jal full_house
	jal small_straight
	jal large_straight
	jal chance
	jal yahtzee

	addi $sp, $sp, 4 # restore the address
	lw $ra, 0($sp)
	
	
	jr $ra	# return


######################################################################################
#--------------------------FUNCTIONAL METHODS FOR PRINTING----------------------------
######################################################################################
do_you_have_this:
	sw $ra, 0($sp)	#save return address
	addi $sp, $sp, -4
	
	li $v0, 4
        la $a0, yes_no
        syscall
	
	li $v0, 5
        syscall
        move $s6, $v0
	
        addi $sp, $sp, 4
        lw $ra, 0($sp)	#restore return address
        
	jr $ra
	
print_final_score:
	sw $ra, 0($sp)	#save return address
	addi $sp, $sp, -4
	
	li $v0, 4
        la $a0, score_tally
        syscall
	
	li $v0, 1		# print this integer
	move $a0, $s4
	syscall
	
	jal new_line
	jal high_score #prints whether it was a new highscore or not
	
        addi $sp, $sp, 4
        lw $ra, 0($sp)	#restore return address
        
	jr $ra
	
new_line:	# prints a new line
	sw $ra, 0($sp)	#save return address
	addi $sp, $sp, -4
			# creates a new line
	li $v0, 4
        la $a0, new
        syscall
        
        addi $sp, $sp, 4
        lw $ra, 0($sp)	#restore return address
        
	jr $ra
	
line:		# prints the lines of the game board
	sw $ra, 0($sp)	#save return address
	addi $sp, $sp, -4
	
	jal new_line
			# creates a horizontal line
	li $v0, 4
        la $a0, underlines
        syscall
        
        jal new_line
        
        addi $sp, $sp, 4
        lw $ra, 0($sp)	#restore return address
        
	jr $ra
	
barr:		# prints the bar for the die
	sw $ra, 0($sp)	#save return address
	addi $sp, $sp, -4	
	
			# creates a vertical line
	li $v0, 4
        la $a0, bars
        syscall
        
        addi $sp, $sp, 4
        lw $ra, 0($sp)	#restore return address
        
	jr $ra
	
tabb: 		# prints the tab for the die
	sw $ra, 0($sp)	#save return address
	addi $sp, $sp, -4
	
			# creates a tab
	li $v0, 4
        la $a0, tab
        syscall
     
        addi $sp, $sp, 4
        lw $ra, 0($sp)	#restore return address
        
	jr $ra
	
few_lines:	# prints the lines for the die
	sw $ra, 0($sp)	#save return address
	addi $sp, $sp, -4
	
			# creates a few horizontal lines
	li $v0, 4
        la $a0, few_line
        syscall
        
        
        addi $sp, $sp, 4
        lw $ra, 0($sp)	#restore return address
        
	jr $ra
	
few_lines_bottom:	# prints the lines for the bottom of the dice

	sw $ra, 0($sp)	#save return address
	addi $sp, $sp, -4
	
			# creates a few horizontal lines
	li $v0, 4
        la $a0, fewer_line
        syscall
        
        
        addi $sp, $sp, 4
        lw $ra, 0($sp)	#restore return address
        
	jr $ra
	
few_spaces:	# prints the space for the bars in the dice
	sw $ra, 0($sp)	#save return address
	addi $sp, $sp, -4
	
			# creates a few spaces
	li $v0, 4
        la $a0, spaces
        syscall
        
        
        addi $sp, $sp, 4
        lw $ra, 0($sp)	#restore return address
        
	jr $ra
a_space:	# prints the 2 spaces for the bars in the dice
	sw $ra, 0($sp)	#save return address
	addi $sp, $sp, -4
	
			# creates two spaces
	li $v0, 4
        la $a0, a_spaces
        syscall
        
        
        addi $sp, $sp, 4
        lw $ra, 0($sp)	#restore return address
        
	jr $ra
################################################################################################
#----------------------------------PRINTING THE DICE--------------------------------------------
################################################################################################
#-------------------- PRINT FIRST ROLL --------------------------------
print_dice1:
	sw $ra, 0($sp)	#save return address
	addi $sp, $sp, -4
	li $t1, 0 	#initialize and reset value in t1
	
	add $t1, $t1, $s1	# put base address into t1

print_die_half1:
	li $t0, 0	# set loop count to 0
	jal new_line
        
top_line_loop:
        #####	creates top of dice
        jal few_lines
        jal tabb
        
        addi $t0, $t0, 1
        blt $t0, 5, top_line_loop
        li $t0, 0	# set loop count back to 0
        
        jal new_line	# goes to next line
        
top_bars:
	 #####	creates top left side of dice
        jal barr
        jal tabb
        jal few_spaces
        jal barr
        jal a_space
        
        addi $t0, $t0, 1
        blt $t0, 5, top_bars
        li $t0, 0	#set loop count back to 0
        
        jal new_line	# goes to next line
 
middle_bars_nums:
 	#####	creates top middle side of dice
        jal barr
        jal tabb

       	lw $t2, 0($t1)	#grab thing from array
        
        li $v0, 1
        move $a0, $t2	#print thing from array
        syscall
        
        jal a_space
        jal a_space
        jal a_space
        jal a_space
        jal a_space
        jal barr
        jal a_space
       
        
        addi $t1, $t1, 4	# index for array +4, offset + base = total
        addi $t0, $t0, 1	# loop counter
        
        blt $t0, 5, middle_bars_nums
	li $t0, 0	#set loop count back to 0
	
        jal new_line	# goes to next line    
        
bottom_bars:
	 #####	creates top left side of dice
        jal barr
        jal tabb
        jal few_spaces
        jal barr
        jal a_space
        
        addi $t0, $t0, 1
        blt $t0, 5, bottom_bars
        li $t0, 0	#set loop count back to 0
        
        jal new_line	# goes to next line
        
bottom_line_loop:
        #####	creates top of dice
        jal barr
        jal few_lines_bottom
        jal a_space
        jal barr
        jal a_space
        
        addi $t0, $t0, 1
        blt $t0, 5, bottom_line_loop
        li $t0, 0	# set loop count back to 0
        
        jal new_line	# goes to next line
        
        ################ DONE ##################
        addi $sp, $sp, 4
        lw $ra, 0($sp)	#restore return address
        
	jr $ra
	
	
	
################################################################################################
#----------------------------------PRINTING THE GAME BOARD--------------------------------------
################################################################################################

up:	# prints the UPPER part of the game board

	sw $ra, 0($sp)	#save return address
	addi $sp, $sp, -4
			# creates the _____UPPER_____
	li $v0, 4
        la $a0, upper
        syscall
        
        jal new_line	# prints a new line, then the line, then a new line
        
        addi $sp, $sp, 4
        lw $ra, 0($sp)	#restore return address
        
	jr $ra
	
one:	# prints the ONES part of the game board
	sw $ra, 0($sp)	#save return address
	addi $sp, $sp, -4
			# creates the ONES
	li $v0, 4
        la $a0, ones
        syscall
        
        lw $t4, 0($s2)
        li $v0, 1		# print this integer
	move $a0, $t4
	syscall
        
    	jal new_line
        
        addi $sp, $sp, 4
        lw $ra, 0($sp)	#restore return address
        
	jr $ra

two:	# prints the TWOS part of the gameboard
	sw $ra, 0($sp)	#save return address
	addi $sp, $sp, -4
			# creates the TWOS
	li $v0, 4
        la $a0, twos
        syscall
        
        lw $t4, 4($s2)
        li $v0, 1		# print this integer
	move $a0, $t4
	syscall
        
        jal new_line	# prints a new line
        
        addi $sp, $sp, 4
        lw $ra, 0($sp)	#restore return address
        
	jr $ra


three:	# prints the THREES part of the gameboard
	sw $ra, 0($sp)	#save return address
	addi $sp, $sp, -4
			# creates the THREES
	li $v0, 4
        la $a0, threes
        syscall
        
        lw $t4, 8($s2)
        li $v0, 1		# print this integer
	move $a0, $t4
	syscall

        jal new_line	# prints a new line
        
        addi $sp, $sp, 4
        lw $ra, 0($sp)	#restore return address
        
	jr $ra


four:		# prints the FOURS part of the game board
	sw $ra, 0($sp)	#save return address
	addi $sp, $sp, -4
			# creates the FOURS
	li $v0, 4
        la $a0, fours
        syscall
                
        lw $t4, 12($s2)
        li $v0, 1		# print this integer
	move $a0, $t4
	syscall

        jal new_line	# prints a new line
        
        addi $sp, $sp, 4
        lw $ra, 0($sp)	#restore return address
        
	jr $ra


five:		#prints the FIVES part of the game board
	sw $ra, 0($sp)	#save return address
	addi $sp, $sp, -4
			# creates the FIVES
	li $v0, 4
        la $a0, fives
        syscall
                
        lw $t4, 16($s2)
        li $v0, 1		# print this integer
	move $a0, $t4
	syscall

        jal new_line	# prints a new line
        
        addi $sp, $sp, 4
        lw $ra, 0($sp)	#restore return address
        
	jr $ra


six:		# pritns the SIXES part of the game board
	sw $ra, 0($sp)	#save return address
	addi $sp, $sp, -4
			# creates the SIXES
	li $v0, 4
        la $a0, sixes
        syscall
                
        lw $t4, 20($s2)
        li $v0, 1		# print this integer
	move $a0, $t4
	syscall

        jal new_line	# prints a new line
        
        addi $sp, $sp, 4
        lw $ra, 0($sp)	#restore return address
        
	jr $ra


bonus:		# prints the BONUS part of the game board
	sw $ra, 0($sp)	#save return address
	addi $sp, $sp, -4
	li $t0, 0	#resets the counting variables
	li $t2, 0
	li $t3, 0
	li $t4, 0
	li $t7, 0
	
			# creates the BONUS
	li $v0, 4
        la $a0, bon # prints first half (until the number)
        syscall
        
	lw $t2, 0($s2)
	add $t3, $t3, $t2	#adds the ones
	
	lw $t2, 4($s2)
	add $t3, $t3, $t2	#adds the twos
	
	lw $t2, 8($s2)
	add $t3, $t3, $t2	#adds the threes
	
	lw $t2, 12($s2)
	add $t3, $t3, $t2	#adds the fours
	
	lw $t2, 16($s2)
	add $t3, $t3, $t2	#adds the fives
	
	lw $t2, 20($s2)
	add $t3, $t3, $t2	#adds the sixes
	
	li $t7, 63
       	sub $t4, $t7, $t3 # subtract the total from 63 to determine how many points away the player is from the bonus
       	bltz $t4, reached_bonus
       	
        li $v0, 1		# print this integer
	move $a0, $t4
	syscall
	
	li $v0, 4
        la $a0, us	# prints the second half (after the number)
        syscall

        jal new_line	# prints a new line
        
        addi $sp, $sp, 4
        lw $ra, 0($sp)	#restore return address
        
	jr $ra
reached_bonus:
	li $v0, 4
        la $a0, reached	# prints the second half (after the number)
        syscall
        
        li $v0, 1		# print this integer
	move $a0, $t3
	syscall
	
	jal new_line

	addi $sp, $sp, 4
        lw $ra, 0($sp)	#restore return address
        
	jr $ra

low:		# prints the _______LOWER__________ part of the game board
	sw $ra, 0($sp)	#save return address
	addi $sp, $sp, -4
			# creates the _____LOWER_____
	li $v0, 4
        la $a0, lower
        syscall
        
        jal new_line	# prints a new line, then the line, then a new line
        
        addi $sp, $sp, 4
        lw $ra, 0($sp)	#restore return address
        
	jr $ra


three_kind:	# prints the THREE OF A KIND part of the game board
	sw $ra, 0($sp)	#save return address
	addi $sp, $sp, -4
			# creates the THREE OF A KIND
	li $v0, 4
        la $a0, threekind
        syscall
                
        lw $t4, 28($s2)
        li $v0, 1		# print this integer
	move $a0, $t4
	syscall

        jal new_line	# prints a new line
        
        addi $sp, $sp, 4
        lw $ra, 0($sp)	#restore return address
        
	jr $ra


four_kind:	# prints the FOUR OF A KIND part of the game board
	sw $ra, 0($sp)	#save return address
	addi $sp, $sp, -4
			# creates the FOUR OF A KIND
	li $v0, 4
        la $a0, fourkind
        syscall
                
        lw $t4, 32($s2)
        li $v0, 1		# print this integer
	move $a0, $t4
	syscall

        jal new_line	# prints a new line
        
        addi $sp, $sp, 4
        lw $ra, 0($sp)	#restore return address
        
	jr $ra

full_house:	# prints the FULL HOUSE part of the game board
	sw $ra, 0($sp)	#save return address
	addi $sp, $sp, -4
			# creates the FULL HOUSE
	li $v0, 4
        la $a0, fullhouse
        syscall
                
        lw $t4, 36($s2)
        li $v0, 1		# print this integer
	move $a0, $t4
	syscall

        jal new_line	# prints a new line
        
        addi $sp, $sp, 4
        lw $ra, 0($sp)	#restore return address
        
	jr $ra

small_straight:	# prints the SMALL STRAIGHT part of the game board
	sw $ra, 0($sp)	#save return address
	addi $sp, $sp, -4
			# creates the SMALL STRAIGHT
	li $v0, 4
        la $a0, smallst
        syscall
                
        lw $t4, 40($s2)
        li $v0, 1		# print this integer
	move $a0, $t4
	syscall

        jal new_line	# prints a new line
        
        addi $sp, $sp, 4
        lw $ra, 0($sp)	#restore return address
        
	jr $ra

large_straight:	# prints the LARGE STRAIGHT part of the gamebaord
	sw $ra, 0($sp)	#save return address
	addi $sp, $sp, -4
			# creates the LARGE STRAIGHT
	li $v0, 4
        la $a0, largest
        syscall
                
        lw $t4, 44($s2)
        li $v0, 1		# print this integer
	move $a0, $t4
	syscall

        jal new_line	# prints a new line
        
        addi $sp, $sp, 4
        lw $ra, 0($sp)	#restore return address
        
	jr $ra

chance:		# prints the CHANCE part of the game board
	sw $ra, 0($sp)	#save return address
	addi $sp, $sp, -4
			# creates the CHANCE
	li $v0, 4
        la $a0, chanc
        syscall
                
        lw $t4, 48($s2)
        li $v0, 1		# print this integer
	move $a0, $t4
	syscall

        jal new_line	# prints a new line
        
        addi $sp, $sp, 4
        lw $ra, 0($sp)	#restore return address
        
	jr $ra

yahtzee:	# prints the YAHTZEE part of the game baord
	sw $ra, 0($sp)	#save return address
	addi $sp, $sp, -4
			# creates the YAHTZEE
	li $v0, 4
        la $a0, yaht
        syscall
                
        lw $t4, 52($s2)
        li $v0, 1		# print this integer
	move $a0, $t4
	syscall

        jal new_line	# prints a new line
        
        addi $sp, $sp, 4
        lw $ra, 0($sp)	#restore return address
        
	jr $ra
	
###############################################################################################################################
###############################################################################################################################
###############################################################################################################################
end_game:	# END THE GAME
	li $v0, 10
        syscall
        
