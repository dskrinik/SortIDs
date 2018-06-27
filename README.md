# SortIDs

I wrote this Java program while working for Jahnel Group. 

My assignment was to read in a list of IDs, sort them, then write them to a file.  The IDs can consist of digits, letters, or both digits and letters. `Sample Input: 7 0 1 abc 44 cat 18` The sorting must be done in natural order. 

Additional requirements: 

`Write this code in a simple text editor and compile and run it via the command line. Comments are not permitted for this exercise. Must build your own sorting algorithm and not use the built-in sort method.`

 My sample list of IDs. 
```
12X
12CDD
2
abcd
0
1
3
12
123
333
123456
a
abc
abcc
12Abc
4bb
```

In this assignment I challenged myself to sorting IDs that are alphanumeric. Nevertheless, IDs with alternating digits and characters, i.e. 123Abc456 or Abc123Def, were more difficult to sort in a natural or alphabetic order. Such IDs would not be sorted in this release of my SortIDs app. 

The resulting output is below. All digit IDs come first, alphanumeric IDs come next, and character IDs are last. 
```
0
1
2
3
12
123
333
123456
4bb
12Abc
12CDD
12X
a
abc
abcc
abcd
```

