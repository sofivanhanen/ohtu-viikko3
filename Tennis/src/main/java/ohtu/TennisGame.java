package ohtu;

public class TennisGame {
    
    private static final int SCORE_THRESHOLD = 4;
    private static final int ADVANTAGE_DIFF = 1;
    private static final int WIN_DIFF = 2;
    
    private int score1 = 0;
    private int score2 = 0;
    private String name1;
    private String name2;

    public TennisGame(String player1, String player2) {
        name1 = player1;
        name2 = player2;
    }

    public void wonPoint(String player) {
        if (player == "player1")
            score1++;
        else
            score2++;
    }

    public String getScore() {
        if (score1 == score2) return scoreAll(score1);
        else if (score1 >= SCORE_THRESHOLD || score2 >= SCORE_THRESHOLD) return scoreAdvantage(score1, score2);
        else return scoreLowPoints(score1, score2);
    }
    
    private static String score(int score) {
        switch (score) {
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";
            default:
                throw new IllegalArgumentException("Asked for undefined score " + score);
        }
    }
    
    private static String scoreLowPoints(int score1, int score2) {
        return score(score1) + "-" + score(score2);
    }
    
    private static String scoreAdvantage(int score1, int score2) {
        int diff = score1-score2;
        if (diff >= WIN_DIFF) return "Win for player1";
        else if (diff <= -WIN_DIFF) return "Win for player2";
        else if (diff >= ADVANTAGE_DIFF) return "Advantage player1";
        else if (diff <= -ADVANTAGE_DIFF) return "Advantage player2";
        
        throw new IllegalStateException("Advantage score undefined for score " + score1 + "-" + score2);
    }
    
    private static String scoreAll(int score) {
        if (score < 4) return score(score) + "-All";
        else return "Deuce";
    }
}