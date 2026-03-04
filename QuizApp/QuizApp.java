import java.util.*;

class Question {
    String question;
    String[] options;
    int correctAnswer;

    Question(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}

public class QuizApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Question> questions = new ArrayList<>();

        questions.add(new Question(
                "What is the capital of India?",
                new String[]{"1. Mumbai", "2. Delhi", "3. Kolkata", "4. Chennai"},
                2));

        questions.add(new Question(
                "Which language runs on JVM?",
                new String[]{"1. Python", "2. C++", "3. Java", "4. Swift"},
                3));

        int score = 0;

        for (Question q : questions) {
            System.out.println("\n" + q.question);
            for (String opt : q.options)
                System.out.println(opt);

            System.out.print("Enter answer: ");
            int ans = sc.nextInt();

            if (ans == q.correctAnswer) {
                score++;
            }
        }

        System.out.println("\nFinal Score: " + score + "/" + questions.size());
        sc.close();
    }
}