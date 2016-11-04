package com.drools.study.drl;

import com.drools.study.drl.domain.GradeDomain;
import com.drools.study.drl.engine.GradeRuleEngine;
import com.drools.study.drl.engine.impl.GradeRuleEngineImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Lingfeng on 2016/4/20.
 */
public class DroolsDrlTest {

    public static void main(String[] args) throws IOException {
        GradeRuleEngine engine = new GradeRuleEngineImpl();

        while (true) {
            InputStream is = System.in;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String input = br.readLine();

            if(null != input && "s".equals(input)){
                System.out.println("初始化规则引擎...");
                engine.initEngine();
                System.out.println("初始化规则引擎结束.");
            }else if("r".equals(input)){
                final GradeDomain pointDomain = new GradeDomain();
                pointDomain.setUserName("hello kity");
                pointDomain.setBackMoney(100d);
                pointDomain.setBuyMoney(500d);
                pointDomain.setBackCount(1);
                pointDomain.setBuyCount(5);
                pointDomain.setBillCurrentMonth(5);
                pointDomain.setBirthDay(true);
                pointDomain.setGrade(0l);

                engine.executeRuleEngine(pointDomain);

                System.out.println("执行完毕BillThisMonth：" + pointDomain.getBillCurrentMonth());
                System.out.println("执行完毕BuyMoney：" + pointDomain.getBuyMoney());
                System.out.println("执行完毕BuyNums：" + pointDomain.getBuyCount());

                System.out.println("执行完毕规则引擎决定发送积分：" + pointDomain.getGrade());
            } else if("e".equals(input)){
                System.out.println("刷新规则文件...");
                engine.refreshEngineRule();
                System.out.println("刷新规则文件结束.");
                return;
            }
        }
    }
}
