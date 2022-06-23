package pro.sky.coursework2exam.service;

import org.springframework.stereotype.Service;
import pro.sky.coursework2exam.data.Question;
import pro.sky.coursework2exam.interfaces.QuestionService;

import java.util.Set;

@Service
public class JavaQuestionService implements QuestionService {

    private final java.util.Random random = new java.util.Random();
    private final JavaQuestionRepository javaQuestionRepository;

    public JavaQuestionService (JavaQuestionRepository javaQuestionRepository) {
        this.javaQuestionRepository = javaQuestionRepository;
    }

    @Override
    public Question add (String question, String answer) {
        return this.javaQuestionRepository.add(question, answer);
    }

    @Override
    public Question add (Question question) {
        return this.javaQuestionRepository.add(question);
    }
    @Override
    public Question remove (Question question) {
        return this.javaQuestionRepository.remove(question);
    }
    @Override
    public Set<Question> getAll () {
        return this.javaQuestionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        int index = random.nextInt(javaQuestionRepository.getAll().size());
        return this.javaQuestionRepository.getAll().stream().toList().get(index);
    }
}
