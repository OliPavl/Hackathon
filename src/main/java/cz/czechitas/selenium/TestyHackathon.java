package cz.czechitas.selenium;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;

public class TestyHackathon {

    public static final String WEB_URL = "http://czechitas-datestovani-hackathon.cz/en/";
    WebDriver prohlizec;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:\\Java-Training\\Selenium\\geckodriver.exe");
        prohlizec = new FirefoxDriver();
        prohlizec.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("Case1")
    public void novyUzivatelMusiBytSchopenZalozitNovyUcet() {
        prohlizec.navigate().to(WEB_URL + "login?back=my-account");

        createAnAccount("abcd@seznam.cz");
        fillInputBox("customer_firstname", "Alibaba");
        fillInputBox("customer_lastname", "Petrovich");
        fillPassword("12345");
        clickOnRegister();
    }

    @Test
    @DisplayName("Case2")
    public void uzivatelSExistujicimUctemSeMusiBytSchopenPrihlasitDoWeboveAplikace() {
        prohlizec.navigate().to(WEB_URL + "login?back=my-account");

        fillEmail("abcd@seznam.cz");
        fillPassword("13245");
        clickOnSignIn();
    }

    @Test
    @DisplayName("Case3")
    public void uzivatelSExistujicimUctemSeMusiBytSchopenPrihlasitAOdhlasit() {
        prohlizec.navigate().to(WEB_URL + "login?back=my-account");

        fillEmail("abcd@seznam.cz");
        fillPassword("12345");
        clickOnSignIn();
        waitForSignIn();
        unfoldDropdown();
        logOut();
    }

    @Test
    @DisplayName("Case4")
    public void uzivatelSExistujicimUctemMusiBytSchopenMenitOsobniUdaje() {
        prohlizec.navigate().to(WEB_URL + "login?back=my-account");

        fillEmail("abcd@seznam.cz");
        fillPassword("12345");
        clickOnSignIn();
        clickOnMyPersonalInformation();
        chooseSocialTitle();
        clearInputBox("firstname");
        fillInputBox("firstname", "Karol");
        clearInputBox("lastname");
        fillInputBox("lastname", "Carevich");
        clearInputBox("email");
        fillEmail("abc@post.cz");
        fillCurrrentPassword("11111");
        fillPassword("AbCd1");
        fillComfirmation("AbCd1");
        signUpForNewsletter();
        receiveOffers();
        clickOnSave();
    }

    public void receiveOffers() {
        WebElement offers = prohlizec.findElement(By.id("optin"));
        offers.click();
    }

    public void clickOnSave() {
        WebElement save = prohlizec.findElement(By.xpath("//*[@class='form-group']//*[text()='Save']"));
        save.click();
    }

    public void fillCurrrentPassword(String hodnota) {
        fillInputBox("old_passwd", hodnota);
    }

    public void fillComfirmation(String hodnota) {
        fillInputBox("confirmation", hodnota);
    }

    public void clickOnRegister() {
        WebElement clickOnRegister = prohlizec.findElement(By.id("submitAccount"));
        clickOnRegister.click();
    }

    public void signUpForNewsletter() {
        WebElement newsletter = prohlizec.findElement(By.id("newsletter"));
        newsletter.click();
    }

    public void createAnAccount(String hodnota) {
        fillInputBox("email_create", hodnota);
        WebElement createAnAccount = prohlizec.findElement(By.id("SubmitCreate"));
        createAnAccount.click();
    }

    public void fillInputBox(String xPath, String hodnota) {
        WebElement vyplnInputBox = prohlizec.findElement(By.id(xPath));
        vyplnInputBox.sendKeys(hodnota);
    }

    public void clearInputBox(String xPath) {
        WebElement vycistInputBox = prohlizec.findElement(By.id(xPath));
        vycistInputBox.clear();
    }


    public void fillEmail(String userName) {
        fillInputBox("email", userName);
    }

    public void fillPassword(String password) {
        fillInputBox("passwd", password);
    }

    public void clickOnSignIn() {
        WebElement klikniSignIn = prohlizec.findElement(By.id("SubmitLogin"));
        klikniSignIn.click();
    }

    public WebElement waitForSignIn() {
        return prohlizec.findElement(By.xpath("//h1[text() = 'My account']"));
    }

    public void logOut() {
        WebElement logOut = prohlizec.findElement(By.xpath("//*[@class='dropdown-menu']//*[text()='Logout']"));
        logOut.click();
    }

    public void unfoldDropdown() {
        WebElement dropdown = prohlizec.findElement(By.id("user_info_acc"));
        dropdown.click();
    }

    public void clickOnMyPersonalInformation() {
        WebElement myPersonalInformation = prohlizec.findElement(By.xpath("//*[@class='myaccount-link-list']//*[text()='My personal information']"));
        myPersonalInformation.click();

    }

    public void chooseSocialTitle() {
        WebElement socialTitle = prohlizec.findElement(By.id("id_gender1"));
        socialTitle.click();
    }

    @AfterEach
    public void tearDown() {
        prohlizec.quit();
    }
}
