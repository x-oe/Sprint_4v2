package praktikum;

import org.openqa.selenium.Keys;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OrderPage {
    private final WebDriver driver;

    private final static By inputOrderName = By.xpath(".//input[@placeholder='* Имя']");
    private final static By inputOrderLastName = By.xpath(".//input[@placeholder='* Фамилия']");
    private final static By inputOrderAddress = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final static By inputOrderMetro = By.xpath(".//input[@placeholder='* Станция метро']");
    private final static By inputOrderTel = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    private final static By ORDER_BUTTON_MIDDLE = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    private final static By inputOrderDate = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final static By inputOrderCountDate = By.xpath(".//div[@class='Dropdown-placeholder']");
    private final static By selectOrderCountDate = By.xpath(".//div[@class='Dropdown-menu']/div[2]");
    private final static By selectOrderColour = By.className("Order_Title__3EKne");
    private final static By inputOrderColour = By.id("black");
    private final static By inputComment = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    private final static By ORDER_BUTTON_COMPLETE = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private final static By ORDER_BUTTON_COMPLETE_YES = By.xpath(".//div[@class='Order_Modal__YZ-d3']/div/button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    private final static By FINAL_ORDER_STATUS = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ']");
    private final static By FINAL_ORDER_STATUS_BUTTON = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    public OrderPage(WebDriver driver){
        this.driver = driver;
    }

    public void inputName(String text){
        driver.findElement(inputOrderName).sendKeys(text);
    }
    public void inputLastName(String text){
        driver.findElement(inputOrderLastName).sendKeys(text);
    }
    public void inputOrderAddress(String text){
        driver.findElement(inputOrderAddress).sendKeys(text);
    }
    public void inputOrderMetro(int n){
        driver.findElement(inputOrderMetro).click();
        for(int i = 0; i < n; i++) {
            driver.findElement(inputOrderMetro).sendKeys(Keys.DOWN);
        }
        driver.findElement(inputOrderMetro).sendKeys(Keys.ENTER);
    }
    public void inputOrderTel(String text){
        driver.findElement(inputOrderTel).sendKeys(text);
    }
    public void clickButtonMiddle(){
        driver.findElement(ORDER_BUTTON_MIDDLE).click();
    }

    public void inputFirstOrderPageAndGo(String name, String lastName, String address, String tel){
        inputName(name);
        inputLastName(lastName);
        inputOrderAddress(address);
        inputOrderMetro(1);
        inputOrderTel(tel);
        clickButtonMiddle();
    }

    public void inputOrderDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy"); // готовим нужный форматтер
        String date = LocalDate.now().plusDays(2).format(formatter); // получаем текущую дату, прибавляем к ней 2 дня и форматируем
        driver.findElement(inputOrderDate).sendKeys(date);
        driver.findElement(inputOrderDate).sendKeys(Keys.ENTER);
    }
    public void inputOrderCountDate(){
        driver.findElement(inputOrderCountDate).click();
        driver.findElement(selectOrderCountDate).click();
    }
    public void inputOrderColour(){
        driver.findElement(inputOrderColour).click();
    }

    public void inputComment(String text){
        driver.findElement(inputComment).sendKeys(text);
    }
    public void clickButtonComplete(){
        driver.findElement(ORDER_BUTTON_COMPLETE).click();
    }
    public void clickButtonCompleteYes(){
        driver.findElement(ORDER_BUTTON_COMPLETE_YES).click();
    }
    public void inputSecondOrderPage(String comment){
        inputOrderDate();
        inputOrderCountDate();
        inputOrderColour();
        inputComment(comment);
        clickButtonComplete();
        clickButtonCompleteYes();
    }

    public String checkFinalPage(){
        driver.findElement(FINAL_ORDER_STATUS_BUTTON).isEnabled();
        return driver.findElement(FINAL_ORDER_STATUS).getText();
    }

}
