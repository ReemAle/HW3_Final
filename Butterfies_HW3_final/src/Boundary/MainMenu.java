package Boundary;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 250);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JButton manufacturerButton = new JButton("Manage Manufacturers");
        manufacturerButton.setBounds(50, 20, 200, 25);
        panel.add(manufacturerButton);

        JButton wineButton = new JButton("Manage Wines");
        wineButton.setBounds(50, 60, 200, 25);
        panel.add(wineButton);

        JButton recommendationButton = new JButton("Wine Recommendation");
        recommendationButton.setBounds(50, 100, 200, 25);
        panel.add(recommendationButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(50, 140, 200, 25);
        panel.add(exitButton);

        manufacturerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManufacturerUI.display();
            }
        });

        wineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WineUI.display();
            }
        });

        recommendationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Recommendation();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
