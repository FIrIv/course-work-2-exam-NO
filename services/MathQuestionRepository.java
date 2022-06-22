package pro.sky.coursework2exam.services;

import org.springframework.stereotype.Repository;
import pro.sky.coursework2exam.data.Question;
import pro.sky.coursework2exam.exceptions.BadInputDataException;
import pro.sky.coursework2exam.exceptions.ItemNotFoundException;
import pro.sky.coursework2exam.interfaces.QuestionRepository;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Repository
public class MathQuestionRepository implements QuestionRepository {

    private Set <Question> questions;

    public MathQuestionRepository () {
        this.questions = new HashSet<Question>();
    }

    @PostConstruct
    private void init () {
        questions.add(new Question("Абсолютная величина числа?","Модуль"));
        questions.add(new Question("Независимая переменная величина?","Аргумент"));
        questions.add(new Question("Место, занимаемое цифрой в записи числа?","Разряд"));
        questions.add(new Question("Зависимая переменная величина?","Функция"));
        questions.add(new Question("Равенство, справедливое при всех допустимых значениях переменных?","Тождество"));
        questions.add(new Question("В рулоне 8 метров полотна. Каждый день продают по 2 метра. В течение скольких дней продавец будет отрезать по 2 метра ткани?","3"));
        questions.add(new Question("Стенные часы отбивают 6 ударов за 30 секунд. За сколько секунд эти часы отобьют 12 ударов в полдень или полночь?","66"));
        questions.add(new Question("Какое наименьшее количество раз справил свой день рождения солдат-призывник, если все свои дни рождения он отмечал?","18"));
        questions.add(new Question("Крепость обнесена квадратной стеной. Какое минимальное количество солдат потребуется для охраны крепости, чтобы каждую стену охраняли 4 солдата?","8"));
        questions.add(new Question("Во сколько раз лестница на 6-й этаж дома длиннее лестницы на 2-й этаж этого же дома?","5"));
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
}
