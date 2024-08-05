package com.smallcase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import com.smallcase.constants.*;


public class Main {
        public static void main(String[] args) {

            WebDriver driver = null;
            try {
                driver = new ChromeDriver();
                String randomString = randomStringGenerator.generateRandomString(8);
                webpage.getWebPage(Urls.STAGING_URL, driver);
                helper.waitUntilElementIsVisible(Selectors.CONNECT_BUTTON, 20, driver);
                helper.clickButtonUsingXPath(Selectors.CONNECT_BUTTON, driver);
                helper.fillInputUsingXPath(Selectors.LOGIN_FIELD, randomString, driver);
                helper.clickButtonUsingXPath(Selectors.SUBMIT_BUTTON, driver);
                helper.waitUntilElementIsVisible(Selectors.IFRAME, 20, driver);
                helper.clickOnCoordinates(100, 100, driver);
                helper.sendSpecificKeys(Keys.KEY_PRESS, driver, true);
                helper.clickButtonInsideIframeByXPath(Selectors.TOP_IFRAME, Selectors.ZERODHA_BUTTON, driver);
                driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
                helper.fillInputUsingXPath(Selectors.TOKEN_FIELD, Strings.LEPRECHAUN, driver);
                helper.clickButtonUsingXPath(Selectors.LOGIN_BUTTON, driver);
                wait.waitForTime(3000);
                driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
                helper.waitUntilElementIsVisible(Selectors.IFRAME_XPATH_2, 20, driver); 
                helper.clickButtonInNestedIframes(List.of(Selectors.IFRAME_XPATH_2, Selectors.IFRAME_XPATH_1), List.of(Selectors.AUTHORIZE_BUTTON), driver);
                helper.clickButtonUsingXPath(Selectors.OPEN_SMALLPLUG_BUTTON, driver);
                helper.clickButtonInsideIframeByXPath(Selectors.IFRAME_XPATH_3, Selectors.CONTINUE_BUTTON, driver);
                helper.clickButtonInsideIframeByXPath(Selectors.IFRAME_XPATH_3, Selectors.DISCOVER_SMALLCASES_BUTTON, driver);
                helper.clickButtonInsideIframeByXPath(Selectors.IFRAME_XPATH_3, Selectors.FREE_ACCESS_BUTTON, driver);
                helper.clickButtonInsideIframeByXPath(Selectors.IFRAME_XPATH_3, Selectors.ALL_WEATHER_INVESTING_BUTTON, driver);
                helper.clickButtonInsideIframeByXPath(Selectors.IFRAME_XPATH_3, Selectors.INVEST_NOW_BUTTON, driver);
                helper.waitUntilElementIsVisible(Selectors.CONFIRM_ORDER_TYPE_BUTTON, 20, driver);
                helper.clickButtonInNestedIframes(List.of(Selectors.IFRAME_XPATH_2, Selectors.IFRAME_XPATH_1), List.of(Selectors.ONE_TIME_BUTTON, Selectors.CONFIRM_ORDER_TYPE_BUTTON), driver);
                helper.waitUntilElementIsVisible(Selectors.CONFIRM_DETAILS_BUTTON, 20, driver);
                helper.clickButtonInNestedIframes(List.of(Selectors.IFRAME_XPATH_2, Selectors.IFRAME_XPATH_1), List.of(Selectors.CONFIRM_DETAILS_BUTTON), driver);
                helper.waitUntilElementIsVisible(Selectors.PLACE_ORDERS_BUTTON, 20, driver);
                helper.clickButtonInNestedIframes(List.of(Selectors.IFRAME_XPATH_2, Selectors.IFRAME_XPATH_1), List.of(Selectors.PLACE_ORDERS_BUTTON), driver);
                helper.waitUntilElementIsVisible(Selectors.CONFIRM_ORDERS_BUTTON, 20, driver);
                helper.clickButtonInNestedIframes(List.of(Selectors.IFRAME_XPATH_2, Selectors.IFRAME_XPATH_1), List.of(Selectors.CONFIRM_ORDERS_BUTTON), driver);
                helper.waitUntilElementIsVisible(Selectors.VIEW_INVESTMENTS_BUTTON, 20, driver);
                helper.clickButtonInsideIframeByXPath(Selectors.IFRAME_XPATH_3, Selectors.VIEW_INVESTMENTS_BUTTON, driver);
                helper.waitUntilElementIsVisible(Selectors.INVEST_MORE_BUTTON, 20, driver);
                helper.clickButtonInsideIframeByXPath(Selectors.IFRAME_XPATH_3, Selectors.INVEST_MORE_BUTTON, driver);
                helper.clickButtonInNestedIframes(List.of(Selectors.IFRAME_XPATH_2, Selectors.IFRAME_XPATH_1), List.of(Selectors.ONE_TIME_SIP, Selectors.CONFIRM_ORDER_TYPE_BUTTON), driver);
                helper.waitUntilElementIsVisible(Selectors.CONFIRM_DETAILS_BUTTON, 20, driver);
                helper.clickButtonInNestedIframes(List.of(Selectors.IFRAME_XPATH_2, Selectors.IFRAME_XPATH_1), List.of(Selectors.CONFIRM_DETAILS_BUTTON), driver);
                helper.waitUntilElementIsVisible(Selectors.PLACE_ORDERS_BUTTON, 20, driver);
                helper.clickButtonInNestedIframes(List.of(Selectors.IFRAME_XPATH_2, Selectors.IFRAME_XPATH_1), List.of(Selectors.PLACE_ORDERS_BUTTON), driver);
                helper.waitUntilElementIsVisible(Selectors.CONFIRM_ORDERS_BUTTON, 20, driver);
                helper.clickButtonInNestedIframes(List.of(Selectors.IFRAME_XPATH_2, Selectors.IFRAME_XPATH_1), List.of(Selectors.CONFIRM_ORDERS_BUTTON), driver);
                helper.waitUntilElementIsVisible(Selectors.VIEW_INVESTMENTS_BUTTON, 20, driver);
                helper.clickButtonInsideIframeByXPath(Selectors.IFRAME_XPATH_3, Selectors.VIEW_INVESTMENTS_BUTTON, driver);
                System.out.println("Program executed successfully");
                wait.waitForTime(100000);

            } catch (Exception e) {
                System.out.println("Program failed to execute");
                e.printStackTrace();
            } finally {
                if (driver != null) {
                    driver.quit();
                }
            }
        }
    }