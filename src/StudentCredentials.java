import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class StudentCredentials {
    private Map<String,String> Credentials = new HashMap<>();
    private String name;
    private String password ;
    public void generateStudentCredentials(){
        Scanner scanner = new Scanner(System.in);
        // Create an instance of Random class
        Random random = new Random();

        // Get user input for name
        System.out.print("Enter full name: ");
        name = scanner.nextLine();
        System.out.println("Username and Password will be appear soon");
        //generate random number between 0 and 99 to add to username ex.(Student56)
        name = name + Integer.toString(getRandomnumber(100));

         // Generate a random number between 0 and 9999 with getRandomnumber()
        //Generate random String to add to the password with getRandomString()
        password = Integer.toString(getRandomnumber(10000))+getRandomString().toString();
        Credentials.put(name , password);
        System.out.println("your Username  " + name);
        System.out.println("your password  " + password);
        System.out.println("Please copy the username and password and try to login");
    }
    public StringBuilder getRandomString(){
        Random random = new Random();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            char randomChar = (char) (random.nextInt(26) + 'a');
            result.append(randomChar);
        }
        return result;
    }

    public  int getRandomnumber(int length) {
        Random random = new Random();
        return random.nextInt(length);
    }
    public Map<String, String> getCredentials() {
        return Credentials;
    }

    public void setCredentials(Map<String, String> credentials) {
        Credentials = credentials;
    }
}
