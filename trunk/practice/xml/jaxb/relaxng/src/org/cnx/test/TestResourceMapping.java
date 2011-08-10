/*
 * Copyright (C) 2011 The CNX Authors
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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.cnx.resourcemapping.ContentTypeImage;
import org.cnx.resourcemapping.LocationInformation;
import org.cnx.resourcemapping.ObjectFactory;
import org.cnx.resourcemapping.Repository;
import org.cnx.resourcemapping.Resource;
import org.cnx.resourcemapping.Resources;
import org.cnx.resourcemapping.VariantCategory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.google.common.io.CharStreams;
import com.thaiopensource.relaxng.jaxp.XMLSyntaxSchemaFactory;

/**
 * A test servlet for initial development and experimentation.
 *
 * TODO(tal): delete when not needed anymore..
 *
 * @author Tal Dayan
 */
@SuppressWarnings({ "serial", "deprecation" })
public class TestResourceMapping extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String packageName = "org.cnx.resourcemapping";
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();
        int sdf = 2345;

        try {
            ServletContext context = getServletContext();
            JAXBContext jaxbContext = JAXBContext.newInstance(packageName, this.getClass().getClassLoader());
            ObjectFactory of = new ObjectFactory();

            Resources resources = of.createResources();
            resources.setSourceRepositoryId("cnx-repo");
            resources.setVersion(new BigDecimal(1.0));

            List<Resource> list = resources.getResource();

            Resource resource = of.createResource();
            list.add(resource);

            ContentTypeImage image = new ContentTypeImage();
            image.setHeightInPixels("100");
            image.setWidgthInPixels("100");
            JAXBElement<ContentTypeImage> jpg = of.createJpg(image);

            VariantCategory jpgVariant = new VariantCategory();
            jpgVariant.setJpg(jpg.getValue());

            resource.setName("myResource");
            resource.setVariantCategory(jpgVariant);

            Repository repository = of.createRepository();
            repository.setOriginUrl("http://cnx-repo.appspot.com");
            repository.setRepositoryId("cnx-repo");
            repository.setResourceId("1234");

            LocationInformation locationinformation = of.createLocationInformation();
            locationinformation.setRepository(repository);
            locationinformation.setUrl("http://www.google.com");
            resource.setLocationInformation(locationinformation);

            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            String string = format(asString(jaxbContext, resources));
            System.out.println(string);

            SchemaFactory factory = new XMLSyntaxSchemaFactory();

            InputStream is = context.getResourceAsStream("/static/resource-mapping.rng");
            URL url = context.getResource("/static/resource-mapping.rng");

            String schemaFromFile = CharStreams.toString(new InputStreamReader(is, "UTF-8"));
            Schema schema = factory.newSchema(url);
            // .newSchema(new File(
            // "/usr/local/google/cnxclients/ruggles-green/apps/resources/resourcemapping/src/resource-mapping.rng.xml"));

            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new StringReader(string)));

            out.println(string);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String asString(JAXBContext pContext, Object pObject) throws JAXBException {
        java.io.StringWriter sw = new StringWriter();

        Marshaller marshaller = pContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.marshal(pObject, sw);

        return sw.toString();
    }

    @SuppressWarnings("deprecation")
    public String format(String unformattedXml) {
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

    private Document parseXmlFile(String in) {
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