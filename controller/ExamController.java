package pro.sky.coursework2exam.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.coursework2exam.data.Question;
import pro.sky.coursework2exam.service.impl.ExaminerServiceImpl;

import java.util.Set;

@RestController
public class ExamController {

    private final ExaminerServiceImpl examinerService;

    public ExamController (ExaminerServiceImpl examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping
    public Set<Question> getQuestions (@RequestParam("amount") int amount) {
        return examinerService.getQuestions(amount);
    }
}
