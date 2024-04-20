package Part2;


import javax.swing.*;
import Part1.Race;
import Part1.Horse;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HorseGraphics {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Horse Race");
        JTextArea textArea = new JTextArea(40, 80);
        textArea.setEditable(false);
        textArea.setFont(new Font("Helvetica Neue", Font.PLAIN, 30));

        JButton resetButton = new JButton("RESET");
        JButton startButton = new JButton("START");

        JButton lengthInput = new JButton("START");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1));
        buttonPanel.add(startButton);
        buttonPanel.add(resetButton);

        frame.add(textArea, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.EAST);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        ConsoleRedirect consoleRedirect = new ConsoleRedirect(textArea);

        Horse horse1 = new Horse('\u265E', "GLITTERHOOF", 0.78);
        Horse horse2 = new Horse('\u2658', "RAINBOW DASH", 0.5);
        Horse horse3 = new Horse('\u265B', "BOJACK", 0.65);

        Race race1 = new Race(10);
        race1.addHorse(horse1, 1);
        race1.addHorse(horse2, 2);
        race1.addHorse(horse3, 3);
        race1.startRace();

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
                        race1.startRace();
                    }
                });
                raceThread.start();
            }
        });
    }
}
