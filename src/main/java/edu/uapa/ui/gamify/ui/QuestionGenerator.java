package edu.uapa.ui.gamify.ui;

import edu.uapa.ui.gamify.models.Question;
import edu.uapa.ui.gamify.models.Response;

import java.util.ArrayList;
import java.util.List;

public class QuestionGenerator {
    private List<Question> questions = new ArrayList<>();

    private void questionification() {
        questions.add(generator("Como te llama?", "Marlin"));
        questions.add(generator("Cuando se descubrio america?", "1492"));
        questions.add(generator("Cuando son 2x2?", "4"));
        questions.add(generator("Que es la vida?", "no se"));
        questions.add(generator("Que es un sueno?", "no se"));
        questions.add(generator("Cien anos de soledad?", "G G M"));
    }

    private Question generator(String ques, String res) {
        Response response1 = new Response();
        response1.setResponse(res);
        Response response2 = new Response();
        response2.setResponse("Antonio");
        Response response3 = new Response();
        response3.setResponse("Ramon");
        Response response4 = new Response();
        response4.setResponse("Coco");

        Question question = new Question();
        question.setId(1L);
        question.setQuestion(ques);
        question.addBadResponse(response2);
        question.addBadResponse(response3);
        question.addBadResponse(response4);
        question.setResponse(response1);

        return question;
    }

    public List<Question> get() {
        questionification();
        return questions;
    }
}
