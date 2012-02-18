package com.google.lantern.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.lantern.shared.ServletPaths;
import com.google.lantern.shared.dto.SearchCriteria;
import com.google.lantern.shared.enums.select.DocumentType;
import com.google.lantern.shared.exceptions.checked.common.GwtServiceException;
import com.google.lantern.shared.objectifyobjects.SearchTags;
import com.google.lantern.shared.objectifyobjects.documents.DocumentEntity;
import com.google.lantern.shared.objectifyobjects.documents.DocumentSearchResultWrapper;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath(ServletPaths.GwtServices.SITES_SERVICE_SERVLET)
public interface DocumentService extends RemoteService {

    public DocumentEntity createDocument(DocumentType documentType, String title, String content,
            SearchTags associatedTags) throws GwtServiceException;

    public DocumentEntity getDocument(Long documentId) throws GwtServiceException;

    public DocumentEntity updateDocument(Long documentId, String content, SearchTags searchTags)
            throws GwtServiceException;

    public DocumentSearchResultWrapper searchDocument(SearchCriteria searchCriteria)
            throws GwtServiceException;
}
