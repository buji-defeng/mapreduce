package hbase;

public class Student {

    private String name;
    private String student_id;
    private String student_class;
    private String understanding;
    private String programming;

    public Student() {
    }

    public Student(String name, String student_id, String student_class, String understanding, String programming) {
        this.name = name;
        this.student_id = student_id;
        this.student_class = student_class;
        this.understanding = understanding;
        this.programming = programming;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getStudent_class() {
        return student_class;
    }

    public void setStudent_class(String student_class) {
        this.student_class = student_class;
    }

    public String getUnderstanding() {
        return understanding;
    }

    public void setUnderstanding(String understanding) {
        this.understanding = understanding;
    }

    public String getProgramming() {
        return programming;
    }

    public void setProgramming(String programming) {
        this.programming = programming;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", student_id='" + student_id + '\'' +
                ", student_class='" + student_class + '\'' +
                ", understanding='" + understanding + '\'' +
                ", programming='" + programming + '\'' +
                '}';
    }
}
