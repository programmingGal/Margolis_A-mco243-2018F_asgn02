package processSchedulingSimulationAsgn02;


//this object contains meta information about a process

public class ProcessControlBlock implements Comparable<ProcessControlBlock>
{
    private IProcess process;  //the process this pcb is assigned to
    private int currInstruction;
    
    // to hold the values that each of the 4 registers are holding
    //made it Integer to allow for nulls if register is empty
    private Integer register1Val;
    private Integer register2Val;      
    private Integer register3Val;
    private Integer register4Val;
    
    public ProcessControlBlock(IProcess process)
    {
    	this.process = process;
    	currInstruction =0;
    	
    	register1Val =null;
    	register2Val = null;
    	register3Val  = null;
    	register4Val=null;
    	
    	
    }
    
    public IProcess getProcess()
    {
    	return process;
    }
    
    public void setCurrInstruction(int curr)
    {
    	currInstruction = curr;
    }
    
    public int getCurrInstruction()
    {
    	return currInstruction;
    }
    
    
    
    public void setRegister1Value(int val)
    {
    	register1Val = val;
    }
    
    public void setRegister2Value(int val)
    {
    	register2Val = val;
    }
    
    public void setRegister3Value(int val)
    {
    	register3Val = val;
    }
    
    public void setRegister4Value(int val)
    {
    	register4Val = val;
    }
    
    
    
    
    
    
    public Integer getRegister1Value()
    {
    	return register1Val;
    }
    
    
    public Integer getRegister2Value()
    {
    	return register2Val;
    }
    
    public Integer getRegister3Value()
    {
    	return register3Val;
    }
    
    
    public Integer getRegister4Value()
    {
    	return register4Val;
    }
    
    
    public int compareTo(ProcessControlBlock otherPcb)
    {
    	
    	
    	return (this.getProcess().compareTo(otherPcb.getProcess()));
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}


