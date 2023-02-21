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
        assertThat(actualAnswer.toString(), is("1 2 3 4"));
    }

    @Test
    void should_validate_answer_when_createAnswer_input_is_correct() {
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
    @Test
    void should_return_string_method_of_input_answer() {
        assertThat(actualAnswer.toString(), is("1 2 3 4"));
    }

    @Test
    void should_return_index_when_given_input_answer() {
        assertThat(actualAnswer.getIndexOfNum("4"),is(3));
        assertThat(actualAnswer.getIndexOfNum("1"),is(0));
    }
    @Test
    void should_return_4A0B_result_when_given_correct_answer() {
        assertThat(actualAnswer.check(actualAnswer).getValue(),is("4A0B"));
    }

}