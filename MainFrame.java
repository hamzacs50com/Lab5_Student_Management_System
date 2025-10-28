package gui;

import backend.StudentManager; // Import your backend
import javax.swing.*;
import java.awt.*;

/**
 * This is the main window (JFrame) of your application.
 * It is hand-coded. Its only job is to hold and switch
 * between all the panels you will make with the .form designer.
 */
public class MainFrame extends JFrame {

    private StudentManager manager;

    private CardLayout cardLayout;
    private JPanel mainPanelContainer; // The panel that holds the cards

    // Keep references to panels that need to be refreshed
    private ViewStudentsPanel viewStudentsPanel;
    private ManageStudentsPanel manageStudentsPanel;

    public MainFrame(StudentManager manager) {
        this.manager = manager;

        setTitle("Student Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Create the card layout and the panel that will use it
        cardLayout = new CardLayout();
        mainPanelContainer = new JPanel(cardLayout);

        // --- Create instances of all your .form GUI panels ---
        // We pass 'this' (the MainFrame) to any panel that needs to
        // tell the MainFrame to switch views.
        LoginPanel loginPanel = new LoginPanel(this);
        DashboardPanel dashboardPanel = new DashboardPanel(this);
        AddStudentPanel addStudentPanel = new AddStudentPanel(this, manager);

        // We save these two so we can call their refreshTable() methods
        viewStudentsPanel = new ViewStudentsPanel(this, manager);
        manageStudentsPanel = new ManageStudentsPanel(this, manager);

        // --- Add each panel's "root" panel to the card layout ---
        // We will create the getRootPanel() method in each .form file.
        mainPanelContainer.add(loginPanel.getRootPanel(), "Login");
        mainPanelContainer.add(dashboardPanel.getRootPanel(), "Dashboard");
        mainPanelContainer.add(addStudentPanel.getRootPanel(), "AddStudent");
        mainPanelContainer.add(viewStudentsPanel.getRootPanel(), "ViewStudents");
        mainPanelContainer.add(manageStudentsPanel.getRootPanel(), "ManageStudents");

        // Add the container to the frame
        add(mainPanelContainer);
    }

    public void showPanel(String panelName) {
        cardLayout.show(mainPanelContainer, panelName);
    }

    // --- Getters for panels that need refreshing ---
    // The Dashboard will call these methods

    public ViewStudentsPanel getViewStudentsPanel() {
        return viewStudentsPanel;
    }

    public ManageStudentsPanel getManageStudentsPanel() {
        return manageStudentsPanel;
    }
}