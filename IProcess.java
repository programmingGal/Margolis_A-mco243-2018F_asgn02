package processSchedulingSimulationAsgn02;

public interface IProcess 
{
  int getPid(); // gets the process id of a process
  String getProcName(); //gets process name
  ProcessState execute (int i); //executes instruction i of the process and returns the ProcessState that results from that execution
  int compareTo(IProcess otherPcb);
  
}
