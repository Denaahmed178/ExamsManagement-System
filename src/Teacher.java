
public interface Teacher {
    void addExam();
    void editExam(int Examid , int newValue ,String attributeName);
    void deleteExam(int id);
    void assignAssignment(int classroomId, Assignment assignment);
    void viewAllExams();
    void report(int studentID);
    void checkGrades(int studentID); 
    void sendNotification(int studentID, String notificationType, String message);
}
