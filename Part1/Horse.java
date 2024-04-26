package Part1;

import Part2.HorseInfo;

import javax.swing.*;
import java.awt.*;

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
        name = horseName.toUpperCase();
        confidence = Math.round(horseConfidence * 10.0) / 10.0;
        fallen = false;
        icon = new ImageIcon((new ImageIcon("Part2/images/horse2.png")).
                getImage().getScaledInstance(275, 275, Image.SCALE_SMOOTH));
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

    public void setName(String newName) {
        if (newName.toUpperCase().equals("TWILIGHT SPARKLE")){
            HorseInfo info = new HorseInfo(this);
            ImageIcon combinedAccecessories = info.combineImages
                    ("Part2/images/accessories/pegasus.png", "Part2/images/accessories/corn.png");
            setIcon(info.combineImages(combinedAccecessories));
        } else if (newName.toUpperCase().equals("INCITATUS")) {
            HorseInfo info = new HorseInfo(this);
            setIcon(info.combineImages("Part2/images/accessories/immortal.png"));
        }
        name = newName.toUpperCase();
        return;
    }

    public void setConfidence(double newConfidence)
    {
        /*Scanner s = new Scanner(System.in);
        while (newConfidence < 0 || newConfidence > 1) {
            System.out.println("Invalid number for " + this.getName() + ". Must be between 0 and 1");
            newConfidence = Double.parseDouble(s.nextLine());
        }*/
        if (newConfidence < 0) {
            newConfidence = 0.1;
        } else if (newConfidence > 1) {
            newConfidence = 0.9;
        }
        this.confidence = Math.round(newConfidence * 10.0) / 10.0;
        return;
    }

    public void setSymbol(char newSymbol)
    {
        symbol = newSymbol;
        return;
    }

    public void setIcon(String filePath)
    {
        Image img = (new ImageIcon(filePath)).getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        return;
    }

    public void setIcon(ImageIcon newIcon)
    {
        icon = newIcon;
        return;
    }
}
