package pro.sky.coursework2exam.services;

import org.springframework.stereotype.Service;
import pro.sky.coursework2exam.data.Question;
import pro.sky.coursework2exam.exceptions.BadRequestException;
import pro.sky.coursework2exam.interfaces.ExaminerService;
import pro.sky.coursework2exam.interfaces.QuestionService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private java.util.Random random = new java.util.Random();

    private List<Integer> countQ = new ArrayList<>();

    private final List<QuestionService> questionServices = new ArrayList<>();

    public ExaminerServiceImpl (JavaQuestionService javaQuestionService,
                                MathQuestionService mathQuestionService) {
        this.questionServices.add(javaQuestionService);
        this.questionServices.add(mathQuestionService);
    }

    @Override
    public Set<Question> getQuestions(int amount) {
        Set <Question> temp = new HashSet<>();
        int temp_amount = 0;

        // генерируем кол-во вопросов для каждого сервиса
        generateCountsJavaMathQuestions (amount);

        int previousCountQ = 0;
        for (int i=0; i<questionServices.size(); i++) {
            while (temp_amount < previousCountQ+countQ.get(i)) {
                Question q = questionServices.get(i).getRandomQuestion();
                if (!temp.contains(q)) {
                    temp.add(q);
                    temp_amount++;
                }
            }
            previousCountQ = countQ.get(i);
        }

        return temp;
    }

    private void generateCountsJavaMathQuestions (int amount) {
        if (this.questionServices.stream().mapToInt(e->e.getAll().size()).sum() < amount) {
            throw new BadRequestException();
        }

        int sum = 0;
        for (int i=0; i<this.questionServices.size(); i++) {
            countQ.add(i, random.nextInt(this.questionServices.get(i).getAll().size()));
            sum += countQ.get(i);
        }

        // если сгенерированные кол-ва вопросов превысили общее нужное кол-во,
        // уменьшаем по всем, пока не дойдем до нормы
        while (sum>amount) {
            for (int i=0; i<this.questionServices.size(); i++) {
                // чтобы ниже 0 не упало кол-во вопросов очередного сервиса
                if (countQ.get(i)>0) {
                    countQ.set(i, countQ.get(i) - 1);
                    sum -= 1;
                    if (sum == amount) {
                        break;
                    }
                }
            }
        }

        // если сгенерированные кол-ва вопросов не дотянули до общего нужного кол-ва,
        // увеличиваем по всем, пока не дойдем до нормы
        while (sum<amount) {
            for (int i=0; i<this.questionServices.size(); i++) {
                countQ.set(i, countQ.get(i)+1);
                sum += 1;
                if (sum == amount) {
                    break;
                }
            }
        }

        for (int i=0; i<this.questionServices.size(); i++) {
            System.out.print("по " + (i+1) + "-ой дисципл. " + countQ.get(i)+" вопросов, ");
        }
        System.out.println();
    }
}
