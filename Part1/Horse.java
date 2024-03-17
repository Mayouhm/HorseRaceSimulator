
/**
 * Write a description of class Horse here.
 * 
 * @author Hamza Mayou 
 * @version 1
 */
public class Horse
{
    //Fields of class Horse
    private String name;
    private char symbol;
    private int distance;
    boolean fallen;
    private double confidence;
    
      
    //Constructor of class Horse
    /**
     * Constructor for objects of class Horse
     */
    public Horse(char horseSymbol, String horseName, double horseConfidence)
    {
        symbol = horseSymbol;
        name = horseName;
        confidence = horseConfidence;
        fallen = false;
    }
    
    
    
    //Other methods of class Horse
    public void fall()
    {
        fallen = true;
        return;
    }
    
    public double getConfidence()
    {
        return this.confidence;
    }
    
    public int getDistanceTravelled()
    {
        return this.distance;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public char getSymbol()
    {
        return this.symbol;
    }
    
    public void goBackToStart()
    {
        distance = 0;
        return;
    }
    
    public boolean hasFallen()
    {
        return this.fallen;
    }

    public void moveForward()
    {
        distance ++;
        return;
    }

    public void setConfidence(double newConfidence)
    {
        confidence = newConfidence;
        return;
    }
    
    public void setSymbol(char newSymbol)
    {
        symbol = newSymbol;
        return;
    }
}
