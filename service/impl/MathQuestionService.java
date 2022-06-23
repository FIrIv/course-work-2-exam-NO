package pro.sky.coursework2exam.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.coursework2exam.data.Question;
import pro.sky.coursework2exam.exception.MethodNotAllowedException;
import pro.sky.coursework2exam.service.QuestionService;

import java.util.HashSet;
import java.util.Set;

@Service
public class MathQuestionService implements QuestionService {

    public MathQuestionService () {
        mathQuestions.add(new Question("Абсолютная величина числа?","Модуль"));
        mathQuestions.add(new Question("Независимая переменная величина?","Аргумент"));
        mathQuestions.add(new Question("Место, занимаемое цифрой в записи числа?","Разряд"));
        mathQuestions.add(new Question("Зависимая переменная величина?","Функция"));
        mathQuestions.add(new Question("Равенство, справедливое при всех допустимых значениях переменных?","Тождество"));
        mathQuestions.add(new Question("В рулоне 8 метров полотна. Каждый день продают по 2 метра. В течение скольких дней продавец будет отрезать по 2 метра ткани?","3"));
        mathQuestions.add(new Question("Стенные часы отбивают 6 ударов за 30 секунд. За сколько секунд эти часы отобьют 12 ударов в полдень или полночь?","66"));
        mathQuestions.add(new Question("Какое наименьшее количество раз справил свой день рождения солдат-призывник, если все свои дни рождения он отмечал?","18"));
        mathQuestions.add(new Question("Крепость обнесена квадратной стеной. Какое минимальное количество солдат потребуется для охраны крепости, чтобы каждую стену охраняли 4 солдата?","8"));
        mathQuestions.add(new Question("Во сколько раз лестница на 6-й этаж дома длиннее лестницы на 2-й этаж этого же дома?","5"));
    }

    private final Set <Question> mathQuestions = new HashSet<>();

    private final java.util.Random random = new java.util.Random();

    @Override
    public Question add (String question, String answer) {
        throw new MethodNotAllowedException();
    }

    @Override
    public Question add (Question question) {
        throw new MethodNotAllowedException();
    }

    @Override
    public Question remove (Question question) {
        throw new MethodNotAllowedException();
    }
    @Override
    public Set<Question> getAll () {

        if (mathQuestions==null) {
            throw new MethodNotAllowedException();
        } else {
            return mathQuestions;
        }
    }

    @Override
    public Question getRandomQuestion() {
        int index = random.nextInt(mathQuestions.size());
        return mathQuestions.stream().toList().get(index);
    }
}
