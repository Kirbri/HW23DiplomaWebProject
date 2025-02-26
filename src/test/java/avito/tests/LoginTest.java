package avito.tests;

import avito.pages.AuthPage;
import avito.pages.MainPage;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import utils.RandomUtils;

@Feature("Тестирование входа в аккаунт случайными данными")

@Tags({
        @Tag("webLogin"),
        @Tag("withoutLogin")
})
public class LoginTest extends TestBase {

    final MainPage mainPage = new MainPage();
    final AuthPage authPage = new AuthPage();
    final RandomUtils randomUtils = new RandomUtils();

    @Test
    @DisplayName("Ошибка входа при незаполненном логине и пароле - 'Заполните поле'")
    @Tag("BLOCKER")
    public void loginErrorWithEmptyLoginAndPasswordTest() {
        mainPage.pressLoginButton();
        authPage.checkOpenLoginForm()
                .pressButtonEntry()
                .errorFillField();
    }

    @Test
    @DisplayName("Ошибка входа при незаполненном пароле - 'Заполните поле'")
    @Tag("BLOCKER")
    public void loginErrorWithEmptyPasswordTest() {
        mainPage.pressLoginButton();
        authPage.checkOpenLoginForm()
                .enterLogin(randomUtils.getRandomMobilePhoneNumber())
                .pressButtonEntry()
                .errorFillField();
    }

    @Test
    @DisplayName("Ошибка входа при незаполненном логине - 'Заполните поле'")
    @Tag("SMOKE")
    public void loginErrorWithEmptyLoginTest() {
        mainPage.pressLoginButton();
        authPage.checkOpenLoginForm()
                .enterPassword(randomUtils.getRandomPassword())
                .pressButtonEntry()
                .errorFillField();
    }

    @Test
    @DisplayName("""
    Появление капчи при заполнении некорректных логина и пароля, ошибка 'Почта не привязана к профилю.\s
    Проверьте, нет ли опечаток, или войдите по телефону'""")
    @Tag("BLOCKER")
    public void inputLoginAndPasswordThenCaptchaTest() {
        mainPage.pressLoginButton();
        authPage.checkOpenLoginForm()
                .enterLogin(randomUtils.getRandomEmailAddress())
                .enterPassword(randomUtils.getRandomPassword())
                .pressButtonEntry()
                .checkCaptcha();
    }

    @Test
    @DisplayName("""
            Появление капчи при заполнении некорректного по длине логина, ошибка 'Длина логина не должна\s
            превышать 64 символа'""")
    @Tag("SMOKE")
    public void inputLoginMore64SymbolsAndPasswordThenCaptchaTest() {
        mainPage.pressLoginButton();
        authPage.checkOpenLoginForm()
                .enterLogin(randomUtils.getRandomPhoneNumber65())
                .enterPassword(randomUtils.getRandomPassword())
                .pressButtonEntry()
                .checkCaptcha();
    }
}