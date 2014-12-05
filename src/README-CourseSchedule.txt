This project was accomplished using a slightly modified directed, acyclic graph
that was implemented as an adjacency list.
The graph works by reading in input and for each class generates the prereqs and 
adds an edge between them. Then increments the number of prereqs for that course.
Once the entire graph is populated, we call generateCourseSchedule, which calls 
getSemester(), which removes all of the vertices that have 0 prereqs.  It then deletes
all the related edges to those vertices from the other vertices.  This recurs as long
as there are vertices in the graph and it returns the string of the classes per 
semester.