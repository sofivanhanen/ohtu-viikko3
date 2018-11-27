package ohtu;

public class TennisGame {
    
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
            score1 += 1;
        else
            score2 += 1;
    }

    public String getScore() {
        String score = "";
        int tempScore=0;
        if (score1==score2)
        {
            return scoreAll(score1);
        }
        else if (score1>=4 || score2>=4)
        {
            int minusResult = score1-score2;
            if (minusResult==1) score ="Advantage player1";
            else if (minusResult ==-1) score ="Advantage player2";
            else if (minusResult>=2) score = "Win for player1";
            else score ="Win for player2";
        }
        else
        {
            for (int i=1; i<3; i++)
            {
                if (i==1) tempScore = score1;
                else { score+="-"; tempScore = score2;}
                switch(tempScore)
                {
                    case 0:
                        score+="Love";
                        break;
                    case 1:
                        score+="Fifteen";
                        break;
                    case 2:
                        score+="Thirty";
                        break;
                    case 3:
                        score+="Forty";
                        break;
                }
            }
        }
        return score;
    }
    
    private String score(int score) {
        switch (score1) {
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";
            default:
                throw new IllegalArgumentException("Asked for score with " + score);
        }
    }
    
    private String scoreAll(int score) {
        if (score < 4) return score(score) + "-All";
        else return "Deuce";
    }
}