import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


// This class is what performs actions on the graph including creating a graph 
/* NOTE: You will notice that I have used System.exit(0) in different scenarios where user enters invalid input
 * This is to avoid from the program from crashing in case the returned results are being used for something 
 * in future updates. It is always better to be safe than sorry.
 */
public class Tracer {

	private Graph routeMap;

	Tracer(){

		this.routeMap=new Graph();

	}


	// Function can be used to get distance along any given route e.g., ABC,ABCD 
	private int getDistance(String route){

		// For validating input
		if(route==null || route.isEmpty())
			return -1;

		route=route.toUpperCase();

		int previous=0;
		int next=0;
		int totalDistance=0;

		//Checks whether the starting point is null or not
		if(routeMap.getNode(route.substring(0,1))!=null){

			while(next !=route.length()-1){

				String previousID=route.substring(previous++,++next);
				String nextID=route.substring(next,next+1);
				int currentDistance=routeMap.getNode(previousID).getDistanceToNode(nextID);

				//Checks whether the next Node is connected to previous or not . -1 = NO SUCH ROUTE
				if(currentDistance==-1){
					return -1;
				}

				else{
					totalDistance=totalDistance+currentDistance;
				}
			}
		}
		else
			return -1;

		return totalDistance;


	}

	// This method either returns "NO SUCH ROUTE" if the route doesn't exists or the total distance of the route returned from the above function as string format
	//NOTE: Above function is private therefore only this function canbe used for getting the total distance
	public String getTotalDistance(String route){

		if(this.getDistance(route)==-1)
			return "NO SUCH ROUTE";
		else
			return ""+this.getDistance(route);

	}

	public Graph createGraph(String filename){

		boolean checker=true;
		Scanner sc=new Scanner(System.in);

		// This do loop gives you a second chance in case the filename passed is invalid.
		do{

			try(BufferedReader br = new BufferedReader(new FileReader(filename))) 
			{

				checker=true;
				String line=br.readLine();
				line=line.substring(line.indexOf(":")+1);
				String[] coordinates=line.split(",");


				for(int run=0;run<coordinates.length;run++)
				{


					String coordinate=coordinates[run].trim();
					coordinate=coordinate.toUpperCase();


					// Ignores if the coordinate is not length of 3
					if(coordinate.length()==3)
					{

						// Ignores if the coordinate is not in the format of CharCharInt
						if(coordinate.substring(0,1).matches("[A-Z]") && coordinate.substring(1,2).matches("[A-Z]") && coordinate.substring(2,3).matches("[0-9]"))
						{


							String startingPoint=coordinate.substring(0,1);
							String endingPoint=coordinate.substring(1,2);
							int distanceInBetween=Integer.parseInt(coordinate.substring(2,3));

							// Suppose a coordinate is AB5.
							//Starting point is A
							//EndingPoint is B
							//distanceInBetween is 5


							// Doesn't adds in the graph if the node already exists
							if(!(this.routeMap.containsNode(startingPoint))){

								this.routeMap.addNode(new Node(startingPoint));
							}

							if(!(this.routeMap.containsNode(endingPoint))){

								this.routeMap.addNode(new Node(endingPoint));

								// Adds in the list of adjacent nodes of the starting point to the ending point
								this.routeMap.getNode(startingPoint).addinList(this.routeMap.getNode(endingPoint),distanceInBetween,endingPoint);
							}

							// Adds in the list of adjacent nodes of the starting point to the ending point even if the endingPoint node exists
							else
								this.routeMap.getNode(startingPoint).addinList(this.routeMap.getNode(endingPoint),distanceInBetween,endingPoint);


						}

					}

				}


				br.close();
				return this.routeMap;

			} 
			catch (FileNotFoundException e) {
				System.out.println("No file found");
				checker=false;
				//Scanner sc=new Scanner(System.in);
				System.out.println("Try again and don't forget to enter the extension of the file !! If you want to exit, type exit");
				System.out.print("Enter the filename:");
				filename = sc.next();// Don't forget the extension of the file !!!
				if(filename.toLowerCase().equals("exit"))
					System.exit(0);
				
				filename=filename.trim();
				

			} 
			catch (IOException e) {
				System.out.println("Something is wrong relating the file");
				checker=false;
				//Scanner sc=new Scanner(System.in);
				System.out.println("Try again and don't forget to enter the extension of the file !! If you want to exit, type exit");
				System.out.print("Enter the filename:");
				filename = sc.next();// Don't forget the extension of the file !!!
				if(filename.toLowerCase().equals("exit"))
					System.exit(0);
				filename=filename.trim();
				
			}
		}while(!checker);

		
		sc.close();
		return this.routeMap;
	}


	public Graph getGraph(){
		return this.routeMap;
	}

	// Checks if station x has at least one  outgoing connecting stations
	public boolean hasConnectingStations(String x){
		if(this.getGraph().getNode(x).getList().isEmpty()){
			System.out.println("No such route since Route "+x+" has no outgoing connecting stations");
			return false;
		}
		else
			return true;
	}

	// Checks if station x has at least one incoming connecting stations
	public boolean hasIncomingStations(String x){

		Iterator<String> allIdentifiers=this.getGraph().getMap().keySet().iterator();

		int finalCheck=this.getGraph().getMap().keySet().size();

		int counter=0;

		while(allIdentifiers.hasNext()){

			if(this.getDistance(allIdentifiers.next()+x)==-1){
				++counter;
			}

		}

		if(finalCheck==counter){

			System.out.println("No such route since Route "+x+" has no incoming connecting stations");
			return false;
		}

		else
			return true;

	}

	// This is the public function which will be used to calculate the minimum distance between two points
	// The purpose of this function is to make the life of user easier and to perform some 
	//validation checks before starting the operation.
	public String getMinimumDistanceBetweenTwoPoints(String start,String end){


		if(start.isEmpty()|| start==null || end.isEmpty() || end==null)
			return "Invalid Input";

		start=start.trim();
		start=start.toUpperCase();

		end=end.trim();
		end=end.toUpperCase();

		if(this.hasConnectingStations(start) && this.hasIncomingStations(end))
		{	
			return ""+this.minimumDistance(start, end,"");			
		}
		else
		{
			System.exit(0);
			return "";
		}

	}	

	/* 
  	The following two functions work together.Overall the whole operation is fairly complex since
	there is recursion happening inside recursion and the functions work in a way that each possible route is 
	considered
	The reason there are two functions for situations in which starting and ending point are same
	 */

	private int minimumDistance(String start,String end,String previous){

		// setting minimum Distance from starting to Infinity and the nearest Node to null
		int minDistance=Integer.MAX_VALUE;
		String nearestNode=null;

		// Gets the list of neighbouring nodes
		Iterator<String> neighbourNodes=this.getGraph().getNode(start).getList().keySet().iterator();

		// This is where it gets interesting. Starts iterating over the list of neighbouring nodes
		while(neighbourNodes.hasNext()){

			String x=neighbourNodes.next();

			// The following if loop avoids endless while loops like going from C TO D TO C and then going back to C
			// Also this if loop makes sure that the next node which is x, is connected going to at least 
			//one other node

			if(previous !=x && this.hasConnectingStations(x)){
				// First recursion call. This will happen for each node in the list above
				int newDistance=this.calculatingMinimumDistance(x,end,start);


				// This is the most important part of the whole calculation
				//if the distance calculated from above is less than the minDistance minDistance becomes newDistance
				//x in this case will be nearest node

				if(newDistance<minDistance){
					minDistance=newDistance;
					nearestNode=x;
					

				}

				// To break the loop once the below function reaches the it's destination
				if(newDistance==0){
					break;
				}
			}
			

		}

		/*
		 This is the distance from the start of the node the nearest of it's neighbors plus the total Distance from
			from all the recursion calls
		 */
		return minDistance+this.getDistance(start+nearestNode);
	}

	private int calculatingMinimumDistance(String next,String end,String previous){


		// This marks the end of the operation. Once the next node becomes equal to the final node it returns 0
		// because end is equal to next hence distance is zero
		if(next.equals(end)){

			return 0;

		}
		else{

			// Second recursion call to the function above
			return this.minimumDistance(next,end,previous);

		}

	}





	/* The two functions below works together and returns a list of all possible routes from
	 * startingPoint stoppingPoint regardless of the distance which have certain maximum number of stops or less
	 * 
	 */
	public ArrayList<String> gettingListOfTrips(String startingPoint,String stoppingPoint,int maxStops){

		/* This list is used to contain all the possible routes from startingPoint
		 * to a maximum number of stops or less.For example if starting point is A and maxStops is 4
		 * This will contain ABCDE , ABC , ABCD , ADCD etc but not something like ABCDEB since it is greater
		 * than 4 
		 */
		ArrayList<String> initialList= new ArrayList<String>();

		// Basic input validation

		if(startingPoint ==null || startingPoint.isEmpty() || maxStops<=0)
		{
			System.out.println("Invalid input");
			System.exit(0);

		}
		startingPoint=startingPoint.trim();
		startingPoint=startingPoint.toUpperCase();

		stoppingPoint=stoppingPoint.trim();
		stoppingPoint=stoppingPoint.toUpperCase();

		if(this.hasConnectingStations(startingPoint) && this.hasIncomingStations(stoppingPoint)){

			/* maxStops is passed as maxStops+1 because the function below starts considering the stations
			 * from the starting point
			 */
			this.listOfTrips(startingPoint,startingPoint,startingPoint,maxStops+1, initialList);

			/* You will notice the function ( the one below) used to generate this list uses recursion 
			 * and returns an empty string therefore this list contains empty strings which are removed over here.
			 */
			while(initialList.contains("")){

				initialList.remove("");

			}

			/* This will be the final filtered out list containing only routes from startingPoint 
			 * to endingPoint with maxStops or less
			 */
			ArrayList<String> finalList= new ArrayList<String>();

			for(int run=0;run<initialList.size();run++)
			{
				String current=initialList.get(run);

				if(current.substring(0,1).equals(startingPoint) && current.substring(current.length()-1).equals(stoppingPoint)){
					finalList.add(current);
				}
			}


			return finalList;
		}
		else{

			System.exit(0);
			return initialList;
		}

	}


	/* The purpose of this function is not return a meaningful string but to add items in the passed list
	 * through recursion
	 * It was very much possible to filter in just the specific items from startingPoint to endPoint
	 * for assignment purposes. The reason I didn't do that because
	 * in this way the program becomes more easier to maintain and update.
	 */
	private String listOfTrips(String start,String result,String initial,int maximumStops,ArrayList<String> list){

		// Stopping point of the function since result is stations that have passed
		if(result.length()==maximumStops){

			return "";

		}

		else{

			// Getting all the connecting nodes to the start node of the parameter
			Iterator<String> neighbourNodes=this.getGraph().getNode(start).getList().keySet().iterator();



			while(neighbourNodes.hasNext()){

				// X is one of the neighbor node of start
				String x=neighbourNodes.next();

				//To make sure that passed stations are stored
				String temp=result;

				// Adding the neighbor node to x . So for example result C becomes CD 
				result=result+x;

				// To avoid adding duplicates
				if(!list.contains(result)){
					list.add(result);
					list.add(this.listOfTrips(x, result, initial, maximumStops, list));
				}

				// This is so that when the function is coming back in the list result same as the start of the loop
				result=temp;


			}

			return "";

		}

	}

	// The following two functions are slightly different versions of the same two functions above
	public ArrayList<String> getListOfTripstoCertainDistance(String startingPoint,String stoppingPoint,int maxDistance){

		ArrayList<String> initialList= new ArrayList<String>();

		// Basic input validation

		if(startingPoint ==null || startingPoint.isEmpty() || maxDistance<=0)
		{
			System.out.println("Invalid input");
			System.exit(0);

		}
		startingPoint=startingPoint.trim();
		startingPoint=startingPoint.toUpperCase();

		stoppingPoint=stoppingPoint.trim();
		stoppingPoint=stoppingPoint.toUpperCase();

		if(this.hasConnectingStations(startingPoint) && this.hasIncomingStations(stoppingPoint)){


			this.listOfTripstoCertainDistance(startingPoint,startingPoint,startingPoint,maxDistance, initialList);

			/* You will notice the function ( the one below) used to generate this list uses recursion 
			 * and returns an empty string therefore this list contains empty strings which are removed over here.
			 */
			while(initialList.contains("")){

				initialList.remove("");

			}

			/* This will be the final filtered out list containing only routes from startingPoint 
			 * to endingPoint with distance less than maxDistance or less
			 * Also note that the initialList will contain some routes for which distances are equal to or greater
			 * than the maxDistance because those are the stopping points of the function below
			 * They are filtered out below
			 */
			ArrayList<String> finalList= new ArrayList<String>();

			for(int run=0;run<initialList.size();run++)
			{
				String current=initialList.get(run);

				if(current.substring(0,1).equals(startingPoint) && current.substring(current.length()-1).equals(stoppingPoint) && this.getDistance(current)<maxDistance){
					finalList.add(current);
				}
			}


			return finalList;
		}
		else{

			System.exit(0);
			return initialList;
		}

	}



	private String listOfTripstoCertainDistance(String start,String result,String initial,int maxDistance,ArrayList<String> list){

		/* Stopping point of the function since totalDistance of the route result is stations becomes greater
		 * than or equal to maxDistance
		 */
		if(this.getDistance(result)>=maxDistance){

			return "";

		}

		else{

			// Getting all the connecting nodes to the start node of the parameter
			Iterator<String> neighbourNodes=this.getGraph().getNode(start).getList().keySet().iterator();



			while(neighbourNodes.hasNext()){

				// X is one of the neighbor node of start
				String x=neighbourNodes.next();

				//To make sure that passed stations are stored
				String temp=result;

				// Adding the neighbor node to x . So for example result C becomes CD 
				result=result+x;

				// To avoid adding duplicates
				if(!list.contains(result)){
					list.add(result);
					list.add(this.listOfTripstoCertainDistance(x, result, initial, maxDistance, list));
				}

				// This is so that when the function is coming back in the list result same as the start of the loop
				result=temp;


			}

			return "";

		}

	}


}