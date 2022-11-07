package ru.netology.web.test;

import org.junit.jupiter.api.*;
import ru.netology.web.data.DataHelper;
import ru.netology.web.data.SQLHelper;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.web.data.SQLHelper.deleteDbData;

class LoginTest {
    LoginPage loginPage;

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }

    @AfterAll
    static void clear() {
    deleteDbData();
    }

    @Test
    void shouldSuccessfullLogin() {
        loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verifyVisibility();
        var verificationCode = SQLHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode.getCode());
    }
}