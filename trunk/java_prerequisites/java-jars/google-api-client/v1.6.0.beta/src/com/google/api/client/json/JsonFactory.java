/*
 * Copyright (c) 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.api.client.json;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

/**
 * Abstract low-level JSON factory.
 *
 * <p>
 * Implementation is thread-safe, and sub-classes must be thread-safe. For maximum efficiency,
 * applications should use a single globally-shared instance of the JSON factory.
 * </p>
 *
 * @since 1.3
 * @author Yaniv Inbar
 */
public abstract class JsonFactory {

  /**
   * Returns a new instance of a low-level JSON parser for the given input stream.
   *
   * @param in input stream
   * @return new instance of a low-level JSON parser
   * @throws IOException if failed
   */
  public abstract JsonParser createJsonParser(InputStream in) throws IOException;

  /**
   * Returns a new instance of a low-level JSON parser for the given string value.
   *
   * @param value string value
   * @return new instance of a low-level JSON parser
   * @throws IOException if failed
   */
  public abstract JsonParser createJsonParser(String value) throws IOException;

  /**
   * Returns a new instance of a low-level JSON parser for the given reader.
   *
   * @param reader reader
   * @return new instance of a low-level JSON parser
   * @throws IOException if failed
   */
  public abstract JsonParser createJsonParser(Reader reader) throws IOException;

  /**
   * Returns a new instance of a low-level JSON serializer for the given output stream and encoding.
   *
   * @param out output stream
   * @param enc encoding
   * @return new instance of a low-level JSON serializer
   * @throws IOException if failed
   */
  public abstract JsonGenerator createJsonGenerator(OutputStream out, JsonEncoding enc)
      throws IOException;

  /**
   * Returns a new instance of a low-level JSON serializer for the given writer.
   *
   * @param writer writer
   * @return new instance of a low-level JSON serializer
   * @throws IOException if failed
   */
  public abstract JsonGenerator createJsonGenerator(Writer writer) throws IOException;

  /**
   * Returns a serialized JSON string representation for the given item using
   * {@link JsonGenerator#serialize(Object)}.
   *
   * @param item data key/value pairs
   * @return serialized JSON string representation
   */
  public final String toString(Object item) {
    return toString(item, false);
  }

  /**
   * Returns a pretty-printed serialized JSON string representation for the given item using
   * {@link JsonGenerator#serialize(Object)} with {@link JsonGenerator#enablePrettyPrint()}.
   *
   * @param item data key/value pairs
   * @return serialized JSON string representation
   * @since 1.6
   */
  public final String toPrettyString(Object item) {
    return toString(item, true);
  }

  /**
   * Returns a serialized JSON string representation for the given item using
   * {@link JsonGenerator#serialize(Object)}.
   *
   * @param item data key/value pairs
   * @return serialized JSON string representation
   */
  private String toString(Object item, boolean pretty) {
    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
    try {
      JsonGenerator generator = createJsonGenerator(byteStream, JsonEncoding.UTF8);
      if (pretty) {
        generator.enablePrettyPrint();
      }
      generator.serialize(item);
      generator.flush();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    try {
      return byteStream.toString("UTF-8");
    } catch (UnsupportedEncodingException exception) {
      // UTF-8 encoding guaranteed to be supported by JVM
      throw new RuntimeException(exception);
    }
  }

  /**
   * Parses a string value as a JSON object, array, or value into a new instance of the given
   * destination class using {@link JsonParser#parse(Class, CustomizeJsonParser)}.
   *
   * @param value JSON string value
   * @param destinationClass destination class that has an accessible default constructor to use to
   *        create a new instance
   * @return new instance of the parsed destination class
   * @since 1.4
   */
  public final <T> T fromString(String value, Class<T> destinationClass) throws IOException {
    return createJsonParser(value).parse(destinationClass, null);
  }
}
