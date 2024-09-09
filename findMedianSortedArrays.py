'''
Author: Tytus Felbor
Solution to LeetCode Exercise 4
This code given two sorted arrays nums1 and nums2 of size m and n respectively, returns the median of the two sorted arrays.
'''

def findMedianSortedArrays(nums1: list[int], nums2: list[int]) -> float:
    #Merge the two arrays
    merged = nums1 + nums2
    merged.sort()
    print("Merged and sorted array: " + str(merged))

    #Find the array size & median index
    size = len(merged)
    medianIndex = []
    if (size % 2 == 0):
        indexOne = size / 2
        indexOne = int(indexOne)
        indexTwo = indexOne - 1
        medianIndex.append(indexOne)
        medianIndex.append(indexTwo)
    else:
        index = size / 2
        index = int(index)
        medianIndex.append(index)
        
    #Calculate the median number, depending on the number of median indices
    median = 0
    s = 0
    if (len(medianIndex) == 2):
        medianNumbers = []
        for i in medianIndex:
            for index, value in enumerate(merged):
                if (index == i):
                    medianNumbers.append(value)
        for n in medianNumbers:
            s += int(n)
        s = s / 2
        return s

    elif(len(medianIndex) == 1):
        medianNumber = merged[medianIndex[0]]
        return medianNumber
    else:
        return 0

print("-------------------------------------------------------------\n" + 
"Author: Tytus Felbor\n" +
"Solution to LeetCode Exercise 4\n" +
"This code given two sorted arrays nums1 and nums2 of size m\n" +
"and n respectively, returns the median of the two sorted arrays.\n" +
"-------------------------------------------------------------\n")

print("Enter a few integers, seperated by a coma and space, to make the first list.")
firstList = input().split(", ")
print("Enter some more integers, also seperated by a coma and space, to make the second list. ")
secondList = input().split(", ")
print("The arrays you've entered are as follows. 1) " + str(firstList) + "; 2) " + str(secondList))
print("The median of two joined arrays: " + str(findMedianSortedArrays(firstList, secondList)))