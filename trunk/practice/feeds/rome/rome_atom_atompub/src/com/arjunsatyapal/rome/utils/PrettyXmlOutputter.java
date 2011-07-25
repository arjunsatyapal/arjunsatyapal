/*
 * Copyright 2011 Google Inc.
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
package com.arjunsatyapal.rome.utils;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * 
 * @author Arjun Satyapal
 */
public class PrettyXmlOutputter {
    public static String prettyXmlOutputDocument(Document doc) {
        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
        return xmlOutputter.outputString(doc);
    }
    
    public static String prettyXmlOutputElement(Element element) {
        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
        return xmlOutputter.outputString(element);
    }

}