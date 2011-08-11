import java.io.File;
import java.io.IOException;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Base64;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

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

/**
 *
 * @author Arjun Satyapal
 */
public class MyMain {
    private final static String MODULE_LOCATION = "/home/arjuns/cnxmodules/m0504.modified";

    public static void main(String[] args) throws IOException {

        File cnxml = new File(MODULE_LOCATION + "/index_auto_generated.cnxml");
        String cnxmlAsString = Files.toString(cnxml, Charsets.UTF_8);
        System.out.println("****From test : " + cnxmlAsString);

        String encodedString = get64bitEncodedString(cnxmlAsString);
        String decodedString = decode64BitEncodedString(encodedString);
        System.out.println(decodedString);
    }

    private static String get64bitEncodedString(String originalString) {
        byte[] encodedStringBytes = Base64.encodeBase64(originalString.getBytes());
        String encoded64BitString = DatatypeConverter.printBase64Binary(encodedStringBytes);

        return encoded64BitString;
    }

    private static String decode64BitEncodedString(String encodedString) {
        byte[] encodedBytes = DatatypeConverter.parseBase64Binary(encodedString);
        byte[] decodedBytes = Base64.decodeBase64(encodedBytes);
        return new String(decodedBytes);
    }

}
