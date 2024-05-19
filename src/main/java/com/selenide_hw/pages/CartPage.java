package com.selenide_hw.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CartPage {

    private final ElementsCollection allNameProductInCartCSS = $$(By.cssSelector("p[class = 'sc-11uohgb-2 elbkhN']"));
    private final SelenideElement closeCartBtnCSS = $(By.cssSelector("button[class='sc-1h98xa9-0 gFkyvN']"));

    public String getNameProductInCart(int index) {
        allNameProductInCartCSS.get(index).shouldBe(visible, Duration.ofSeconds(10));
        return allNameProductInCartCSS.get(index).getText();
    }

    public List<String> getAllNameProductInCart() {
        allNameProductInCartCSS.get(0).shouldBe(visible, Duration.ofSeconds(10));
        List<String> allNameProductInCartCSSList = new ArrayList<>();
        allNameProductInCartCSS.forEach(x -> allNameProductInCartCSSList.add(x.getText()));
        return allNameProductInCartCSSList;
    }

}
