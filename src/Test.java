import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Test {

    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        

        driver = new ChromeDriver();

        
        verifyPageURL();
        verifyCheckboxSelectable();
        selectDropdownOption();
        verifyTextOnPage();
        checkElementVisibility();
        verifyLinkRedirect();
        handleAlertPopup();
        uploadFile();
        scrollPage();
        verifyPageSourceContainsText();
        inputTextAndVerify();
        clearTextInput();
        buttonClickTest();
        getElementAttribute();
        checkElementEnabled();
        countElements();
        waitForElement();
        navigateBackAndForward();
        getPageTitleTest();
        refreshPageTest();

        driver.quit();
    }

    // 1. Verify Page URL
    static void verifyPageURL() {
        driver.get("https://the-internet.herokuapp.com/");
        String url = driver.getCurrentUrl();
        System.out.println("Current URL: " + url);
        System.out.println(url.equals("https://the-internet.herokuapp.com/") ? "URL Test Passed" : "URL Test Failed");
    }

    // 2. Verify Checkbox is Selectable
    static void verifyCheckboxSelectable() {
        driver.get("https://the-internet.herokuapp.com/checkboxes");
        WebElement checkbox1 = driver.findElement(By.xpath("(//input[@type='checkbox'])[1]"));
        checkbox1.click();
        System.out.println("Checkbox 1 selected? " + checkbox1.isSelected());
        checkbox1.click();
        System.out.println("Checkbox 1 selected after deselect? " + checkbox1.isSelected());
    }

    // 3. Select Option from Dropdown
    static void selectDropdownOption() {
        driver.get("https://the-internet.herokuapp.com/dropdown");
        Select dropdown = new Select(driver.findElement(By.id("dropdown")));
        dropdown.selectByVisibleText("Option 2");
        System.out.println("Dropdown selected: " + dropdown.getFirstSelectedOption().getText());
    }

    // 4. Verify Text on Page
    static void verifyTextOnPage() {
        driver.get("https://the-internet.herokuapp.com/");
        WebElement header = driver.findElement(By.tagName("h1"));
        System.out.println(header.getText().contains("Welcome to the-internet") ? "Text Found" : "Text Not Found");
    }

    // 5. Check Element Visibility
    static void checkElementVisibility() {
        driver.get("https://the-internet.herokuapp.com/");
        WebElement header = driver.findElement(By.tagName("h1"));
        System.out.println("Is element visible? " + header.isDisplayed());
    }

    // 6. Verify Link Redirect
    static void verifyLinkRedirect() {
        driver.get("https://the-internet.herokuapp.com/");
        driver.findElement(By.linkText("A/B Testing")).click();
        System.out.println("Redirected to: " + driver.getCurrentUrl());
    }

    // 7. Handle Alert Popups
    static void handleAlertPopup() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        Thread.sleep(1000);
        Alert alert = driver.switchTo().alert();
        System.out.println("Alert Text: " + alert.getText());
        alert.accept();
    }

    // 8. Upload a File
    static void uploadFile() {
        driver.get("https://the-internet.herokuapp.com/upload");
        WebElement uploadInput = driver.findElement(By.id("file-upload"));
        uploadInput.sendKeys("/path/to/your/file.txt");  // Replace with your file path
        driver.findElement(By.id("file-submit")).click();
        System.out.println("File uploaded");
    }

    // 9. Scroll Page
    static void scrollPage() {
        driver.get("https://the-internet.herokuapp.com/infinite_scroll");
        WebElement footer = driver.findElement(By.tagName("footer"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", footer);
        System.out.println("Scrolled to footer: " + footer.isDisplayed());
    }

    // 10. Verify Page Source Contains Text
    static void verifyPageSourceContainsText() {
        driver.get("https://the-internet.herokuapp.com/");
        String source = driver.getPageSource();
        System.out.println(source.contains("Welcome to the-internet") ? "Page source contains text" : "Text not found");
    }

    // 11. Input Text and Verify
    static void inputTextAndVerify() {
        driver.get("https://the-internet.herokuapp.com/login");
        WebElement userInput = driver.findElement(By.id("username"));
        userInput.sendKeys("tomsmith");
        String val = userInput.getAttribute("value");
        System.out.println(val.equals("tomsmith") ? "Input test passed" : "Input test failed");
    }

    // 12. Clear Text Input
    static void clearTextInput() {
        driver.get("https://the-internet.herokuapp.com/login");
        WebElement userInput = driver.findElement(By.id("username"));
        userInput.sendKeys("text to clear");
        userInput.clear();
        System.out.println(userInput.getAttribute("value").isEmpty() ? "Clear input passed" : "Clear input failed");
    }

    // 13. Button Click Test
    static void buttonClickTest() {
        driver.get("https://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        WebElement flash = driver.findElement(By.id("flash"));
        System.out.println("Login message: " + flash.getText());
    }

    // 14. Get Element Attribute
    static void getElementAttribute() {
        driver.get("https://the-internet.herokuapp.com/");
        WebElement link = driver.findElement(By.linkText("A/B Testing"));
        String href = link.getAttribute("href");
        System.out.println("Link href: " + href);
    }

    // 15. Check if Element is Enabled
    static void checkElementEnabled() {
        driver.get("https://the-internet.herokuapp.com/login");
        WebElement btn = driver.findElement(By.cssSelector("button[type='submit']"));
        System.out.println("Is login button enabled? " + btn.isEnabled());
    }

    // 16. Count Elements
    static void countElements() {
        driver.get("https://the-internet.herokuapp.com/");
        int count = driver.findElements(By.tagName("a")).size();
        System.out.println("Number of links on homepage: " + count);
    }

    // 17. Wait for Element (simple sleep demo)
    static void waitForElement() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        driver.findElement(By.cssSelector("#start button")).click();
        Thread.sleep(6000);  // Ideally use WebDriverWait in real tests
        WebElement finish = driver.findElement(By.id("finish"));
        System.out.println("Element displayed after wait? " + finish.isDisplayed());
    }

    // 18. Navigate Back and Forward
    static void navigateBackAndForward() {
        driver.get("https://the-internet.herokuapp.com/");
        driver.findElement(By.linkText("A/B Testing")).click();
        System.out.println("Current URL: " + driver.getCurrentUrl());
        driver.navigate().back();
        System.out.println("Back to: " + driver.getCurrentUrl());
        driver.navigate().forward();
        System.out.println("Forward to: " + driver.getCurrentUrl());
    }

    // 19. Get Page Title Test
    static void getPageTitleTest() {
        driver.get("https://the-internet.herokuapp.com/");
        System.out.println("Page title: " + driver.getTitle());
    }

    // 20. Refresh Page Test
    static void refreshPageTest() {
        driver.get("https://the-internet.herokuapp.com/");
        driver.navigate().refresh();
        System.out.println("Page refreshed");
    }
}
