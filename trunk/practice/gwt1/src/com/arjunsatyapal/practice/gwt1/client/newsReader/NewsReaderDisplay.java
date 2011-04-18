/**
 * 
 */
package com.arjunsatyapal.practice.gwt1.client.newsReader;

import com.arjunsatyapal.practice.gwt1.client.Display;
import com.arjunsatyapal.practice.gwt1.client.SimpleCallback;

/**
 * @author fkereki
 */
public interface NewsReaderDisplay
    extends Display {
  String getTextToSearchFor();

  void setNews(String htmlNews);

  void setOnGetNewsCallback(SimpleCallback<Object> acb);
}
