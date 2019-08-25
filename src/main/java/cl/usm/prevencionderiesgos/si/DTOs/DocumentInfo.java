package cl.usm.prevencionderiesgos.si.DTOs;

public class DocumentInfo {

    private String title;
    private int pages;


    public String getTitle() { return title; }

    public int getPages() { return pages; }


    public DocumentInfo(String title, int pages) {
        this.title = title;
        this.pages = pages;
    }
}
