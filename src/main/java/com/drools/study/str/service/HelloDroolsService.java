package com.drools.study.str.service;

import com.drools.study.str.domain.Message;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.definition.KnowledgePackage;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;

import java.io.StringReader;
import java.util.Collection;

/**
 * Created by Lingfeng on 2016/4/20.
 */
public class HelloDroolsService {

    private static StatefulKnowledgeSession kSession;

    public static void init() {
        KnowledgeBuilder kBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

        // read second rule from String
        String myRule = "import com.drools.study.str.domain.Message " +
                "rule \"Hello World 2\" " +
                "when " +
                "$message : Message(status == 0) " +
                "then " +
                "System.out.println($message.getMessage()); " +
                "end";

        Resource myResource = ResourceFactory.newReaderResource(new StringReader(myRule));
        kBuilder.add(myResource, ResourceType.DRL);

        // Check the builder for errors
        if (kBuilder.hasErrors()) {
            System.out.println(kBuilder.getErrors().toString());
            throw new RuntimeException("Unable to compile String");
        }

        // get the compiled packages (which are serializable)
        Collection<KnowledgePackage> pkgs = kBuilder.getKnowledgePackages();

        // add the packages to a knowledgebase (deploy the knowledge packages)
        KnowledgeBase kBase = KnowledgeBaseFactory.newKnowledgeBase();
        kBase.addKnowledgePackages(pkgs);

        kSession = kBase.newStatefulKnowledgeSession();
    }

    public static void initMessage() {
        Message msg = new Message();
        msg.setMessage("Hi, I am a message.");
        msg.setStatus(Message.HELLO);


        kSession.insert(msg);
    }

    public static void fireRules() {
        kSession.fireAllRules();
        kSession.dispose();
    }
}
