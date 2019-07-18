package cl.usm.prevencionderiesgos.si.DTOs;

public class SendFileResponse {

    private byte[] file;

    public SendFileResponse(byte[] file) {
        this.file = file;
    }

    public byte[] getFile() {
        return file;
    }
}
