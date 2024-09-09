'''
Author: Tytus Felbor
Solution to LeetCode Exercise 13
This code takes a roman numeral as an input, and converts it into an integer.
'''

def romanToInt(s: str) -> int:
    #Setting up a dictionary to match the Roman numerals with a corresponding integer
    valToSym = {
        "I" : 1,
        "V" : 5,
        "X" : 10,
        "L" : 50,
        "C" : 100,
        "D" : 500,
        "M" : 1000
    }
    total = 0
    
    #Translate the Roman numeral to an integer
    for i in range(len(str(s)) - 1):
        if (valToSym[s[i]] < valToSym[s[i+1]]):
            total -= valToSym[s[i]]
        else:
            total += valToSym[s[i]]
    
    #Return the total adding the last integer
    return total + valToSym[s[-1]]
 
print("------------------------------------------------------------------------------\n"
"Author: Tytus Felbor\n" +
"Solution to LeetCode Exercise 13\n" +
"This code takes a roman numeral as an input, and converts it into an integer.\n" +
"------------------------------------------------------------------------------\n") 
    
print("Enter a roman numeral below, it will get converted to an int\n")
roman = input()

print("\nThe roman numeral : " + roman + "; Presented as an integer is: " + str(romanToInt(roman)))    
