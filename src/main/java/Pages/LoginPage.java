package Pages;

import Utils.reports.ExtentTestManager;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginPage extends BasePage{
    String eMail="a101hepsiburada@gmail.com";
    String Pass="Deneme01";
    String Account="Hesabım";

    By HepsiburadaLogoControl=new By.ByXPath("//a[@title='Hepsiburada']//*[name()='svg']");
    By AccountButton=new By.ByXPath("//div[@id='myAccount']");
    By Login=new By.ByXPath("//a[@id='login']");
    By EMailLabel=new By.ByXPath("//input[@id='txtUserName']");
    By LoginButton=new By.ByXPath("//button[@id='btnLogin']");
    By PasswordLabel=new By.ByXPath("//input[@id='txtPassword']");
    By OkeyButton=new By.ByXPath("//button[@id='btnEmailSelect']");
    By LoginControl=new By.ByXPath("//a[@title='Hesabım']"); //hesabım yazısını görürse
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    public void LoginFunction() {
        waitForSecond(2);
        Boolean HepsiburadaPageControl = isDisplay(HepsiburadaLogoControl);
        Assert.assertTrue(HepsiburadaPageControl);
        ExtentTestManager.getTest().log(Status.PASS,"Hepsiburada page opened");

        waitForSecond(1);
        click(AccountButton);
        waitForSecond(2);
        click(Login);
        waitForSecond(2);
        click(EMailLabel);
        send(EMailLabel,eMail);
        waitForSecond(2);
        click(LoginButton);
        waitForSecond(2);
        click(PasswordLabel);
        waitForSecond(2);
        send(PasswordLabel,Pass);
        waitForSecond(2);
        click(OkeyButton);
        waitForSecond(5);
       Boolean LoginControle = isDisplay(LoginControl);
        Assert.assertTrue(LoginControle);
        ExtentTestManager.getTest().log(Status.PASS,"login is made");

    }
}
