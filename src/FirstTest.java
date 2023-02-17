import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.net.URL;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","C:/JavaAppiumAutomation/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }
    @Test
    public void firstTest()
    {
       waitForElementAndClick(
               By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
               "Cannot find Skip button",
               5
       );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );



        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "penis",
                "Penis can not be input",
                5
        );


        waitForElementPresent(
                By.xpath("//*[@resource-id  ='org.wikipedia:id/search_results_list']//*[@text='Penis enlargement']"),
                "Cannot find Penis enlargement",
                15

        );

    }

    @Test

    public void testCancelSearch()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find Skip button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "penis",
                "Cannot find and send penis into search field",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X for close search",
                5
        );

        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X is still on the screen",
                5
        );
    }


    @Test
    public void testCompareArticleTitle()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find Skip button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );


        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "penis",
                "Penis can not be input",
                5
        );


        waitForElementAndClick(
                By.xpath("//*[@resource-id  ='org.wikipedia:id/search_results_list']//*[@text='Penis enlargement']"),
                "Cannot find Penis enlargement",
                15
        );

        WebElement title_element = waitForElementPresent(
                By.xpath("//*[@resource-id  ='pcs-edit-section-title-description']"),
                "Can not find Penis Title",
                15
        );
        String article_title = title_element.getText();

        Assert.assertEquals(
                "We see Not Penis Enlargement!",
                "Technique aimed to increase the size of a human penis",
                article_title
        );
    }

    @Test

    public void testClearTheSearchField() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find Skip button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "penis",
                "Cannot find and send penis into search field",
                5
        );
        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Govno, peredelivay",
                5
        );

        WebElement elementSearch = waitForElementPresent(
                By.id("org.wikipedia:id/search_src_text"),
                "Govno, peredelivay",
                5
        );

        String searchFielText = elementSearch.getText();

        Assert.assertEquals(
                "Nihuya",
                "Search Wikipedia",
                searchFielText
        );

        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X is still on the screen",
                5
        );
    }

    @Test
    public void searchForFeaturedArticle()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find Skip button",
                5
        );

        assertElementHasText(
                By.id("org.wikipedia:id/view_card_header_title"),
                "Where the fuck",
                "Featured article",
                15
        );
    }

    @Test
    public void searchForSeveralArticleAndCancel(){
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find Skip button",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "penis",
                "Cannot find and send penis into search field",
                5
        );

//        Assert.assertTrue(
//                "Count of element < 2",
//                waitForAllElementsPresent(
//                        By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_list']"),
//                        "Cannot find count of  Articles",
//                        15
//                ).size() >1
//        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Udolyai pri mne",
                5
        );

        waitForElementNotPresent(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_list']"),
                "Result of search is still present on the page",
                5
        );
    }

    @Test
    public void testSwipeArticle() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find Skip button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );


        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Isekai",
                "Isekai can not be input",
                5
        );

        waitForElementAndClick(
                By.className("android.widget.ImageButton"),
                "Dont go to main page",
                1
        );

        System.out.println("Ya Bil prav, prepod pidor!!!");


//        waitForElementAndClick(
//                By.xpath("//*[@resource-id  ='org.wikipedia:id/search_results_list']//*[@text='Isekai']"),
//                "Cannot find Isekai",
//                15
//        );
//
//        WebElement title_element = waitForElementPresent(
//                By.xpath("//*[@resource-id  ='pcs-edit-section-title-description']"),
//                "Can not find Isekai Title",
//                15
//        );
        swipeUpToFindElement(
                By.xpath("//*[@text='View article in browser']"),
               "Can not find text in futer",
                20
        );
    }

    @Test
    public void addArticleToReadingList(){
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find Skip button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );


        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Isekai",
                "Isekai can not be input",
                5
        );


        waitForElementAndClick(
                By.xpath("//*[@resource-id  ='org.wikipedia:id/search_results_list']//*[@text='Isekai']"),
                "Cannot find Isekai",
                15
        );

        WebElement title_element = waitForElementPresent(
                By.xpath("//*[@resource-id  ='pcs-edit-section-title-description']"),
                "Can not find Isekai Title",
                15
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/page_save"),
                "Cannot find 'Save' button" ,
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/snackbar_action"),
                "Can not find 'Add to list' button",
                5
        );

        String listName = "Test List";

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                listName,
                "Cannot input name of the new favourite List",
                1
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/secondary_text_input"),
                "Какое то описание!!!",
                "Cannot put discription to a new list",
                1
        );

        waitForElementAndClick(
                By.id("android:id/button1"),
                "Can not click OK button to close new window",
                1
        );


        waitForElementAndClick(
                By.id("org.wikipedia:id/snackbar_action"),
                "Cannot find VIEW LIST",
                3
        );

        swipeElementToLeft(
                By.id("org.wikipedia:id/page_list_item_title"),
                "Cannot find test article in the list"
        );

        waitForElementNotPresent(
                By.id("org.wikipedia:id/page_list_item_title"),
                "Article is on the screen",
                3
        );

    }

    @Test
    public void testAmountOfNotEmptySearch(){
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find Skip button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );

        String search_Line = "Linkin Park discography";

        String search_results_locator = "//*[@resource-id = 'org.wikipedia:id/page_list_item_title']";

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                search_Line,
                search_Line+" can not be input",
                5
        );

        int amount_of_search_results = getamountOfElements(
                By.xpath(search_results_locator)
        );

        System.out.println("Count of articles = "+amount_of_search_results);

        Assert.assertTrue(
                "There is nothing in search list",
                amount_of_search_results > 0
        );
    }


    @Test  //HomeWork
    public void saveTwoArticlesInTheList(){
        String nameOfFirstArticle = "Penis";
        String nameOfSecondArticle = "Penis enlargement";
        String locatorForSearchingNameOfFirstArticle =
                "//*[@resource-id  ='org.wikipedia:id/search_results_list']//*[@text='"+ nameOfFirstArticle+"']";
        String locatorForSearchingNameOfSecondArticle =
                "//*[@resource-id  ='org.wikipedia:id/search_results_list']//*[@text='"+ nameOfSecondArticle+"']";
        String listName = "Test List";
        String descriptionName ="Описание ёпта!!!";
        String locatorForCreatedList = "//*[@text = '"+listName+"']";
        String locatorForTheFirstArticle = "//*[@text='"+ nameOfFirstArticle+"']";
        String locatorForTheSecondtArticle = "//*[@text='"+ nameOfSecondArticle+"']";



        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find Skip button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );


        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                nameOfFirstArticle,
                nameOfFirstArticle + " can not be input",
                5
        );


        waitForElementAndClick(
                By.xpath(locatorForSearchingNameOfFirstArticle),
                "Cannot find " + nameOfFirstArticle,
                15
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/page_save"),
                "Cannot find 'Save' button" ,
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/snackbar_action"),
                "Can not find 'Add to list' button",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                listName,
                "Cannot input name of the new favourite List",
                1
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/secondary_text_input"),
                descriptionName,
                "Cannot write the description to the list",
                1
        );

        waitForElementAndClick(
                By.id("android:id/button1"),
                "Can not click OK button to close new window",
                1
        );

        waitForElementAndClick(
                By.className("android.widget.ImageButton"),
                "Can not find back arrow to go searching list",
                1
        );
        waitForElementAndClick(
                By.xpath(locatorForSearchingNameOfSecondArticle),
                "Cannot find " + nameOfSecondArticle,
                15
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/page_save"),
                "Cannot find 'Save' button" ,
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/snackbar_action"),
                "Can not find 'Add to list' button",
                5
        );
        waitForElementAndClick(
                By.xpath(locatorForCreatedList),
                "Cannot add second artical to "+listName,
                1
        );
        waitForElementAndClick(
                By.className("android.widget.ImageButton"),
                "Can not find back arrow to go searching list",
                1
        );
        waitForElementAndClick(
                By.className("android.widget.ImageButton"),
                "Cannot go to main page by closing searching list",
                3
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/nav_tab_reading_lists"),
                "Cannot find Saved button on the main page",
                1
        );
        waitForElementAndClick(
                By.xpath(locatorForCreatedList),
                "Cant find created "+ listName+ "in the save window",
                3
        );
        waitForAllElementsPresent(
                By.xpath(locatorForTheFirstArticle),
                "Cannot find "+nameOfFirstArticle+" in the saved " + listName,
                1
        );
        waitForAllElementsPresent(
                By.xpath(locatorForTheSecondtArticle),
                "Cannot find "+ nameOfSecondArticle+ "in the saved " + listName,
                1
        );

        swipeElementToLeft(
                By.xpath(locatorForTheSecondtArticle),
                "Cannot delete "+nameOfSecondArticle
        );

        waitForElementNotPresent(
                By.xpath(locatorForTheSecondtArticle),
                nameOfSecondArticle+" is still on the screen",
                3
        );
        waitForElementAndClick(
                By.xpath(locatorForTheFirstArticle),
                "Suka test upadesh udolu",
                1
        );

        WebElement title_element = waitForElementPresent(
                By.className("android.widget.TextView"),
                "Can not aricle name",
                15
        );

        String article_title = title_element.getText();

        Assert.assertEquals(
                "We see Not Penis Enlargement!",
                nameOfFirstArticle,
                article_title
        );
    }




    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent(By by, String error_message)
    {
        return waitForElementPresent(by, error_message, 5);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message +"\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return  element;
    }

    private void assertElementHasText(By by, String error_message, String value, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        Assert.assertEquals(
                "Elements are not equal",
                waitForElementPresent(by, error_message, 5).getText(),
                value
        );
    }

    private List<WebElement> waitForAllElementsPresent(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    protected void swipeUp(int timeOfSwipe){
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action.press(x, start_y).waitAction(timeOfSwipe).moveTo(x, end_y).release().perform();
    }

    protected void swipeUpQuick(){
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes){
        int already_swiped = 0;
        while (driver.findElements(by).size() ==0){
            if (already_swiped > max_swipes){
                waitForAllElementsPresent(by, "Can not find element by swiping. \n"+ error_message,0);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }

    protected void swipeElementToLeft(By by, String error_message){
        WebElement element = waitForElementPresent(by, error_message,10);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y+lower_y) /2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x,middle_y)
                .waitAction(150)
                .moveTo(left_x,middle_y)
                .release()
                .perform();
    }

    private int getamountOfElements(By by){
        List elements = driver.findElements(by);
        return  elements.size();
    }
}

