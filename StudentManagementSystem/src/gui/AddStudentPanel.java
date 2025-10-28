package gui;

import backend.Student;
import backend.StudentManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddStudentPanel {
    private JPanel addStudentRootPanel;
    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField departmentField;
    private JComboBox<String> genderBox;
    private JTextField gpaField;
    private JButton saveButton;
    private JButton backButton;

    private MainFrame mainFrame;
    private StudentManager manager;

    public AddStudentPanel(MainFrame mainFrame, StudentManager manager) {
        this.mainFrame = mainFrame;
        this.manager = manager;

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel("Dashboard");
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });
    }


    private void addStudent() {
        String idStr = idField.getText().trim();
        String name = nameField.getText().trim();
        String ageStr = ageField.getText().trim();
        String gpaStr = gpaField.getText().trim();
        String department = departmentField.getText().trim();

        //------- Validation --------
        if (idStr.isEmpty() || name.isEmpty() || ageStr.isEmpty() || gpaStr.isEmpty() || department.isEmpty()) {
            JOptionPane.showMessageDialog(addStudentRootPanel, "All fields must be filled.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            int age = Integer.parseInt(ageStr);
            double gpa = Double.parseDouble(gpaStr);
            String gender = (String) genderBox.getSelectedItem();

            Student newStudent = new Student(id, name, age, gender, department, gpa);

            boolean success = manager.addStudent(newStudent);

            if (success) {
                JOptionPane.showMessageDialog(addStudentRootPanel, "Student Added Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
            } else {
                JOptionPane.showMessageDialog(addStudentRootPanel,
                        "Could not add student. An error occurred.\n(Possible reasons: Student ID already exists, or file could not be saved.)",
                        "Save Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(addStudentRootPanel, "ID, Age, and GPA must be valid numbers.", "Validation Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // This method lets the MainFrame get the panel.
    public JPanel getRootPanel() {
        return addStudentRootPanel;
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        departmentField.setText("");
        gpaField.setText("");
        genderBox.setSelectedIndex(0);
    }
}
