package com.sbnz.kjar;

import org.drools.decisiontable.ExternalSpreadsheetCompiler;
import java.io.InputStream;

public class TemplateLoader {
    public static String compileTemplate(String templatePath, String excelDataPath) {
        InputStream templateStream = TemplateLoader.class.getResourceAsStream(templatePath);
        if (templateStream == null) {
            throw new IllegalStateException("Template input stream error " + templatePath);
        }

        InputStream dataStream = TemplateLoader.class.getResourceAsStream(excelDataPath);
        if (dataStream == null) {
            throw new IllegalStateException("Data input stream error " + excelDataPath);
        }

        ExternalSpreadsheetCompiler compiler = new ExternalSpreadsheetCompiler();

        return compiler.compile(dataStream, templateStream, 2, 1);
    }
}
