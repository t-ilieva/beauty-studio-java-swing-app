import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDBConnection {

    static Connection connection = null;

    public MyDBConnection(){

    }
    public static Connection getConnection() {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:C:\\Users\\Lenovo\\Projects\\BeautyStudioDB\\beautyStudioDB", "sa", "123");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e2) {
            e2.printStackTrace();
        }

        return connection;
    }
}
