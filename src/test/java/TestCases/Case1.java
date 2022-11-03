package TestCases;

import Drivers.ChromeDrivers;
import Pages.AfterLoginInPage;
import Pages.BeforeLoginPage;
import Pages.LoginPage;
import org.testng.annotations.Test;

public class Case1 extends ChromeDrivers {

    @Test
    public void HepsiburadaTest() {
        LoginPage Login=new LoginPage(driver);
        AfterLoginInPage AftrLogin=new AfterLoginInPage(driver);
        BeforeLoginPage BeforeLogin=new BeforeLoginPage(driver);

        BeforeLogin.BeforeLoginFunction();
        Login.LoginFunction();
        AftrLogin.AfterLoginFunction();

    }
}
