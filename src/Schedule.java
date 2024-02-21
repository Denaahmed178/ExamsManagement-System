 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import java.util.Random;

public class Schedule {
    private static final String[] DAYS = {"Sunday","Monday", "Tuesday", "Wednesday", "Thursday"};
    private static final String[] TIMES = {"8:00 - 9:00", "9:00 - 10:00", "10:00 - 11:00", "11:00 - 12:00", "12:00 - 13:00"};
    private static final String[] SUBJECTS = {"Mathematics", "Physics", "Chemistry", "Biology", "English", "History"};
    private static final String[] CLASSROOMS = {"Classroom 1", "Classroom 2", "Classroom 3", "Classroom 4", "Classroom 5"};

    public  Map<String, List<String>> prepareTimetable() {
        Map<String, List<String>> schedule = new HashMap<>();

        for (String day : DAYS) {
            List<String> daySchedule = new ArrayList<>();
            // gets a random subject assigned to the classroom
            for (int i = 0; i < 5; i++) {
                String subject = SUBJECTS[getRandomIndex(SUBJECTS)];
                String classroom = CLASSROOMS[getRandomIndex(CLASSROOMS)];
                String time = TIMES[i];

                daySchedule.add(subject + " (" + classroom + ") " + time);
            }

            schedule.put(day, daySchedule);
        }

        return schedule;
    }

    public  void printTimetable(Map<String, List<String>> schedule) {
        for (Map.Entry<String, List<String>> entry : schedule.entrySet()) {
            System.out.println(entry.getKey());

            for (String event : entry.getValue()) {
                System.out.println(event);
            }

            System.out.println();
        }
    }

    public static int getRandomIndex(String[] array) {
        Random random = new Random();
        return random.nextInt(array.length);
    }
}
