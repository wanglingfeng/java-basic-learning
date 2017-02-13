package com.currency.study;

import java.util.Currency;
import java.util.Locale;

/**
 * Created by lfwang on 2017/1/20.
 */
public class CurrencyTest {

    public static void main(String[] args) {
        Currency chinaCurrency = Currency.getInstance(Locale.CHINA);
        System.out.println("china: " + chinaCurrency.getDisplayName() + " " + chinaCurrency.getSymbol() + " " + chinaCurrency.getCurrencyCode());
    }
}
