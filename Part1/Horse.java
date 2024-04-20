package Part1;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

/**
 * This holds the information for Horse objects
 *
 * @author Hamza Mayou
 * @version 1.2
 */
public class Horse
{
    //Fields of class Horse
    private String name;
    private char symbol;
    private int distance;
    boolean fallen;
    private double confidence;
    private ImageIcon icon;


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

    public void unfall()
    {
        fallen = false;
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

    public ImageIcon getIcon()
    {
        return this.icon;
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
        Scanner s = new Scanner(System.in);
        while (newConfidence < 0 || newConfidence > 1) {
            System.out.println("Invalid number for " + this.getName() + ". Must be between 0 and 1");
            newConfidence = Double.parseDouble(s.nextLine());
        }
        this.confidence = newConfidence;
        return;
    }

    public void setSymbol(char newSymbol)
    {
        symbol = newSymbol;
        return;
    }

    public void setIcon(String filePath)
    {
        Image img = (new ImageIcon(filePath)).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        return;
    }
}
