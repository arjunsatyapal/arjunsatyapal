/*
 * Copyright (C) Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.lantern.server.utils.xml;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * 
 * @author Arjun Satyapal
 */
public class XmlPrinter {
    public static String prettyPrint(String xml) throws JDOMException, IOException {
        SAXBuilder builder = new SAXBuilder();

        InputStream is = new ByteArrayInputStream(xml.getBytes("UTF-8"));
        Document doc = (Document) builder.build(is);

        XMLOutputter xmlOutput = new XMLOutputter();

        // display nice nice
        Format format =
                Format.getPrettyFormat().setExpandEmptyElements(true).setOmitDeclaration(true);

        xmlOutput.setFormat(format);

        return xmlOutput.outputString(doc);
    }
}
