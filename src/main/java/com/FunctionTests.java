package com;

import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Pattern;

/**
 * @author keleguo
 * @date Created in 2018/7/31
 */
public class FunctionTests {

    public static void main(String[] args) {
        String str = "(com.dfire.tp.client.service.ITradePlatformService.auditOrder|com.dfire.tp.client.service.ITradePlatformService.submitOrder|com.dfire.cashsoa.loginByAppKey|com.dfire.soa.cloudcash.loginByTrainNo|com.dfire.cashsoa.login|com.dfire.soa.cloudcash.ILoginClientService.switchShopByAppKey|com.dfire.cashsoa.switchShop|com.dfire.soa.cloudcash.changeOrder|com.dfire.soa.cloudcash.remindDish|com.dfire.tp.client.service.ITradePlatformService.cancelInstance|com.dfire.tp.client.service.ITradePlatformService.freeInstance|com.dfire.tp.client.service.ITradePlatformService.modifyInstancePrice|com.dfire.tp.client.service.ITradePlatformService.modifyInstanceWeight|com.dfire.soa.cloudcash.sendMessage|com.dfire.soa.cloudcash.sendCustomerPageMessage|com.dfire.tp.client.service.ITradePlatformService.modifyOrder|com.dfire.tp.client.service.ITradePlatformService.cancelOrder|com.dfire.soa.cloudcash.remindOrder|com.dfire.dpush.bindSeat|com.dfire.msstate.saveOrUpdateMenuBalance|com.dfire.msstate.clearMenuBalanceFromCloudCash|com.dfire.msstate.clearMenuBalanceFromCloudCash|com.dfire.tp.client.service.ITradePlatformService.checkoutOrder|com.dfire.soa.cloudcash.audit|com.dfire.tp.client.service.ITradePlatformService.reverseCheckout|com.dfire.tp.client.service.ITradePlatformService.promoteBill|com.dfire.tp.client.service.ITradePlatformService.verifyMcPromotion|com.dfire.soa.cloudcash.retainOrder|com.dfire.tp.client.service.ITradePlatformService.collectPay|com.dfire.soa.cloudcash.collectPay|com.dfire.soa.cloudcash.ICartBaseClientService.clearCart|com.dfire.consumer.clearCart)";
        Pattern pattern = Pattern.compile(str);
        Long t = System.currentTimeMillis();
        String s = "com.dfire.tp.client.service.ITradePlatformService.auditOrder";
        System.out.println(System.currentTimeMillis() - t);
        Function<Pattern, Boolean> transform = it -> it.asPredicate().test(s);
        System.out.println(System.currentTimeMillis() - t);
        Function<Pattern, Boolean> transform2 = it -> it.asPredicate().test(s);

        System.out.println(System.currentTimeMillis() - t);
        boolean inBoolean = Optional.ofNullable(pattern).map(transform).orElse(true);
        System.out.println(inBoolean);
        System.out.println(System.currentTimeMillis() - t);
        Optional.ofNullable(pattern).map(transform).orElse(true);
        System.out.println(System.currentTimeMillis() - t);
    }
}
