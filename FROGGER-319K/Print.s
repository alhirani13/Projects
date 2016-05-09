; Print.s
; Student names: Dylan Keeton & Al Hirani
; Last modification date: change this to the last modification date or look very silly
; Runs on LM4F120 or TM4C123
; EE319K lab 7 device driver for any LCD
;
; As part of Lab 7, students need to implement these LCD_OutDec and LCD_OutFix
; This driver assumes two low-level LCD functions
; ST7735_OutChar   outputs a single 8-bit ASCII character
; ST7735_OutString outputs a null-terminated string 

    IMPORT   ST7735_OutChar
    IMPORT   ST7735_OutString
    EXPORT   LCD_OutDec
    EXPORT   LCD_OutFix

    AREA    |.text|, CODE, READONLY, ALIGN=2
    THUMB

  

;-----------------------LCD_OutDec-----------------------
; Output a 32-bit number in unsigned decimal format
; Input: R0 (call by value) 32-bit unsigned number
; Output: none
; Invariables: This function must not permanently modify registers R4 to R11
LCD_OutDec
PLACE 	EQU 0
BILLION EQU 1000000000		;HIGHEST POSSIBLE THING TO DIVIDE
	  PUSH	{R3-R11,LR}		;PUSH ALL RESISTORS WE CANT AFFECT
	  LDR 	R1,=BILLION		;LETS START DIVIDING CHIEF
	  PUSH	{R1,R7}
	  MOV 	R3,#10			;TO DIVIDE BILLION ALL THE WAY DOWN TO 1'S PLACE
	  ADDS 	R0,#0			;CHECK IF R0 IS 0
	  BEQ 	ZERO				;IF 0 BRANCH 
	  
CHECK LDR	R1,[SP,#PLACE]
	  UDIV 	R2,R0,R1			;R0/WHATEVER PLACE R1 IS SUCH AS 1S,10S,100S,ETC.
	  CMP 	R2,#0				;IF NUMBER IS LESS THAN R1
	  BNE 	NEXT				;IF IT IS 0, WE CAN MOVE DOWN TO NEXT PLACE.. R1/10
	  UDIV 	R1,R1,R3			;R1/10
	  STR 	R1,[SP,#PLACE]
	  CMP 	R1,#0				;IF WE ARE AT 1S PLACE AND DIVIDE BY 10, WE ARE DONE
	  BEQ 	DONE				;BRANCH IF DONE DIVIDING
	  B 	CHECK				;GO TO NEXT PLACE
	  
CHEK  LDR 	R1,[SP,#PLACE]
	  UDIV 	R2,R0,R1			;R0/WHATEVER PLACE R1 IS SUCH AS 1S,10S,100S,ETC.
NEXT  PUSH	{R0-R3}			;PUSH THE REGISTERS WE ARE WORKING WITH
	  ADD 	R0,R2,#0x30		;ADD 0x30 TO MAKE ASCII CHARACTER
	  BL 	ST7735_OutChar		;PRINT NUMBER
	  POP	{R0-R3}			;POP REGISTERS WE WORKING WITH
	  MUL 	R2,R2,R1			;WE ARE NOW-
	  SUB 	R0,R0,R2			;GETTING THE REMAINDER FROM THE DIVISION
	  UDIV 	R1,R1,R3			;DIVIDE R1 BY 10
	  STR 	R1,[SP,#PLACE]
	  CMP 	R1,#0				;IF WE HAVE JUST DONE 1S PLACE WE ARE DONE
	  BEQ 	DONE				;BRANCH IF DONE DIVIDING
	  B 	CHEK	  			;ELSE KEEP DIVIDING
	  
ZERO  ADD 	R0,#0x30			;IF 0 JUST PRINT OUT ASCII FOR 0
	  BL 	ST7735_OutChar		;PRINT 0
DONE  POP	{R1,R7}
	  POP	{R3-R11,LR}		;POP NONUSABLE REGISTERS
      BX  	LR				;LEAVE SUBROUTINE
;* * * * * * * * End of LCD_OutDec * * * * * * * *

; -----------------------LCD _OutFix----------------------
; Output characters to LCD display in fixed-point format
; unsigned decimal, resolution 0.001, range 0.000 to 9.999
; Inputs:  R0 is an unsigned 32-bit number
; Outputs: none
; E.g., R0=0,    then output "0.000 "
;       R0=3,    then output "0.003 "
;       R0=89,   then output "0.089 "
;       R0=123,  then output "0.123 "
;       R0=9999, then output "9.999 "
;       R0>9999, then output "*.*** "
; Invariables: This function must not permanently modify registers R4 to R11
LCD_OutFix

PLACE1	 EQU 0
THOUSAND EQU 1000			
TENK 	 EQU 10000
	 PUSH	{R3-R11,LR}		;PUSH WHAT REGISTERS WE ARE USING
	 MOV 	R3,#10				;R3 = 10 SO WE CAN MOVE WHICH PLACE WE ARE IN
	 LDR 	R1,=THOUSAND		;SET R1 TO HIGHEST DIVISOR
	 PUSH	{R1,R7}
	 LDR 	R5,=TENK			;CHECK IF NUMBER > 9999
	 CMP 	R0,R5				;IF SO WE WILL BRANCH
	 BHS 	STARS				;GO TO ALL STARS
	 
CHEC LDR 	R1,[SP,#PLACE]
	 UDIV 	R2,R0,R1			;GET DIVDIDED ANSWER WITHOUT REMIANDER
	 PUSH	{R0-R3}			;PUSH REGISTERS WE ARE USING
	 MOV 	R0,R2				;PRINT THE ANSWER OF DIVISION EXPRESSION
	 ADD 	R0,#0x30			;CHANGE IT TO ASCII CHARACTER
	 BL 	ST7735_OutChar		;PRINT NUMBER
	 POP	{R0-R3}				;POP BACK THE REGISTERS
	 CMP 	R1,#1000			;CHECK IF IT WAS THE FIRST NUMBER
	 BNE 	PASTDECIMAL		;IF NOT MOVE FORWARD		
	 PUSH	{R0-R3}			;IF IT WAS
	 MOV 	R0,#0x2E			;WE NEED TO PRINT A DECIMAL POINT
	 BL 	ST7735_OutChar		;PRINT OUT THE POINT
	 POP	{R0-R3}				;POP BACK THE REGISTERS
PASTDECIMAL
	 MUL 	R2,R2,R1			;DO THE DIVISION EXPRESSION
	 SUB 	R0,R0,R2			; AND SUBTRACT FROM ORIGINAL NUMBER
	 UDIV 	R1,R1,R3			;TO GET THE REMAINDER
	 STR 	R1,[SP,#PLACE]
	 CMP 	R1,#0				;IF WE ARE DONE DIVIDING
	 BEQ 	FIN				;BRANCH OUT
	 B 		CHEC	  				;IF NOT GO TO NEXT PLACE
FIN	 POP	{R1,R7}
	 POP	{R3-R11,LR}			;POP ALL SAVED REGISTERS
     BX   	LR				;LEAVE SUBROUTINE
	 
STARS
	 MOV 	R0,#0x2A			;CODE FOR PRINTING OUT *.***
	 BL 	ST7735_OutChar		;|
	 MOV 	R0,#0x2E			;|
	 BL 	ST7735_OutChar		;IN THE EVENT THAT THE NUMBER IS > 9999
	 MOV 	R0,#0x2A			;|
	 BL 	ST7735_OutChar		;|
	 MOV 	R0,#0x2A			;|
	 BL 	ST7735_OutChar		;|
	 MOV 	R0,#0x2A			;|
	 BL 	ST7735_OutChar		;|
	 B 		FIN					;|
	 
 
     ALIGN
;* * * * * * * * End of LCD_OutFix * * * * * * * *

     ALIGN                           ; make sure the end of this section is aligned
     END                             ; end of file
