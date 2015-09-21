import java.util.ArrayList;

//This class is just used for assignment purposes
public class Questions {
	
	private Tracer tracer;
	
	Questions(String filename){
		
		tracer=new Tracer();
		tracer.createGraph(filename);
		
	}
	
	public String q1(){
		return tracer.getTotalDistance("ABC");
	}
	
	public String q2(){
		return tracer.getTotalDistance("AD");
	}
	
	public String q3(){
		return tracer.getTotalDistance("ADC");
	}
	
	public String q4(){
		return tracer.getTotalDistance("AEBCD");
	}
	
	public String q5(){
		return tracer.getTotalDistance("AED");
	}
	
	public int q6(){
		return tracer.gettingListOfTrips("C","C",3).size();
	}
	
	//Note: Question 7 uses the same logic as 6 but only stations with 4 stops are counted from A to C
	public int q7(){
		ArrayList<String> test=tracer.gettingListOfTrips("A","C",4);
		
		int result=0;
		
		for(int run=0;run<test.size();run++){
			
			if(test.get(run).length()==5)
				++result;
		}
		
		
		return result;
		
	}
	//
	public String q8(){
		
		return tracer.getMinimumDistanceBetweenTwoPoints("A","C");
	}
	
	public String q9(){
		
		return tracer.getMinimumDistanceBetweenTwoPoints("B","B");
	}
	
	public int q10(){
		
		return tracer.getListOfTripstoCertainDistance("C","C",30).size();
	}
}
