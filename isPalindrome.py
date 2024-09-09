'''
Author: Tytus Felbor
Solution to LeetCode Exercise 9
This code given an integer x, return true if x is a palindrome, and false otherwise.
'''

def isPalindrome(x: int) -> bool:
    #Setting up the necessary variables
    test = False
    leftToRight = str(x)
    rightToLeft = ""
    firstIndex = 0
    lastIndex = len(leftToRight) - 1
    
    #Generating the word backwards
    for c in range(lastIndex, firstIndex - 1, - 1):
        rightToLeft = rightToLeft + leftToRight[c]
    
    #Checking for palindrome number
    if (rightToLeft == leftToRight):
        test = True
    else:
        test = False
    
    return test

print("------------------------------------------------------------------------------------\n" + 
"Author: Tytus Felbor\n" +
"Solution to LeetCode Exercise 9\n" +
"This code given an integer x, return true if x is a palindrome, and false otherwise.\n" +
"------------------------------------------------------------------------------------\n")

print("Enter a number below to check if it's a palindrome.")
num = input()
palindrome = isPalindrome(num)
if (palindrome):
    print("\nThe number: " + num + ". Is a palindrome!\n")
else:
    print("\nThe number: " + num + ". Is not a palindrome.\n")
