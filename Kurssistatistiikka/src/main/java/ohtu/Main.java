package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // ÄLÄ laita githubiin omaa opiskelijanumeroasi
        String studentNr = "012345678";
        if ( args.length>0) {
            studentNr = args[0];
        }
        
        Submission[] subs = getSubmissionsForStudent(studentNr);
        
        System.out.println("Opiskelijanumero " + studentNr);
        for (Submission submission : subs) {
            System.out.println(submission);
        }
        System.out.println("");
        
        Course[] courses = getCourses();
        
        System.out.println("Kurssit");
        for (Course course : courses) {
            System.out.println(course);
        }

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