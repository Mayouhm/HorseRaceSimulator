package Part2;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * This generates the application for the Horse Racing
 * 
 * @author Hamza Mayou 
 * @version 0.1
 */

public class HorseGraphics {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Horse Race");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Horse Race");
        Font titleFont = new Font("Arial", Font.BOLD, 40);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);
        frame.add(titlePanel, BorderLayout.NORTH);

        JPanel horsePanel = new JPanel(new GridLayout(3, 1));
        JLabel horse1Label = new JLabel("HORSE 1");
        JLabel horse2Label = new JLabel("HORSE 2");
        JLabel horse3Label = new JLabel("HORSE 3");
        horsePanel.add(horse1Label);
        horsePanel.add(horse2Label);
        horsePanel.add(horse3Label);
        frame.add(horsePanel, BorderLayout.WEST);

        frame.setVisible(true);
    }
}