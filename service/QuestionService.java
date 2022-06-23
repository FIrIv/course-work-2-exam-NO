package pro.sky.coursework2exam.service;

import pro.sky.coursework2exam.data.Question;

import java.util.Set;

public interface QuestionService {
    Question add (String question, String answer);
    Question add (Question question);
    Question remove (Question question);
    Set<Question> getAll ();
    Question getRandomQuestion ();
}
