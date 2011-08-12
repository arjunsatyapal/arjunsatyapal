package com.arjunsatyapal.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;

import javax.servlet.http.*;

import com.google.common.io.CharStreams;

@SuppressWarnings("serial")
public class HttpclientwithappengineServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Enumeration headernames = req.getHeaderNames();

        while(headernames.hasMoreElements()) {
            Object currHeader = headernames.nextElement();
            System.out.println(currHeader.toString() + " : " + req.getHeader(currHeader.toString()));
        }

        InputStream inputStream = req.getInputStream();
        String string = CharStreams.toString( new InputStreamReader( inputStream, "UTF-8" ) );


        resp.setContentType("text/plain");
        resp.getWriter().print("hello\n");
        resp.getWriter().println("length = " + string.length() + " : " + string);
        resp.getWriter().print("world\n");
    }
}
