package pro.sky.coursework2exam.services;

import org.springframework.stereotype.Service;
import pro.sky.coursework2exam.data.Question;
import pro.sky.coursework2exam.exceptions.BadInputDataException;
import pro.sky.coursework2exam.exceptions.ItemNotFoundException;
import pro.sky.coursework2exam.interfaces.QuestionService;

import java.util.HashSet;
import java.util.Set;
@Service
public class JavaQuestionService implements QuestionService {
    private Set <Question> questions;
    private java.util.Random random = new java.util.Random();
    public JavaQuestionService () {
        this.questions = new HashSet<Question>();
    }

    @Override
    public Question add(String question, String answer) {
        if (question == null || answer == null) {
            throw new BadInputDataException();
        }
        Question temp = new Question (question, answer);
        this.questions.add(temp);
        return temp;
    }

    @Override
    public Question add(Question question) {
        if (question == null) {
            throw new NullPointerException();
        }
        if (question.getQuestion()==null || question.getAnswer()==null) {
            throw new BadInputDataException();
        }
        this.questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (question == null) {
            throw new NullPointerException();
        }
        if (question.getQuestion()==null || question.getAnswer()==null) {
            throw new BadInputDataException();
        }
        if (!this.questions.contains(question)) {
            throw new ItemNotFoundException();
        }
        this.questions.remove(question);
        return question;
    }

    @Override
    public Set<Question> getAll() {
        return questions;
    }

    @Override
    public Question getRandomQuestion() {
        int index = random.nextInt(this.questions.size());
        return this.questions.stream().toList().get(index);
    }
}
