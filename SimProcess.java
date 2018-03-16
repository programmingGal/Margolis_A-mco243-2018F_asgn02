package processSchedulingSimulationAsgn02;

// this class simulates a process

public class SimProcess implements IProcess
{
	private int pid;   // process id
	private String procName;
	private int ttlInstructions;
	private IRandomValueGenerator randObj;
	
   public SimProcess(int pid, String procName,int ttlInstructions, IRandomValueGenerator randObj)
   {
	   this.pid = pid;
	   this.procName = procName;
	   this.ttlInstructions=ttlInstructions;
	   this.randObj = randObj;
   }
   
   public int getPid()
   {
	   return pid;
   }
   
   public String getProcName()
   {
	   return procName;
   }
   
   
   
   //executes the next instruction and returns the ProcessState that the execution causes
   public ProcessState execute (int i)
   {
	   System.out.println("Process " + procName + ", PID: " + pid + " executing instruction: " + i);
	   
	   if (i >=ttlInstructions)
	   {
		   return ProcessState.FINISHED;
	   }
	   else 
	   {   
		 //for this simulation: process blocks with 15% probability from executing the next instruction
		   
		   boolean processBlocked = randObj.getTrueWithProbability(.15);  //process blocks with 15% probability from executing the next instruction 
		   
		   if (!processBlocked)
		   {
			   return ProcessState.READY;
		   }
		   else
		   {
			   return ProcessState.BLOCKED;
		   }
	   }
   }
   
   
   public int compareTo(IProcess otherProcess)
   {
	   if (this.pid == otherProcess.getPid())
			   {
		            return 0;
			   }
	   
	   else if (this.pid > otherProcess.getPid())
	   {
		   return 1;
	   }
	   else
	   {
		   return -1;
	   }
		   
   }
   
   
   
}
