package com.drools.study.drl.engine;

import com.drools.study.drl.domain.GradeDomain;

/**
 * 规则接口
 *
 * Created by Lingfeng on 2016/4/20.
 */
public interface GradeRuleEngine {

    /**
     * 初始化规则引擎
     */
    void initEngine();

    /**
     * 执行规则引擎
     *
     * @param gradeDomain 积分Fact
     */
    void executeRuleEngine(final GradeDomain gradeDomain);

    /**
     * 刷新规则引擎中的规则
     */
    void refreshEngineRule();
}
