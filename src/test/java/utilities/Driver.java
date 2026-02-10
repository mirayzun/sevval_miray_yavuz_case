package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class Driver {
    private Driver(){} //obje olusturulmasini engellemek amaciyla yapilir singletonPattern denir
    static WebDriver driver;
    public static WebDriver getDriver(){
        if(driver==null){
            switch (ConfigReader.getProperty("browser")){
                case "chrome":
                    driver= new ChromeDriver();
                    break;
                case "edge":
                    driver= new EdgeDriver();
                    break;
                case "mozilla":
                    driver= new FirefoxDriver();
                    break;
                case "safari":
                    driver= new SafariDriver();
                    break;
                default:
                    driver= new ChromeDriver();
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        }
        return  driver;
    }

    public static void closeDriver(){
        if(driver!=null){
            driver.close();
            driver=null;
        }
    }
    public static void quitDriver(){
        if(driver!=null){
            driver.quit();
            driver=null;
        }
    }
}
