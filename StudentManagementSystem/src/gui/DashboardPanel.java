package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DashboardPanel {
    private JPanel dashboardRootPanel;
    private JButton addStudentButton;
    private JButton viewStudentsButton;
    private JButton manageStudentsButton;
    private JButton logoutButton;

    private MainFrame mainFrame;

    public DashboardPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;


        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel("AddStudent");
            }
        });

        viewStudentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getViewStudentsPanel().refreshTable();
                mainFrame.showPanel("ViewStudents");
            }
        });

        manageStudentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getManageStudentsPanel().refreshTable();
                mainFrame.showPanel("ManageStudents");
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel("Login");
            }
        });
    }

    // This method lets the MainFrame get the panel.
    public JPanel getRootPanel() {
        return dashboardRootPanel;
    }
}
