package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CareersPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ResuableMethods;

public class C02_CareersPage extends ResuableMethods {

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
        waitForVisibility(careersPage.oneItem,50);

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
