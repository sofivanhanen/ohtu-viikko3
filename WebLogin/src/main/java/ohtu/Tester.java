package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://localhost:4567");
        
        // Front page
        printViewPurpose("front page");
        System.out.println(driver.getPageSource());
        
        // Login
        printViewPurpose("login");
        
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
        
        System.out.println(driver.getPageSource());

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        element.submit();
        
        System.out.println(driver.getPageSource());
        
        // Logout
        printViewPurpose("logout");
        
        element = driver.findElement(By.linkText("logout"));
        element.click();
        
        System.out.println(driver.getPageSource());
        
        // Failed login (incorrect password)
        printViewPurpose("failed login: incorrect password");
        
        element = driver.findElement(By.linkText("login"));
        element.click();
        
        System.out.println(driver.getPageSource());

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("suklaaonhyvaa");
        element = driver.findElement(By.name("login"));
        element.submit();
        
        System.out.println(driver.getPageSource());
        
        // Failed login (unknown user )
        printViewPurpose("failed login: unknown user");

        element = driver.findElement(By.name("username"));
        element.sendKeys("sofia");
        element = driver.findElement(By.name("password"));
        element.sendKeys("suklaaonhyvaa");
        element = driver.findElement(By.name("login"));
        element.submit();
        
        System.out.println(driver.getPageSource());
        
        // Go back
        printViewPurpose("back to front page");
        
        element = driver.findElement(By.linkText("back to home"));
        element.click();
        
        System.out.println(driver.getPageSource());
        
        driver.quit();
    }
    
    private static void printViewPurpose(String purpose) {
        System.out.println("    ------------");
        System.out.println("    " + purpose.toUpperCase());
        System.out.println("    ------------");
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
