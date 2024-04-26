package Part2;

import Part1.Horse;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

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

        JPanel imagePanel = new JPanel(new BorderLayout());
        JLabel imageLabel = new JLabel();
        Image img = (horse.getIcon()).getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        Icon icon = new ImageIcon(img);
        imageLabel.setIcon(icon);

        imagePanel.add(imageLabel, BorderLayout.NORTH);

        JButton customiseButton = new JButton("Randomise Image");
        customiseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> imageNames = new ArrayList<>();

                try (BufferedReader br = new BufferedReader(
                        new FileReader("Part2/images/accessories/accessories.txt"))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        imageNames.add(line);
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (RuntimeException er) {

                }

                Random rand = new Random();
                int randomIndex = rand.nextInt(imageNames.size());
                String selectedImageName = imageNames.get(randomIndex);

                horse.setIcon(new ImageIcon((horse.getIcon()).getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH)));
                ImageIcon combinedImageIcon = combineImages("Part2/images/accessories/" + selectedImageName);
                horse.setIcon(combinedImageIcon);
                Image img = (combinedImageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
                Icon icon = new ImageIcon(img);
                imageLabel.setIcon(icon);


            }
        });
        customiseButton.setPreferredSize(new Dimension(customiseButton.getPreferredSize().width, 100));

        imagePanel.add(customiseButton, BorderLayout.SOUTH);

        frame.add(imagePanel, BorderLayout.EAST);

        frame.setIconImage((horse.getIcon()).getImage());
        frame.setSize(600, 600);
        frame.setVisible(true);
    }

    public static ImageIcon combineImages(String accessoryFilePath) {
        ImageIcon image1 = new ImageIcon("Part2/images/horse2.png");
        ImageIcon image2 = new ImageIcon(accessoryFilePath);

        BufferedImage combinedImage = new BufferedImage(
                Math.max(image1.getIconWidth(), image2.getIconWidth()),
                Math.max(image1.getIconHeight(), image2.getIconHeight()),
                BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = combinedImage.createGraphics();
        g2d.drawImage(image1.getImage(), 0, 0, null);
        g2d.drawImage(image2.getImage(), 0, 0, null);
        g2d.dispose();
        ImageIcon combinedImageIcon = new ImageIcon(combinedImage);
        return combinedImageIcon;
    }

    public static ImageIcon combineImages(ImageIcon combinedAccessories) {
        ImageIcon image1 = new ImageIcon("Part2/images/horse2.png");
        ImageIcon image2 = combinedAccessories;

        BufferedImage combinedImage = new BufferedImage(
                Math.max(image1.getIconWidth(), image2.getIconWidth()),
                Math.max(image1.getIconHeight(), image2.getIconHeight()),
                BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = combinedImage.createGraphics();
        g2d.drawImage(image1.getImage(), 0, 0, null);
        g2d.drawImage(image2.getImage(), 0, 0, null);
        g2d.dispose();
        ImageIcon combinedImageIcon = new ImageIcon(combinedImage);
        return combinedImageIcon;
    }

    public static ImageIcon combineImages(String firstImagePath, String secondImagePath) {
        ImageIcon image1 = new ImageIcon(firstImagePath);
        ImageIcon image2 = new ImageIcon(secondImagePath);

        BufferedImage combinedImage = new BufferedImage(
                Math.max(image1.getIconWidth(), image2.getIconWidth()),
                Math.max(image1.getIconHeight(), image2.getIconHeight()),
                BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = combinedImage.createGraphics();
        g2d.drawImage(image1.getImage(), 0, 0, null);
        g2d.drawImage(image2.getImage(), 0, 0, null);
        g2d.dispose();
        ImageIcon combinedImageIcon = new ImageIcon(combinedImage);
        return combinedImageIcon;
    }

    public static void main(String[] args) {
        Horse horse = new Horse('A', "RIDER", 0.4);
        horse.setName("INCITATUS");
        HorseInfo info = new HorseInfo(horse);

        info.examineHorse();
    }
}
