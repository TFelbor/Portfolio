'''
Author: Tytus Felbor
Solution to LeetCode Exercise 14
This code is a function to find the longest common prefix string amongst an array of strings.
If there is no common prefix, returns an empty string "".
'''

def longestCommonPrefix(strs: list[str]) -> str:
    #Declare the necessary variables
    letterIndex = 0
    prefix = ""
    mismatch = False
    
    #Run a loop to continue looking for a common prefix letter as longs as it doesn't encounter a mismatch of characters
    while (not mismatch):
        letterToCheck = strs[0][letterIndex]
        print("Checking the letter: " + letterToCheck)
        
        #Loop through the enumerated string to compare the characters in each string
        for index, value in enumerate(strs):
            print("Index: " + str(index) + ", Value: " + value)
            
            #If it's a match and it's the last string of the array, add the common letter to the prefix and repeat the process with the next letter in the index
            if(letterToCheck == value[letterIndex] and index == (len(strs) - 1)):
                print(letterToCheck + " == " + value[letterIndex] + "\n\n" + "Found a common letter: " + letterToCheck + "\n")
                prefix += letterToCheck
                letterIndex += 1
                
           #If it's a match, but it's not the last string of the array, continue to check the same character in the next string in the array
            elif(letterToCheck == value[letterIndex]):
                print(letterToCheck + " == " + value[letterIndex])
                continue
                
           #If a mismatch is encountered, break the loop
            else:
                print(letterToCheck + " != " + value[letterIndex])
                mismatch = True
                break
    print("---------------------------------------------")
    
    #Return the prefix with the common letters found
    return prefix

print('---------------------------------------------------------------------------------------------\n'
'Author: Tytus Felbor\n' +
'Solution to LeetCode Exercise 14\n' +
'This code is a function to find the longest common prefix string amongst an array of strings.\n' +
'If there is no common prefix, returns an empty string "".\n' +
'---------------------------------------------------------------------------------------------\n')

print("Enter a few words, seperated by commas and a space, to test for their longest common prefix\n")
strings = input()
strings = strings.split(", ")

print("---------------------------------------------")

prefix = longestCommonPrefix(strings)
print("\nResult: " + prefix + "\n")
