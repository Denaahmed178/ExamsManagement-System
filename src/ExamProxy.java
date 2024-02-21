import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ExamProxy implements ExamService {
    private Exam exam;
    private  static Map<Integer , Exam> availableExams ;

    public ExamProxy(Exam exam) {
       availableExams = new HashMap<>();
        this.exam = exam;
        // declare exam1 to make it available to the students
        Exam exam1 = new Exam(23, "math");
        exam1.setExamType("weekly"); exam1.setDate(new Date()); exam1.setDuration(1); exam1.setGrade(10);
        exam1.setDescription(" solve the next problem ");
        availableExams.put(exam1.getExamID(),exam1);
    }



    @Override
    public void ViewExam()
    {
        // Check access before viewing the exam
        if(availableExams.containsKey(exam.getExamID()))
        {
            exam= availableExams.get(exam.getExamID());
            exam.ViewExam();
            System.out.println("Exam is Existed !!!!!");
            System.out.println("Exam ID: " + exam.getExamID());
            System.out.println("Description: " + exam.getDescription());
        } else
        {
            System.out.println("Exam is NOT Existed ?????"); 
        }
     
    }

}
