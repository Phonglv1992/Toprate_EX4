import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
    public static Connection getConnect() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection
                    ("jdbc:oracle:thin:@localhost:1521:orcl", "QL_TRUNG_TAM", "123456");
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
