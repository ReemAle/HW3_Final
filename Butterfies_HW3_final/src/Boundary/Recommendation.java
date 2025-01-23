package Boundary;

import Control.WineRecommendation;
import Entity.Wine;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Recommendation extends JFrame {

    private JComboBox<String> wineTypeComboBox;
    private JList<String> foodPairingList;
    private JList<String> occasionList;
    private DefaultListModel<String> selectedFoodModel;
    private DefaultListModel<String> selectedOccasionModel;
    private JTextArea resultTextArea;
    private JButton addFoodButton, addOccasionButton, searchButton;

    private WineRecommendation wineRecommendation;

    public Recommendation() {
        wineRecommendation = new WineRecommendation();

        // Frame setup
        setTitle("Wine Recommendation");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create components
        JLabel wineTypeLabel = new JLabel("Wine Type:");
        wineTypeComboBox = new JComboBox<>(fetchWineTypes());

        JLabel foodPairingLabel = new JLabel("Food Pairing:");
        foodPairingList = new JList<>(fetchFoodPairings());
        JScrollPane foodScrollPane = new JScrollPane(foodPairingList);

        JLabel selectedFoodLabel = new JLabel("Selected Food:");
        selectedFoodModel = new DefaultListModel<>();
        JList<String> selectedFoodList = new JList<>(selectedFoodModel);
        JScrollPane selectedFoodScrollPane = new JScrollPane(selectedFoodList);

        addFoodButton = new JButton("Add Food");
        addFoodButton.setPreferredSize(new Dimension(100, 25));

        JLabel occasionLabel = new JLabel("Occasion:");
        occasionList = new JList<>(fetchOccasions());
        JScrollPane occasionScrollPane = new JScrollPane(occasionList);

        JLabel selectedOccasionLabel = new JLabel("Selected Occasions:");
        selectedOccasionModel = new DefaultListModel<>();
        JList<String> selectedOccasionList = new JList<>(selectedOccasionModel);
        JScrollPane selectedOccasionScrollPane = new JScrollPane(selectedOccasionList);

        addOccasionButton = new JButton("Add Occasion");
        addOccasionButton.setPreferredSize(new Dimension(120, 25));

        searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(100, 30));

        resultTextArea = new JTextArea(10, 30);
        resultTextArea.setEditable(false);
        JScrollPane resultScrollPane = new JScrollPane(resultTextArea);

        // Layout
        JPanel mainPanel = new JPanel();
        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(wineTypeLabel)
                        .addComponent(foodPairingLabel)
                        .addComponent(selectedFoodLabel)
                        .addComponent(occasionLabel)
                        .addComponent(selectedOccasionLabel)
                        .addComponent(searchButton))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(wineTypeComboBox)
                        .addComponent(foodScrollPane)
                        .addComponent(selectedFoodScrollPane)
                        .addComponent(occasionScrollPane)
                        .addComponent(selectedOccasionScrollPane)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(addFoodButton)
                                .addComponent(addOccasionButton)))
                .addComponent(resultScrollPane)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(wineTypeLabel)
                        .addComponent(wineTypeComboBox))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(foodPairingLabel)
                        .addComponent(foodScrollPane))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(selectedFoodLabel)
                        .addComponent(selectedFoodScrollPane))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(addFoodButton)
                        .addComponent(addOccasionButton))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(occasionLabel)
                        .addComponent(occasionScrollPane))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(selectedOccasionLabel)
                        .addComponent(selectedOccasionScrollPane))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(searchButton)
                        .addComponent(resultScrollPane))
        );

        add(mainPanel);
        pack();

        // Event listeners
        addFoodButton.addActionListener(e -> addSelectedFood());
        addOccasionButton.addActionListener(e -> addSelectedOccasion());
        searchButton.addActionListener(e -> performSearch());

        setVisible(true);
    }

    private String[] fetchWineTypes() {
        return new String[]{"", "Red Wine", "White Wine", "Sparkling Wine", "Rose Wine", "Dessert Wine", "Fortified Wine"};
    }

    private String[] fetchFoodPairings() {
        return new String[]{"Grilled Steak", "Fruit Tart", "Mushroom Risotto", "Roasted Chicken", "Appetizer Selection", "Shrimp Cocktail"};
    }

    private String[] fetchOccasions() {
        return new String[]{"Wedding", "Birthday Celebration", "Anniversary", "Wine Tasting", "Corporate Event", "Graduation Party"};
    }

    private void addSelectedFood() {
        List<String> selectedFoods = foodPairingList.getSelectedValuesList();
        for (String food : selectedFoods) {
            if (!selectedFoodModel.contains(food)) {
                selectedFoodModel.addElement(food);
            }
        }
    }

    private void addSelectedOccasion() {
        List<String> selectedOccasions = occasionList.getSelectedValuesList();
        for (String occasion : selectedOccasions) {
            if (!selectedOccasionModel.contains(occasion)) {
                selectedOccasionModel.addElement(occasion);
            }
        }
    }

    private void performSearch() {
        String selectedWineType = (String) wineTypeComboBox.getSelectedItem();
        List<String> selectedFoods = new ArrayList<>();
        for (int i = 0; i < selectedFoodModel.getSize(); i++) {
            selectedFoods.add(selectedFoodModel.getElementAt(i));
        }

        List<String> selectedOccasions = new ArrayList<>();
        for (int i = 0; i < selectedOccasionModel.getSize(); i++) {
            selectedOccasions.add(selectedOccasionModel.getElementAt(i));
        }

        List<Wine> recommendedWines = wineRecommendation.getRecommendedWines(
                selectedWineType.isEmpty() ? null : selectedWineType,
                selectedFoods.isEmpty() ? null : selectedFoods,
                selectedOccasions.isEmpty() ? null : selectedOccasions
        );

        resultTextArea.setText("");

        if (recommendedWines.isEmpty()) {
            resultTextArea.setText("No wines found for the selected criteria. Please try again.");
        } else {
            for (Wine wine : recommendedWines) {
                resultTextArea.append(wine.toString() + "\n");
            }
        }
    }

    public static void main(String[] args) {
        new Recommendation();
    }
}
