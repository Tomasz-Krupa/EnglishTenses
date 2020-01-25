package tenses;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class QuestionFactory {

    public static final String ADD_QUESTION_QUERY = "INSERT INTO \"questions\" (question, A, B, C, D, rightanswer) VALUES (?,?,?,?,?,?) ";

    private final Connection connection;
    List<Question> questions = new ArrayList<>();

    public QuestionFactory(Connection c) {
        this.connection = c;
    }

    /**
     * Method adding new question to database
     */
    void addQuestion(Question question) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement(ADD_QUESTION_QUERY);
            questionFactory(question);
            statement.setString(1, question.getQuestionBody());
            statement.setString(2, question.getA());
            statement.setString(3, question.getB());
            statement.setString(4, question.getC());
            statement.setString(5, question.getD());
            statement.setString(6, question.getRightAnswer());
            statement.executeUpdate();
            System.out.println("Pytanie dodano do bazy");
        }
        catch (SQLException e) {
            System.out.println("Nie udało się dodać pytania.");
            e.printStackTrace();
        }

    }
        /**
         * Method scanning question from console
         */
        void questionFactory (Question question){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Podaj treść pytania:");
            question.setQuestionBody(scanner.next());
            System.out.println("Podaj odpowiedź A:");
            question.setA(scanner.next());
            System.out.println("Podaj odpowiedź B:");
            question.setB(scanner.next());
            System.out.println("Podaj odpowiedź C:");
            question.setC(scanner.next());
            System.out.println("Podaj odpowiedź D:");
            question.setD(scanner.next());
            System.out.println("Podaj właściwą odpowiedź:");
            question.setRightAnswer(scanner.next());
        }
    }

