package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class CareersPage {
    public CareersPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath ="//*[@data-cli_action='accept_all']")
    public WebElement acceptAllBtn;

    @FindBy(css = "a[href*='department=qualityassurance']")
    public WebElement allJobBtn;

    @FindBy(css ="div[id^='wrap-close-button']")
    public WebElement closeBtn;

    @FindBy(xpath ="//*[@id='filter-by-location']")
    public WebElement allLocationFltr;

    @FindBy(xpath ="(//*[@class='position-list-item-wrapper bg-light'])[1]")
    public WebElement firstItem;

    @FindBy(xpath = "//*[@class='job-location istanbulturkiye']")
    public WebElement selectOptn;

    @FindBy(xpath ="//*[@name='filter-by-department']")
    public WebElement departmentFltr;

    @FindBy(xpath = "//*[@class='job-team qualityassurance']")
    public WebElement departmentselectOptn;

    @FindBy(xpath = "//*[@class='position-list-item-wrapper bg-light']")
    public List<WebElement> ilanLst;
    ////div[@id='jobs-list']

    @FindBy(xpath = "//a[@class='btn btn-navy rounded pt-2 pr-5 pb-2 pl-5']")
    public WebElement wiewBtn;



}
