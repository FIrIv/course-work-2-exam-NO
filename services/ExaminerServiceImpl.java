package pro.sky.coursework2exam.services;

import org.springframework.stereotype.Service;
import pro.sky.coursework2exam.data.Question;
import pro.sky.coursework2exam.exceptions.BadRequestException;
import pro.sky.coursework2exam.interfaces.ExaminerService;
import pro.sky.coursework2exam.interfaces.QuestionService;

import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;

    public ExaminerServiceImpl (QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Set<Question> getQuestions(int amount) {
        Set <Question> temp = new HashSet<>();
        int temp_amount = 0;
        if (questionService.getAll().size() < amount) {
            throw new BadRequestException();
        }
        while (temp_amount<amount) {
            Question q = questionService.getRandomQuestion();
            if (!temp.contains(q)) {
                temp.add(q);
                temp_amount++;
            }
        }
        return temp;
    }

    public int size() {
        return questionService.getAll().size();
    }
}
