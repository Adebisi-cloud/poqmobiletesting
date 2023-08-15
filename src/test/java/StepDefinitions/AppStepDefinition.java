package StepDefinitions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AppStepDefinition {

    public static AppiumDriver<MobileElement> appiumDriver;


    @Given("^I launch the cottonTraderApp$")
    public void iLaunchTheCottonTraderApp() throws MalformedURLException {

        //Set Desired capabilities for Appium
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability( MobileCapabilityType.UDID, "R58W702C7KA" );
        desiredCapabilities.setCapability( MobileCapabilityType.DEVICE_NAME, "GalaxyA40s" );
        desiredCapabilities.setCapability( MobileCapabilityType.PLATFORM_NAME, "Android" );
        desiredCapabilities.setCapability( MobileCapabilityType.PLATFORM_VERSION, "13.0" );
        desiredCapabilities.setCapability( MobileCapabilityType.NO_RESET, "true" );
        desiredCapabilities.setCapability( "appPackage", "com.cottontradersltd.cottontraders" );
        desiredCapabilities.setCapability( "appActivity", "com.poqstudio.app.platform.presentation.splash.view.SplashActivity" );


        //Initialize Appium driver
        URL url = new URL( "http://127.0.0.1:4723/wd/hub" );
        appiumDriver = new AppiumDriver<>( url, desiredCapabilities );
        appiumDriver.manage().timeouts().implicitlyWait( 80L, TimeUnit.SECONDS );
    }

    @When("^I navigate to the shop bottom navigation$")
    public void iNavigateToTheShopBottomNavigation() {
        MobileElement shopBottom = appiumDriver.findElementByXPath( "//android.widget.FrameLayout[@content-desc=\"SHOP\"]/android.widget.FrameLayout/android.widget.ImageView" );
        shopBottom.click();
    }

    @And("I select Men")
    public void iSelectMen() {
        MobileElement men = appiumDriver.findElementByAccessibilityId( "Men collapsed" );
        men.click();

    }

    @And("I select Clothing")
    public void iSelectClothing() {
        MobileElement clothing = appiumDriver.findElementByAccessibilityId( "Clothing collapsed" );
        clothing.click();
    }

    @And("I select knitwear category")
    public void iSelectKnitwearCategory() throws Exception {
        MobileElement knitwear = appiumDriver.findElementByXPath( "//android.widget.Button[@content-desc=\"Knitwear\"]/android.widget.TextView" );
        knitwear.click();
    }
        @And("I select any other with seven items category")
        public void iSelectAnyOtherWithSevenItemsCategory () {
            MobileElement anyOtherWithSevenItemsCategory = appiumDriver.findElementByAccessibilityId( "Brand collapsed" );
            anyOtherWithSevenItemsCategory.click();
            while (true) {
                boolean knitwear = false;
            }
        }


    @And("I select the seventh item displayed")
    public void iSelectTheSeventhItemDisplayed() {

       MobileElement  seventhItem = appiumDriver.findElementByXPath( "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ScrollView/androidx.recyclerview.widget.RecyclerView/android.widget.Button[1]/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ImageView" );
         seventhItem.click();
    }

    public static AppiumDriver<MobileElement> getAppiumDriver() {
        return appiumDriver;
    }

    @Override
    public String toString() {
        return "AppStepDefinition{}";
    }

    private  void scrollListToSelectItem() {
        //scroll the list to find the desired item
       Dimension dimension = appiumDriver.manage().window().getSize();
        int startY = (int)(dimension.getHeight()* 0.8);
        int endY = (int) (dimension.getHeight()* 0.2);

        boolean itemFound = false;
        int scrollAttempts = 0;
        while(!itemFound && scrollAttempts < 5) {
            try {
                List<MobileElement> items = appiumDriver.findElementsByCustom( "" );
                If(items.size() >= 7 ); {
                    MobileElement desiredItem = items.get( 7 -1 ); // Convert to 0-indexed
                    desiredItem.click();
                    itemFound = true;
                }
                {
                    swipe(startY, endY);
                    scrollAttempts++;
                }
            } catch (NoSuchElementException e) {
                swipe(startY, endY);
                scrollAttempts++;
            }
        }

        if(!itemFound) {
            throw new java.util.NoSuchElementException("Unable to find item at index" + 7 );
        }
    }

    private void If(boolean b) {
    }

    private void swipe(int startY, int endY) {
        appiumDriver.switchTo();
     }

    @And("^I select a color if available$")
    public void iSelectAColorIfAvailable() {
        MobileElement color = appiumDriver.findElementByXPath( "//android.widget.Button[@index=1]" );
        color.click();
    }

    @And("^I add the item to bag$")
    public void iAddTheItemToBag() {

        MobileElement addItemBag = appiumDriver.findElementByXPath( "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.Button" );
        addItemBag.click();

        MobileElement selectASize = appiumDriver.findElementByXPath( "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[2]");
        selectASize.click();


    }


    @And("^I should see a snackBar$")
    public void iVerifyASnackBar() {
        MobileElement snackBar = appiumDriver.findElementById( "com.cottontradersltd.cottontraders:id/action_bag" );
        Assert.assertTrue( snackBar.isDisplayed() );
    }

    @And("^I view the bag$")
    public void iViewTheBag() {
        MobileElement viewBag = appiumDriver.findElementById( "com.cottontradersltd.cottontraders:id/action_bag" );
        viewBag.click();
    }

    @After
    public void afterScenario() {
        appiumDriver.quit();
    }



}







