package Boundary;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class XMLBoundary {

    public void openXMLFile() {
        // Create a JFileChooser instance
        JFileChooser fileChooser = new JFileChooser();

        // Set a filter for XML files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("XML Files", "xml");
        fileChooser.setFileFilter(filter);

        // Show the open dialog
        int result = fileChooser.showOpenDialog(null);

        // Process the result
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());

            // Here you can add further logic to process the selected XML file
            // For example, parsing the file or displaying its content

        } else if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("File selection was canceled.");
        } else {
            System.out.println("An error occurred during file selection.");
        }
    }

    public static void main(String[] args) {
        // Example usage
        XMLBoundary xmlBoundary = new XMLBoundary();
        xmlBoundary.openXMLFile();
    }
}
