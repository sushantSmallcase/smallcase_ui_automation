package com.smallcase;


import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException; 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.awt.Robot;
import java.awt.event.InputEvent;

import org.openqa.selenium.NoSuchElementException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class helper {

    private static final Logger logger = Logger.getLogger(helper.class.getName());
    private static final int MAX_RETRIES = 3;
    private static final int WAIT_TIME_MS = 5000;

    public static void clickButtonUsingRobotClass(String xpath, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        for (int retries = 0; retries < MAX_RETRIES; retries++) {
            try {
                WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
                Robot robot = new Robot();
                int x = button.getLocation().getX() + button.getSize().getWidth() / 2;
                int y = button.getLocation().getY() + button.getSize().getHeight() / 2;
                robot.mouseMove(x, y);
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                return;
            } catch (TimeoutException e) {
                logger.log(Level.SEVERE, "Button not clickable within the time limit.");
            } catch (ElementClickInterceptedException e) {
                logger.log(Level.SEVERE, "Button click was intercepted with XPath: " + xpath);
            } catch (Exception e) {
                logger.log(Level.SEVERE, "An error occurred.");
            }
            waitForRetry();
        }
        logger.log(Level.SEVERE, "Failed to click the button using Robot class after multiple attempts.");
    }

    public static void clickButtonUsingXPath(String xpath, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        for (int retries = 0; retries < MAX_RETRIES; retries++) {
            try {
                WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
                button.click();
                return;
            } catch (TimeoutException e) {
                logger.log(Level.WARNING, "Button not clickable within the time limit. Attempt: " + (retries + 1));
            } catch (ElementClickInterceptedException e) {
                logger.log(Level.WARNING, "Button click was intercepted. Attempt: " + (retries + 1));
            } catch (Exception e) {
                logger.log(Level.SEVERE, "An error occurred.");
                break;
            }
            waitForRetry();
        }
        logger.log(Level.SEVERE, "Failed to click the button after multiple attempts.");
    }

    public static void fillInputUsingXPath(String xpath, String text, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        for (int retries = 0; retries < MAX_RETRIES; retries++) {
            try {
                WebElement input = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
                input.click();
                input.clear();
                input.sendKeys(text);
                return;
            } catch (TimeoutException e) {
                logger.log(Level.WARNING, "Input not clickable within the time limit. Attempt: " + (retries + 1));
            } catch (ElementClickInterceptedException e) {
                logger.log(Level.WARNING, "Input click was intercepted. Attempt: " + (retries + 1));
            } catch (Exception e) {
                logger.log(Level.SEVERE, "An error occurred.");
                break;
            }
            waitForRetry();
        }
        logger.log(Level.SEVERE, "Failed to fill the input field after multiple attempts.");
    }

    public static void clickOnCoordinates(int x, int y, WebDriver driver) {
        try {
            Actions actions = new Actions(driver);
            actions.moveByOffset(x, y).click().perform();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred while clicking on coordinates.");
        }
        waitForRetry();
    }

    public static void clickButtonInsideIframeByXPath(String iframeXpath, String buttonXpath, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        for (int retries = 0; retries < MAX_RETRIES; retries++) {
            try {
                WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(iframeXpath)));
                driver.switchTo().frame(iframe);
                WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(buttonXpath)));
                button.click();
                logger.info("Button clicked inside iframe.");
                driver.switchTo().defaultContent();
                return;
            } catch (ElementClickInterceptedException e) {
                logger.log(Level.WARNING, "Button click was intercepted. Attempt: " + (retries + 1));
            } catch (Exception e) {
                logger.log(Level.SEVERE, "An error occurred.");
                driver.switchTo().defaultContent();
                break;
            }
            waitForRetry();
        }
        logger.log(Level.SEVERE, "Failed to click the button inside iframe after multiple attempts.");
    }

    public static void clickButtonInNestedIframes(
            List<String> iframeXpaths,
            List<String> buttonXpaths,
            WebDriver driver) {

        WebDriverWait wait = new WebDriverWait(driver, 20);

        for (int retries = 0; retries < MAX_RETRIES; retries++) {
            try {
                if (iframeXpaths != null && !iframeXpaths.isEmpty()) {
                    for (String iframeXpath : iframeXpaths) {
                        WebElement iframe = wait
                                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(iframeXpath)));
                        driver.switchTo().frame(iframe);
                    }
                }

                if (buttonXpaths != null && !buttonXpaths.isEmpty()) {
                    for (String buttonXpath : buttonXpaths) {
                        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(buttonXpath)));
                        button.click();
                        logger.info("Button clicked with XPath: " + buttonXpath);
                    }
                }

                driver.switchTo().defaultContent();
                return; 
            } catch (ElementClickInterceptedException e) {
                logger.log(Level.WARNING, "Button click was intercepted. Attempt: " + (retries + 1));
            } catch (Exception e) {
                logger.log(Level.SEVERE, "An error occurred.");
                driver.switchTo().defaultContent();
                break;
            }
            waitForRetry();
        }
        logger.log(Level.SEVERE, "Failed to click the button(s) after multiple attempts.");
    }

    public static void sendSpecificKeys(String[] keys, WebDriver driver, boolean sameTime) {
        try {
            Actions actions = new Actions(driver);
            if (sameTime) {
                actions.sendKeys(keys).perform();
            } else {
                for (String key : keys) {
                    actions.sendKeys(key).perform();
                    waitForRetry();
                }
            }
            wait.waitForTime(1000);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred while sending keys.");
        }
    }

    public static void waitUntilElementIsVisible(String xpath, Integer timeout, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            logger.info("Element is visible.");
        } catch (NoSuchElementException e) {
            logger.log(Level.SEVERE, "Element not found.");
        } catch (TimeoutException e) {
            logger.log(Level.SEVERE, "Element not visible within the time limit.");
        }
    }

    private static void waitForRetry() {
        try {
            Thread.sleep(WAIT_TIME_MS);
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, "Interrupted while waiting before retry.");
        }
    }
}