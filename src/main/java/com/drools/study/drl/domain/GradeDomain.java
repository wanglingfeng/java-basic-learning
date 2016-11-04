package com.drools.study.drl.domain;

import lombok.Data;

/**
 * 积分计算对象
 *
 * Created by Lingfeng on 2016/4/20.
 */
@Data
public class GradeDomain {

    // 用户名
    private String userName;
    // 是否当日生日
    private boolean birthDay;
    // 增加积分数目
    private long grade;
    // 当月购物次数
    private int buyCount;
    // 当月退货次数
    private int backCount;
    // 当月购物总额
    private double buyMoney;
    // 当月退货总额
    private double backMoney;
    // 当月信用卡还款次数
    private int billCurrentMonth;

    /**
     * 记录积分发送流水，防止重复发放
     *
     * @param userName 用户名
     * @param type 积分发放类型
     */
    public void recordGradeLog(String userName, String type) {
        System.out.println("增加对"+ userName + "的类型为"+ type + "的积分操作记录。");
    }
}
