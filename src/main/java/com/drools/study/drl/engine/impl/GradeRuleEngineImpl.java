package com.drools.study.drl.engine.impl;

import com.drools.study.drl.domain.GradeDomain;
import com.drools.study.drl.engine.GradeRuleEngine;
import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.definition.KnowledgePackage;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 规则接口实现类
 *
 * Created by Lingfeng on 2016/4/20.
 */
public class GradeRuleEngineImpl implements GradeRuleEngine {

    private static KnowledgeBase knowledgeBase;

    @Override
    public void initEngine() {
        // 设置时间格式
        System.setProperty("drools.dateformat", "yyyy-MM-dd HH:mm:ss");
        knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
        knowledgeBase.addKnowledgePackages(getBuilderFromDrlFile().getKnowledgePackages());
    }

    @Override
    public void executeRuleEngine(GradeDomain gradeDomain) {
        if (null == knowledgeBase.getKnowledgePackages() || 0 == knowledgeBase.getKnowledgePackages().size()) {
            return;
        }

        StatefulKnowledgeSession statefulSession = knowledgeBase.newStatefulKnowledgeSession();
        statefulSession.insert(gradeDomain);

        statefulSession.fireAllRules(match -> !match.getRule().getName().contains("_test"));
        statefulSession.dispose();
    }

    @Override
    public void refreshEngineRule() {
        knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
        Collection<KnowledgePackage> kPackages = knowledgeBase.getKnowledgePackages();

        for (KnowledgePackage kPackage : kPackages) {
            knowledgeBase.removeKnowledgePackage(kPackage.getName());
        }

        initEngine();
    }

    private KnowledgeBuilder getBuilderFromDrlFile() {
        // 装载测试脚本文件
        List<Reader> readers = readRuleFromDrlFile();

        KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

        /* for (Reader r : readers) {
            // 通过将文件转为reader添加drl文件
            knowledgeBuilder.add(ResourceFactory.newReaderResource(r), ResourceType.DRL);
        } */
        // 通过classLoader添加resources文件夹下的指定相对路径的drl文件
        knowledgeBuilder.add(ResourceFactory.newClassPathResource(
                "/drl/addpoint.drl", GradeRuleEngineImpl.class), ResourceType.DRL);
        knowledgeBuilder.add(ResourceFactory.newClassPathResource(
                "/drl/subpoint.drl", GradeRuleEngineImpl.class), ResourceType.DRL);

        // 检查脚本是否有问题
        if (knowledgeBuilder.hasErrors()) {
            System.out.println(knowledgeBuilder.getErrors().toString());
            throw new RuntimeException("Unable to compile drl");
        }

        return knowledgeBuilder;
    }


    private List<Reader> readRuleFromDrlFile() {
        List<String> drlFilePath = new ArrayList<>();
        drlFilePath.add("D:/Project/IdeaProjects/study/src/main/resources/drl/addpoint.drl");
        drlFilePath.add("D:/Project/IdeaProjects/study/src/main/resources/drl/subpoint.drl");

        List<Reader> readers = new ArrayList<>();

        try {
            for (String ruleFilePath : drlFilePath) {
                readers.add(new FileReader(new File(ruleFilePath)));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return readers;
    }
}
