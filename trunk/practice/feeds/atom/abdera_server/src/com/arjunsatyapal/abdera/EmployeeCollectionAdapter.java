/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  The ASF licenses this file to You
 * under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.  For additional information regarding
 * copyright in this work, please see the NOTICE file in the top level
 * directory of this distribution.
 */
package com.arjunsatyapal.abdera;

import org.apache.abdera.Abdera;
import org.apache.abdera.factory.Factory;
import org.apache.abdera.i18n.iri.IRI;
import org.apache.abdera.model.Content;
import org.apache.abdera.model.Person;
import org.apache.abdera.protocol.server.RequestContext;
import org.apache.abdera.protocol.server.context.ResponseContextException;
import org.apache.abdera.protocol.server.impl.AbstractEntityCollectionAdapter;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class EmployeeCollectionAdapter extends
        AbstractEntityCollectionAdapter<Employee> {
    private static final String ID_PREFIX = "tag:acme.com,2007:employee:entry:";

    private AtomicInteger nextId = new AtomicInteger(1000);
    private Map<Integer, Employee> employees = new HashMap<Integer, Employee>();
    private Factory factory = new Abdera().getFactory();

    // START SNIPPET: feedmetadata
    /**
     * A unique ID for this feed.
     */
    @Override
    public String getId(RequestContext request) {
        return "tag:acme.com,2007:employee:feed";
    }

    /**
     * The title of our collection.
     */
    @Override
    public String getTitle(RequestContext request) {
        return "Acme Employee Database";
    }

    /**
     * The author of this collection.
     */
    @Override
    public String getAuthor(RequestContext request) {
        return "Acme Industries";
    }

    // END SNIPPET: feedmetadata
    // START SNIPPET: getEntries
    @Override
    public Iterable<Employee> getEntries(RequestContext request) {
        return employees.values();
    }

    // END SNIPPET: getEntries
    // START SNIPPET: getEntry
    @Override
    public Employee getEntry(String resourceName, RequestContext request)
            throws ResponseContextException {
        Integer id = getIdFromResourceName(resourceName);
        return employees.get(id);
    }

    private Integer getIdFromResourceName(String resourceName)
            throws ResponseContextException {
        int idx = resourceName.indexOf("-");
        if (idx == -1) {
            throw new ResponseContextException(404);
        }
        return new Integer(resourceName.substring(0, idx));
    }

    @Override
    public String getName(Employee entry) {
        return entry.getId() + "-" + entry.getName().replaceAll(" ", "_");
    }

    // END SNIPPET: getEntry
    // START SNIPPET: entryMetadata
    @Override
    public String getId(Employee entry) {
        return ID_PREFIX + entry.getId();
    }

    @Override
    public String getTitle(Employee entry) {
        return entry.getName();
    }

    @Override
    public Date getUpdated(Employee entry) {
        return entry.getUpdated();
    }

    @SuppressWarnings("unused")
    @Override
    public List<Person> getAuthors(Employee entry, RequestContext request)
            throws ResponseContextException {
        Person author = request.getAbdera().getFactory().newAuthor();
        author.setName("Acme Industries");
        return Arrays.asList(author);
    }

    @Override
    public Object getContent(Employee entry, RequestContext request) {
        Content content = factory.newContent(Content.Type.TEXT);
        content.setText(entry.getName());
        return content;
    }

    // END SNIPPET: entryMetadata
    // START SNIPPET: methods
    @SuppressWarnings("unused")
    @Override
    public Employee postEntry(String title, IRI id, String summary,
            Date updated, List<Person> authors, Content content,
            RequestContext request) throws ResponseContextException {
        Employee employee = new Employee();
        employee.setName(content.getText().trim());
        employee.setId(nextId.getAndIncrement());
        employees.put(employee.getId(), employee);

        return employee;
    }

    @SuppressWarnings("unused")
    @Override
    public void putEntry(Employee employee, String title, Date updated,
            List<Person> authors, String summary, Content content,
            RequestContext request) throws ResponseContextException {
        employee.setName(content.getText().trim());
    }

    @Override
    public void deleteEntry(String resourceName, RequestContext request)
            throws ResponseContextException {
        Integer id = getIdFromResourceName(resourceName);
        employees.remove(id);
    }
    // END SNIPPET: methods
}