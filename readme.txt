Name:Simran Singhi
ID: 1001863958
Net ID: sxs3960
Program language: Java


Created the program in visual studio.

- Program Structure:

Created a node class for storing the current node city, connecting city to that city and the cost of the path.
Comparator function used in ncomp and hcomp class to sort the path cost.
Program starts from the main function, checks which search to execute informed or uninformed. 
If condition doesnt matches the else condition is executed for invalid arguments
Program proceeds to the sepfile function to seperate it to [row][col]
then inside the find route class, generateCityMap function is executed.This Function stores the input file information in city_map.
determineroute function is used to determine route and store it.
pathTraceback function is used to traceback the route and print:
nodes popped: 
nodes expanded: 
nodes generated: 
distance: infinity
route:

- HOW TO RUN:

1. Open Visual Studio and open file named "find_route.java" which is in the zipped folder.
3. Create object file
	"javac find_route.java"
4. Run the code
   Uninformed
	"java find_route input1.txt Bremen Kassel"
   Informed
	"java find_route input1.txt Bremen Kassel h_kassel.txt"