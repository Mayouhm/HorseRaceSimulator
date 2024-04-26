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
import java.util.Arrays;
import java.util.Random;

public class HorseGraphics {
    static Horse[] horseArray;
    static JPanel horsePanel = new JPanel(new GridLayout(1, 3));
    public static void main(String[] args) {
        startRaceGUI();
    }

    public static void startRaceGUI() {
        JFrame frame = new JFrame("Horsin Around");
        JTextArea textArea = new JTextArea(5, 30);
        textArea.setEditable(false);
        textArea.setFont(new Font("Helvetica Neue", Font.PLAIN, 30));

        JPanel titlePanel = new JPanel();
        /*
        JLabel titleLabel = new JLabel("Horse Race");
        Font titleFont = new Font("Arial", Font.BOLD, 40);
        titleLabel.setFont(titleFont);
         */
        JLabel titleLabel = new JLabel();
        Icon icon = new ImageIcon("Part2/images/title.png");
        titleLabel.setIcon(icon);
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

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
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
        horseArray = addToHorseArray(horseArray, horse1, 1);
        horseArray = addToHorseArray(horseArray, horse2, 2);
        horseArray = addToHorseArray(horseArray, horse3, 3);

        Race race1 = new Race(10);

        for (int i = 0; i < horseArray.length; i++) {
            race1.addHorse(horseArray[i], i+1);
        }
        race1.resetRace();

        //Adds Horses at bottom
        setUpHorses(frame, horsePanel, horseArray);

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
                    if (Integer.parseInt(raceSizeField.getText()) <= 35 && Integer.parseInt(raceSizeField.getText()) > 0) {
                        race1.setRaceLength(Integer.parseInt(raceSizeField.getText()));
                        race1.resetRace();
                    } else {
                        System.out.println("This is in inappropriate length. Must be between 1 and 35!");
                    }
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
                if (horses1[5] != null) {
                    System.out.println("Too many horses");
                    return;
                }
                for (int i = 0; i < horses1.length; i++) {
                    if (horses1[i] == null) {
                        position = i + 1;
                        break;
                    }
                }

                if (position != -1) {
                    JFrame randFrame = new JFrame("Horse Name");
                    randFrame.setSize(250, 125);
                    randFrame.setLocationRelativeTo(null);

                    JPanel randPanel = new JPanel();
                    randPanel.setLayout(new FlowLayout());

                    JLabel nameLabel = new JLabel("What's the name of the horse?");
                    JTextField nameField = new JTextField(15);
                    JButton randomizeButton = new JButton("R");
                    JButton okButton = new JButton("OK");
                    JButton cancelButton = new JButton("Cancel");

                    randPanel.add(nameLabel);
                    randPanel.add(nameField);
                    randPanel.add(randomizeButton);
                    randPanel.add(okButton);
                    randPanel.add(cancelButton);

                    randFrame.setIconImage((new ImageIcon("Part2/images/horse2.png")).getImage());
                    randFrame.add(randPanel);
                    randFrame.setVisible(true);
                    Horse horse = new Horse('x', "na", 0.5);

                    randomizeButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
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
                            String name = names.get(randomIndex);
                            nameField.setText(name);
                        }
                    });

                    okButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String name = nameField.getText();;
                            if (name.equals("")) {
                                name = "NEMO";
                            }
                            horse.setName(name);

                            randFrame.dispose();

                            JFrame symbolFrame = new JFrame("Horse Name");
                            symbolFrame.setSize(325, 125);
                            symbolFrame.setLocationRelativeTo(null);

                            JPanel symbolPanel = new JPanel();
                            symbolPanel.setLayout(new FlowLayout());

                            JLabel symbolLabel = new JLabel("What symbol are you using? Must be one character!");
                            JTextField symbolField = new JTextField(20);
                            JButton symbRandomiseButton = new JButton("R");
                            JButton okButton = new JButton("OK");
                            JButton cancelButton = new JButton("Cancel");

                            symbolPanel.add(symbolLabel);
                            symbolPanel.add(symbolField);
                            symbolPanel.add(symbRandomiseButton);
                            symbolPanel.add(okButton);
                            symbolPanel.add(cancelButton);

                            symbolFrame.setIconImage((new ImageIcon("Part2/images/knightqueen.png")).getImage());
                            symbolFrame.add(symbolPanel);
                            symbolFrame.setVisible(true);

                            symbRandomiseButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    ArrayList<String> symbols = new ArrayList<>();

                                    try (BufferedReader br = new BufferedReader(new FileReader("Part2/horseSymbols.txt"))) {
                                        String line;
                                        while ((line = br.readLine()) != null) {
                                            symbols.add(line);
                                        }
                                    } catch (IOException ex) {
                                        throw new RuntimeException(ex);
                                    } catch (RuntimeException er) {

                                    }

                                    Random rand = new Random();
                                    int randomIndex = rand.nextInt(symbols.size());
                                    String symbol = symbols.get(randomIndex);
                                    symbolField.setText(symbol);
                                }

                            });

                            okButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    char symbol = '\u297E';
                                    try {
                                        symbol = symbolField.getText().charAt(0);;
                                    } catch (StringIndexOutOfBoundsException s) {
                                        System.out.println("Must be a single character");
                                    }
                                    horse.setSymbol(symbol);

                                    symbolFrame.dispose();

                                    String confidenceStr = JOptionPane.showInputDialog("What's the confidence? Must be between 0 and 1.");
                                    double confidence = 0.5;
                                    try {
                                        confidence = Double.parseDouble(confidenceStr);
                                        if (confidence < 0 || confidence > 1) {
                                            System.out.println("Invalid input. Must be between 0 and 1.");
                                        }
                                    } catch (NumberFormatException n) {
                                        System.out.println("Invalid input. Try again!");
                                    }
                                    horse.setConfidence(confidence);
                                    race1.addHorse(horse, position);
                                    horseArray = addToHorseArray(horseArray, horse, position);
                                    setUpHorses(frame, horsePanel, horseArray);
                                    race1.resetRace();
                                }
                            });

                            cancelButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    symbolFrame.dispose();
                                }
                            });
                        }
                    });
                    cancelButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            randFrame.dispose();
                        }
                    });
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
                        setUpHorses(frame, horsePanel, horseArray);
                        race1.resetRace();
                    } catch (NumberFormatException n) {
                        System.out.println("Invalid input. Try again!");
                    }
                } else {
                    System.out.println("Can't remove any more horses");
                }
            }
        });

        titlePanel.setBackground(new Color(252, 182, 3));
        textArea.setBackground(new Color(255, 181, 185));


        frame.setIconImage((new ImageIcon("Part2/images/horse2.png")).getImage());
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
    public static void arrayPrinter(Horse[] givenArray) {
        String generatedString = "{";
        for (int i = 0; i < givenArray.length - 1; i++) {
            generatedString = generatedString + givenArray[i] + ", ";
        }
        generatedString = generatedString + givenArray[givenArray.length - 1] + "}";
        System.out.println(generatedString);
        return;
    }

    public static JPanel setUpHorses(JFrame frame, JPanel horsePanel, Horse[] horses){
        horsePanel.removeAll();
        for (Horse horse : horses){
            if (horse != null ){
                JButton horseButton = new JButton(horse.getName());
                horseButton.setPreferredSize(new Dimension(horseButton.getPreferredSize().width, 150));
                horsePanel.add(horseButton);
                frame.add(horsePanel, BorderLayout.SOUTH);
                horseButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        HorseInfo info = new HorseInfo(horse);
                        info.examineHorse();
                    }
                });

            }
        }
        return horsePanel;
    }
}