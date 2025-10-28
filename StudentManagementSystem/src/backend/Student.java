package backend;

public class Student  {
    private int studentID;
    private String fullName;
    private int age;
    private String gender;
    private String department;
    private double gpa;

    public Student(int studentID, String fullName, int age, String gender, String department, double gpa) {
        this.studentID = studentID;
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.department = department;
        this.gpa = gpa;
    }

    // StudentID getter and setter
    public int getStudentID() {
        return studentID;
    }
    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    // FullName getter and setter
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    // Age getter and setter
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    // getGender getter and setter
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    // Department getter and setter
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }

    // gpa getter and setter
    public double getGpa() {
        return gpa;
    }
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String toString() {
        return "Student{" + "studentID=" + studentID + ", fullName='" + fullName + '\'' + "}";
    }
}