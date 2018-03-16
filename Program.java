package processSchedulingSimulationAsgn02;

//This program simulates an Operating System that takes care of  processes being given control of the processor at different times.

import java.util.ArrayList;
import java.util.LinkedList;



public class Program 
{
   public static void main(String [] args)
   {
	   IRandomValueGenerator randObj = new RandomValueGenerator();
	   
	   IProcessor processor = new SimProcessor(randObj);
	   
	   ArrayList<IProcess> processes = new ArrayList<IProcess>(); // used to keep track of all processes, when a process is finished it can free up the resources
	   
	   IProcess proc1 = new SimProcess(1,"Process1", 100,randObj);
	   IProcess proc2 = new SimProcess(2,"Process2", 289,randObj);
	   IProcess proc3 = new SimProcess(3,"Process3", 385,randObj);
	   IProcess proc4 = new SimProcess(4,"Process4", 277,randObj);
	   IProcess proc5 = new SimProcess(5,"Process5", 369,randObj);
	   IProcess proc6 = new SimProcess(6,"Process6", 400,randObj);
	   IProcess proc7 = new SimProcess(7,"Process7", 177,randObj);
	   IProcess proc8 = new SimProcess(8,"Process8", 220,randObj);
	   IProcess proc9 = new SimProcess(9,"Process9", 343,randObj);
	   IProcess proc10 = new SimProcess(10,"Process10", 199,randObj);
	   
	   processes.add(proc1);
	   processes.add(proc2);
	   processes.add(proc3);
	   processes.add(proc4);
	   processes.add(proc5);
	   processes.add(proc6);
	   processes.add(proc7);
	   processes.add(proc8);
	   processes.add(proc9);
	   processes.add(proc10);
	   
	   
	   
	   ProcessControlBlock pcb1 = new ProcessControlBlock(proc1);
	   ProcessControlBlock pcb2 = new ProcessControlBlock(proc2);
	   ProcessControlBlock pcb3 = new ProcessControlBlock(proc3);
	   ProcessControlBlock pcb4 = new ProcessControlBlock(proc4);
	   ProcessControlBlock pcb5 = new ProcessControlBlock(proc5);
	   ProcessControlBlock pcb6 = new ProcessControlBlock(proc6);
	   ProcessControlBlock pcb7= new ProcessControlBlock(proc7);
	   ProcessControlBlock pcb8 =new ProcessControlBlock(proc8);
	   ProcessControlBlock pcb9=new ProcessControlBlock(proc9);
	   ProcessControlBlock pcb10= new ProcessControlBlock(proc10);
	   
	   //we only allow each process to have the processor for a quantum of 5 (here 5 iterations)
	   final int QUANTUM = 5;
	   
	   ArrayList <ProcessControlBlock>  blockedProcesses = new ArrayList <ProcessControlBlock>();  //blocked cuz waiting for an event to happen
	   
	   //made a Linked list - but really need to add functionality (make it a priority queue) to make this useful in ordering the readyProcesses in a smarter way
	   LinkedList<ProcessControlBlock> readyProcesses = new LinkedList<ProcessControlBlock>(); // these are ready to run as soon as the processor frees up
	   
	   readyProcesses.add(pcb1);
	   readyProcesses.add(pcb2);
	   readyProcesses.add(pcb3);
	   readyProcesses.add(pcb4);
	   readyProcesses.add(pcb5);
	   readyProcesses.add(pcb6);
	   readyProcesses.add(pcb7);
	   readyProcesses.add(pcb8);
	   readyProcesses.add(pcb9);
	   readyProcesses.add(pcb10);
	   
	 int currentProcessExecutions = 0; //so each process shouldn't exceed the quantum
	 ProcessControlBlock currProcessPcb =null;
	   
	   //do 3000 iterations and each time either execute an instruction of the currentProcess or do a context switch
	   for (int i =1; i<3000;i++)
	   {
		   
				        if (processor.getCurrentRunningProcess()==null)  //continue until we have a running process
				        {
				        	
				        		 currProcessPcb = readyProcesses.remove();
				        		
				        	    processor.setCurrentRunningProcess(currProcessPcb.getProcess());
				        	
				        	
				        }
		   
		        
		        
		        
		        
		        
		        if (processor.getCurrentExecutingInstruction() ==0)   
		        {
		        processor.setCurrentExecutingInstruction(1);
		        }
		        
		   
		         //now we have executing process. Execute next instruction and may have to follow it with a context switch:
		        
		        
		        	
		        	System.out.print("Step " + i + ": ");
		        	ProcessState processState = processor.getCurrentRunningProcess().execute(processor.getCurrentExecutingInstruction());
		        	
		        	//wake up blocked processes with 30% probability
		        	
		        	for(int numBlocked =0 ; numBlocked < blockedProcesses.size(); numBlocked++)
	        		{
			        		if (randObj.getTrueWithProbability(.30))  //if it returns true, awaken the blocked process
			        		{
			        			
			        			readyProcesses.add(blockedProcesses.remove(numBlocked));
			        			numBlocked++;
			        			
			        		}
			        			
	        		}
		        	
		        			        	
		        	currentProcessExecutions++;   // increment each time this process executes (never going past 5)
		        	processor.setCurrentExecutingInstruction(processor.getCurrentExecutingInstruction() +1);
		        	
		        	//now check if context switch is needed (processor should start running a different process): if yes, do so. 
		        	//Otherwise, process remains on the processor and next iteration of the loop will execute its next instruction.
		        	if (processState != ProcessState.READY || currentProcessExecutions == QUANTUM)
		        	{  
		                 //increment i to go to the next step as long as i doesn't exceed 3000
		        		   if(i< 3000)
		        		   {
		        		     i++;
		        		   }
		        		   else
		        		   {
		        			   break;
		        		   }
		        		   
		        		   
		        		//perform context switch: 
				        	if (processState== ProcessState.FINISHED)  
				        	{
				        		System.out.println("*** Process completed ***");
				            }
				        	else if (processState == ProcessState.BLOCKED)
				        	{
				        		System.out.println("*** Process blocked ***");
				        		blockedProcesses.add(currProcessPcb);  // blocked process so put on blocked list
				        	}
				        	else 
				        	{
				        		System.out.println("*** Quantum Expired ***");
				        		readyProcesses.add(currProcessPcb);   //still has more instructions so put on ready list
				        	}
				        	
				        	//save info to this process' pcb
				        	currProcessPcb.setCurrInstruction(processor.getCurrentExecutingInstruction());
				        	currProcessPcb.setRegister1Value(processor.getRegisterValue());
				        	currProcessPcb.setRegister2Value(processor.getRegisterValue());
				        	currProcessPcb.setRegister3Value(processor.getRegisterValue());
				        	currProcessPcb.setRegister4Value(processor.getRegisterValue());
				        	
				        	System.out.println("Step " + i + ": Context Switch: Saving Process:  " + processor.getCurrentRunningProcess().getPid() +
				        			"\n\tInstruction: " + processor.getCurrentExecutingInstruction()+ " - R1: " + currProcessPcb.getRegister1Value() +", R2: "
				        			+ currProcessPcb.getRegister2Value() + ", R3: " + currProcessPcb.getRegister3Value() + ", R4: "+ currProcessPcb.getRegister4Value());
				        	
				        	// check if any ready processes to put onto the processor
				        	if(readyProcesses.isEmpty())
				        	{
						        		System.out.println("*** Processor is idle ***");
						        		if(blockedProcesses.isEmpty()) // no blocked processes too, so all are finished, stop iterating
						        		{
						        			System.out.println("*** All processes have completed.***");
						        			break;
						        		}
						        		
						        		else
						        		{
							        			while(readyProcesses.isEmpty())
							        			{
							        				//wake up blocked processes with 30% probability
							        				for(int numBlocked =0 ; numBlocked < blockedProcesses.size(); numBlocked++)
									        		{
											        		if (randObj.getTrueWithProbability(.30))  //if it returns true, awaken the blocked process
											        		{
											        			
											        			readyProcesses.add(blockedProcesses.remove(numBlocked));
											        			numBlocked++;
											        			
											        		}
											        			
									        		}
							    		        	
							        			}
							        			
							           }
				        	   }
				        	
				        		//restoring a ready process to the processor
				        		currProcessPcb = readyProcesses.remove();
				        		processor.setCurrentRunningProcess(currProcessPcb.getProcess());
				        		processor.setCurrentExecutingInstruction(currProcessPcb.getCurrInstruction());
				        		processor.setRegisterValue(0, currProcessPcb.getRegister1Value());
				        		processor.setRegisterValue(1, currProcessPcb.getRegister2Value());
				        		processor.setRegisterValue(2, currProcessPcb.getRegister3Value());
				        		processor.setRegisterValue(3, currProcessPcb.getRegister4Value());
				        		
				        		currentProcessExecutions =0;  //reset it for the new process that was just put on the processor
						        		
				        	
				        	
				        	System.out.println("\t\tRestoring Process: "+ currProcessPcb.getProcess().getPid()+
				        			"\n\tInstruction: " + processor.getCurrentExecutingInstruction()+ " - R1: " + currProcessPcb.getRegister1Value() +", R2: "
						        	+ currProcessPcb.getRegister2Value() + ", R3: " + currProcessPcb.getRegister3Value() + ", R4: "+ currProcessPcb.getRegister4Value());
				        	
				        	
				        	// now after done context switch, awaken blocked processes with 30% probability
				        	
				        	for(int numBlocked =0 ; numBlocked < blockedProcesses.size(); numBlocked++)
			        		{
					        		if (randObj.getTrueWithProbability(.30))  //if it returns true, awaken the blocked process
					        		{
					        			
					        			readyProcesses.add(blockedProcesses.remove(numBlocked));
					        			numBlocked++;
					        			
					        		}
					        			
			        		}
		        	
		        	
		        	   }//end if to see if context switch needed
		        	
		        	
		        	
		        }//end for loop
		   
		   
		   
		   
		   
		   
		   
		   
		   
	   }//end main
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	  
	   
	   
   }//end program

