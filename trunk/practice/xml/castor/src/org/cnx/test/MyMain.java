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
package org.cnx.test;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.cnx.resourcemapping.Jpg;
import org.cnx.resourcemapping.LocationInformation;
import org.cnx.resourcemapping.LocationInformationChoice;
import org.cnx.resourcemapping.Repository;
import org.cnx.resourcemapping.Resource;
import org.cnx.resourcemapping.Resources;
import org.cnx.resourcemapping.VariantCategory;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.thaiopensource.relaxng.jaxp.XMLSyntaxSchemaFactory;

/**
 *
 * @author Arjun Satyapal
 */
@SuppressWarnings("deprecation")
public class MyMain {
    public static void main(String[] args) throws IOException, MarshalException,
            ValidationException, SAXException {
        Resources resources = new Resources();
        resources.setSourceRepositoryId("cnx-repo");
        resources.setVersion(new BigDecimal(1.0));

        Resource myResource = new Resource();
        myResource.setName("myResource");

        resources.addResource(myResource);

        Jpg jpg = new Jpg();
        jpg.setHeightInPixels("100");
        jpg.setWidgthInPixels("100");

        VariantCategory vCat = new VariantCategory();
        vCat.setJpg(jpg);
        myResource.setVariantCategory(vCat);



        Repository repository = new Repository();
        repository.setOriginUrl("http://cnx-repo.appspot.com");
        repository.setRepositoryId("cnx-repo");
        repository.setResourceId("1234");

        LocationInformationChoice locationInfoChoice = new LocationInformationChoice();
        locationInfoChoice.setRepository(repository);


        LocationInformation locationinformation = new LocationInformation();
        locationinformation.setLocationInformationChoice(locationInfoChoice);
        locationinformation.setUrl("http://www.google.com");
        myResource.setLocationInformation(locationinformation);


        String encoding = "ISO-8859-1";
        StringWriter strWriter = new StringWriter();

        resources.marshal(strWriter);
        String formattedXml = format(strWriter.toString());

        SchemaFactory factory = new XMLSyntaxSchemaFactory();
        Schema schema =
            factory
                .newSchema(new File(
                    "/usr/local/google/cnxclients/ruggles-green/apps/resources/resourcemapping/src/resource-mapping.rng.xml"));

        Validator validator = schema.newValidator();
        validator.validate(new StreamSource(new StringReader(formattedXml)));

        System.out.println(formattedXml);
    }

    @SuppressWarnings("deprecation")
    public static String format(String unformattedXml) {
        try {
            final Document document = parseXmlFile(unformattedXml);

            OutputFormat format = new OutputFormat(document);
            format.setLineWidth(65);
            format.setIndenting(true);
            format.setIndent(2);
            Writer out = new StringWriter();
            XMLSerializer serializer = new XMLSerializer(out, format);
            serializer.serialize(document);

            return out.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static Document parseXmlFile(String in) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(in));
            return db.parse(is);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
