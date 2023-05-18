import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.locators.RelativeLocator;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

class BackToTheFutureTest {
    private static WebDriver driver;

    public BackToTheFutureTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\ChormeDriver\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    @DisplayName("TestWithNavigate")
    public void testWithTitle() {
        //Given
        String openWeb = "https://www.imdb.com";
        String ExpectedPageTitle = "IMDb: Ratings, Reviews, and Where to Watch the Best Movies & TV Shows";
        //When
        driver.navigate().to(openWeb);
        String ActualPageTitle = driver.getTitle();
        //Then
        Assertions.assertEquals(ExpectedPageTitle, ActualPageTitle);
    }

    @Test
    @DisplayName("SearchTheMovie")
    public void SearchTheMovie() throws InterruptedException {
        //Given
        String openWeb = "https://www.imdb.com";
        String searchedMovieTitle = "Back to the Future";
        //When
        driver.get(openWeb);

        String searchBlockElementName = "suggestion-search";
        WebElement searchBox = driver.findElement(By.id(searchBlockElementName));
        searchBox.sendKeys(searchedMovieTitle);
        searchBox.submit();

        WebElement movieLink = driver.findElement(By.partialLinkText(searchedMovieTitle));
        movieLink.click();

        String descriptionBlockClassName = "sc-5f699a2-3";
        WebElement description = driver.findElement(By.className(descriptionBlockClassName));

        String expectedDescription = "Marty McFly, a 17-year-old high school student, is accidentally sent 30 years into the past in a time-traveling DeLorean invented by his close friend, the maverick scientist Doc Brown.";
        //Then
        Assertions.assertEquals(expectedDescription, description.getText());
    }

    @AfterEach
    public void CleanUp() {
        driver.quit();
    }
}