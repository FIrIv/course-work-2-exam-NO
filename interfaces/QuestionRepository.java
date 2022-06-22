package pro.sky.coursework2exam.interfaces;

import pro.sky.coursework2exam.data.Question;

import java.util.Set;

public interface QuestionRepository {
    Question add (String question, String answer);
    Question add (Question question);
    Question remove (Question question);
    Set<Question> getAll ();
}
