package ohtu;

public class Submission {
    private String course;
    private int week;
    private int hours;
    private int[] exercises;
    
    public String getCourse() {
        return course;
    }
    
    public void setCourse(String course) {
        this.course = course;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int[] getExercises() {
        return exercises;
    }

    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }
    
    private String exercisesAsString() {
        String value = "";
        for (int exercise : exercises) {
            value += (" " + exercise);
        }
        return value;
    }

    @Override
    public String toString() {
        return course + ": Viikko " + week + ": Tunteja käytetty " + hours + ", tehdyt tehtävät: " + exercisesAsString();
    }
    
}