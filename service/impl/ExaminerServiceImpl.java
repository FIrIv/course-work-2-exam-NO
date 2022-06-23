package pro.sky.coursework2exam.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.coursework2exam.data.Question;
import pro.sky.coursework2exam.exception.BadRequestException;
import pro.sky.coursework2exam.service.ExaminerService;
import pro.sky.coursework2exam.service.QuestionService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final List<QuestionService> questionServices = new ArrayList<>();

    public ExaminerServiceImpl (JavaQuestionService javaQuestionService,
                                MathQuestionService mathQuestionService) {
        this.questionServices.add(javaQuestionService);
        this.questionServices.add(mathQuestionService);
    }

    @Override
    public Set<Question> getQuestions(int amount) {
        Set <Question> temp = new HashSet<>();
        int tempAmount = 0;

        if (this.questionServices.stream().mapToInt(e->e.getAll().size()).sum() < amount) {
            throw new BadRequestException();
        }

        while (tempAmount < amount) {
            for (int i=0; i<this.questionServices.size(); i++) {
                Question q = questionServices.get(i).getRandomQuestion();
                // проверяю, потому что tempAmount не надо увеличивать,
                // если вопрос уже есть в temp
                if (!temp.contains(q)) {
                    temp.add(q);
                    tempAmount++;
                }
            }
        }

        return temp;
    }
}
