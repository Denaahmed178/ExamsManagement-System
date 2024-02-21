/*
import java.util.Scanner;
*/

import java.util.Scanner;

class main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Teacher normalteacher=new NormalTeacher();
        normalteacher.addExam();
        System.out.print("Enter Exam id you want to edit : ");
        int id =scanner.nextInt();
        System.out.print(" Enter what do you want to edit :    ");
        String attributeName = scanner.next();
        System.out.print(" Enter the new value : ");
        int newValue = scanner.nextInt();

normalteacher.editExam(id ,newValue ,attributeName);
normalteacher.viewAllExams();
       /* Exam exam = new Exam(1, "science");
        exam.setDate(new Date());
        exam.setGrade(10);
        exam.setExamType("final");
        Student  student = new Student();
        Teacher normalteacher=new NormalTeacher();
        Teacher supervisingTeacher = new SupervisingTeacher(normalteacher);
        NormalTeacher no=new NormalTeacher();
        no.setNotificationType("SMS");
        //test supervised teacher functionality
        supervisingTeacher.performOperations(student, exam);

        Assignment assignment=new Assignment();
        assignment.setDescription("solve in 3 days");assignment.setAssignmentId(10);
        assignment.setDeadline(new Date());assignment.setSubject("physics");
        supervisingTeacher.assignAssignment(12 ,assignment);*/
         //p.PrepareTimetable(); done
            // p.getExamInformation();  done

           //  p.addTeacher();//  done
       /* NormalTeacher t = new NormalTeacher();
        t.setFullName("maher");
        t.setTeacherId(233);
        p.getTeachers().put(t.getTeacherId(),t);
            //p.editTeacherDetails(); done
            p.viewAllTeachersDetails();  //done
            p.DeleteTeacher(233);*/

            //done
        /*Student s =new Student(23,"maher","234sdfsdf",3);
       s.writeNoteToPrincipal();
       p.viewAllNotes();*/




    }

   
}
