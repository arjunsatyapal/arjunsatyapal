package com.example.myproject.server;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.example.myproject.client.BlobService;
import com.example.myproject.client.entities.Picture;

@SuppressWarnings("serial")
public class BlobServiceImpl extends RemoteServiceServlet implements
    BlobService {

  //Start a GAE BlobstoreService session and Objectify session
  BlobstoreService blobstoreService = BlobstoreServiceFactory
      .getBlobstoreService();
//  Objectify ofy = ObjectifyService.begin();
//  
//  //Register the Objectify Service for the Picture entity
//  static {
//    ObjectifyService.register(Picture.class);
//  }

  //Generate a Blobstore Upload URL from the GAE BlobstoreService
  @Override
  public String getBlobStoreUploadUrl() {

    //Map the UploadURL to the uploadservice which will be called by
    //submitting the FormPanel
    return blobstoreService
        .createUploadUrl("/blobstoreexample/uploadservice");
  }

  /* (non-Javadoc)
   * @see com.example.myproject.client.BlobService#getPicture(java.lang.String)
   */
  @Override
  public Picture getPicture(String id) {
    // TODO(arjuns): Auto-generated method stub
    return null;
  }

//  //Retrieve the Blob's meta-data from the Datastore using Objectify
//  @Override
//  public Picture getPicture(String id) {
//    
//    long l = Long.parseLong(id);
//    Picture picture = ofy.get(Picture.class, l);
//    return picture;
//  }
//  
//  //Override doGet to serve blobs.  This will be called automatically by the Image Widget
//  //in the client
//  @Override
//  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
//      throws ServletException, IOException {
//
//        BlobKey blobKey = new BlobKey(req.getParameter("blob-key"));
//        blobstoreService.serve(blobKey, resp);
//
//  }
}