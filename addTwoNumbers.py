'''
Author: Tytus Felbor
Solution to LeetCode Exercise 2 (edited to operate with Lists instead of Linked-Lists)
This code given two non-empty lists representing two non-negative integers, returns their sum.
The digits are stored in reverse order, and each of their components contains a single digit.
It adds the two numbers and returns the sum as a list.
'''

def addTwoNumbers(l1, l2):
    #Unload the two lists to obtain an integer from each
    strNum1 = ""
    strNum2 = ""
    for n in range(len(l1) - 1, -1, -1):
        strNum1 += str(l1[n])
    print("Number in the first list: " + strNum1)
    
    for n in range(len(l2) - 1, -1, -1):
        strNum2 += str(l2[n])
    print("Number in the second list: " + strNum2)
    
    #Calculate the sum of the two numbers
    s = int(strNum1) + int(strNum2)
   
    #Convert it to a string to load the number backwards into a list
    s = str(s)
    print("The sum: " + s)
    
    #Load the number backwards into a list
    result = []
    for n in range(len(s) - 1, -1, -1):
        result.append(s[n])
    print("The result: " + str(result))
    
    #Return the result
    return result
    
print("Enter your first number. Each digit of the number seperate by a comma and a space.")
numberOne = input().split(", ")

print("Enter your second number. Also, each digit of the number should be seperated by a comma and a space.")
numberTwo = input().split(", ")

print("Your first number is: " + str(numberOne) + ". The second number is: " + str(numberTwo))

nOneRev = []
for n in range(len(numberOne) - 1, -1, - 1):
    nOneRev.append(numberOne[n])

nTwoRev = []
for n in range(len(numberTwo) - 1, - 1, - 1):
    nTwoRev.append(numberTwo[n])
    
print("The two numbers reversed are: 1) " + str(nOneRev) + "; 2) " + str(nTwoRev))
print("The reversed sum of those two numbers is: " + str(addTwoNumbers(nOneRev, nTwoRev)))

   
