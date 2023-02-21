package tw.core;/*
 * This Java source file was generated by the Gradle 'init' task.
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tw.core.exception.OutOfGuessCountException;
import tw.core.generator.AnswerGenerator;
import tw.core.model.GuessResult;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GameTest {

    private final Answer actualAnswer = Answer.createAnswer("1 2 3 4");
    private Game game;

    @BeforeEach
    public void setUp() throws Exception {
        AnswerGenerator answerGenerator = mock(AnswerGenerator.class);
        when(answerGenerator.generate()).thenReturn(actualAnswer);
        game = new Game(answerGenerator);
    }


    @Test
    void should_get_the_success_status_when_guess_input_is_correct() throws Exception {

        //given
//        excuteSuccessGuess();
        GuessResult guess = game.guess(Answer.createAnswer("1 2 3 4"));
        //when
        //then
        assertThat(guess.getResult(), is("4A0B"));
        assertThat(game.checkStatus(), is("success"));

    }

    @Test
     void should_get_the_fail_status_when_input_times_is_6() throws OutOfGuessCountException {
        Answer actualAnswer = Answer.createAnswer("1 3 5 7");
        Answer actualAnswer2 = Answer.createAnswer("2 4 6 8");
        game.guess(actualAnswer);
        game.guess(actualAnswer2);
        game.guess(actualAnswer);
        game.guess(actualAnswer2);
        game.guess(actualAnswer);
        game.guess(actualAnswer2);
        assertThat(game.checkStatus(), is("fail"));
    }

    @Test
    void should_get_the_fail_status_when_input_is_not_correct() throws OutOfGuessCountException {
        Answer actualAnswer = Answer.createAnswer("1 3 5 7");
        Answer actualAnswer2 = Answer.createAnswer("2 4 6 8");
        game.guess(actualAnswer);
        game.guess(actualAnswer2);
        game.guess(actualAnswer);
        game.guess(actualAnswer2);
        game.guess(actualAnswer);
        game.guess(actualAnswer2);
        assertThat(game.checkStatus(), is("fail"));
    }

    @Test
    void should_continue_when_input_times_smaller_than_6() throws OutOfGuessCountException {
        Answer actualAnswer = Answer.createAnswer("1 3 5 7");
        game.guess(actualAnswer);
        assertThat(game.checkStatus(), is("continue"));
    }
    @Test
    void should_return_true_when_checkContinue_equals_continue() throws OutOfGuessCountException {
        Answer actualAnswer = Answer.createAnswer("1 3 5 7");
        game.guess(actualAnswer);
        assertThat(game.checkContinue(), is(true));
    }

    @Test
    void should_return_guessHistory() throws OutOfGuessCountException {
        Answer actualAnswer = Answer.createAnswer("1 3 5 7");
        GuessResult guessResult = game.guess(actualAnswer);
        assertThat(game.guessHistory().get(0), is(guessResult));
    }

    @Test
    void should_return_4A0B_result_when_given_correct_answer() {
        GuessResult guessResult = null;
        try {
            guessResult = game.guess(Answer.createAnswer("1 2 3 4"));
        } catch (OutOfGuessCountException e) {
            throw new RuntimeException(e);
        }
        assertThat(guessResult.getResult(),is("4A0B"));
    }
}
