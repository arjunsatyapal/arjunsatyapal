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
package com.google.lantern.server.utils.sites;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

import com.google.lantern.server.utils.sites.SitesUriBuilder;

/**
 * Test for {@link SitesUriBuilder}
 * 
 * @author Arjun Satyapal
 */
public class SitesUriBuilderTest {
    @Test
    public void testAppendChild() throws Exception {
        String expected = "hello/world";
        String real = new SitesUriBuilder().appendChild("hello").appendChild("world").build();
        assertEquals(expected, real);
        
        // case 1.
        real = new SitesUriBuilder("hello").appendChild("world").build();
        assertEquals(expected, real);

        real = new SitesUriBuilder("hello/").appendChild("world").build();
        assertEquals(expected, real);

        real = new SitesUriBuilder("hello/").appendChild("/world").build();
        assertEquals(expected, real);

        expected = "alpha/beta/gamma";
        real = new SitesUriBuilder("alpha").appendChild("beta").appendChild("gamma").build();
        assertEquals(expected, real);
        
        expected = "a-b-c";
        real = new SitesUriBuilder("a.......b......c").build();
        assertEquals(expected, real);
        
        expected = "a--b---c";
        real = new SitesUriBuilder("a....-b-....-c").build();
        assertEquals(expected, real);
        
        expected = "this-is-going-to-be-a-very-long-tile-so-that-url-is-truncted-this-has-perio-and---hyphens";
        real = new SitesUriBuilder("This Is Going To Be A Very Long Tile So That Url Is Truncted. This has Perio and - Hyphens.").build();
        assertEquals(expected, real);
    }

    @Test
    public void testAppendQuery() throws Exception {
        String expected = "hello?query=answer";
        String real = new SitesUriBuilder("hello").appendQuery("query=answer").build();
        assertEquals(expected, real);

        real = new SitesUriBuilder("hello?").appendQuery("query=answer").build();
        assertEquals(expected, real);

        real = new SitesUriBuilder("hello?").appendQuery("?query=answer").build();
        assertEquals(expected, real);

        expected = "hello?query1=answer1&query2=answer2";
        real =
                new SitesUriBuilder("hello").appendQuery("query1=answer1").appendQuery("query2=answer2")
                        .build();
        assertEquals(expected, real);

        expected = "hello?query1=answer1&query2=answer2";
        real =
                new SitesUriBuilder("hello").appendQuery("query1=answer1")
                        .appendQuery("&query2=answer2")
                        .build();
        assertEquals(expected, real);
    }

    @Test
    public void testUrlStartWithSlash() throws Exception {
        try {
            new SitesUriBuilder("/hello").build();
            fail();
        } catch (IllegalArgumentException e) {
            // expected.
        }
    }

    @Test
    public void testAppendChildWithQuery() throws Exception {
        String expected = "hello/world?query1=answer1&query2=answer2";
        String real =
                new SitesUriBuilder("hello").appendChild("world").appendQuery("query1=answer1")
                        .appendQuery("query2=answer2").build();
        
        assertEquals(expected, real);
    }
}
