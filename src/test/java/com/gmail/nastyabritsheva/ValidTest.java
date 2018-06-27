package com.gmail.nastyabritsheva;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class ValidTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "E:/testing/autotesting/chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://sandbox.cardpay.com/MI/cardpayment2.html?orderXml=PE9SREVSIFdBTExFVF9JRD0nODI5OScgT1JERVJfTlVNQkVSPSc0NTgyMTEnIEFNT1VOVD0nMjkxLjg2JyBDVVJSRU5DWT0nRVVSJyAgRU1BSUw9J2N1c3RvbWVyQGV4YW1wbGUuY29tJz4KPEFERFJFU1MgQ09VTlRSWT0nVVNBJyBTVEFURT0nTlknIFpJUD0nMTAwMDEnIENJVFk9J05ZJyBTVFJFRVQ9JzY3NyBTVFJFRVQnIFBIT05FPSc4NzY5OTA5MCcgVFlQRT0nQklMTElORycvPgo8L09SREVSPg==&sha512=998150a2b27484b776a1628bfe7505a9cb430f276dfa35b14315c1c8f03381a90490f6608f0dcff789273e05926cd782e1bb941418a9673f43c47595aa7b8b0d");
    }

    private Select getSelect(WebElement element) {
        return new Select(element);
    }

    @Test // Заполнение и отправка формы валидными данными
    public void Test01 () {
        WebElement inputCardNum = driver.findElement(By.id("input-card-number"));
        inputCardNum.sendKeys("4000000000000002");

        WebElement inputCardHolder = driver.findElement(By.id("input-card-holder"));
        inputCardHolder.sendKeys("Anastasiya");

        WebElement selectCardExpiresMonth = driver.findElement(By.id("card-expires-month"));
        getSelect(selectCardExpiresMonth).selectByValue("12");

        WebElement selectCardExpiresYear = driver.findElement(By.id("card-expires-year"));
        getSelect(selectCardExpiresYear).selectByValue("2020");

        WebElement inputCardCVC = driver.findElement(By.id("input-card-cvc"));
        inputCardCVC.sendKeys("505");
        inputCardCVC.submit();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement button = driver.findElement(By.id("success"));
    }

    @Test // Эмуляция 3D-Secure
    public void Test02 () {
        WebElement button = driver.findElement(By.id("success"));
        button.click();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement div = driver.findElement(By.id("payment-info-actions"));
    }

    @Test // Воврат в на страницу магазина
    public void Test03 () {
        WebElement button = driver.findElement(By.id("formSubmit"));
        button.click();
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}