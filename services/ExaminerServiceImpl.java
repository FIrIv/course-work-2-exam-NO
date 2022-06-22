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

    private java.util.Random random = new java.util.Random();
    private int countJavaQ;
    private int countMathQ;

    private final JavaQuestionService javaQuestionService;

    private final MathQuestionService mathQuestionService;

    public ExaminerServiceImpl (JavaQuestionService javaQuestionService,
                                MathQuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
    }

    @Override
    public Set<Question> getQuestions(int amount) {
        Set <Question> temp = new HashSet<>();
        int temp_amount = 0;

        generateCountsJavaMathQuestions (amount);

        while (temp_amount<this.countJavaQ) {
            Question q = javaQuestionService.getRandomQuestion();
            if (!temp.contains(q)) {
                temp.add(q);
                temp_amount++;
            }
        }

        while (temp_amount<this.countJavaQ + this.countMathQ) {
            Question q = mathQuestionService.getRandomQuestion();
            if (!temp.contains(q)) {
                temp.add(q);
                temp_amount++;
            }
        }

        return temp;
    }

    private void generateCountsJavaMathQuestions (int amount) {
        if (javaQuestionService.getAll().size() + mathQuestionService.getAll().size() < amount) {
            throw new BadRequestException();
        }

        this.countJavaQ = random.nextInt(amount);
        if (this.countJavaQ>javaQuestionService.getAll().size()) {
            this.countJavaQ = javaQuestionService.getAll().size();
        }

        this.countMathQ = amount - this.countJavaQ;
        if (countMathQ>mathQuestionService.getAll().size()) {
            this.countJavaQ += this.countMathQ - mathQuestionService.getAll().size();
            this.countMathQ = mathQuestionService.getAll().size();
        }
    }
}
