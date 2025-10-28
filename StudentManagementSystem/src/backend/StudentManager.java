package backend;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class StudentManager {
    private List<Student> studentList;
    private static final String DATA_FILE = "students.txt";

    public StudentManager() {
        loadStudentsFromFile();
    }

    public boolean addStudent(Student student) {
        if(findStudentById(student.getStudentID()).isPresent()){
            return false;
        }
        studentList.add(student);
        return saveStudentsToFile();
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(studentList);
    }

    public boolean updateStudent(Student updatedStudent) {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getStudentID() == updatedStudent.getStudentID()) {
                studentList.set(i, updatedStudent);
                return saveStudentsToFile();
            }
        }
        return false;
    }

    public boolean deleteStudent(int deletedstudentId) {
        boolean removed = studentList.removeIf(student -> student.getStudentID() == deletedstudentId);
        if (removed) {
            return saveStudentsToFile();
        }
        return false;
    }

    public Optional<Student> findStudentById(int id) {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getStudentID() == id) {
                return Optional.of(studentList.get(i));
            }
        }
        return Optional.empty();
    }

    // to list all the student with the search query
    public List<Student> searchStudents(String query) {
        String lowerCaseQuery = query.toLowerCase();
        return studentList.stream()
                .filter(s -> s.getFullName().toLowerCase().contains(lowerCaseQuery) ||
                        String.valueOf(s.getStudentID()).contains(lowerCaseQuery))
                .collect(Collectors.toList());
    }

    private void loadStudentsFromFile() {
        studentList = new ArrayList<>();
        File file = new File(DATA_FILE);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(DATA_FILE))){
            String line;
            while ((line = br.readLine()) != null){
                try {
                    String[] data = line.split(",");
                    if (data.length == 6){
                        int id = Integer.parseInt(data[0]);
                        String name = data[1];
                        int age = Integer.parseInt(data[2]);
                        String gender = data[3];
                        String department = data[4];
                        double gpa = Double.parseDouble(data[5]);
                        Student student = new Student(id, name, age, gender, department, gpa);
                        studentList.add(student);
                    }
                }catch (Exception e){}
            }
        } catch (IOException e) {}
    }

    private boolean saveStudentsToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(DATA_FILE))){
            for (Student student : studentList){
                String line = student.getStudentID() + "," +
                        student.getFullName() + "," +
                        student.getAge() + "," +
                        student.getGender() + "," +
                        student.getDepartment() + "," +
                        student.getGpa();
                pw.println(line);
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
