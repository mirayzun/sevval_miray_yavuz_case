package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ResuableMethods;

public class C01_HomePage extends ResuableMethods {
    @Test
    public void homeTest(){
        HomePage homePage= new HomePage();

        // home page adresine git
        Driver.getDriver().get(ConfigReader.getProperty("homeURL"));
        waitForPageToLoad(5);
        Assert.assertTrue(isElementVisible(homePage.headerLogo));

        // accept all butonuna tikla
        kontrolTikla(homePage.acceptAllBtn);


        //driver kapatilir
        Driver.quitDriver();
    }
}
