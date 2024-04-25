package Part1;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.lang.Math;
import Part1.Horse;

/**
 * A three-horse race, each horse running in its own lane
 * for a given distance
 *
 * @author McFarewell
 * @version 2.0
 */
public class Race
{
    public boolean finished;
    private int raceLength;
    private Horse[] laneHorseArray;
    private Horse lane1Horse;
    private Horse lane2Horse;
    private Horse lane3Horse;

    /**
     * Constructor for objects of class Race
     * Initially there are no horses in the lanes
     *
     * @param distance the length of the racetrack (in metres/yards...)
     */
    public Race(int distance)
    {
        // initialise instance variables
        finished = true;
        raceLength = distance;
        laneHorseArray = new Horse[6];
        for (Horse laneHorse : laneHorseArray) {
            laneHorse = null;
        }
        /*
        lane1Horse = null;
        lane2Horse = null;
        lane3Horse = null;
         */
    }

    public void setRaceLength(int length) {
        raceLength = length;
    }
    /**
     * Adds a horse to the race in a given lane
     *
     * @param theHorse the horse to be added to the race
     * @param laneNumber the lane that the horse will be added to
     */
    public void addHorse(Horse theHorse, int laneNumber)
    {
        for (int i = 1; i <= 6; i++) {
            if (laneNumber == i) {
                laneHorseArray[i-1] = theHorse;
            } else {
                System.out.println("Cannot add horse to lane " + laneNumber + " because there is no such lane");
            }
        }
        /*
        if (laneNumber == 1)
        {
            laneHorseArray[0] = theHorse;
            lane1Horse = theHorse;
        }
        else if (laneNumber == 2)
        {
            laneHorseArray[1] = theHorse;
            lane2Horse = theHorse;
        }
        else if (laneNumber == 3)
        {
            laneHorseArray[2] = theHorse;
            lane3Horse = theHorse;
        }
        else if (laneNumber == 4)
        {
            laneHorseArray[3] = theHorse;
            lane3Horse = theHorse;
        }
        else if (laneNumber == 5)
        {
            laneHorseArray[4] = theHorse;
            lane3Horse = theHorse;
        }
        else if (laneNumber == 6)
        {
            laneHorseArray[5] = theHorse;
            lane3Horse = theHorse;
        }
        else
        {
            System.out.println("Cannot add horse to lane " + laneNumber + " because there is no such lane");
        }*/
    }

    public void removeHorse(int position) {
        if (position > 6 || position < 1) {
            System.out.println("Invalid input must be between 1 and 6 inclusive");
            return;
        }
        for (int i = position-1; i < 5;i++){
            laneHorseArray[i]=laneHorseArray[i+1];
        }
        laneHorseArray[5] = null;
        resetRace();
    }


    /**
     * Start the race
     * The horse are brought to the start and
     * then repeatedly moved forward until the
     * race is finished
     */
    public void startRace()
    {
        //declare a local variable to tell us when the race is finished
        finished = false;

        //reset all the lanes (all horses not fallen and back to 0).
        /*
        if (lane1Horse != null) {lane1Horse.goBackToStart();}
        if (lane2Horse != null) {lane2Horse.goBackToStart();}
        if (lane3Horse != null) {lane3Horse.goBackToStart();}*/
        for (Horse laneHorse : laneHorseArray) {
            if (laneHorse != null) {laneHorse.goBackToStart();}
        }

        while (!finished)
        {
            //move each horse
            for (Horse laneHorse : laneHorseArray) {
                if (laneHorse != null) {moveHorse(laneHorse);}
            }
            /*
            if (lane1Horse != null) {moveHorse(lane1Horse);}
            if (lane2Horse != null) {moveHorse(lane2Horse);}
            if (lane3Horse != null) {moveHorse(lane3Horse);}
             */

            //print the race positions
            printRace();

            //if any of the three horses has won the race is finished
            for (Horse laneHorse : laneHorseArray) {
                if (laneHorse != null ){
                    if (raceWonBy(laneHorse)) {
                        printRace();
                        System.out.println("And the winner is " + laneHorse.getName());
                        finished = true;
                        break;
                    }
                }
            }
            boolean allFallen = true;
            for (Horse laneHorse : laneHorseArray) {
                if (laneHorse != null ){
                    if (!laneHorse.hasFallen()) {
                        allFallen = false;
                    }
                }
            }
            if (allFallen) {
                printRace();
                System.out.println("All horses have fallen. No winner");
                finished = true;
            }
            /*
            if (lane1Horse != null && raceWonBy(lane1Horse))
            {
                printRace();
                System.out.println("And the winner is " + lane1Horse.getName());
                finished = true;
            } else if (lane2Horse != null && raceWonBy(lane2Horse))
            {
                printRace();
                System.out.println("And the winner is " + lane2Horse.getName());
                finished = true;
            } else if (lane3Horse != null && raceWonBy(lane3Horse))
            {
                printRace();
                System.out.println("And the winner is " + lane3Horse.getName());
                finished = true;
            }

            if (lane1Horse.hasFallen() && lane2Horse.hasFallen() && lane3Horse.hasFallen()) {
                printRace();
                System.out.println("All horses have fallen. No winner");
                finished = true;
            }
            */

            //wait for 100 milliseconds
            try{
                TimeUnit.MILLISECONDS.sleep(100);
            }catch(Exception e){}
        }
    }

    /**
     * Randomly make a horse move forward or fall depending
     * on its confidence rating
     * A fallen horse cannot move
     *
     * @param theHorse the horse to be moved
     */
    private void moveHorse(Horse theHorse)
    {
        //if the horse has fallen it cannot move,
        //so only run if it has not fallen

        if  (!theHorse.hasFallen())
        {
            //the probability that the horse will move forward depends on the confidence;
            if (Math.random() < theHorse.getConfidence())
            {
                theHorse.moveForward();
            }

            //the probability that the horse will fall is very small (max is 0.1)
            //but will also will depends exponentially on confidence
            //so if you double the confidence, the probability that it will fall is *2
            if (Math.random() < (0.1*theHorse.getConfidence()*theHorse.getConfidence()))
            {
                theHorse.setConfidence(theHorse.getConfidence()-0.1);
                theHorse.fall();
            }
        }
    }

    /**
     * Determines if a horse has won the race
     *
     * @param theHorse The horse we are testing
     * @return true if the horse has won, false otherwise.
     */
    private boolean raceWonBy(Horse theHorse)
    {
        if (theHorse.getDistanceTravelled() == raceLength)
        {
            theHorse.setConfidence(theHorse.getConfidence()+0.1);
            return true;
        }
        else
        {
            return false;
        }
    }

    /***
     * Print the race on the terminal
     */
    private void printRace()
    {
        System.out.print('\u000C');  //clear the terminal window

        multiplePrint('=',raceLength+3); //top edge of track
        System.out.println();

        for (Horse laneHorse : laneHorseArray) {
            if (laneHorse != null){
                printLane(laneHorse);
                System.out.println();
            }
        }
        /*
        if (lane1Horse != null) {
            printLane(lane1Horse);
            System.out.println();
        }

        if (lane2Horse != null) {
            printLane(lane2Horse);
            System.out.println();
        }

        if (lane3Horse != null) {
            printLane(lane3Horse);
            System.out.println();
        }
         */


        multiplePrint('=',raceLength+3); //bottom edge of track
        System.out.println();
    }

    /**
     * print a horse's lane during the race
     * for example
     * |           X                      |
     * to show how far the horse has run
     */
    private void printLane(Horse theHorse)
    {
        //calculate how many spaces are needed before
        //and after the horse
        int spacesBefore = theHorse.getDistanceTravelled();
        int spacesAfter = raceLength - theHorse.getDistanceTravelled();

        //print a | for the beginning of the lane
        System.out.print('|');

        //print the spaces before the horse
        multiplePrint(' ',spacesBefore);

        //if the horse has fallen then print dead
        //else print the horse's symbol
        if(theHorse.hasFallen())
        {
            System.out.print('\u274C');
        }
        else
        {
            System.out.print(theHorse.getSymbol());
        }

        //print the spaces after the horse
        multiplePrint(' ',spacesAfter);

        //print the | for the end of the track
        System.out.print('|');
        System.out.print(" " + theHorse.getName() + " (Current Confidence " + theHorse.getConfidence() + ")");
    }


    /***
     * print a character a given number of times.
     * e.g. printmany('x',5) will print: xxxxx
     *
     * @param aChar the character to Print
     */
    private void multiplePrint(char aChar, int times)
    {
        int i = 0;
        while (i < times)
        {
            System.out.print(aChar);
            i = i + 1;
        }
    }

    public void resetRace() {
        //reset all the lanes (all horses not fallen and back to 0).
        for (Horse laneHorse : laneHorseArray) {
            if (laneHorse != null) {laneHorse.goBackToStart();}
        }
        /*
        if (lane1Horse != null) {lane1Horse.goBackToStart();}
        if (lane2Horse != null) {lane2Horse.goBackToStart();}
        if (lane3Horse != null) {lane3Horse.goBackToStart();}
         */
        //check if fallen
        for (Horse laneHorse : laneHorseArray) {
            if (laneHorse != null) {
                if (laneHorse.hasFallen()) {
                    laneHorse.unfall();
                }
            }
        }
        /*
        if (lane1Horse != null) {
            if (lane1Horse.hasFallen()) {
                lane1Horse.unfall();
            }
        }
        if (lane2Horse != null) {
            if (lane2Horse.hasFallen()) {
                lane2Horse.unfall();
            }
        }
        if (lane3Horse != null) {
            if (lane3Horse.hasFallen() ) {
                lane3Horse.unfall();
            }
        }
         */
        printRace();
    }
}
