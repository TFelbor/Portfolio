'''
Author: Tytus Felbor
Solution to LeetCode Exercise 1
This code given an array of integers nums and an integer target, returns indices of the
two numbers such that they add up to target. If target isn't found it returns an empty list.
'''

def twoSum(nums: list[int], target: int) -> list[int]:
    found = False
    for i in range(len(nums)):
        for j in range(i + 1, len(nums)):
            if (int(nums[j]) == target - int(nums[i])):
                found = True
                return [i, j]
    if (not found):
        return []

print("--------------------------------------------------------------------------------------------\n" + 
"Author: Tytus Felbor\n" +
"Solution to LeetCode Exercise 1\n" +
"This code given an array of integers nums and an integer target, returns indices of the \n" +
"two numbers such that they add up to target. If target isn't found it returns an empty list.\n" + 
"--------------------------------------------------------------------------------------------\n")

print("Enter a few numbers, seperated by a coma and a space, below.")
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
