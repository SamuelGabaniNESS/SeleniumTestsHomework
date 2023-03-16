package com.academy;

import org.openqa.selenium.*;
import org.openqa.selenium.devtools.v85.input.model.MouseButton;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class SeleniumTest1 {
    WebDriver driver = new EdgeDriver();

    @BeforeTest
    public void setUp() {
        System.setProperty(
                "webdriver.chrome.driver",
                "C:\\webdrivers\\msedgedriver.exe");
        driver.manage().window().maximize();
        driver.get("https://testautomationpractice.blogspot.com/");
    }

    @Test
    public void fillNewWindowsForm()
    {
        // press alert button and accept it
        WebElement alertButton = driver.findElement(By.xpath("//*[@id=\"HTML9\"]/div[1]/button"));
        alertButton.click();
        driver.switchTo().alert().accept();
        driver.switchTo().defaultContent();
        // pick a date
        WebElement datePicker = driver.findElement(By.id("datepicker"));
        datePicker.sendKeys("01/01/1999");
        // choose options in dropboxes
        WebElement dropBoxSpeed = driver.findElement(By.id("speed"));
        Select selectSpeed = new Select(dropBoxSpeed);
        selectSpeed.selectByVisibleText("Medium");

        WebElement dropBoxFile = driver.findElement(By.id("files"));
        Select selectFile = new Select(dropBoxFile);
        selectFile.selectByVisibleText("PDF file");

        WebElement dropBoxNumber = driver.findElement(By.id("number"));
        Select selectNumber = new Select(dropBoxNumber);
        selectNumber.selectByVisibleText("2");

        WebElement dropBoxProduct = driver.findElement(By.id("products"));
        Select selectProduct = new Select(dropBoxProduct);
        selectProduct.selectByVisibleText("Yahoo");

        WebElement dropBoxAnimal = driver.findElement(By.id("animals"));
        Select selectAnimal = new Select(dropBoxAnimal);
        selectAnimal.selectByVisibleText("Avatar");
    }

    @Test(priority = 2)
    public void fillDoubleClickAndDragDropForm()
    {
        // double click copy text button
        WebElement doubleClickButton = driver.findElement(
                By.xpath("//*[@id=\"HTML10\"]/div[1]/button"));
        Actions act = new Actions(driver);
        act.doubleClick(doubleClickButton).perform();
        // field1 and field2 same value?
        String field1 = driver.findElement(By.id("field1")).getText();
        String field2 = driver.findElement(By.id("field2")).getText();
        Assert.assertEquals(field1,field2);
        //System.out.println(field1.equals(field2));
        // drag nad drop
        WebElement draggableObject = driver.findElement(By.id("draggable"));
        WebElement droppableObject = driver.findElement(By.id("droppable"));
        act.dragAndDrop(draggableObject,droppableObject).perform();
    }

    @Test(priority = 2)
    public void fillDragAndDropImagesForm()
    {
        // drag nad drop images
        Actions act = new Actions(driver);
        WebElement draggableImage1 = driver.findElement(By.xpath("//*[@id=\"gallery\"]/li[1]"));
        WebElement draggableImage2 = driver.findElement(By.xpath("//*[@id=\"gallery\"]/li[2]"));
        WebElement trash = driver.findElement(By.xpath("//*[@id=\"trash\"]"));
        act.dragAndDrop(draggableImage1,trash).perform();
        act.dragAndDrop(draggableImage2,trash).perform();


        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"trash\"]/ul")));
        //in trash?
        WebElement trashedImage1 = driver.findElement(By.xpath("//*[@id=\"trash\"]/ul/li[1]"));
        WebElement trashedImage2 = driver.findElement(By.xpath("//*[@id=\"trash\"]/ul/li[2]"));
        WebElement gallery = driver.findElement(By.id("gallery"));
        if(trashedImage1!=null){
            System.out.println("Element Image1 is in trash");
            act.dragAndDrop(trashedImage1,gallery).perform();
        }
        if(trashedImage2!=null){
            System.out.println("Element Image2 is in trash");
            act.dragAndDrop(trashedImage2,gallery).perform();
        }

        //slider
        WebElement slider = driver.findElement(By.id("slider"));
        act.click(slider).perform();

    }

    @Test(priority = 3)
    public void fillTheVolunteerSignUpForm()
    {
        //get to website
        String ActualTitle = driver.getTitle();
        String ExpectedTitle = "Automation Testing Practice";
        System.out.print(ActualTitle);
        Assert.assertEquals(ActualTitle, ExpectedTitle);

        //fill volunteer sign up form
        new WebDriverWait(
                driver, Duration
                .ofSeconds(10))
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
                        By.xpath("//*[@id=\"frame-one1434677811\"]")));
        // fill first name
        WebElement firstName = driver.findElement(By.name("RESULT_TextField-1"));
        firstName.sendKeys("Samuel");
        // fill last name
        WebElement lastName = driver.findElement(By.name("RESULT_TextField-2"));
        lastName.sendKeys("Gabani");
        // fill phone
        WebElement phone = driver.findElement(By.name("RESULT_TextField-3"));
        phone.sendKeys("0123456789");
        // fill country
        WebElement country = driver.findElement(By.name("RESULT_TextField-4"));
        country.sendKeys("Slovakia");
        // fill city
        WebElement city = driver.findElement(By.name("RESULT_TextField-5"));
        city.sendKeys("Kosice");
        // fill email
        WebElement email = driver.findElement(By.name("RESULT_TextField-6"));
        email.sendKeys("samuelgabani@ness.com");
        // click gender
//        WebElement gender = driver.findElement(By.id("RESULT_RadioButton-7_0"));
//        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", gender);
        WebElement gender = driver.findElement(
                By.xpath("/html/body/form/div[2]/div[15]/table/tbody/tr[1]/td/label"));
        gender.click();
        // day of the week
        WebElement dayOfTheWeek = driver.findElement(
                By.xpath("//*[@id=\"q15\"]/table/tbody/tr[7]/td/label"));
        dayOfTheWeek.click();
        // best time to contact
        WebElement bestContactTime = driver.findElement(
                By.xpath("//*[@id=\"RESULT_RadioButton-9\"]/option[4]"));
        bestContactTime.click();
        // upload file
        WebElement uploadFile = driver.findElement(By.id("RESULT_FileUpload-10"));
//        uploadFile.sendKeys(System.getProperty("user.dir")+"\\src\\com\\academy\\ness.png");
        //submit form
        WebElement submit = driver.findElement(By.name("Submit"));
        submit.click();

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
