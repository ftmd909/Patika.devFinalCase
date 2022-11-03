package Pages;

import Utils.reports.ExtentTestManager;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class BeforeLoginPage extends BasePage{
    String SearchText="İyi Hissetmek - David Burns";

    By CoockiesAccept=new By.ByXPath("//button[@id=\"onetrust-accept-btn-handler\"]");
    By SearchTab=new By.ByXPath("//input[@class='desktopOldAutosuggestTheme-UyU36RyhCTcuRs_sXL9b']");
    By SearchIcon=new By.ByXPath("//div[@class='SearchBoxOld-cHxjyU99nxdIaAbGyX7F']");
    By SelectProduct=new By.ByXPath("//li[@id='i0']");
    By SelectProductAddToBasket=new By.ByXPath("//button[@data-test-id='product-info-button']");
    By ScContainer =new By.ByXPath("(//header[@class='title-wrapper'])[2]");
    By AddToBasketButton=new By.ByXPath("//button[@id='addToCart']");
    By GoToBAsketButton=new By.ByXPath("//button[normalize-space()='Sepete git']");
    By MyBasketControl=new By.ByXPath("//h1[normalize-space()='Sepetim']");
    By DeleteProduct=new By.ByXPath("//a[@aria-label='Ürünü Kaldır']");
    By BasketIsEmpty=new By.ByXPath("//h1[contains(text(),'Sepetin şu an boş')]");
    By BasketProductTabs=new By.ByXPath("//div[@class='merchantBoxWrapper_10ife']");

    public BeforeLoginPage(WebDriver driver) {
        super(driver);
    }
    public void BeforeLoginFunction() {
        waitForSecond(2);
        click(CoockiesAccept);
        Boolean CoockiesAcceptControl = isDisplay(CoockiesAccept);
        Assert.assertTrue(CoockiesAcceptControl);
        ExtentTestManager.getTest().log(Status.PASS,"Cookies accepted");

        waitForSecond(1);
        click(SearchTab);
        send(SearchTab,SearchText);
        waitForSecond(2);

        click(SearchIcon);
        waitForSecond(2);

        scrollPageElement(drivers().findElement(SelectProduct));

        click(SelectProductAddToBasket);
        waitForSecond(2);

        click(SelectProduct);
        waitForSecond(1);

        String orginalWindow = drivers().getWindowHandle();
        drivers().switchTo().window(orginalWindow);

        for (String windowHandleProduct : drivers().getWindowHandles()) {
            if (!orginalWindow.contentEquals(windowHandleProduct)) {
                drivers().switchTo().window(windowHandleProduct);
                waitForSecond(5);

                String ProductNameControl = find(By.xpath("//h1[@id='product-name']"))
                        .getText();
                Assert.assertEquals(ProductNameControl,SearchText);
                waitForSecond(2);
                ExtentTestManager.getTest().log(Status.PASS,"The select product is true");

                waitForSecond(2);
                scrollPageElement(drivers().findElement(ScContainer));
                waitForSecond(3);

                Boolean ProductControl = isDisplay(AddToBasketButton);
                Assert.assertTrue(ProductControl);
                ExtentTestManager.getTest().log(Status.PASS,"Selected product page Opened");  //Seçilen ürün sayfası açıldı

                waitForSecond(2);
                click(AddToBasketButton);
                waitForSecond(2);
                click(GoToBAsketButton);

                waitForSecond(2);
                Boolean MyBasketPageControl = isDisplay(MyBasketControl);
                Assert.assertTrue(MyBasketPageControl);
                ExtentTestManager.getTest().log(Status.PASS,"MyBasket Page Opened");

                String BasketItemControl = find(By.xpath("//span[@id='basket-item-count']"))
                        .getText();
                Assert.assertEquals(BasketItemControl,"2");
                waitForSecond(2);
                ExtentTestManager.getTest().log(Status.PASS,"There are 2 products in the cart");

                while (isDisplay(BasketProductTabs) == true){
                    if(isDisplay(BasketIsEmpty) == true){
                        drivers().close();
                    }
                    else {
                        waitForSecond(3);
                        click(DeleteProduct);
                    }
                }
            }
            waitForSecond(2);
            drivers().switchTo().window(orginalWindow);
        }
    }

}
