package com.sbnz.kjar.factory;

import java.io.InputStream;
import com.sbnz.kjar.KjarApplication;
import com.sbnz.kjar.TemplateLoader;
import lombok.NoArgsConstructor;
import org.kie.api.KieBase;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.io.ResourceType;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;

@NoArgsConstructor
public class ForwardChainingKieBaseFactory {
    private static final String[] STATIC_RULE_FILES = {
            "rules/intermediate-facts.drl",
            "rules/global-status-limits.drl",
            "rules/final-grading.drl"
    };

    public static KieBase createKieBase() {
        KieHelper kieHelper = new KieHelper();

        for (String ruleFile : STATIC_RULE_FILES) {
            InputStream resourceStream = KjarApplication.class.getResourceAsStream("/" + ruleFile);
            if (resourceStream == null) {
                throw new IllegalStateException("Could not load static rule file: " + ruleFile);
            }
            kieHelper.addResource(ResourceFactory.newInputStreamResource(resourceStream), ResourceType.DRL);
        }

        String simpleDrl = TemplateLoader.compileTemplate(
                "/rules/templates/simple-classification-template.drt",
                "/rules/templates/data/simple-classification-template-data.xlsx"
        );
        kieHelper.addContent(simpleDrl, ResourceType.DRL);

        String compositeDrl = TemplateLoader.compileTemplate(
                "/rules/templates/composite-status-template.drt",
                "/rules/templates/data/composite-status-template-data.xlsx"
        );

        kieHelper.addContent(compositeDrl, ResourceType.DRL);

        String finalDrl = TemplateLoader.compileTemplate(
                "/rules/templates/final-grading-template.drt",
                "/rules/templates/data/final-grading-template-data.xlsx"
        );

        kieHelper.addContent(finalDrl, ResourceType.DRL);

        Results results = kieHelper.verify();
        if (results.hasMessages(Message.Level.ERROR)) {
            throw new IllegalStateException("Forward chaining DRL build failed: "
                    + results.getMessages(Message.Level.ERROR));
        }

        return kieHelper.build(EventProcessingOption.STREAM);
    }
}
