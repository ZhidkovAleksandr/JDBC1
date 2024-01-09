package task6;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
public class Task6 {

    private static final String URL = "jdbc:mysql://localhost:3306/Task6";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "skull_pass";

    public static void main(String[] args) throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loading success!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        Statement statement = null;

        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            statement = connection.createStatement();



            statement.addBatch("INSERT INTO ExampleTable(name) VALUES ('Second')");
            statement.addBatch("INSERT INTO ExampleTable(name) VALUES ('Third')");
            statement.executeBatch();

            String up = "UPDATE ExampleTable SET name = 'Java' WHERE id = 1";
            String del = "DELETE FROM ExampleTable WHERE id = 3";

            int fRes = statement.executeUpdate(up);
            int sRes = statement.executeUpdate(del);

            System.out.println("updated rows: " + fRes);
            System.out.println("updated rows: " + sRes);

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            connection.close();
            statement.close();
        }
    }

}
