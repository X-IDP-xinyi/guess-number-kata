package tw.core;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tw.core.exception.AnswerFormatIncorrectException;
import tw.core.model.Record;

import static com.google.common.base.Preconditions.checkNotNull;
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
        Answer answer = Answer.createAnswer("1 2 6 8");
        try {
            answer.validate();
        } catch (AnswerFormatIncorrectException e) {
            System.out.println("Do not Throw AnswerFormatIncorrectException");
        }
    }

    @Test
    void should_invalidate_answer_when_createAnswer_input_is_false() {
        Answer answer = Answer.createAnswer("1 2 12 4");
        try {
            answer.validate();
        } catch (AnswerFormatIncorrectException e) {
            System.out.println("Throw AnswerFormatIncorrectException");
        }
    }

    @Test
    void should_invalidate_answer_when_createAnswer_input_size_false() {
        Answer answer = Answer.createAnswer("1 2 4");
        try {
            answer.validate();
        } catch (AnswerFormatIncorrectException e) {
            checkNotNull(e);
        }
    }

    @Test
    void should_return_string_method_of_input_answer() {
        assertThat(actualAnswer.toString(), is("1 2 3 4"));
    }

    @Test
    void should_return_index_when_given_input_answer() {
        assertThat(actualAnswer.getIndexOfNum("4"), is(3));
        assertThat(actualAnswer.getIndexOfNum("1"), is(0));
    }

    @Test
    void should_return_4A0B_result_when_given_correct_answer() {
        assertThat(actualAnswer.check(actualAnswer).getValue(), is("4A0B"));
    }

    @Test
    void should_return_0A0B_result_when_given_failed_answer() {
        Answer answer = Answer.createAnswer("5 6 7 8");
        assertThat(actualAnswer.check(answer).getValue(), is("0A0B"));
    }

    @Test
    void should_create_Answer_when_given_inputStr() {
        String inputStr = "1 2 3 4";
        assertThat(Answer.createAnswer(inputStr).toString(), is("1 2 3 4"));
    }
    @Test
    public void should_return_Record_when_check_input_is_inputAnswer() {
        Answer actualAnswer1 = Answer.createAnswer("1 2 6 3");
        Answer actualAnswer2 = Answer.createAnswer("1 2 3 6");
        Record record = actualAnswer1.check( actualAnswer2 );
        assertThat(record.getValue(),is(String.format("2A2B")));
    }

}