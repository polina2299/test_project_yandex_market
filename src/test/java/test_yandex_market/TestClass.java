package test_yandex_market;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class TestClass extends BeforeAfter {
    @Test
    public void enterTest() throws InterruptedException  {
        driver.get("https://market.yandex.ru");
        //юнопка меню
        WebElement element =driver.findElementByXPath("/html/body/div[1]/div/span/div[2]/noindex/div[2]/div/div/div[1]/div[1]");
//нажатие на кнопку меню
        Actions oAction = new Actions(driver);
        oAction.moveToElement(element);
        oAction.click(element).build().perform();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        element=driver.findElement(By.xpath("//*[text()='Лакомства']"));
        //перевод курсора на "Товары для животных", нажатие на "Лакомства"
        oAction.moveToElement(driver.findElementByXPath("/html/body/div[1]/div/span/div[2]/noindex/div[1]/div/div/div/div/div/div/div[1]/div/div[11]")).moveToElement(element).click().build().perform();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //установка цены мин 50
        driver.findElementByXPath("//*[@id=\"glpricefrom\"]").sendKeys("50");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //установка цены макс 150
        driver.findElementByXPath("//*[@id=\"glpriceto\"]").sendKeys("150");
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        //выбор "с доставкой"
        driver.findElementByXPath("//*[@id=\"search-prepack\"]/div/div/div[3]/div/div/div[2]/div[9]/div/div/fieldset/ul/li[1]/div/label/div/span").click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        //выбор производителя феликс
        driver.findElementByXPath("//*[text()='Felix']").click();
        WebDriverWait wait = new WebDriverWait(driver, 100, 1000);
        wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//*[contains(text(), 'Лакомства для кошек Felix')]")));
//нажатие на первый товар в списке
        driver.findElementByXPath("/html/body/div[1]/div[5]/div[2]/div[1]/div[2]/div/div[1]/div[1]/div[4]/div[1]/div[1]/a").click();
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        //переход на вкладку с первым товаром из списка
        driver.switchTo().window(newTab.get(1));
        String name1;
        //название первого товара
        name1=driver.findElementByXPath("/html/body/div[1]/div[6]/div/div/div/div[1]/div[1]/div/h1").getText();
        System.out.println(name1);
        Thread.sleep(300);
        //добавление товара в сравнение
        wait.until(ExpectedConditions.visibilityOf( driver.findElementByXPath("/html/body/div[1]/div[6]/div/div/div/div[2]/div/div/span[1]")));
        driver.findElementByXPath("/html/body/div[1]/div[6]/div/div/div/div[2]/div/div/span[1]").click();
        //возвращение на предыдущую страницу
        driver.switchTo().window(newTab.get(0));
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
//нажатие на производителя Felix
        driver.findElementByXPath("//*[text()='Felix']").click();
        //нажатие на производителя Мнямс
        driver.findElementByXPath("//*[text()='Мнямс']").click();
        //ожидание загрузки страницы
        wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//*[contains(text(), 'Лакомства для кошек Мнямс')]")));
        //нажатие на второй товар в списке
        driver.findElementByXPath("/html/body/div[1]/div[5]/div[2]/div[1]/div[2]/div/div[1]/div[2]/div[4]/div[1]/div[1]/a").click();
        Thread.sleep(300);
        newTab = new ArrayList<String>(driver.getWindowHandles());
        //переход на вкладку со сторым товаром
        driver.switchTo().window(newTab.get(2));
        //добавление товара в сравнение
        driver.findElementByXPath("/html/body/div[1]/div[6]/div/div/div/div[2]/div/div/span[1]").click();
        String name2;
        //название второго товара
        name2=driver.findElementByCssSelector("body > div.main > div.n-product-summary__header > div > div > div > div.n-product-title__text-container > div.n-product-title__text-container-top > div > h1").getText();
        System.out.println(name2);
        wait.until(ExpectedConditions.visibilityOf(   driver.findElementByXPath("//html/body/div[6]/div/div/div[3]/a")));
        //нажатие на кнопку "сравнить"
        driver.findElementByXPath("/html/body/div[6]/div/div/div[3]/a").click();
        //проверка названия первого товара
        assertTrue(driver.findElementByXPath("/html/body/div[1]/div[5]/div[2]/div[4]/div/div/div/div[2]/a").getText().contains(name1));
        //проверка названия второго товара
        assertTrue(driver.findElementByXPath("/html/body/div[1]/div[5]/div[2]/div[4]/div/div/div/div[1]/a").getText().contains(name2));
        //получение строки с информацией о цене первого товара
        String firstprice=driver.findElementByXPath("/html/body/div[1]/div[5]/div[2]/div[5]/div[2]/div/div[1]/div").getText();
        int n1=firstprice.length();
        //удалените символов "от " и "р"
        firstprice=firstprice.substring(3,n1-1).trim();
        //получение цены первого товара
        int fp=Integer.valueOf(firstprice);
        //получение строки с информацией о цене второго товара
        String secondprice=driver.findElementByXPath("/html/body/div[1]/div[5]/div[2]/div[5]/div[2]/div/div[2]/div").getText();
        int n2=secondprice.length();
        secondprice=secondprice.substring(3,n2-1).trim();
        //цена второго товара
        int sp=Integer.valueOf(secondprice);//цена второго товара
        //проверка, что стоимость товаров не превышает 300
        assertTrue(fp+sp<=300);
        Actions action = new Actions(driver);
        //удаление товара производителя Felix
        action.moveToElement(driver.findElementByXPath("/html/body/div[1]/div[5]/div[2]/div[4]/div/div/div/div[2]/div[2]/span"));
        action.click(driver.findElementByXPath("/html/body/div[1]/div[5]/div[2]/div[4]/div/div/div/div[2]/div[2]/span")).build().perform();
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        //проверка на отсутствие первого добавленного товара
        System.out.println("Тест на удаление товара производителя Felix");
        isDeleted("/html/body/div[1]/div[5]/div[2]/div[4]/div/div/div/div[2]/a");
        System.out.println();
        //удаление товаров из сравнения
        driver.findElementByXPath("/html/body/div[1]/div[5]/div[1]/div[2]/div[2]/span").click();
        Thread.sleep(300);
        //проверка на  отсутсвие элементов
        System.out.println("Тест на удаление товаров из сравнения");
        isDeleted("/html/body/div[1]/div[5]/div[2]/div[4]/div/div/div");


    }
    public static void isDeleted(String locator) {
        try{
            System.out.println(!(driver.findElementByXPath(locator).isDisplayed()));
        }catch (NoSuchElementException e) {
            System.out.println(true);
        }
        catch ( StaleElementReferenceException e) {
            System.out.println(true);
        }
    }

}
