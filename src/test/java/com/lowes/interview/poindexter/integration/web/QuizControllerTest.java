package com.lowes.interview.poindexter.integration.web;

import com.lowes.interview.poindexter.model.response.QuestionResponse;
import com.lowes.interview.poindexter.model.response.QuizResponse;
import com.lowes.interview.poindexter.services.quiz.QuizService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(QuizController.class)
public class QuizControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuizService quizService;

    @Test
    public void shouldReturnQuizzes() throws Exception {
        QuestionResponse question1 = new QuestionResponse("type", "difficulty", "question", List.of("1, 2", "3"), "correct");
        QuestionResponse question2 = new QuestionResponse("type2", "difficulty2", "question2", List.of("12, 22", "32"), "correct2");
        QuizResponse quiz1 = new QuizResponse("cat1", List.of(question1, question2));
        QuizResponse quiz2 = new QuizResponse("cat2", List.of(question2, question2));
        when(quizService.getQuizzes()).thenReturn(List.of(quiz1, quiz2));
        this.mockMvc.perform(get("/coding/exercise/quiz")).andDo(print()).andExpect(content().string(equalTo("{\"quiz\":[{\"category\":\"cat1\",\"results\":[{\"type\":\"type\",\"difficulty\":\"difficulty\",\"question\":\"question\",\"allAnswers\":[\"1, 2\",\"3\"],\"correctAnswer\":\"correct\"},{\"type\":\"type2\",\"difficulty\":\"difficulty2\",\"question\":\"question2\",\"allAnswers\":[\"12, 22\",\"32\"],\"correctAnswer\":\"correct2\"}]},{\"category\":\"cat2\",\"results\":[{\"type\":\"type2\",\"difficulty\":\"difficulty2\",\"question\":\"question2\",\"allAnswers\":[\"12, 22\",\"32\"],\"correctAnswer\":\"correct2\"},{\"type\":\"type2\",\"difficulty\":\"difficulty2\",\"question\":\"question2\",\"allAnswers\":[\"12, 22\",\"32\"],\"correctAnswer\":\"correct2\"}]}]}")));
    }
}
