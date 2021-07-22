# Artificial-Intelligence
Implement a search algorithm that can find a route between any two cities.

find_route input_filename origin_city destination_city heuristic_filename

An example command line is:

find_route input1.txt Bremen Kassel (For doing Uninformed search)
or
find_route input1.txt Bremen Kassel h_kassel.txt (For doing Informed search)

If heuristic is not provided then program must do uninformed search. Argument input_filename is the name of a text file such as input1.txt, that describes road connections between cities in some part of the world. For example, the road system described by file input1.txt can be visualized in Figure 1 shown above. You can assume that the input file is formatted in the same way as input1.txt: each line contains three items. The last line contains the items "END OF INPUT", and that is how the program can detect that it has reached the end of the file. The other lines of the file contain, in this order, a source city, a destination city, and the length in kilometers of the road connecting directly those two cities. Each city name will be a single word (for example, we will use New_York instead of New York), consisting of upper and lowercase letters and possibly underscores.

IMPORTANT NOTE: MULTIPLE INPUT FILES WILL BE USED TO GRADE THE ASSIGNMENT, FILE input1.txt IS JUST AN EXAMPLE. YOUR CODE SHOULD WORK WITH ANY INPUT FILE FORMATTED AS SPECIFIED ABOVE.

The program will compute a route between the origin city and the destination city, and will print out both the length of the route and the list of all cities that lie on that route. It should also display the number of nodes expanded and nodes generated. For example,

find_route input1.txt Bremen Kassel

should have the following output:

Nodes Popped: 12
Nodes Expanded: 6
Nodes Generated: 20
Distance: 297.0 km
Route:
Bremen to Hannover, 132.0 km
Hannover to Kassel, 165.0 km

and

find_route input1.txt London Kassel

should have the following output:

Nodes Popped: 7
Nodes Expanded: 4
Nodes Generated: 7
Distance: Infinity
Route:
None


If a heuristic file is provided then program must perform Informed search. The heuristic file gives the estimate of what the cost could be to get to the given destination from any start state (note this is just an estimate). In this case the command line would look like

find_route input1.txt Bremen Kassel h_kassel.txt

Here the last argument contains a text file what has the heuristic values for every state wrt the given destination city (note different destinations will need different heuristic values). For example, you have been provided a sample file h_kassel.txt which gives the heuristic value for every state (assuming kassel is the goal). Your program should use this information to reduce the number of nodes it ends up expanding. Other than that, the solution returned by the program should be the same as the uninformed version. For example,

find_route input1.txt Bremen Kassel h_kassel.txt

should have the following output:

Nodes Popped: 3
Nodes Expanded: 2
Nodes Generated: 8
Distance: 297.0 km
Route:
Bremen to Hannover, 132.0 km
Hannover to Kassel, 165.0 km
