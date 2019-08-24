package cl.usm.prevencionderiesgos.si.DTOs;

import java.util.List;

public class Documents {

    private List<DocumentInfo> documents;

    public Documents(List<DocumentInfo> documents) { this.documents = documents; }

    public List<DocumentInfo> getDocuments() { return documents; }
}
