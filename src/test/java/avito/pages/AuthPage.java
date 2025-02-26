package avito.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class AuthPage {
    private final SelenideElement loginForm = $("[data-marker='login-form']");

    @Step("Проверка открытия формы входа в аккаунт")
    public AuthPage checkOpenLoginForm() {
        loginForm.shouldHave(text("Вход"));
        return this;
    }

    @Step("Нажать кнопку 'Войти'")
    public AuthPage pressButtonEntry() {
        $("[data-marker='login-form/submit']").click();
        return this;
    }

    @Step("Ошибка при незаполнении логина или пароля - 'Заполните поле'")
    public AuthPage errorFillField() {
        $("[data-marker='field/error']").shouldHave(text("Заполните поле"));
        return this;
    }

    @Step("Ввести случайный логин - {value}")
    public AuthPage enterLogin(String value) {
        $("[data-marker='login-form/login/input']").setValue(value);
        return this;
    }

    @Step("Ввести случайный пароль - {value}")
    public AuthPage enterPassword(String value) {
        $("[data-marker='login-form/password/input']").setValue(value);
        return this;
    }

    @Step("Проверка наличия капчи")
    public AuthPage checkCaptcha() {
        $("[href='https://www.geetest.com/first_page']").shouldBe(exist);
        return this;
    }
}