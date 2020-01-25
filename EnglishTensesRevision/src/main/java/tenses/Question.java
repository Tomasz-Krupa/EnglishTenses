package tenses;

import lombok.Getter;
import lombok.Setter;

public class Question {
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String questionBody;
    @Getter
    @Setter
    private String A;
    @Getter
    @Setter
    private String B;
    @Getter
    @Setter
    private String C;
    @Getter
    @Setter
    private String D;
    @Getter
    @Setter
    private String rightAnswer;

    public String toString() {
        return
                        id + ". " +
                        questionBody + '\n' +
                        "A) " + A + '\n' +
                        "B) " + B + '\n' +
                        "C) " + C + '\n' +
                        "D) " + D + '\n';
    }
}
