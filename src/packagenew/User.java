package packagenew;

public class User extends student{
    public String classId;
    public double mark;

    public User() {
    }

    public User(String id, String name, boolean gender, String classId, double mark) {
        super(id, name, gender);
        this.classId = classId;
        this.mark = mark;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

}
