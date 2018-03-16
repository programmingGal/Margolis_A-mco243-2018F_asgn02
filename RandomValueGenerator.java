package processSchedulingSimulationAsgn02;

import java.util.Random;

public class RandomValueGenerator implements IRandomValueGenerator
{

       private Random randObj;
       
	   public RandomValueGenerator()
	   {
		   randObj = new Random();
	   }
	   
	   
	   //returns a random value
	   public int getNextInt()
	   {
		   return randObj.nextInt();
	   }
	   
	   //returns true or false with the probability that's passed in
	   public boolean getTrueWithProbability(double p)
	   {
		  
		   
		  double randGenerated =  randObj.nextDouble();
		  
		  if (randGenerated <= p)
		  {
			  return true;
		  }
		  
		  else
		  {
			  return false;
		  }
		   
		   
	   }
}
