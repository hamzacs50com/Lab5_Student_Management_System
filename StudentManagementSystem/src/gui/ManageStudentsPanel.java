package gui;

import backend.Student;
import backend.StudentManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Optional;

public class ManageStudentsPanel {
    private JPanel manageRootPanel;
    private JButton searchButton;
    private JTextField searchField;
    private JTextField gpaField;
    private JTextField idField;
    private JTextField departmentField;
    private JTextField nameField;
    private JTextField ageField;
    private JComboBox<String> genderBox;
    private JButton backButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton clearButton;

    private MainFrame mainFrame;
    private StudentManager manager;

    // --- Table Components (created in code) ---
    private JTable studentTable;
    private DefaultTableModel tableModel;

    public ManageStudentsPanel(MainFrame mainFrame, StudentManager manager) {
        this.mainFrame = mainFrame;
        this.manager = manager;

        // creating the table manually
        String[] columnNames = {"ID", "Full Name", "Age", "Gender", "Department", "GPA"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        studentTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(studentTable);
        manageRootPanel.add(scrollPane, BorderLayout.CENTER);

        idField.setEditable(false); // ID is the key and should not be changed

        studentTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                populateFormFromTable();
            }
        });

        // listeners
        searchButton.addActionListener(e -> searchStudents(searchField.getText()));
        updateButton.addActionListener(e -> updateStudent());
        deleteButton.addActionListener(e -> deleteStudent());
        clearButton.addActionListener(e -> clearForm());
        backButton.addActionListener(e -> mainFrame.showPanel("Dashboard"));
    }

    // This method lets the MainFrame get the panel.
    public JPanel getRootPanel() {
        return manageRootPanel;
    }

    private void populateFormFromTable() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow != -1) {
            // Get the ID from the table (column 0)
            int studentId = (int) tableModel.getValueAt(selectedRow, 0);

            // Find the full student object from the manager
            Optional<Student> studentOpt = manager.findStudentById(studentId);
            studentOpt.ifPresent(s -> { // if student was found
                idField.setText(String.valueOf(s.getStudentID()));
                nameField.setText(s.getFullName());
                ageField.setText(String.valueOf(s.getAge()));
                genderBox.setSelectedItem(s.getGender());
                departmentField.setText(s.getDepartment());
                gpaField.setText(String.valueOf(s.getGpa()));
            });
        }
    }

    private void updateStudent() {
        if (idField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(manageRootPanel, "Please select a student from the table to update.", "Update Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int id = Integer.parseInt(idField.getText());
            int age = Integer.parseInt(ageField.getText());
            double gpa = Double.parseDouble(gpaField.getText());

            Student updatedStudent = new Student(id, nameField.getText(), age, (String) genderBox.getSelectedItem(), departmentField.getText(), gpa);

            boolean success = manager.updateStudent(updatedStudent);

            if (success) {
                JOptionPane.showMessageDialog(manageRootPanel, "Student updated successfully!");
                refreshTable();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(manageRootPanel, "Could not update student. A file save error occurred.", "Update Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(manageRootPanel, "Age and GPA must be valid numbers.", "Validation Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteStudent() {
        if (idField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(manageRootPanel, "Please select a student from the table to delete.", "Delete Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Confirmation Dialog (Lab Requirement)
        int confirm = JOptionPane.showConfirmDialog(manageRootPanel,
                "Are you sure you want to delete this student?\nName: " + nameField.getText() + "\nID: " + idField.getText(),
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            int id = Integer.parseInt(idField.getText());
            boolean success = manager.deleteStudent(id);

            if (success) {
                JOptionPane.showMessageDialog(manageRootPanel, "Student deleted successfully.");
                refreshTable();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(manageRootPanel, "Could not delete student. A file save error occurred.", "Delete Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void searchStudents(String query) {
        List<Student> results = manager.searchStudents(query);
        tableModel.setRowCount(0); // Clear table
        for (Student s : results) {
            Object[] row = {s.getStudentID(), s.getFullName(), s.getAge(), s.getGender(), s.getDepartment(), s.getGpa()};
            tableModel.addRow(row);
        }
    }

    public void refreshTable() {
        searchStudents(""); // Searching with an empty query shows all students
    }

    private void clearForm() {
        studentTable.clearSelection();
        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        genderBox.setSelectedIndex(0);
        departmentField.setText("");
        gpaField.setText("");
    }
}
