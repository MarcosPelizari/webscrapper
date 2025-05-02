package scrap;

public class Reviews {
    private String name;
    private String date;
    private int score;
    private String text;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Reviews{" +
                "name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", score=" + score +
                ", text='" + text + '\'' +
                '}';
    }
}
