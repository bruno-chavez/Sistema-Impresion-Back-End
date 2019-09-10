package cl.usm.prevencionderiesgos.si.DTOs;

public class StudentPagesDocs {

    private int pages;
    private int docs;

    public StudentPagesDocs(int pages, int docs) {
        this.pages = pages;
        this.docs = docs;
    }


    public int getPages() {
        return pages;
    }

    public int getDocs() {
        return docs;
    }

}
