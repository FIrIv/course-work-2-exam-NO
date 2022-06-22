package pro.sky.coursework2exam.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.coursework2exam.data.Question;
import pro.sky.coursework2exam.services.JavaQuestionService;
import pro.sky.coursework2exam.services.MathQuestionService;

import java.util.Set;

@RestController
@RequestMapping(path = "/math")
public class MathQuestionController {
    private final MathQuestionService mathQuestionService;

    public MathQuestionController (MathQuestionService mathQuestionService) {
        this.mathQuestionService = mathQuestionService;
    }

    @GetMapping(path ="")
    public Set<Question> getAllQuestions () {
        return mathQuestionService.getAll();
    }

    @GetMapping(path ="/add")
    public Question addQuestion (@RequestParam("question") String question,
                                 @RequestParam("answer") String answer) {
        return mathQuestionService.add(question, answer);
    }

    @GetMapping(path ="/remove")
    public Question removeQuestion (@RequestParam("question") String question,
                                    @RequestParam("answer") String answer) {
        return mathQuestionService.remove(new Question(question, answer));
    }
}
