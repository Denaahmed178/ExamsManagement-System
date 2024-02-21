import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class principal {
    private int principle_id ;
    private String Username;
    private String Password;
    private String Email;
    Scanner scanner = new Scanner(System.in);
    private static principal principalInstant = null ;
    private Map<Integer, NormalTeacher> teachers = new HashMap<>();
    ArrayList<Student> students = new ArrayList<>();
    ArrayList<String> notes = new ArrayList<>();
    private StudentCredentials studentData = new StudentCredentials();

    private principal() {
        principle_id=1;
        Username ="MS.John Doe";
        Password ="password00";
        Email ="JohnDoe@mail.com";
    }

    public static principal getInstant(){
        if(principalInstant ==null){
            principalInstant =new principal();
        }
        return principalInstant;
    }
    public boolean Sign_in(){


        // Get user input for username and password
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        // Check if username and password are correct
        if (username.equals(principalInstant.getUsername()) && password.equals(principalInstant.getPassword())) {
            System.out.println("Sign-in successful!");
            return true;
            // Perform further actions or redirect to another function
        } else {
            System.out.println("Invalid username or password. Please try again.");
            return false;
        }


    }
    public void sendStudentCredentials(){
        studentData.generateStudentCredentials();
    }
    public  static void PrepareTimetable(){
        Schedule TimetableInstant = new Schedule();
        Map<String, List<String>> schedule = TimetableInstant.prepareTimetable();
        TimetableInstant.printTimetable(schedule);
    }


    public void addTeacher(){
        NormalTeacher teacher = new NormalTeacher();
        // fill the data dynamically
        System.out.print("Enter new Teacher's full name: ");
        teacher.setFullName(scanner.nextLine());
        System.out.print("Enter new Teacher's Username: ");
        teacher.setUsername(scanner.nextLine());
        System.out.print("Enter new Teacher's email: ");
        teacher.setEmail(scanner.nextLine());
        System.out.print("Enter new Teacher's age : ");
        int age   = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter new Teacher's  salary : ");
        teacher.setSalary(Double.parseDouble(scanner.nextLine()));
        System.out.print("Enter  new Teacher's subject: ");
        teacher.setSubject(scanner.nextLine());

        System.out.print("Enter  new Teacher's id : ");
        teacher.setTeacherId(Integer.parseInt(scanner.nextLine()));

        System.out.print("Enter  new Teacher's notification type : ");
        teacher.setNotificationType(scanner.nextLine());

        // add in the list
        teachers.put(teacher.getTeacherId() , teacher);
        System.out.println("a new teacher has been added successfully");
    }

    public void editTeacherDetails(int Teacherid, String attributeName , String newValue) {

        NormalTeacher teacher = null;
        if(teachers.containsKey(Teacherid)) {
            teacher = teachers.get(Teacherid);
        }
        // this to retrieve the (set method ) of the attribute dynamically
        try {
            Method setterMethod = NormalTeacher.class.getMethod("set" + attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1), String.class);
            //exception for number values
            if(attributeName.equals("age")){
                int age = Integer.parseInt(newValue);
                setterMethod.invoke(teacher, age);
            } else if (attributeName.equals("salary")) {
                double s = Double.parseDouble(newValue);
                setterMethod.invoke(teacher, s);
            }else {
                setterMethod.invoke(teacher, newValue);
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("Error editing teacher details: " + e.getMessage());
        }
        System.out.println("Successful edit !!!!!!");
    }
    public void DeleteTeacher(int id){
        NormalTeacher teacher ;
        //check if the map has the teacher
        if(teachers.containsKey(id)) {
            teacher = teachers.get(id);
            teachers.remove(teacher.getTeacherId(),teacher);
            System.out.println("Teacher has been deleted");
        }
        else
            System.out.println("there isn't a teacher with this id");
    }
    public void viewAllTeachersDetails(){
        // Iterate over the HashMap and display all the values
        for (Map.Entry<Integer, NormalTeacher> entry : teachers.entrySet()) {
            System.out.println("teacher id : " + entry.getKey() +"  "+
                    "teacher name: "+ entry.getValue().getFullName() +"  "
                    +" email: " + entry.getValue().getEmail()+" "
                    + "  age :"+ entry.getValue().getAge()
                    +" salary : "+ entry.getValue().getSalary()
                    +" Subject :"+ entry.getValue().getSubject()
                    +" Notification : " + entry.getValue().getNotificationType()
            );
        }
    }

    public void viewAllStudentsDetails(){
        // print all students
        for (Student student : students) {
            System.out.println("student id :"+ student.getStudentID()
                    +"  name :  "+student.getUserName()
                    +"  Grade :  "+student.getGrade());
        }
    }


    public void getExamInformation(){
        System.out.print(" To get the Exam information enter exam ID : ");
        int id = scanner.nextInt();
        System.out.print("enter  the subject : ");
        String sub = scanner.next();

        ExamService proxy = new ExamProxy(new Exam(id,sub ));
        proxy.ViewExam();
    }


    public void receiveNote(String note) {
        notes.add(note);
        System.out.println("your note have been sent");
    }
    public void viewAllNotes(){
        // print all students
        for (String note : notes) {
            System.out.println(note);
        }
    }
    public Map<Integer, NormalTeacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Map<Integer, NormalTeacher> teachers) {
        this.teachers = teachers;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public ArrayList<String> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<String> notes) {
        this.notes = notes;
    }
    public StudentCredentials getStudentData() {
        return studentData;
    }

    public void setStudentData(StudentCredentials studentData) {
        this.studentData = studentData;
    }

    public int getPrinciple_id() {
        return principle_id;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public String getEmail() {
        return Email;
    }

    public void setPrinciple_id(int principle_id) {
        this.principle_id = principle_id;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setEmail(String email) {
        Email = email;
    }

}
