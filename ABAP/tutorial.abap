REPORT ZHELLO1.
*You may type the report name in lower case letters, but the editor will change it to upper case. So the names of ABAP objects are ‘Not’ case sensitive.
WRITE 'Hello World'.
Write 'This is ABAP Tutorial'. 

*Using the colon notation, it could be rewritten this way −
WRITE: 'Hello', 
       'ABAP', 
       'World'.	   
WRITE: 'Hello', 'ABAP', 'World'.

* This is the comment line
WRITE 'Hello'. "Here is the partial comment " character

*Without NO-ZERO command, the output is: 0000000050
DATA: W_NUR(10) TYPE N.
      MOVE 50 TO W_NUR.
      WRITE W_NUR NO-ZERO.

*The SKIP command helps in inserting blank lines on the page.
WRITE 'This is the 1st line'. 
SKIP. 
WRITE 'This is the 2nd line'. 
*This is the 1st line 
*This is the 2nd line

*We may use the SKIP command to insert multiple blank lines.
SKIP number_of_lines.
*The output would be several blank lines defined by the number of lines. The SKIP command can also position the cursor on a desired line on the page.
SKIP TO LINE line_number.


*The ULINE command automatically inserts a horizontal line across the output. It’s also possible to control the position and length of the line. The syntax is pretty simple −
ULINE.
*exp
WRITE 'This is Underlined'.
ULINE.
*The above code produces the following output −> This is Underlined (and a horizontal line below this).

*Messages
*Message	Type		Consequences
*E			Error		The message appears and the application halts at its current point. If the program is running in background mode, the job is canceled and the message is recorded in the job log.
*W			Warning		The message appears and the user must press Enter for the application to continue. In background mode, the message is recorded in the job log.
*I			Information	A pop-up window opens with the message text and the user must press Enter to continue. In background mode, the message is recorded in the job log.
*A			Abend		This message class cancels the transaction that the user is currently using.
*S			Success		This provides an informational message at the bottom of the screen. The information displayed is positive in nature and it is just meant for user feedback. The message does not impede the program in any way.
*X			Abort		This message aborts the program and generates an ABAP short dump.

*Elementary Data Types
*Type			Keyword
*Byte field		  X
*Text field		  C
*Integer		  I
*Floating point	  F
*Packed number	  P
*Text string	STRING

*Type				 Typical Length			Typical Range
*X					 1 byte					Any byte values (00 to FF)
*C					 1 character			1 to 65535
*N 					 (numeric text filed)	1 character	1 to 65535
*D 					 (character-like date)	8 characters	8 characters
*T 					 (character-like time)	6 characters	6 characters
*I					 4 bytes				-2147483648 to 2147483647
*F					 8 bytes				2.2250738585072014E-308 to 1.7976931348623157E+308 positive or negative
*P					 8 bytes				[-10^(2len -1) +1] to [+10^(2len -1) 1] (where len = fixed length)
*STRING				 Variable				Any alphanumeric characters
*XSTRING(byte string)Variable				Any byte values (00 to FF)


*In this example, we have a character string of type C with a predefined length 40. 
*STRING is a data type that can be used for any character string of variable length (text strings).
*Type STRING data objects should generally be used for character-like content where fixed length is not important.

REPORT YR_SEP_12. 
DATA text_line TYPE C LENGTH 40. 
text_line = 'A Chapter on Data Types'. 
Write text_line. 

DATA text_string TYPE STRING. 
text_string = 'A Program in ABAP'. 
Write / text_string. 

DATA d_date TYPE D. 
d_date = SY-DATUM. 
Write / d_date.
"A Chapter on Data Types 
"A Program in ABAP 
"12092015 -> date

*The complex types are classified into Structure types and Table types.
*Arrays can be simple or structure arrays. 
*In ABAP, arrays are called internal tables and they can be declared and operated upon in many ways when compared to other programming languages.

*SAP ABAP - Variables
*DATA <f> TYPE <type> VALUE <val>. 

DATA d1(2) TYPE C.  
DATA d2 LIKE d1.  
DATA minimum_value TYPE I VALUE 10. 
*In the above code snippet, d1 is a variable of C type, d2 is a variable of d1 type, and minimum_value is a variable of ABAP integer type I.

*Static Variables
*Static variables are declared in subroutines, function modules, and static methods.
*The lifetime is linked to the context of the declaration.
*With ‘CLASS-DATA’ statement, you can declare variables within the classes.
*The ‘PARAMETERS’ statement can be used to declare the elementary data objects that are linked to input fields on a selection screen.
*You can also declare the internal tables that are linked to input fields on a selection screen by using ‘SELECT-OPTIONS’ statement.
*Following are the conventions used while naming a variable −
*You cannot use special characters such as "t" and "," to name variables.
*The name of the predefined data objects can’t be changed.
*The name of the variable can’t be the same as any ABAP keyword or clause.
*The name of the variables must convey the meaning of the variable without the need for further comments.
*Hyphens are reserved to represent the components of structures. Therefore, you are supposed to avoid hyphens in variable names.
*The underscore character can be used to separate compound words.


REPORT ZTest123_01. 
*PARAMETERS
PARAMETERS: NAME(10) TYPE C, 
CLASS TYPE I, 
SCORE TYPE P DECIMALS 2, 
CONNECT TYPE MARA-MATNR. 
*NAME represents a parameter of 10 characters, CLASS specifies a parameter of integer type with the default size in bytes
*SCORE represents a packed type parameter with values up to two decimal places, and CONNECT refers to the MARA-MATNF type of ABAP Dictionary.

*Reference Variables
*DATA <ref> TYPE REF TO <type> VALUE IS INITIAL. 

*REF TO addition declares a reference variable ref.
*The specification after REF TO specifies the static type of the reference variable.
*The static type restricts the set of objects to which <ref> can refer.
*The dynamic type of reference variable is the data type or class to which it currently refers.
*The static type is always more general or the same as the dynamic type.
*The TYPE addition is used to create a bound reference type and as a start value, and only IS INITIAL can be specified after the VALUE addition.


*OBJE CLASS Kullanimi
CLASS C1 DEFINITION. 
PUBLIC SECTION. 
DATA Bl TYPE I VALUE 1. 
ENDCLASS. 
DATA: Oref TYPE REF TO C1 , 
Dref1 LIKE REF TO Oref, 
Dref2 TYPE REF TO I . 
CREATE OBJECT Oref. 
GET REFERENCE OF Oref INTO Dref1. 
CREATE DATA Dref2. 
Dref2→* = Dref1→*→Bl.

*In the above code snippet, an object reference Oref and two data reference variables Dref1 and Dref2 are declared.
*Both data reference variables are fully typed and can be dereferenced using the dereferencing operator →* at operand positions.

*System Variables

















