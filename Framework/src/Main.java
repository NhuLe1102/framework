import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Main {
    public static void main(String[] args) {

        // Mở browser
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate tới page
        driver.get("https://www.booking.com/?chal_t=1776681197179&force_referer=");

        // Đóng popup/cookie nếu có bằng nhiều selector ổn định.
        By[] popupCloseLocators = new By[]{
                By.cssSelector("button[aria-label*='Dismiss' i]"),
        };

        boolean popupClosed = false;
        for (By locator : popupCloseLocators) {
            try {
                WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(locator));
                closeBtn.click();
                popupClosed = true;
                break;
            } catch (Exception ignored) {
                // thử locator tiếp theo
            }
        }


        // Tìm ô search
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("ss")));

        // Nhập keyword
        searchBox.sendKeys("Ho Chi Minh City");

        // Submit
        driver.findElement(By.xpath("//button[.='Search']")).click();
        System.out.println("Search done");
        driver.quit();

        // Đóng browser
        driver.quit();
    }
}
