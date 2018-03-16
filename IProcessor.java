package processSchedulingSimulationAsgn02;

//represents an interface for a processor object

public interface IProcessor 
{
   IProcess getCurrentRunningProcess();
   void setCurrentRunningProcess(IProcess p);
   
   int getCurrentExecutingInstruction();
   void setCurrentExecutingInstruction(int i);
   
   ProcessState executeNextInstruction();  // of current running process
   
   void setRegisterValue(int i,Integer val);   //sets specified register location to specified value
   int getRegisterValue();            // gets the value of specified register location
   
}
