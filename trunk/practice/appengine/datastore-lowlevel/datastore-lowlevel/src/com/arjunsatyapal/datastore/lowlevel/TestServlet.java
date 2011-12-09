package com.arjunsatyapal.datastore.lowlevel;

import com.google.appengine.api.datastore.EntityNotFoundException;

import com.google.appengine.api.datastore.Entity;

import com.google.appengine.api.datastore.Key;

import com.google.appengine.api.datastore.KeyFactory;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class TestServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

        UserEntity userEntity = new UserEntity(user.getUserId(), user.getEmail());
        
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        datastore.put(userEntity.toDatastoreEntity());
        
        
        Key key = KeyFactory.createKey("UserEntity", user.getUserId());
        
        Entity readEntity= null;
        try {
            readEntity = datastore.get(key);
        } catch (EntityNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        UserEntity readUser = UserEntity.fromDatastoreEntity(readEntity);
        
        resp.getWriter().println("done");
    }
}
