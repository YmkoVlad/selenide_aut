package com.selenide_hw;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.selenide_hw.enums.Capability;
import com.selenide_hw.listeners.LocalListener;
import com.selenide_hw.utils.PropertyReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.support.decorators.WebDriverDecorator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import static com.codeborne.selenide.Selenide.open;

public class BaseTest {

   public void setUp(){
       WebDriverManager.chromedriver().setup();
       Configuration.browser = PropertyReader.getConfigPropery(Capability.BROWSER);
       Configuration.browserSize = "1920x1080";
       Configuration.headless = false;
       SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
               .screenshots(true)
               .savePageSource(true)
       );
       SelenideLogger.addListener("locallistener", new LocalListener());
   }

   @BeforeMethod
    public void init(){
       setUp();
   }

   @AfterMethod
    public void tearDown(){
       Selenide.closeWebDriver();
   }
}
