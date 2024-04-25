package Part2;


import javax.swing.*;

import Part1.Race;
import Part1.Horse;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class HorseGraphics {
    static Horse[] horseArray;
    public static void main(String[] args) {
        JFrame frame = new JFrame("Horse Race");
        JTextArea textArea = new JTextArea(5, 30);
        textArea.setEditable(false);
        textArea.setFont(new Font("Helvetica Neue", Font.PLAIN, 30));

        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Horse Race");
        Font titleFont = new Font("Arial", Font.BOLD, 40);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);
        frame.add(titlePanel, BorderLayout.NORTH);

        JButton resetButton = new JButton("RESET");
        JButton startButton = new JButton("START");

        JLabel raceSizeLabel = new JLabel("Race Size:");
        JTextField raceSizeField = new JTextField(5);
        JButton insertButton = new JButton("Insert");

        JLabel addHorseLabel = new JLabel("Adjust Number of Horses:");
        JButton addHorseButton = new JButton("+");
        JButton removeHorseButton = new JButton("-");

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(startButton);
        buttonPanel.add(resetButton);

        JPanel sizeAdjuster = new JPanel(new GridLayout(2,3));
        sizeAdjuster.add(raceSizeLabel);
        sizeAdjuster.add(raceSizeField);
        sizeAdjuster.add(insertButton);

        sizeAdjuster.add(addHorseLabel);
        sizeAdjuster.add(addHorseButton);
        sizeAdjuster.add(removeHorseButton);

        frame.add(textArea, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.EAST);
        frame.add(sizeAdjuster, BorderLayout.WEST);


        ConsoleRedirect consoleRedirect = new ConsoleRedirect(textArea);

        horseArray = new Horse[6];

        Horse horse1 = new Horse('\u265E', "GLITTERHOOF", 0.78);
        Horse horse2 = new Horse('\u2658', "RAINBOW DASH", 0.5);
        Horse horse3 = new Horse('\u265B', "BOJACK", 0.65);
        Horse horse4 = new Horse('\u265A', "CLEVER HANS", 0.7);
        Horse horse5 = new Horse('\u2655', "THE HOUYHNHNM", 0.4);
        Horse horse6 = new Horse('\u2657', "NAPOLEON", 0.8);
        horseArray = addToHorseArray(horseArray, horse1, 1);
        horseArray = addToHorseArray(horseArray, horse2, 2);
        horseArray = addToHorseArray(horseArray, horse3, 3);
        horseArray = addToHorseArray(horseArray, horse4, 4);
        horseArray = addToHorseArray(horseArray, horse5, 5);
        horseArray = addToHorseArray(horseArray, horse6, 6);

        Race race1 = new Race(10);

        for (int i = 0; i < horseArray.length; i++) {
            race1.addHorse(horseArray[i], i+1);
        }

        horseArray = removeFromHorseArray(race1, horseArray, 3);
        /*
        race1.removeHorse(2);
        removeFromHorseArray(horseArray, 2);
        race1.removeHorse(3);
        removeFromHorseArray(horseArray, 3);
         */

        race1.resetRace();


        //Adds Horses at bottom
        setUpHorses(frame, horseArray);

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                race1.resetRace();
            }
        });

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                race1.resetRace();
                Thread raceThread = new Thread(new Runnable() {
                    public void run() {
                        if (race1.finished) {
                            race1.startRace();
                        }
                    }
                });
                raceThread.start();
            }
        });

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    race1.setRaceLength(Integer.parseInt(raceSizeField.getText()));
                    race1.resetRace();
                } catch (NumberFormatException n) {
                    System.out.println("Invalid input. Must be integer.");
                }
            }
        });
        final Horse[] horses1 = horseArray;
        addHorseButton.addActionListener(new ActionListener() {
            boolean randomise;
            int position = -1;
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < horses1.length; i++) {
                    if (horses1[i] == null) {
                        position = i + 1;
                    }
                }

                if (position != -1) {
                    JDialog randomiseOption = new JDialog(frame, "Randomise Horse?", true);
                    randomiseOption.setLayout(new FlowLayout());

                    JButton yesButton = new JButton("Yes");
                    yesButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            randomise = true;
                            randomiseOption.dispose();
                        }
                    });

                    JButton noButton = new JButton("No");
                    noButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            randomise = false;
                            randomiseOption.dispose();
                        }
                    });

                    randomiseOption.add(new JLabel("Do you want to randomise horse name?"));
                    randomiseOption.add(yesButton);
                    randomiseOption.add(noButton);

                    randomiseOption.setSize(500, 100);
                    randomiseOption.setLocationRelativeTo(frame);
                    randomiseOption.setVisible(true);
                    String name;
                    if (!randomise) {
                        name = JOptionPane.showInputDialog("What's the name of the horse?");
                    } else {
                        ArrayList<String> names = new ArrayList<>();

                        try (BufferedReader br = new BufferedReader(new FileReader("Part2/horseNames.txt"))) {
                            String line;
                            while ((line = br.readLine()) != null) {
                                names.add(line);
                            }
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        } catch (RuntimeException er) {

                        }

                        Random rand = new Random();
                        int randomIndex = rand.nextInt(names.size());
                        name = names.get(randomIndex);
                    }
                    String symbolStr = JOptionPane.showInputDialog("What symbol are you using? Must be one character!");
                    char symbol = 'X';
                    try {
                        symbol = symbolStr.charAt(0);
                    } catch (StringIndexOutOfBoundsException n) {
                        System.out.println("Must be a single character");
                    }
                    String confidenceStr = JOptionPane.showInputDialog("What's the confidence? Must be between 0 and 1.");
                    double confidence = 0.1;
                    try {
                        confidence = Double.parseDouble(confidenceStr);
                        if (confidence < 0 || confidence > 1) {
                            System.out.println("Invalid input. Must be between 0 and 1.");
                        }
                    } catch (NumberFormatException n) {
                        System.out.println("Invalid input. Try again!");
                    }
                    Horse horse = new Horse(symbol, name, confidence);
                    race1.addHorse(horse, position);
                    horseArray = addToHorseArray(horseArray, horse, position);
                    setUpHorses(frame, horseArray);
                    race1.resetRace();
                    arrayPrinter(horses1);
                }
            }
        });

        removeHorseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (horseArray[2] != null) {
                    String inputValue = JOptionPane.showInputDialog("Which Horse do you want to remove? Pick between 1 and 6.");
                    try {
                        int positionToRemove = Integer.parseInt(inputValue);
                        horseArray = removeFromHorseArray(race1, horseArray, positionToRemove);
                        setUpHorses(frame, horseArray);
                        race1.resetRace();

                    } catch (NumberFormatException n) {
                        System.out.println("Invalid input. Try again!");
                    }
                } else {
                    System.out.println("Can't remove any more horses");
                }

            }
        });

        frame.setSize(1500, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static Horse[] addToHorseArray(Horse[] horses, Horse horse, int position) {
        horses[position-1] = horse;
        return horses;
    }

    public static Horse[] removeFromHorseArray(Race race, Horse[] horses, int position) {
        if (position > 6 || position < 1) {
            System.out.println("Invalid input must be between 1 and 6 inclusive");
            return horses;
        }
        for (int i = position-1; i < 5;i++){
            horses[i]=horses[i+1];
        }
        horses[5] = null;
        race.removeHorse(position);
        return horses;
    }
    public static <T> void arrayPrinter(T[] givenArray) {
        String generatedString = "{";
        for (int i = 0; i < givenArray.length - 1; i++) {
            generatedString = generatedString + givenArray[i] + ", ";
        }
        generatedString = generatedString + givenArray[givenArray.length - 1] + "}";
        System.out.println(generatedString);
        return;
    }

    public static void setUpHorses(JFrame frame, Horse[] horses){
        JPanel horsePanel = new JPanel(new GridLayout(1, 6));
        String imageFilePath = "Part2\\images\\horse2.png";
        /*horse1.setIcon(imageFilePath);
        horse2.setIcon(imageFilePath);
        horse3.setIcon(imageFilePath);

        JLabel horse1Label = new JLabel();
        JLabel horse2Label = new JLabel();
        JLabel horse3Label = new JLabel();

        JLabel horse1TextLabel = new JLabel(horse1.getName());
        JLabel horse2TextLabel = new JLabel(horse2.getName());
        JLabel horse3TextLabel = new JLabel(horse3.getName());

        Font horseFont = new Font("Arial", Font.PLAIN, 20);

        horse1Label.setIcon(horse1.getIcon());
        horse2Label.setIcon(horse2.getIcon());
        horse3Label.setIcon(horse3.getIcon());

        horsePanel.add(horse1Label);
        horsePanel.add(horse1TextLabel);
        horsePanel.add(horse2Label);
        horsePanel.add(horse2TextLabel);
        horsePanel.add(horse3Label);
        horsePanel.add(horse3TextLabel);

        horse1TextLabel.setFont(horseFont);
        horse2TextLabel.setFont(horseFont);
        horse3TextLabel.setFont(horseFont);
        frame.add(horsePanel, BorderLayout.SOUTH);*/
        for (Horse horse : horses){
            if (horse != null ){
                horse.setIcon(imageFilePath);

                JLabel horseLabel = new JLabel();

                JLabel horseTextLabel = new JLabel(horse.getName());

                Font horseFont = new Font("Arial", Font.PLAIN, 20);

                horseLabel.setIcon(horse.getIcon());

                horsePanel.add(horseLabel);
                horsePanel.add(horseTextLabel);

                horseTextLabel.setFont(horseFont);

                frame.add(horsePanel, BorderLayout.SOUTH);
            }
        }
    }
}
