package com.regex.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Lingfeng on 2016/3/17.
 */
public class CompileTest {

    public static final Pattern USERNAME_PATTERN = Pattern.compile("^[\\w\\-\\.]+$", Pattern.CASE_INSENSITIVE);
    public static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w\\-\\.]+@([\\w\\-]+\\.)+\\w+$", Pattern.CASE_INSENSITIVE);
    public static final Pattern MOBILE_PATTERN = Pattern.compile("^1\\d{10}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern SUCCESS_PATTERN = Pattern.compile(".*\"success\":\"([^\"]+)\".*", Pattern.CASE_INSENSITIVE);
    public static final Pattern BUSINESS_REGISTRATION_NO_REGEXP = Pattern.compile("^[A-Z0-9]+$");
    public static final Pattern ALL_NUMBER_REGEXP = Pattern.compile("^\\d+$");
    public static final Pattern PHONE_REGEXP = Pattern.compile("^([\\+]+\\d{1,6})?\\s?(\\d{1,6}+\\-)?\\d{6,12}+$");

    public static final Pattern ENGLISH_WITH_BLANK_REGEXP = Pattern.compile("^[a-zA-Z0-9\\.,_\\-\\(\\)/\\\\&'\"\\s]*$");

    public static void main(String[] args) {
        String username = "asd";
        Matcher usernameMatcher = USERNAME_PATTERN.matcher(username);
        System.out.println("username match : " + usernameMatcher.matches());

        String email = "joseph.wlf-123@qq.com";
        Matcher emailMatcher = EMAIL_PATTERN.matcher(email);
        System.out.println("email match : " + emailMatcher.matches());

        String mobile = "12345678901";
        Matcher mobileMatcher = MOBILE_PATTERN.matcher(mobile);
        System.out.println("mobile match : " + mobileMatcher.matches());

        String success = "{\"success\":\"true\",\"msg\":\"hello world\"}";
        Matcher successMatcher = SUCCESS_PATTERN.matcher(success);
        System.out.println("success match : " + successMatcher.matches());
        System.out.println(successMatcher.group(1));

        String businessRegistrationNo = "321213AZ123";
        Matcher businessRegistrationNoMatcher = BUSINESS_REGISTRATION_NO_REGEXP.matcher(businessRegistrationNo);
        System.out.println("business registration no match : " + businessRegistrationNoMatcher.matches());

        String allNumber = "13123121312";
        Matcher allNumberMatcher = ALL_NUMBER_REGEXP.matcher(allNumber);
        System.out.println("all number match : " + allNumberMatcher.matches());

        String phone = "+86 021-15021489117";
        Matcher phoneMatcher = PHONE_REGEXP.matcher(phone);
        System.out.println("phone match : " + phoneMatcher.matches());

        String englishName = "Hong Kong. 1, 2-3_4(5)sa'6'with\"7\"and/with&more\\end";
        Matcher englishNameMatcher = ENGLISH_WITH_BLANK_REGEXP.matcher(englishName);
        System.out.println(englishName + " ---- " + "english name match : " + englishNameMatcher.matches());
    }
}
