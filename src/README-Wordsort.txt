Our program works by first generating a graph of all the vertices and making
edges between things that are next to each other in the order.  This becomes
the result that we have. 

The driver reads two words at a time in the dictionary, and compares them character by character, 
until a difference is found between them.  Once a difference is found, the program
automatically assumes that the character in the first word will be less than the value
in the second word.  This establishes the order.  Then it adds them as vertices to the graph data structure
called DirectedGraphPt2.java, and adds a directed edge from the first letter to the second letter. 
If a vertex has already been created, and if it already has an edge, that edge will be replaced by the new edge.
Finally it will output the order in a char array back to the program.

After finding the alphabetical order and putting it them in order in a character array, we evaluate
each word in the unsorted file, comparing two words character by character again, until a difference is
found. When found, we use a compareTo method that checks which character is smaller depending on our
alphabet. We use a bubble sort to sort the unsorted list with the compareTo method. Afterwards, we
create an output file with the sorted words.