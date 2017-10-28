package com.bigroi.shop.web.test;

import java.util.List;
import java.util.Map;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ChaneProductPage {
    private Map<String, String> data;
    private WebDriver driver;
    private int timeout = 15;

    @FindBy(name = "description")
    @CacheLookup
    private WebElement description;

    @FindBy(css = "a[href='?code=41&locale=en']")
    @CacheLookup
    private WebElement en;

    @FindBy(id = "name")
    @CacheLookup
    private WebElement name;

    private final String pageLoadedText = "";

    private final String pageUrl = "/bigroi_shop_web/product/edit?code=41";

    @FindBy(name = "price")
    @CacheLookup
    private WebElement price;

    @FindBy(css = "a.btn.btn-primary")
    @CacheLookup
    private WebElement products;

    @FindBy(name = "quantity")
    @CacheLookup
    private WebElement quantity;

    @FindBy(css = "input.btn.btn-primary")
    @CacheLookup
    private WebElement save;

    @FindBy(css = "button.btn.btn-primary")
    @CacheLookup
    private WebElement shoppingCart0;

    public ChaneProductPage() {
    }

    public ChaneProductPage(WebDriver driver) {
        this();
        this.driver = driver;
    }

    public ChaneProductPage(WebDriver driver, Map<String, String> data) {
        this(driver);
        this.data = data;
    }

    public ChaneProductPage(WebDriver driver, Map<String, String> data, int timeout) {
        this(driver, data);
        this.timeout = timeout;
    }

    /**
     * Click on En Link.
     *
     * @return the ChaneProductPage class instance.
     */
    public ChaneProductPage clickEnLink() {
        en.click();
        return this;
    }

    /**
     * Click on Products Link.
     *
     * @return the ChaneProductPage class instance.
     */
    public ChaneProductPage clickProductsLink() {
        products.click();
        return this;
    }

    /**
     * Click on Save Button.
     *
     * @return the ChaneProductPage class instance.
     */
    public ChaneProductPage clickSaveButton() {
        save.click();
        return this;
    }

    /**
     * Click on Shopping Cart 0 Button.
     *
     * @return the ChaneProductPage class instance.
     */
    public ChaneProductPage clickShoppingCart0Button() {
        shoppingCart0.click();
        return this;
    }

    /**
     * Fill every fields in the page.
     *
     * @return the ChaneProductPage class instance.
     */
    public ChaneProductPage fill() {
        setNameTextField();
        setPriceTextField();
        setDescriptionTextField();
        setQuantityTextField();
        return this;
    }

    /**
     * Fill every fields in the page and submit it to target page.
     *
     * @return the ChaneProductPage class instance.
     */
    public ChaneProductPage fillAndSubmit() {
        fill();
        return submit();
    }

    /**
     * Set default value to Description Text field.
     *
     * @return the ChaneProductPage class instance.
     */
    public ChaneProductPage setDescriptionTextField() {
        return setDescriptionTextField(data.get("DESCRIPTION"));
    }

    /**
     * Set value to Description Text field.
     *
     * @return the ChaneProductPage class instance.
     */
    public ChaneProductPage setDescriptionTextField(String descriptionValue) {
        description.sendKeys(descriptionValue);
        return this;
    }

    /**
     * Set default value to Name Text field.
     *
     * @return the ChaneProductPage class instance.
     */
    public ChaneProductPage setNameTextField() {
        return setNameTextField(data.get("NAME"));
    }
    
    public String getNameTextField() {
        return name.getAttribute("value");
    }

    /**
     * Set value to Name Text field.
     *
     * @return the ChaneProductPage class instance.
     */
    public ChaneProductPage setNameTextField(String nameValue) {
        name.sendKeys(nameValue);
        return this;
    }

    /**
     * Set default value to Price Text field.
     *
     * @return the ChaneProductPage class instance.
     */
    public ChaneProductPage setPriceTextField() {
        return setPriceTextField(data.get("PRICE"));
    }

    /**
     * Set value to Price Text field.
     *
     * @return the ChaneProductPage class instance.
     */
    public ChaneProductPage setPriceTextField(String priceValue) {
        price.sendKeys(priceValue);
        return this;
    }

    /**
     * Set default value to Quantity Text field.
     *
     * @return the ChaneProductPage class instance.
     */
    public ChaneProductPage setQuantityTextField() {
        return setQuantityTextField(data.get("QUANTITY"));
    }

    /**
     * Set value to Quantity Text field.
     *
     * @return the ChaneProductPage class instance.
     */
    public ChaneProductPage setQuantityTextField(String quantityValue) {
        quantity.sendKeys(quantityValue);
        return this;
    }

    /**
     * Submit the form to target page.
     *
     * @return the ChaneProductPage class instance.
     */
    public ChaneProductPage submit() {
        save.submit();
        return this;
    }

    /**
     * Verify that the page loaded completely.
     *
     * @return the ChaneProductPage class instance.
     */
    public ChaneProductPage verifyPageLoaded() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getPageSource().contains(pageLoadedText);
            }
        });
        return this;
    }

    /**
     * Verify that current page URL matches the expected URL.
     *
     * @return the ChaneProductPage class instance.
     */
    public ChaneProductPage verifyPageUrl() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getCurrentUrl().contains(pageUrl);
            }
        });
        return this;
    }
}
