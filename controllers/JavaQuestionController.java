package pro.sky.coursework2exam.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.coursework2exam.data.Question;
import pro.sky.coursework2exam.services.JavaQuestionService;

import java.util.Set;

@RestController
@RequestMapping (path = "/java")
public class JavaQuestionController {

    private final JavaQuestionService javaQuestionService;

    public JavaQuestionController (JavaQuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @GetMapping(path ="")
    public Set<Question> getAllQuestions () {
        return javaQuestionService.getAll();
    }

    @GetMapping(path ="/add")
    public Question addQuestion (@RequestParam("question") String question,
                                 @RequestParam("answer") String answer) {
        return javaQuestionService.add(question, answer);
    }

    @GetMapping(path ="/remove")
    public Question removeQuestion (@RequestParam("question") String question,
                                 @RequestParam("answer") String answer) {
        return javaQuestionService.remove(new Question(question, answer));
    }
}
