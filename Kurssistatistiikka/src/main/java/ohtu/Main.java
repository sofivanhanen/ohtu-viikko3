package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // ÄLÄ laita githubiin omaa opiskelijanumeroasi
        String studentNr = "012345678";
        if ( args.length>0) {
            studentNr = args[0];
        }
        
        Submission[] subs = getSubmissionsForStudent(studentNr);
        Course[] courses = getCourses();
        printConclusiveInfoOnStudent(subs, courses);
    }
    
    public static void printConclusiveInfoOnStudent(Submission[] subs, Course[] courses) {
        System.out.println("");
        for (Course course : courses) {
            List<Submission> relevantSubs = getOrderedSubmissionsForCourse(subs, course.getName());
            
            if (relevantSubs.isEmpty()) continue;
            
            System.out.println(course);
            System.out.println("");
            for (Submission sub : relevantSubs) {
                System.out.println("Viikko " + sub.getWeek() + ":");
                System.out.println("  Tehtyjä tehtäviä " + sub.getExercises().length + "/" + course.getExercises()[sub.getWeek()]);
                System.out.println("  Aikaa kului " + sub.getHours() + "h");
                System.out.println("  Tehdyt tehtävät: " + sub.exercisesAsString());
                System.out.println("");
            }
            System.out.println("Yhteensä: " + sumOfExercises(relevantSubs) + "/" + sumOfExercises(course) + " tunteja " + sumOfHours(relevantSubs));
            System.out.println("");
        }
    }
    
    public static int sumOfExercises(List<Submission> subs) {
        int sum = 0;
        for (Submission sub : subs) {
            sum += sub.getExercises().length;
        }
        return sum;
    }
    
    public static int sumOfHours(List<Submission> subs) {
        int sum = 0;
        for (Submission sub : subs) {
            sum += sub.getHours();
        }
        return sum;
    }
    
    public static int sumOfExercises(Course course) {
        int sum = 0;
        for (int exercises : course.getExercises()) {
            sum += exercises;
        }
        return sum;
    }
    
    public static int sumOfExercises(Course[] courses) {
        int sum = 0;
        for (Course course : courses) {
            sum += sumOfExercises(course);
        }
        return sum;
    }
    
    public static List<Submission> getOrderedSubmissionsForCourse(Submission[] subs, String courseName) {
        ArrayList<Submission> relevantSubs = new ArrayList<>();
        
        for (Submission sub : subs) {
            if (sub.getCourse().equals(courseName)) {
                relevantSubs.add(sub);
            }
        }
        
        relevantSubs.sort(new Comparator(){
            @Override
            public int compare(Object o1, Object o2) {
                return ((Submission)o1).getWeek() - ((Submission)o2).getWeek();
            }
        });
        
        return relevantSubs;
    }
    
    public static Submission[] getSubmissionsForStudent(String studentNr) throws IOException {
        String url = "https://studies.cs.helsinki.fi/courses/students/"+studentNr+"/submissions";

        String bodyText = Request.Get(url).execute().returnContent().asString();

        Gson mapper = new Gson();
        return mapper.fromJson(bodyText, Submission[].class);
    }
    
    public static Course[] getCourses() throws IOException {
        String url = "https://studies.cs.helsinki.fi/courses/courseinfo";
        
        String bodyText = Request.Get(url).execute().returnContent().asString();
        
        Gson mapper = new Gson();
        return mapper.fromJson(bodyText, Course[].class);
    }
}