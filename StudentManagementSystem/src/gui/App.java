package gui;

import backend.StudentManager; // Import our backend
import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        // Use SwingUtilities to ensure the GUI starts safely
        SwingUtilities.invokeLater(() -> {
            // 1. Create the one and only instance of the "backend"
            StudentManager manager = new StudentManager();

            // 2. Create the main window and pass the engine to it
            MainFrame mainFrame = new MainFrame(manager);

            // 3. Show the window
            mainFrame.setVisible(true);
        });
    }
}