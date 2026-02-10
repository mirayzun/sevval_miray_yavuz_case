package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CareersPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class C02_CareersPage extends ReusableMethods {

    @Test
    public void careersTest(){
        // careers sayfasina git
        CareersPage careersPage= new CareersPage();
        Driver.getDriver().get(ConfigReader.getProperty("careersURL"));
        waitForPageToLoad(5);

        //*JavascriptExecutor js=(JavascriptExecutor) Driver.getDriver();

        // Accept butonuna tikla
        kontrolTikla(careersPage.acceptAllBtn);

        // all QA jobs butonuna tikla
        kontrolTikla(careersPage.allJobBtn);

        //20 saniye bekler, firstItem 20 saniye icerisinde gozukmez ise exception firlat
        Assert.assertTrue(isElementDisplayed(careersPage.firstItem, 20));

        // all location filter alanina tikla
        kontrolTikla(careersPage.allLocationFltr);

        // Turkiye , Istanbul sec
        kontrolTikla(careersPage.selectOptn);
        kontrolTikla(careersPage.allLocationFltr);

        // Qualtity Assurance kontrol
        String expected ="Quality Assurance";
        verifyElementContainsText(careersPage.departmentselectOptn,expected);

        // ilanları listeye al ve kontrol et
        Assert.assertTrue(verifyJobPositionsDetails(careersPage.ilanLst));

        // wiew buton tikla
        kontrolTikla(careersPage.wiewBtn);

        // yeni sayfaya gec
        switchToPage();

        // url kontrol
        pageURLCheck();

        //driver kapat
        Driver.quitDriver();
    }
}
