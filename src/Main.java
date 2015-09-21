import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
// The above libraries are here for manual testing of the program

public class Main {
		
	public static void main(String[]args){
	
		
		
		// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ //
		
		/*Alternatively you can supply file name through command line by uncommenting the following four lines 
		 * and commenting out the first line below the commented lines
		 */
		
		/*
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter filename: ");
		String filename = sc.next();// Don't forget the extension of the file !!!
		Questions questions=new Questions(filename.trim());
		sc.close();
		*/
		
		// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@//
		
		Questions questions=new Questions("file.txt");
		
		System.out.println("Output #1: "+questions.q1());
		System.out.println("Output #2: "+questions.q2());
		System.out.println("Output #3: "+questions.q3());
		System.out.println("Output #4: "+questions.q4());
		System.out.println("Output #5: "+questions.q5());
		System.out.println("Output #6: "+questions.q6());
		System.out.println("Output #7: "+questions.q7());
		System.out.println("Output #8: "+questions.q8());
		System.out.println("Output #9: "+questions.q9());
		System.out.println("Output #10: "+questions.q10());
		
		System.out.println("============");
		
		// #################################################################### //
		
		// The following commented out lines had been used to manually test the code. You can also test 
		// the essential functions by first uncommenting the first two lines below. 
		
		//Tracer tracer=new Tracer();
		
		//tracer.createGraph("file.txt");
		
		//System.out.println(tracer.getMinimumDistanceBetweenTwoPoints("C","C"));
		
		/*ArrayList<String> list= tracer.gettingListOfTrips("C","c ",3);
		
		for(int run=0;run<list.size();run++){
				System.out.println(list.get(run));
		}*/
			
		
		/*String d=tracer.getTotalDistance("AEBCD");
		
		System.out.println("Distance AEBCD is :"+d);
		 */		
		
		
		
		/*ArrayList<String> secondlist= tracer.getListOfTripstoCertainDistance("C","c ",30);
		
		for(int run=0;run<secondlist.size();run++){
				System.out.println(secondlist.get(run));
		}
		*/
	
		
		// ################################################################### //
		
		
		 
	}
}
