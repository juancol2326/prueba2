package com.saucedemo.playwright.page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.List;
import java.util.stream.Collectors;
public class ProductsPage {
    private final Page page;


    private final String shoppingCartIcon ="#shopping_cart_container";
    private final String sortDropDown =".product_sort_container";
    private final String priceLabels =".inventory_item_price";

    private final String backpackAddToCart = "#add-to-cart-sauce-labs-backpack";
    private final String backpackRemove ="#remove-sauce-labs-backpack";

    private final String burgerMenuButton = "#react-burger-menu-btn";
    private final String logoutLink = "#logout_sidebar_link";

    public ProductsPage(Page page){
        this.page = page;
    }

    public void addBAckPackToCart() {
        page.click(backpackAddToCart);
    }

    public Locator getBackpackRemoveButton(){
        return  page.locator(backpackRemove);
    }

    public CartPage goToCart (){
        page.click(shoppingCartIcon);
        return  new CartPage(page);
    }

    public void sortBy(String option){
        page.selectOption(sortDropDown, option);
    }

    public List<Double> getAllProductPrices() {
        List<String> pricesTexts = page.locator(priceLabels).allInnerTexts();
        return pricesTexts.stream()
                .map(text -> Double.parseDouble(text.replace("$", "")))
                .collect(Collectors.toList());
    }

    public void logout(){
        page.click(burgerMenuButton);
        page.click(logoutLink);
    }
}
