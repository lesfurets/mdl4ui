package org.ez18n.apt;

import java.io.IOException;

import org.apache.commons.io.IOUtils;

public class TemplateLoader {
    public static final String load(String resource) throws IOException {
        return IOUtils.toString(TemplateLoader.class.getResourceAsStream(resource));
    }
}
