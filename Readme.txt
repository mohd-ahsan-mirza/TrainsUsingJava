- First of all I would like to thank you for giving me the opportunity to join ThoughtWorks and also for this assignment.I
  throughly enjoyed doing my chosen problem.

- The design of the overall train problem program is simple and yet is complex at the same time. It is simple because I have separted
  all the different elements involved in seperate classes and is complex because of the whole process involved behind some of the 
  essential action methods and interactions between different methods and classes.

- There are four different classes in the solution. The class Questions is just for assignment purposes and pretty straightforward.

- Please open the relationships image file located in the file. It is a rough illustration of relationships involved between different 
  classes. The class Node and Graph has a aggregation relationship since a Graph can exist without Nodes ( A graph can be empty basically)
  As you can see and as you will observe when you will look in the tracer class,a tracer needs to have a graph class to exist which
  can be collaborated when you look at it's constructor, therefore they both have a composition relationship.

- Nodes are basically the edges of the graph. The properties includes a label for example A,B etc a hashmap containing all the adjacent
  nodes and another hashmap containing the distances from the neighbour nodes. Please note that the nodes in these maps have been 
  shallow copied on purpose to reflect any changes made on any node everywhere it is contained.

- Graph in simple terms is collection of all the nodes involved 

- Tracer as the name suggests is the class that performs all the actions on the graph, so all the main functions are actually 
  in that class.

- There are safeguards in the solution to protect it from crashing in case of invalid inputs 

- There are detailed comments in the code explaining how everything works

- Running the application is very simple.I used eclipse to develop this program. Just unzip the files and run the program.The main 
  function already has all the functions required for the program to produce output from the sample test data that was provided

- The test file is in the main folder. Don't move it anywhere else !!. It won't crash the program(another safeguard) but then
  you will have to enter the filename from the console and the program won't terminate until it recieves the right unless you type in
  exit (Regardless of the case)

- The program has been designed in way that it is to maintain and update for furthur updates.

- While designing this program, my goal wasn't primarily to just answer the questions provided in the program but to create a program 
  that is dynamic in nature and functions can be used for different purposes . An example of this would be the the function used in the
  sixth and the seventh question is the same but in case of the seventh , there is more filtering involved at the final stage

 



 		
  