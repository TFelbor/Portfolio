'''
Author: Tytus Felbor
Solution to LeetCode Exercise 1
This code given an array of integers nums and an integer target, returns indices of the
two numbers such that they add up to target. If target isn't found it returns an empty list
'''

def twoSum(nums: list[int], target: int) -> list[int]:
    # Initialize a flag to track if a pair is found
    found = False
    
    # Outer loop to iterate through each element in the list
    for i in range(len(nums)):
        
        # Inner loop to check each subsequent element for a pair
        for j in range(i + 1, len(nums)):
            
            # Check if the current pair of elements sum up to the target
            if (int(nums[j]) == target - int(nums[i])):
                
                # If a pair is found, set the flag and return their indices
                found = True
                return [i, j]
    
    # If no pair is found after all iterations, return an empty list
    if (not found):
        return []

print("\nEnter a few numbers, seperated by a coma and a space, below.")
numberz = input()
numberz = numberz.split(", ")

print("\nNow enter an integer target below.")
trgt = input()

print("\nThe numbers you've chosen are: " + str(numberz) + ". The target to be found is: " + str(trgt))

result = twoSum(numberz, int(trgt))

if (not result):
    print("\nTarget couldn't be found\n")
else:
    print("\nTarget found! The indices that add up to " + str(trgt) + " are: " + str(result) + "\n")
