package processSchedulingSimulationAsgn02;

public interface IRandomValueGenerator 
{
    int getNextInt();  //returns random integer value
    boolean getTrueWithProbability(double p);
}
