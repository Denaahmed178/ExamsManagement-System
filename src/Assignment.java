import java.util.Date;

public class Assignment {
   private String Description;

   private Date Deadline;
   private int Grade;
   private String subject;
   private int assignmentId;


   public String getSubject() {
        return subject;
    }

    public void setSubject(String Subject) {
        this.subject = Subject;
    }
    
    public String getDescription() {
        return Description;
    }

    public void setDescription(String desc) {
        this.Description = desc;
    }
    

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setDeadline(Date Deadline) {
        this.Deadline = Deadline;
    }

    public void setGrade(int Grade) {
        this.Grade = Grade;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }
   
    
    
}
