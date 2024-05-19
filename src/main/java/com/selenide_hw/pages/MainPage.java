package com.selenide_hw.pages;
/**
 * Главная страница
 */

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.impl.CollectionElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;


import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainPage {

    private final ElementsCollection allNameProductCSS = $$(By.cssSelector("p[class = 'sc-124al1g-4 eeXMBo']"));
    private final ElementsCollection addBtnInCartXPATH = $$(By.xpath("//*[text() = 'Add to cart']"));
    private final ElementsCollection filterBtnBySizeXPATH = $$(By.xpath("//span[@class = 'checkmark']"));
    private final SelenideElement titelNumberOfProductsXPATH = $x("//main[@class ='sc-ebmerl-4 iliWeY']//p");
    private final SelenideElement openCartBtnXPATH = $x("//button[@class='sc-1h98xa9-0 gFkyvN']");

    public MainPage(String url){
        Selenide.open(url);
    }

    /**
     * кликает по кнопке добавить в корзину по индексу, из массива
     * @param indexBtn
     * @return
     */
    public CartPage clickAddtoCartBtn(int indexBtn) {
        if (addBtnInCartXPATH.get(indexBtn).shouldBe(clickable, Duration.ofSeconds(10)).exists()) {
            addBtnInCartXPATH.get(indexBtn).click();
        }
        return new CartPage();
    }

    /**
     *Отдает заголовок продукта по индексу, из массива
     * @param index
     * @return
     */
    public String getNameProduct(int index) {
        allNameProductCSS.get(index).shouldBe(visible, Duration.ofSeconds(10));
        return allNameProductCSS.get(index).getText();
    }

    /**
     *Создает массив из заголовков карточек продукта на странице
     * @return
     */
    public List<String> getAllNameProduct() {
        allNameProductCSS.get(0).shouldBe(visible, Duration.ofSeconds(10));
        List<String> allNameProductCSSList = new ArrayList<>();
        allNameProductCSS.forEach(x -> allNameProductCSSList.add(x.getText()));
        return allNameProductCSSList;
    }

    /**
     * Добавляет все продукты на странице в корзину
     * @return
     */
    public CartPage clickAddtoCartBtnAll() {
        addBtnInCartXPATH.get(0).shouldBe(clickable, Duration.ofSeconds(10));
        addBtnInCartXPATH.forEach(x ->
                ((JavascriptExecutor) WebDriverRunner
                        .getWebDriver())
                        .executeScript("arguments[0].click();", x.toWebElement()));
        return new CartPage();
    }

    /**
     * кликает по кнопке филтр, по индексу из массива
     * @param indexBtn
     * @return
     */
    public void clickFilterBtnBySize(int indexBtn) {
        filterBtnBySizeXPATH.get(0).shouldBe(clickable, Duration.ofSeconds(10));
        filterBtnBySizeXPATH.get(indexBtn).click();
    }

    /**
     * Отдает количество продуктов на странице по заголовку
     * @return
     */
    public int countProductByTitile() {
        allNameProductCSS.get(0).shouldBe(clickable, Duration.ofSeconds(10));
        String strTitleAllProduct = titelNumberOfProductsXPATH.getText();
        return Integer.valueOf(strTitleAllProduct.replaceAll("\\D+", ""));
    }

    /**
     * тестовый метод
     * @param indexBtn
     * @return
     */
    public String getTextFilterBtn(int indexBtn) {
        filterBtnBySizeXPATH.get(0).shouldBe(clickable, Duration.ofSeconds(10));
        return filterBtnBySizeXPATH.get(indexBtn).getText();
    }

    /**
     * Кликает по кнопке фильтр, находя её из массива по тексту элемента
     * @param btnName
     * @return
     */
    public void clickFilterBtnByText(String btnName) {
        filterBtnBySizeXPATH.get(0).shouldBe(clickable, Duration.ofSeconds(10));
        for (int i = 0; i < filterBtnBySizeXPATH.size(); i++) {
            if (filterBtnBySizeXPATH.get(i).getText().equals(btnName)) {
                filterBtnBySizeXPATH.get(i).click();
            }
        }
    }


}
