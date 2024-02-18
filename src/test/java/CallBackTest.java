import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CallBackTest {

    @Test
    void shouldPost(){
       //throw new UnsupportedOperationException();
        //загружаем страницу
        open ("http://localhost:9999");
       //ищем эелементы
        SelenideElement form = $("[enctype=\"application/x-www-form-urlencoded\"]");
        //взаимодействуем с элементами
        form.$("[data-test-id=name] input").setValue("Василий Мамин-Сибиряк");
        form.$("[data-test-id=phone] input").setValue("+79270000000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();

        SelenideElement form2 = $(".Success_successBlock__2L3Cw");
        form2.$("[data-test-id=order-success]").shouldHave(exactText(  "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }

    @Test
    void shouldntPost() {
        open ("http://localhost:9999");
        SelenideElement form = $("[enctype=\"application/x-www-form-urlencoded\"]");
        form.$("[data-test-id=name] input").setValue("Василий Мамин-Сибирёк");
        form.$(".button").click();
        form.$(".input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

}
