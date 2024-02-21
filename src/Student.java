import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//import static sun.util.locale.LocaleUtils.isEmpty;

public class Student {
    private int StudentID;
    private String UserName;
    private String Password;
    private int Grade;
    private Map<Integer, Student> studentsMain = new HashMap<>();

    principal p = principal.getInstant();
   /* public Student(int StudentID, String UserName, String Password, int yearGrade) {
        this.StudentID = StudentID;
        this.UserName = UserName;
        this.Password = Password;
        this.Grade = yearGrade;
    }*/



    public void Login(Student student) {
        StudentCredentials s= p.getStudentData();
        p.sendStudentCredentials();
      Map<String,String> c =s.getCredentials();
//try to log in
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        if ((c.containsKey(username)) || (c.containsValue(password))) {
            student.setUserName(username);
            student.setPassword(password);
            System.out.println("Successfully Login");
        }else {
            System.out.println("!!!! Invalid Password OR UserName !!!!");
        }
        System.out.println("login for the first time");
        System.out.println(" Enter your Id");
        student.setStudentID(scanner.nextInt());
        System.out.println(" Enter your Grade");
        student.setGrade(scanner.nextInt());
       studentsMain.put(student.StudentID,student);
       setMaps(student); // to sync other array and map for the other class
    }
    // to sync other array and map for the other class when new student has been login
public void setMaps(Student s){
        p.getStudents().add(s);
    Teacher normalteacher=new NormalTeacher();
    SupervisingTeacher supervisor =new SupervisingTeacher(normalteacher);
    supervisor.getStudents().put(s.StudentID,s);
}
    public void takeExam(){
        Scanner scanner = new Scanner(System.in);
        System.out.print(" To get the Exam information enter exam ID : ");
        int id = scanner.nextInt();
        System.out.print("enter  the subject : ");
        String sub = scanner.next();

        ExamService proxy = new ExamProxy(new Exam(id,sub ));
        proxy.ViewExam();
        System.out.println("submit your answer");
        String answer = scanner.next();
        System.out.println("best wishes");
        System.out.println("the teacher will notify you when the grade is published");
    }
    public void viewGrades() {
        Teacher normalteacher=new NormalTeacher();
        SupervisingTeacher supervisor =new SupervisingTeacher(normalteacher);
        supervisor.report(this.StudentID);
    }
    public void writeNoteToPrincipal() {
        Scanner scanner = new Scanner(System.in);

        //write note to principal
        System.out.println("***** Please Enter your Note *****");
        String note = scanner.nextLine();

        p.receiveNote(note);
    }

    public void setStudentID(int studentID) {
        StudentID = studentID;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setGrade(int grade) {
        Grade = grade;
    }

    public int getStudentID() {
        return StudentID;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPassword() {
        return Password;
    }

    public int getGrade() {
        return Grade;
    }
}
