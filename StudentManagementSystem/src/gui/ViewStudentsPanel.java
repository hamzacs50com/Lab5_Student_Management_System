package gui;

import backend.Student;
import backend.StudentManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewStudentsPanel {
    private JPanel viewStudentsRootPanel;
    private JButton backButton;

    private MainFrame mainFrame;
    private StudentManager manager;

    private JTable studentsTable;
    private DefaultTableModel tableModel;

    public ViewStudentsPanel(MainFrame mainFrame, StudentManager manager) {
        this.mainFrame = mainFrame;
        this.manager = manager;

        // creating the table manually
        String[] columnNames = {"ID", "Full Name", "Age", "Gender", "Department", "GPA"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            // Make cells non-editable
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        studentsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(studentsTable);
        viewStudentsRootPanel.add(scrollPane, BorderLayout.CENTER);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel("Dashboard");
            }
        });
    }

    // This method lets the MainFrame get the panel.
    public JPanel getRootPanel() {
        return viewStudentsRootPanel;
    }

    public void refreshTable() {
        tableModel.setRowCount(0); // Clear all existing data
        List<Student> students = manager.getAllStudents();
        for (Student s : students) {
            Object[] row = {
                    s.getStudentID(),
                    s.getFullName(),
                    s.getAge(),
                    s.getGender(),
                    s.getDepartment(),
                    s.getGpa()
            };
            tableModel.addRow(row);
        }
    }
}
