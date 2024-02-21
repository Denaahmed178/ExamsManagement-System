/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


abstract class TeacherDecorator implements Teacher{
  
    //attribute with the same type as interface
    private Teacher teacher;
    
    //constructor
    public TeacherDecorator(Teacher teacher) {
        this.teacher = teacher;
    }
    
    @Override
    public void addExam() {
       teacher.addExam();
               
    }

    @Override
    public void editExam(int Examid, int newValue, String attributeName)  {
        teacher.editExam(Examid,newValue ,attributeName );
    }

    @Override
    public void deleteExam(int id) {
        teacher.deleteExam(id);
    }
   
    @Override
    public void viewAllExams() {
        teacher.viewAllExams();
    }
    @Override
   public void assignAssignment(int classroomId, Assignment assignment){
        teacher.assignAssignment( classroomId,  assignment);
    }

  @Override
   public void report(int studentID)
   {
    teacher.report(studentID);
   }
   
   @Override
   public void checkGrades(int studentID)
   {
    teacher.checkGrades(studentID);
   }
    @Override
    public void sendNotification(int studentID, String notificationType, String message)
    {
      teacher.sendNotification(studentID, notificationType, message);
    }
}
