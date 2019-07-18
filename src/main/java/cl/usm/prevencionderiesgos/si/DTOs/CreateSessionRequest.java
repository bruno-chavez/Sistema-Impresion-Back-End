package cl.usm.prevencionderiesgos.si.DTOs;

public class CreateSessionRequest {

    private String email;
    private String password;


    public CreateSessionRequest() { }


    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }


}
