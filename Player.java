
public class Player {
    private String name = "No Name";
    private String[] score = {"-1","-1","-1"};
    private int totalScore = 0;

    Player(String name){
        this.name = name;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setScore(int gameIndex, String newScore){
        this.score[gameIndex] = newScore;
        totalScore += Integer.parseInt(newScore);
    }

    public String getName(){
        return this.name;
    }

    public String getScore(int gameIndex){
        return this.score[gameIndex];
    }

    public int getTotalScore(){
        return totalScore;
    }
}
