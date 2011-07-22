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
package com.arjunsatyapal.jerseyabdera.restapi.cnxmodules.impl;

import org.apache.abdera.Abdera;
import org.apache.abdera.factory.Factory;
import org.apache.abdera.i18n.iri.IRI;
import org.apache.abdera.model.Content;
import org.apache.abdera.model.Person;
import org.apache.abdera.protocol.server.RequestContext;
import org.apache.abdera.protocol.server.impl.AbstractEntityCollectionAdapter;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @author Arjun Satyapal
 */
public class CnxModuleCollectionAdapter extends
        AbstractEntityCollectionAdapter<CnxModuleEntity> {
    // TODO(arjuns) : This should be replaced by datastore Ids.
    private static AtomicInteger nextId = new AtomicInteger(1000);
    private static Map<String, CnxModuleEntity> moduleEntityMap= new HashMap<String, CnxModuleEntity>();
    
    private Factory factory = new Abdera().getFactory();
    


    // Title for this feed.
    @Override
    public String getTitle(RequestContext request) {
        return "AtomPub Collection of Modules (http://cnx-repo/module/feed).";
    }

    @Override
    public CnxModuleEntity postEntry(String title, IRI id, String summary,
            Date updated, List<Person> authors, Content content,
            RequestContext request) {
        // TODO(arjuns) : Add builder.
        CnxModuleEntity entity = new CnxModuleEntity();
        entity.setTitle(title);
        entity.setSummary(summary);
        entity.setUpdated(updated);
        entity.setAuthor(authors.get(0));
        // TODO(arjuns) : See if saving text is valid.
        entity.setContent(content.getText());
        String entityId = Long.toString(nextId.incrementAndGet());
        entity.setId(entityId);
        moduleEntityMap.put(entityId, entity);
        
        return entity;
    }

    @Override
    public void deleteEntry(String resourceName, RequestContext request) {
        moduleEntityMap.remove(resourceName);
    }

    @Override
    public Object getContent(CnxModuleEntity entry, RequestContext request) {
        Content content = factory.newContent(Content.Type.XML);
        content.setText(entry.getContent());
        return content;
    }

    @Override
    public Iterable<CnxModuleEntity> getEntries(RequestContext request) {
        return moduleEntityMap.values();
    }

    @Override
    public CnxModuleEntity getEntry(String resourceName, RequestContext request) {
        return moduleEntityMap.get(resourceName);
    }

    @Override
    public String getId(CnxModuleEntity entry) {
        return entry.getId();
    }

    @Override
    public String getName(CnxModuleEntity entry) {
        return entry.getId();
    }

    @Override
    public String getTitle(CnxModuleEntity entry) {
        return entry.getTitle();
    }

    @Override
    public Date getUpdated(CnxModuleEntity entry) {
        return entry.getUpdated();
    }

    @Override
    public void putEntry(CnxModuleEntity entry, String title, Date updated,
            List<Person> authors, String summary, Content content,
            RequestContext request) {
        
        // TODO(arjuns) : This may need to query from maps.
        entry.setTitle(title);
        entry.setSummary(summary);
        entry.setUpdated(updated);
        entry.setAuthor(authors.get(0));
        // TODO(arjuns) : See if saving text is valid.
        entry.setContent(content.getText());
    }

    @Override
    public String getAuthor(RequestContext request) {
        return "CNX Repository";
    }

    // Id for this feed.
    @Override
    public String getId(RequestContext request) {
        return "http://cnx-repo/module/feed";
    }
}
