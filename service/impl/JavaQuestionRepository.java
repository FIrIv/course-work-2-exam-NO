package pro.sky.coursework2exam.service.impl;

import org.springframework.stereotype.Repository;
import pro.sky.coursework2exam.data.Question;
import pro.sky.coursework2exam.exception.BadInputDataException;
import pro.sky.coursework2exam.exception.ItemNotFoundException;
import pro.sky.coursework2exam.service.QuestionRepository;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Repository
public class JavaQuestionRepository implements QuestionRepository {

    private final Set <Question> questions;

    public JavaQuestionRepository () {
        this.questions = new HashSet<Question>();
    }

    @PostConstruct
    private void init () {
        questions.add(new Question("Сокрытие реализации при помощи модификаторов доступа, при помощи геттеров и сеттеров.", "Инкапсуляция"));
        questions.add(new Question("Существует ли класс, который не наследуются от Object?","Нет"));
        questions.add(new Question("Существует ли класс, который не наследуются от Object?","Не существует"));
        questions.add(new Question("Модфикатор уровня доступа - только класс, внутри которого он объявлен.","private"));
        questions.add(new Question("Модфикатор уровня доступа - только класс, внутри которого он объявлен.","Private"));
        questions.add(new Question("Модфикатор уровня доступа - полноценный доступ во всем приложении.","public"));
        questions.add(new Question("Модфикатор уровня доступа - полноценный доступ во всем приложении.","Public"));
        questions.add(new Question("Набор из названия метода и аргументов, какие принимает метод.","Сигнатура"));
        questions.add(new Question("Набор из названия метода и аргументов, какие принимает метод.","Сигнатура метода"));
        questions.add(new Question("Способность программы идентично использовать объекты с одинаковым интерфейсом без информации о конкретном типе этого объекта.","Полиморфизм"));
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
            throw new BadInputDataException();
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
            throw new BadInputDataException();
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
