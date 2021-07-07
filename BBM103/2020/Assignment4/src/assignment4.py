import sys
# get uppercase alphabet
import string
# assertion library
from unittest import TestCase
# check file
import os


# matrix multiplication
def multiply(matrix1, matrix2):
    # save calculated value
    result = []
    # column
    for i in matrix2:
        new_matrix = []
        # key matrix row
        for j in matrix1:
            # store result on calc variable
            calc = 0
            # calculation loop
            for k in range(len(j)):
                calc += i[k] * j[k]
            # add result value in list
            new_matrix.append(calc)
        # create result matrix from new matrix
        result.append(new_matrix)
    # return result
    return result

# Flipping a matrix over its diagonal. The rows and columns get swapped.
def transposeMatrix(matrix):
    result = []
    for i in range(len(matrix[0])):
        list1 = []
        for j in range(len(matrix)):
            list1.append(matrix[j][i])
        result.append(list1)

    return result

# If A is a square matrix, then the minor of the entry in the i th row and j th column
def MatrixMinor(matrix, i, j):
    list1 = []
    for row in (matrix[:i] + matrix[i + 1:]):
        list1.append(row[:j] + row[j + 1:])
    return list1


# base case for 2x2 matrixatrix
def MatrixDeternminant2_2(matrix):
    return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]


# base case for 2x2 matrixatrix
def MatrixInverse2x2(matrix):
    determinant = MatrixDeternminant2_2(matrix)
    # special case for 2x2 matrixatrix:
    return [[matrix[1][1] / determinant, -1 * matrix[0][1] / determinant],
            [-1 * matrix[1][0] / determinant, matrix[0][0] / determinant]]

# calculate determinant
def MatrixDeternminant(matrix):
    if len(matrix) == 2:
        return MatrixDeternminant2_2(matrix)

    determinant = 0
    for c in range(len(matrix)):
        # -1 power (sum of coordinates) * selected value * sub matrix(2x2)
        determinant += ((-1) ** c) * matrix[0][c] * MatrixDeternminant(MatrixMinor(matrix, 0, c))
    return determinant

# calculate inverse
def MatrixInverse(matrix):
    # calculate determinant
    determinant = MatrixDeternminant(matrix)

    # find matrix of cofactors
    cofactors = []
    # calculate cofactor
    for r in range(len(matrix)):
        # store calculateted cofactor
        cofactorRow = []
        for c in range(len(matrix)):
            # get minor
            minor = MatrixMinor(matrix, r, c)
            # append cofactor -> -1 power (sum of coordinates) * sub matrix(2x2)
            cofactorRow.append(((-1) ** (r + c)) * MatrixDeternminant(minor))
        cofactors.append(cofactorRow)
    # transpoze calculated matrix
    cofactors = transposeMatrix(cofactors)
    # cofactors[r][c] = cofactors[r][c] / determinant
    for r in range(len(cofactors)):
        for c in range(len(cofactors)):
            cofactors[r][c] = cofactors[r][c] / determinant

    return cofactors


# uppercase letters
uppercase_alphabet = string.ascii_uppercase
# digits
digits = string.digits


# give an error if string contains invalid character
def check_input_line(mode, lines):
    # if mode equals enc -> check input by character
    if mode == "enc":
        for line in lines:
            for i in line:
                if i != " " and i not in uppercase_alphabet:
                    raise ValueError("Invalid character in input file error")
    # if mode equals dec -> check input by digit
    elif mode == "dec":
        for value in lines:
            if value != "," and value != "-" and value not in digits:
                raise ValueError("Invalid character in input file error")


# encryption process
def encryption(listed_plain_text, key_matrix, output_file):
    # list to string
    plain_text = "".join(listed_plain_text)

    # n = splitter
    n = len(key_matrix)
    # list comprension -> split string by n
    divided_string = [plain_text[i:i + n] for i in range(0, len(plain_text), n)]

    # char to integer
    list1 = []
    for i in divided_string:
        new_list = []
        for j in range(len(i)):
            if i[j] in uppercase_alphabet:
                new_list.append(uppercase_alphabet.index(i[j]) + 1)
            # - value
            else:
                new_list.append(27)
        list1.append(new_list)

    # start encryption
    encrypted_matrix = multiply(key_matrix, list1)

    # result to string
    cipher_text = ""
    # get 1 elements from encrypted list
    for i in encrypted_matrix:
        # get 1 value from
        for j in i:
            # string concat
            cipher_text += str(int(j)) + ","

    # delete extra ,
    cipher_text = cipher_text[:-1]

    # print result to output file
    print(cipher_text, end="", file=output_file)


# decryption process
def decryption(cipher_text, key_matrix, output_file):
    # split string
    splitted_encrypted_text = cipher_text.split(",")
    # inverse of Key matrix
    if len(key_matrix) == 2:
        inverse_key_matrix = MatrixInverse2x2(key_matrix)
    else:
        inverse_key_matrix = MatrixInverse(key_matrix)

    # n = splitter
    n = len(key_matrix)
    # list comprension -> split string by n
    divided_cipher = [list(map(int, splitted_encrypted_text[i:i + n])) for i in
                      range(0, len(splitted_encrypted_text), n)]

    # string list to matrix
    # inverse key matrix X decrypted_text
    decrypted_text = multiply(inverse_key_matrix, divided_cipher)
    # integer to character
    for i in decrypted_text:
        for j in i:
            j = int(j)
            if j != 27:
                print(uppercase_alphabet[j - 1], end="", file=output_file)
            else:
                print(" ", end="", file=output_file)


def main(arguments):
    try:
        # assertion object
        assertion_object = TestCase()
        # "Parameter number" error: Occurs when the program has different numbers of parameters than 4.
        assertion_object.assertEqual(len(arguments), 4, "Parameter number")

        # "Undefined parameter" error:
        # Occurs if a value other than "enc" or "dec" is entered in the operation type parameter.
        try:
            assertion_object.assertEqual(arguments[0], "enc", "Undefined parameter")
        except AssertionError:
            assertion_object.assertEqual(arguments[0], "dec", "Undefined parameter")

        # os.path.exists(arguments[1]) -> return bool value
        key_file_check = os.path.exists(arguments[1])
        # if key_file_check doesnt True , five a error
        assertion_object.assertTrue(key_file_check, "Key file not found")

        # "Input file not found" error:
        # Occurs when the input file cannot be found in the path specified by the parameter.
        input_file_check = os.path.exists(arguments[2])
        assertion_object.assertTrue(input_file_check, "Input file not found")

        # The input file could not be read error
        # error: Occurs when the format of the input file specified by the parameter is corrupt.
        extension_input_file = arguments[2][-3:]
        assertion_object.assertEqual(extension_input_file, "txt", "The input file could not be read")

        # The key file could not be read
        # "Key file could not be read" error: Occurs when the format of the key file specified by the parameter is corrupt.
        extension_key_file = arguments[1][-3:]
        assertion_object.assertEqual(extension_key_file, "txt", "The key file could not be read")

        # open key input file by given arguments
        key_file = open(arguments[1], "r")
        # open cipher or plain text file by given arguments
        input_file = open(arguments[2], "r")

        output_file = open(arguments[3], "w")

        # read 1 line from input file
        input_line = input_file.readline()
        # if end of the line contains new line character, it will delete
        input_line = input_line.rstrip("\n")
        # convert string to uppercase
        input_line = input_line.upper()

        # Input file is empty -> if line is empty, give an error
        assertion_object.assertNotEqual(input_line, "", "Input file is empty")

        # read key_file lines
        key_matrix = []
        for ki in key_file.readlines():
            # if end of the line contains new line character, it will delete
            ki = ki.rstrip("\n")
            # split line by "," delimiter
            ki = ki.split(",")
            try:
                ki = list(map(int, ki))
                key_matrix.append(ki)
            except:
                raise ValueError("Invalid character in Key file")

        # "Key file is empty" error: Occurs when the input file specified by the parameter is empty.
        assertion_object.assertNotEqual(key_matrix, [], "Key file is empty")
        # Invalid character in input file error
        check_input_line(arguments[0], input_line)
        # if input line contains space , it will change space to underscore(_)
        input_lines = input_line.replace(" ", "_")

        # if first argument enc , start encryption
        if arguments[0] == "enc":
            encryption(input_lines, key_matrix, output_file)
        # if first argument enc , start decryption
        elif arguments[0] == "dec":
            decryption(input_lines, key_matrix, output_file)

        # close output file
        output_file.close()

    except AssertionError as e:
        print(e.args[0].split(" : ")[1])
    except ValueError as v:
        print(v)
    except Exception as e:
        print(e)


# program start
main(sys.argv[1:])
