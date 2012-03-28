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
package com.arjunsatyapal.guice;

import java.sql.Date;

import com.google.inject.Injector;

import com.google.inject.Guice;

/**
 *
 * 
 * TODO(arjuns): Add test for this class.
 *
 * @author Arjun Satyapal
 */
public class MyMain {
  public static void main(String[] args) {
    Injector injector = Guice.createInjector(new GuiceModule());
    
    PaymentFactory factory = injector.getInstance(PaymentFactory.class);
    Payment impl = factory.create(new Date(System.currentTimeMillis()));
    
    System.out.println(impl.hello());
  }
}
