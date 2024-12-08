import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageobject.MainPage;

import static org.junit.Assert.assertEquals;
import static pageobject.constants.MainPageConstants.*;

@RunWith(Parameterized.class)
public class MainPageFaqTest extends BaseUITest {

    private final String questionFAQ;
    private final String answerFAQ;

    public MainPageFaqTest(String questionFAQ, String answerFAQ) {
        this.questionFAQ = questionFAQ;
        this.answerFAQ = answerFAQ;
    }

    @Parameterized.Parameters

    public static Object[][] checkFAQAnswerTest() {
        return new Object[][]{
                {QUESTION_HOW_MUCH_COSTS, ANSWER_HOW_MUCH_COSTS},
                {QUESTION_WANT_SOME_SCOOTERS, ANSWER_WANT_SOME_SCOOTERS},
                {QUESTION_CALC_RENT_TIME, ANSWER_CALC_RENT_TIME},
                {QUESTION_ORDER_TODAY, ANSWER_ORDER_TODAY},
                {QUESTION_EXTEND_RETURN_EARLIER, ANSWER_EXTEND_RETURN_EARLIER},
                {QUESTION_CHARGING_TOGETHER, ANSWER_CHARGING_TOGETHER},
                {QUESTION_CANCEL_ORDER, ANSWER_CANCEL_ORDER},
                {QUESTION_DELIVER_OUTSIDE_MRR, ANSWER_DELIVER_OUTSIDE_MRR},

        };
    }


    @Test

    public void isAnswerOpenedTest() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.scrollToElement(questionFAQ);
        objMainPage.isEnabledQuestionButton(questionFAQ);
        objMainPage.clickFAQQuestionButton(questionFAQ);
        objMainPage.isAnswerVisible();
        String actAnswersText = objMainPage.getAnswersText();
        String expAnswerText = answerFAQ;
        assertEquals("Текст ответа не совпадает с ожидаемым", expAnswerText, actAnswersText);
    }
}
