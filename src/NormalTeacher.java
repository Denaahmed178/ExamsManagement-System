/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import java.util.Date;

public class NormalTeacher implements Teacher{

   private String Username;
    private String password;
    private String fullName;
    private int age;
    private double salary;
    private String subject;
    private String Email;
    private int TeacherId;
    private  String NotificationType;
    private Map<Integer, Exam> exams = new HashMap<>();
    private Map<Integer, Assignment> assignments = new HashMap<>();
    private Map<Integer, Classroom> classrooms = new HashMap<>();
    public NormalTeacher() {
        Exam exam1 = new Exam(23, "math");
        exam1.setExamType("weekly"); exam1.setDate(new Date()); exam1.setDuration(1); 
        exam1.setDescription(" solve the next problem ");
        exams.put(exam1.getExamID(),exam1);

        Exam exam2 = new Exam(24, "science");
        exam2.setExamType("weekly"); exam2.setDate(new Date()); exam2.setDuration(2); 
        exam2.setDescription(" solve the next problem ");
        exams.put(exam2.getExamID(),exam2);

        ///*****assignmnts*/////
        Assignment assignment1=new Assignment();
        assignment1.setDescription("solve in 3 days");assignment1.setAssignmentId(10);
        assignment1.setDeadline(new Date());assignment1.setSubject("physics");

        Assignment assignment2=new Assignment();
        assignment2.setDescription("solve in 3 days");assignment2.setAssignmentId(20);
        assignment2.setDeadline(new Date());assignment2.setSubject("science");
        assignments.put(assignment1.getAssignmentId(),assignment1);
        assignments.put(assignment2.getAssignmentId(),assignment1);
        //*****classess*//////
        Classroom class1=new Classroom();
        class1.setClassId(12);class1.setClassName("D1");

        Classroom class2=new Classroom();
        class2.setClassId(13);class2.setClassName("D2");
        classrooms.put(class1.getClassId(),class1);
        classrooms.put(class2.getClassId(),class2);
    }
    
    @Override
    public void report(int studentID) {
        System.out.println("//*****Normal Teacher:doesnt have functionality for issuing report **//////");
    }
    @Override
    public void checkGrades(int studentID) {
        System.out.println("//*****Normal Teacher:doesnt have functionality for check grades for specific studentt **//////");
    }
    
    @Override
    public void addExam() {
        // Implement the specific behavior for adding an exam for NormalTeacher
        System.out.println("///***NormalTeacher: Adding an exam***///");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new new Exam's id: ");
        int id =Integer.parseInt(scanner.nextLine());

        System.out.print("Enter new new Exam's Subject: ");
        String sub =scanner.nextLine();
        Exam exam =  new Exam(id , sub);
        System.out.print("Enter new new Exam's Type: ");
        exam.setExamType(scanner.nextLine());

        System.out.print("Enter new new Exam's Description: ");
        exam.setDescription(scanner.nextLine());

        System.out.print("Enter new new Exam's Duration: ");
        exam.setDuration(Integer.parseInt(scanner.nextLine()));

        // Read and parse the date
        System.out.print("Enter new Exam's Date (yyyy-MM-dd): ");
        String dateString = scanner.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = dateFormat.parse(dateString);
            exam.setDate(date);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
            return; // Exit the method if the date is not in the correct format
        }

        // add in the list
        exams.put(exam.getExamID() , exam);
        System.out.println("a new exam has been added successfully");
    }

    @Override
    public void editExam(int Examid , int newValue ,String attributeName) {
        // Implement the specific behavior for editing an exam for NormalTeacher
        System.out.println("///***NormalTeacher: Editing an exam***///");

        Exam exam ;
        if(exams.containsKey(Examid)) {
            exam = exams.get(Examid);
        }
        else {
            throw new RuntimeException(" Sorry can not edit ID not found");
        }

        // this to retrieve the (set method ) of the attribute dynamically
        try {
            //exception for number values
            if(attributeName.equals("date")){
                System.out.println(" can not edit the Date");
            }
            if(attributeName.equals("subject")){
                Method setterMethod = Exam.class.getMethod("set" + attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1), String.class);

                System.out.print(" Enter the new value : ");
               String sub = String.valueOf(newValue);
                setterMethod.invoke(exam, sub);
            } else if (attributeName.equals("ExamType")) {
                Method setterMethod = Exam.class.getMethod("set" + attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1), String.class);
                System.out.print(" Enter the new value : ");
                String sub = String.valueOf(newValue);
                setterMethod.invoke(exam, sub);
            }else {
                Method setterMethod = Exam.class.getMethod("set" + attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1), int.class);
                setterMethod.invoke(exam, newValue);
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("Error editing exam details: " + e.getMessage());
        }
        System.out.println("Successful edit !!!!!!");
    }

    @Override
    public void deleteExam(int id) {
        // Implement the specific behavior for deleting an exam for NormalTeacher
        System.out.println("///***NormalTeacher: Deleting an exam***///");
        Exam exam ;
        //check if the map has the teacher
        if(exams.containsKey(id)) {
            exam = exams.get(id);
            exams.remove(exam.getExamID(),exam);
            System.out.println("Exam has been deleted");
        }
        else
            System.out.println("there isn't an EXAM with this id");
    }

    @Override
    public void assignAssignment(int classroomId, Assignment assignment) {
        // Implement the specific behavior for assigning an assignment for NormalTeacher
        // Implement the specific behavior for assigning an assignment for NormalTeacher
        //Assignment assignment=new Assignment();
        System.out.println("///***NormalTeacher: Assigning an assignment***///");
        Classroom classroomTarget=new Classroom();
        if (classrooms.containsKey(classroomId)) {
            classroomTarget = classrooms.get(classroomId);
            // Check if the assignment already exists in the classroom
            if (classroomTarget.getAssignments().containsKey(assignment.getAssignmentId())) {
                System.out.println("Assignment already exists in the classroom. Not assigning again.");
            } else {
                // Assign the assignment to the classroom
                getAssignments().put(assignment.getAssignmentId(), assignment);
                System.out.println("Assignment details:");
                System.out.println("Classroom Target: " + classroomTarget.getClassName());
                System.out.println("Assignment Description: " + assignment.getDescription());
                System.out.println("Assignment subject: " + assignment.getSubject());
                // You can include additional details based on your Assignment class properties
            }
        } else {
            System.out.println("Classroom with ID " + classroomId + " does not exist.");
        }
    }
    @Override
    public void viewAllExams() {
        System.out.println("///***NormalTeacher: View all Exams***///");
        for (Map.Entry<Integer, Exam> entry : exams.entrySet()){
            System.out.println("exam id : " + entry.getKey() +"  "+
                    "exam subject: "+ entry.getValue().getSubject() +"  "
                    +"exam type: " + entry.getValue().getExamType()+" "
                    + " Description :"+ entry.getValue().getDescription()+""
                    +" Duration : "+ entry.getValue().getDuration()+" "
                    +" Date :"+ entry.getValue().getDate()+"  "
            );

        }
    }
   @Override
    public void sendNotification(int studentID, String notificationType, String message) {
       System.out.println("//*****Normal Teacher:doesnt have functionality for send notification **//////");
    }
 
    public Map<Integer, Exam> getExams() {
        return exams;
    }

    public void setExams(Map<Integer, Exam> exams) {
        this.exams = exams;
    }

    public Map<Integer, Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(Map<Integer, Assignment> assignments) {
        this.assignments = assignments;
    }

    public Map<Integer, Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(Map<Integer, Classroom> classrooms) {
        this.classrooms = classrooms;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setTeacherId(int teacherId) {
        TeacherId = teacherId;
    }

    public void setNotificationType(String notificationType) {
        NotificationType = notificationType;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public String getSubject() {
        return subject;
    }

    public String getEmail() {
        return Email;
    }

    public int getTeacherId() {
        return TeacherId;
    }

    public String getNotificationType() {
        return NotificationType;
    }




    
}
