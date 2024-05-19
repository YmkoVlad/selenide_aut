package com.selenide_hw;

import com.selenide_hw.pages.CartPage;
import com.selenide_hw.pages.MainPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class TestCases extends BaseTest{

    private final static String URL = "https://react-shopping-cart-67954.firebaseapp.com/";

    @Test
    public void verifyAddProductInCartByTitle() throws InterruptedException {
        MainPage mainPage = new MainPage(URL);
        String nameProductMainPage = mainPage.getNameProduct(0);
        CartPage cartPage = mainPage.clickAddtoCartBtn(0);
        String nameProductInCart = cartPage.getNameProductInCart(0);
        Assert.assertEquals(nameProductMainPage, nameProductInCart);
    }

    @Test
    public void verifyAddAllProductInCartByTitle() {
        MainPage mainPage = new MainPage(URL);
        List<String> allProductNameByMainPage = mainPage.getAllNameProduct();
        CartPage cartPage = mainPage.clickAddtoCartBtnAll();
        List<String> allProductNameByCartPage = cartPage.getAllNameProductInCart();
        Assert.assertEquals(allProductNameByMainPage, allProductNameByCartPage);
    }

    @Test
    public void verifyAddFiltByCountProduct() throws InterruptedException {
        MainPage mainPage = new MainPage(URL);
        int sizeAllProductByMainPage = mainPage.getAllNameProduct().size();
        mainPage.clickFilterBtnBySize(1);
        Thread.sleep(1000);
        int sizeAllProductByMainPageAfterFilter = mainPage.getAllNameProduct().size();
        Assert.assertTrue(sizeAllProductByMainPage > sizeAllProductByMainPageAfterFilter, "Size filter doesn't workgi");
    }

    @Test
    public void verifyAddFiltByTitel() throws InterruptedException {
        MainPage mainPage = new MainPage(URL);
        int countProductbyTitle = mainPage.countProductByTitile();
        mainPage.clickFilterBtnBySize(1);
        Thread.sleep(2000);
        int countProductbyTitleAfterFilter = mainPage.countProductByTitile();
        Assert.assertTrue(countProductbyTitle > countProductbyTitleAfterFilter, "Size filter doesn't workgi");
    }

    /**
     * Датапровайдер для фильтра по размеру
     * @return
     */
    @DataProvider(name = "filterBySize")
    public Object[][] getData() {
        return new Object[][]{
                {"XS", 1},
                {"S", 2},
                {"M", 1},
                {"ML", 2},
                {"L", 10},
                {"XL", 10},
                {"XXL", 4}
        };
    }

    @Test(dataProvider = "filterBySize")
    public void verifyFilterByClickBtn(String size, int numberOfProducts) throws InterruptedException {
        MainPage mainPage = new MainPage(URL);
        mainPage.clickFilterBtnByText(size);
        Thread.sleep(1000);
        mainPage.countProductByTitile();
        Assert.assertEquals(mainPage.countProductByTitile(), numberOfProducts);

    }
}
