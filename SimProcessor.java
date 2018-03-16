package processSchedulingSimulationAsgn02;

//this class simulates a processor

public class SimProcessor implements IProcessor
{
   private IRandomValueGenerator randObj;
   private IProcess currentProcess;
   private Integer [] registers;   //made it Integer and not int so will allow nulls for empty registers
   private int currInstruction;
   
   
   public SimProcessor(IRandomValueGenerator randObj)
   {
	   this.randObj = randObj;
	   registers = new Integer [4];
	   currInstruction =0;
   }
   
   public void setCurrentRunningProcess(IProcess proc)
   {
	   currentProcess = proc;
   }
   
   public IProcess getCurrentRunningProcess()
   {
	   return currentProcess;
   }
   
   public void setCurrentExecutingInstruction(int i)
   {
	   currInstruction = i;
   }
   
   public int getCurrentExecutingInstruction()
   {
	   return currInstruction;
   }
   
   
   //puts the value into the specified register location
   public void setRegisterValue(int i, Integer val)
   {
	   registers[i]=val;
   }
   
   
   // returns the value of the specified register location: (for simulation - returns a random value)
   
   public int getRegisterValue()
   {
	   return randObj.getNextInt();
   }
   
   
   // executes next instruction of current process and returns the resulted state of the currentProcess
   
   public ProcessState executeNextInstruction()
   {
	   ProcessState pStateAfterExecution = currentProcess.execute(currInstruction);
	   currInstruction++;
	   
	   return pStateAfterExecution;
   }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
}
