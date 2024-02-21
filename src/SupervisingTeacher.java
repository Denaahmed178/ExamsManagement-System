import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SupervisingTeacher extends TeacherDecorator {
    
    private static Map<Integer, Student> students = new HashMap<>();
    private static Map<Integer, Exam> exams = new HashMap<>();
    
    
       
    public SupervisingTeacher(Teacher teacher) {
        super(teacher);
        Exam exam1 = new Exam(23, "math");
        exam1.setExamType("weekly"); exam1.setDate(new Date()); exam1.setGrade(10);
        exam1.setStudentID(20201700);
        exams.put(exam1.getExamID(),exam1);
        
        Exam exam2 = new Exam(24, "science");
        exam2.setExamType("weekly"); exam2.setDate(new Date()); exam2.setGrade(20);
        exam2.setStudentID(20201700);
        exams.put(exam2.getExamID(),exam2);
        
        ///***Students**///
        Student student1=new Student();
        student1.setStudentID(20201700);student1.setUserName("Yousef");
        students.put(student1.getStudentID(),student1);
        
        Student student2=new Student();
        student2.setStudentID(20201800);student2.setUserName("john");
        students.put(student2.getStudentID(),student2);
        
    }

    public Map<Integer, Student> getStudents() {
        return students;
    }

    public void setStudents(Map<Integer, Student> students) {
        this.students = students;
    }

    public Map<Integer, Exam> getExams() {
        return exams;
    }

    public void setExams(Map<Integer, Exam> exams) {
        this.exams = exams;
    }

   
     @Override
    public void addExam() {
        super.addExam(); 
         System.out.println("//***SupervisingTeacher: The teacher supervises adding the Exam**//");
    }

    @Override
    public void editExam(int Examid, int newValue, String attributeName)  {
        super.editExam(Examid, newValue, attributeName);
        System.out.println("//***SupervisingTeacher: supervises Editing the Exam**//");
    }

    @Override
    public void deleteExam(int id) {
    super.deleteExam(id);
    System.out.println("//***SupervisingTeacher: supervises the deletion the Exam***//");
    }
  ///not completed
    @Override
   public void assignAssignment(int classroomId, Assignment assignment){
      super.assignAssignment( classroomId, assignment);
      System.out.println("//***SupervisingTeacher: supervises the assignment of assignments to the classes***//");
    }
    @Override
    public void viewAllExams() {
        super.viewAllExams(); 
        System.out.println("//***SupervisingTeacher: supervises the presentation of the available exams***//");
    }
 
   
   @Override
   public void report(int studentID)
   {
     super.report(studentID);
     issueReportGrade(studentID); 
   }
   
   private void issueReportGrade(int studentID) {
       System.out.println("//***SupervisingTeacher: issuing reports for students***//");
       Student student =new Student(); 
       // Check if the student exists
        if (students.containsKey(studentID)) {
            student=students.get(studentID);
            System.out.println("Report send to " + student.getUserName() + ":");
            System.out.println("Exams for Student with ID " + studentID + ":"); 
            // Iterate through exams and find exams for the specified student ID
            for (Map.Entry<Integer, Exam> entry : exams.entrySet()) {
                 Exam exam = entry.getValue();
                if (exam.getStudentID() == studentID) {
                    
                    System.out.println("Exam ID: " + exam.getExamID() + ", Subject: " + exam.getSubject() +
                            ", Type: " + exam.getExamType() + ", Date: " + exam.getDate() + ", Grade: " + exam.getGrade());
                }
                else{System.out.println("Student with ID " + studentID + " does not have Exams in this Subject."+exam.getSubject());}
            }
        } else {
            System.out.println("Student with ID " + studentID + " does not exist.");
        }   

    }
   @Override
   public void checkGrades(int studentID)
   {
     super.checkGrades(studentID);
     checkStudentGrade(studentID);
   }
   
    private void checkStudentGrade(int studentID) {

       System.out.println("//***SupervisingTeacher: Check student Grade***//");
       Student student =new Student(); 
       // Check if the student exists
        if (students.containsKey(studentID)) {
            student=students.get(studentID);
            System.out.println("Grades for Student  " + student.getUserName() + ":"); 
            // Iterate through exams and find exams for the specified student ID
            for (Map.Entry<Integer, Exam> entry : exams.entrySet()) {
                 Exam exam = entry.getValue();
                if (exam.getStudentID() == studentID) {
                    
                    System.out.println( " Subject: " + exam.getSubject()  + ", Grade: " + exam.getGrade());
                }
                else{System.out.println("Student with ID " + studentID + " does not have Exams in this Subject."+exam.getSubject());}
            }
        } else {
            System.out.println("Student with ID " + studentID + " does not exist.");
        }
    }
    @Override
    public void sendNotification(int studentID, String notificationType, String message) {
        super.sendNotification(studentID, notificationType, message);
        System.out.println("//***SupervisingTeacher: Send Notification to Student***//");
        Notification notification1 =  CreateNotification(studentID, notificationType, message);
        notification1.send();
    }
 

private Notification CreateNotification(int studentID,String notificationType,String message)
    {
        Student student =new Student();
         if (students.containsKey(studentID)) {
            student=students.get(studentID);
             System.out.println("SupervisingTeacher: Notification sent to " + student.getUserName());
              if(notificationType.equalsIgnoreCase("Email"))
              {
               System.out.println(message);
               return new EmailNotification(message);
               
              }
               if(notificationType.equalsIgnoreCase("SMS"))
              {
               System.out.println(message);
               return new SMSNotification(message);
              }
               
            }else {
            System.out.println("Student with ID " + studentID + " does not exist.");
            }   
          throw  new IllegalArgumentException();
    }
 
    
}

