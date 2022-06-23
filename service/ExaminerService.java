package pro.sky.coursework2exam.service;

import pro.sky.coursework2exam.data.Question;

import java.util.Set;

public interface ExaminerService {
    public Set<Question> getQuestions (int amount);
}
