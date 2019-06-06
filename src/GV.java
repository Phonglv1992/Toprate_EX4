import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class GV {

    int id;

    String account;

    String firstNam;

    String lastName;

    String email;

    Date lastLogin;

    public GV() {
    }

    public GV(int id, String account, String firstNam, String lastName, String email, Date lastLogin) {
        this.id = id;
        this.account = account;
        this.firstNam = firstNam;
        this.lastName = lastName;
        this.email = email;
        this.lastLogin = lastLogin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getFirstNam() {
        return firstNam;
    }

    public void setFirstNam(String firstNam) {
        this.firstNam = firstNam;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Override
    public String toString() {
        return "GV{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", firstNam='" + firstNam + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", lastLogin=" + lastLogin +
                '}';
    }

}
