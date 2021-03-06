/*
 * Copyright 2012 Google Inc.
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
package com.arjunsatyapal.jersey;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.json.JSONConfiguration.NaturalBuilder;
import com.sun.jersey.api.json.JSONJAXBContext;
import java.util.List;
import java.util.Set;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;


/**
 * JAXB Context Resolver for 
 * {@link com.google.light.server.constants.http.ContentTypeEnum#APPLICATION_JSON}. This will
 * return {@link JSONJAXBContext} with {@link JSONConfiguration#natural()} notation.
 * 
 * TODO(arjuns): Add test for this class.
 *
 * @author Arjun Satyapal
 */
@Produces(MediaType.APPLICATION_JSON)
@Provider
public final class JAXBJsonContextResolver implements ContextResolver<JAXBContext> {
  private final JAXBContext context;

  @SuppressWarnings("rawtypes")
  private final Set<Class> types;

  @SuppressWarnings("rawtypes")
  public JAXBJsonContextResolver() throws Exception {
    List<Class> list = Lists.newArrayList();
    list.add(Foo.class);
    this.types = Sets.newHashSet(list);
    
    NaturalBuilder configBuilder = JSONConfiguration.natural();
    configBuilder.humanReadableFormatting(true);
    
    Class[] classes = {Foo.class};
    this.context = new JSONJAXBContext(configBuilder.build(), classes);
  }

  @Override
  public JAXBContext getContext(Class<?> objectType) {
    return (types.contains(objectType)) ? context : null;
  }
}
