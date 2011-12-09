package com.google.arjuns.fts;

import com.google.appengine.api.search.Document;
import com.google.appengine.api.search.Field;
import com.google.appengine.api.search.Index;
import com.google.appengine.api.search.IndexManager;
import com.google.appengine.api.search.IndexManagerFactory;
import com.google.appengine.api.search.IndexSpec;
import com.google.appengine.api.search.SearchRequest;
import com.google.appengine.api.search.SearchResponse;
import com.google.appengine.api.search.SearchResult;
import com.google.appengine.repackaged.com.google.common.collect.Lists;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class FTSServlet extends HttpServlet {
    private static final String CONTENT = "content";
    private static final String DOCUMENT_TEXT = "documentText";
    private static final String SEARCH_STRING = "searchString";
    private static final int SEARCH_LIMIT = 800;

    private static final String INDEX_NAME = "content-index";

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text");
        String documentText = req.getParameter(DOCUMENT_TEXT);
        String searchString = req.getParameter(SEARCH_STRING);

        StringBuilder outputBuilder = new StringBuilder();

        outputBuilder.append("\n\n Deleting old entries from index.");
        deleteExisting();
        
        outputBuilder.append("\n\n").append(DOCUMENT_TEXT).append(" = ").append(documentText);
        outputBuilder.append("\n\n").append(SEARCH_STRING).append(" = ").append(searchString);

        Document searchDocument = toSearchDocument(documentText);
        outputBuilder.append("\n\n").append("Search Document = ").append(searchDocument);
        

        try {
            getIndex().add(searchDocument);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

        List<Document> matched = findDocuments(searchString, SEARCH_LIMIT);
        
        outputBuilder.append("\n\nFound ").append(matched.size()).append(" documents.");
        resp.getWriter().println(outputBuilder.toString());
    }

    private void deleteExisting() {
        List<Document> matched = findDocuments("", SEARCH_LIMIT);
        
        for (Document curr : matched) {
            getIndex().deleteDocument(curr.getId());
        }
    }

    public static Index getIndex() {
        IndexManager indexManager = IndexManagerFactory.newIndexManager();
        Index index = indexManager.getIndex(IndexSpec.newBuilder().setName(INDEX_NAME));
        return index;
    }
    
    public Document toSearchDocument(String documentText) {
        Document.Builder builder = Document.newBuilder();

        builder.setId(Long.toString(System.currentTimeMillis()));
        builder.addField(Field.newBuilder()
                .setName(CONTENT).setText(documentText));
        return builder.build();
    }
    

    public List<Document> findDocuments(String query, int limit) {
        List<Document> matched = Lists.newArrayList();
        try {
            SearchRequest request = SearchRequest.newBuilder()
                    .setQuery(query)
                    .setLimit(limit)
                    .build();
            SearchResponse response = getIndex().search(request);
            for (SearchResult result : response) {
                matched.add(result.getDocument());
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Search request with query " + query + " failed", e);
        }
        return matched;
    }
}
