package statistics;

import statistics.matcher.All;
import statistics.matcher.And;
import statistics.matcher.HasAtLeast;
import statistics.matcher.HasFewerThan;
import statistics.matcher.Matcher;
import statistics.matcher.Or;
import statistics.matcher.PlaysIn;

public class Querybuilder {

    private Matcher matcher;

    public Querybuilder() {
        this.matcher = new All();
    }

    public Matcher build() {
        return matcher;
    }

    public Querybuilder playsIn(String team) {
        matcher = new And(matcher, new PlaysIn(team));
        return this;
    }
    
    public Querybuilder hasAtLeast(int value, String category) {
        matcher = new And(matcher, new HasAtLeast(value, category));
        return this;
    }
    
    public Querybuilder hasFewerThan(int value, String category) {
        matcher = new And(matcher, new HasFewerThan(value, category));
        return this;
    }
    
    public Querybuilder oneOf(Matcher first, Matcher second) {
        matcher = new Or(first, second);
        return this;
    }

}
