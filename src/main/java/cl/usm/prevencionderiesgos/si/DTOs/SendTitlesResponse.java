package cl.usm.prevencionderiesgos.si.DTOs;

import java.util.List;

public class SendTitlesResponse {

    private List<String> titles;

    public SendTitlesResponse(List<String> titles) {
        this.titles = titles;
    }

    public List<String> getTitles() {
        return titles;
    }
}
