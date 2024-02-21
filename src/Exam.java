

import java.util.Date;

public class Exam implements ExamService {

    
       private int examID;
       private int Duration;
       private int Grade;
       private Date date;
       private String ExamType;
       private String subject ;
       private int StudentID;

    public int getStudentID() {
        return StudentID;
    }

    public void setStudentID(int StudentID) {
        this.StudentID = StudentID;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    private String Description;

    public String getDescription() {
        return Description;
    }

    public int getExamID() {
        return examID;
    }

    public int getDuration() {
        return Duration;
    }

    public int getGrade() {
        return Grade;
    }

    public Date getDate() {
        return date;
    }

    public String getExamType() {
        return ExamType;
    }
    
    public Exam(int examID, String subject) {
        this.examID = examID;
        this.subject = subject;
    }
    // add a condition for the exam type
    public void setExamType(String examType) {
        if(examType.equals("monthly") ||examType.equals("weekly") ||examType.equals("final")) {
            ExamType = examType;
        }else{
            throw new RuntimeException(" The exam can be WEEKLY or MONTHLY or FINAL") ;
        }


    }

    public void setExamID(int examID) {
        this.examID = examID;
    }

    public void setDuration(int duration) {
        Duration = duration;
    }

    public void setGrade(int grade) {
        Grade = grade;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public void ViewExam()
    {

       System.out.println("********<Exam Details>********");
        System.out.println("Subject :"+ subject);
       System.out.println("Grade : "+ Grade);
       System.out.println("Duration : "+ Duration);
       System.out.println("ExamType : "+ ExamType);
       System.out.println("Date : "+ date);
       
    }
    
    
}
