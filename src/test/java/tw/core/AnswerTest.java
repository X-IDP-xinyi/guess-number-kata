package tw.core;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tw.core.exception.AnswerFormatIncorrectException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by jxzhong on 2017/9/23.
 */
class AnswerTest {
    private Answer actualAnswer;

    @BeforeEach
    public void setUp() {
        actualAnswer = Answer.createAnswer("1 2 3 4");
    }

    @Test
    void should_return_answer_when_createAnswer_input_is_inputStr() {
        actualAnswer = Answer.createAnswer("1 2 3 4");
        assertThat(actualAnswer.toString(), is("1 2 3 4"));
    }

    @Test
    void should_validate_answer_when_createAnswer_input_is_correct() {
        actualAnswer = Answer.createAnswer("1 2 3 4");
        try {
            actualAnswer.validate();
        } catch (AnswerFormatIncorrectException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void should_invalidate_answer_when_createAnswer_input_is_false() {
        actualAnswer = Answer.createAnswer("1 2 12 4");
        try {
            actualAnswer.validate();
        } catch (AnswerFormatIncorrectException e) {
//            throw new RuntimeException(e);
            System.out.println("Throw AnswerFormatIncorrectException");
        }
    }


}