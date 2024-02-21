
import java.util.HashMap;
import java.util.Map;

public class Classroom {
   private int classId;
   private String className;
   private int Capacity;
   private Map<Integer, Assignment> assignments = new HashMap<>();
   private Map<Integer, Classroom> classrooms = new HashMap<>();



    public Map<Integer, Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(Map<Integer, Classroom> classrooms) {
        this.classrooms = classrooms;
    }
    public Map<Integer, Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(Map<Integer, Assignment> assignments) {
        this.assignments = assignments;
    }

    public int getClassId() {
        return classId;
    }

    public void setCapacity(int Capacity) {
        this.Capacity = Capacity;
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }
   
    
    
}
