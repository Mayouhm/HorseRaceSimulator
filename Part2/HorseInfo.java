package Part2;

import Part1.Horse;

import javax.swing.*;
import java.awt.*;

public class HorseInfo {
    Horse horse;
    public HorseInfo(Horse kHorse) {
        horse = kHorse;
    }
    public void examineHorse() {
        JFrame frame = new JFrame("Horse Info");

        JPanel infoPanel = new JPanel(new GridLayout(3, 1));

        JLabel nameLabel = new JLabel("Name: " + horse.getName());
        JLabel symbolLabel = new JLabel("Symbol: " + horse.getSymbol());
        JLabel confidenceLabel = new JLabel("Confidence: " + horse.getConfidence());

        Font labelFont = new Font("Helvetica Neue", Font.BOLD, 20);

        nameLabel.setFont(labelFont);
        symbolLabel.setFont(labelFont);
        confidenceLabel.setFont(labelFont);

        infoPanel.add(nameLabel);
        infoPanel.add(symbolLabel);
        infoPanel.add(confidenceLabel);

        frame.add(infoPanel, BorderLayout.CENTER);

        JPanel imagePanel = new JPanel();
        JLabel imageLabel = new JLabel();
        String filePath = "Part2/images/horse2.png";
        Image img = (new ImageIcon(filePath)).getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        Icon icon = new ImageIcon(img);
        imageLabel.setIcon(icon);

        imagePanel.add(imageLabel);

        frame.add(imagePanel, BorderLayout.EAST);


        frame.setIconImage((new ImageIcon("Part2/images/horse2.png")).getImage());
        frame.setSize(500, 500);
        frame.setVisible(true);


        JLabel horseLabel = new JLabel("Part2/");
    }

    public static void main(String[] args) {

    }
}
