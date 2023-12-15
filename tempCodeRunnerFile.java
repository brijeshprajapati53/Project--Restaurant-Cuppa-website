import java.lang.*;
import java.io.*;
import java.sql.*;

public class Connectivity {

    public static void main(String[] args) {
        try {
            // Define SQL statements
            String dropTable = "DROP TABLE student";
            String createTable = "CREATE TABLE student (c_id INTEGER, c_name VARCHAR(20))";
            String insert1 = "INSERT INTO student (c_id, c_name) VALUES (1, 'aaa')";
            String insert2 = "INSERT INTO student (c_id, c_name) VALUES (2, 'bbb')";
            String insert3 = "INSERT INTO student (c_id, c_name) VALUES (3, 'ccc')";
            String select = "SELECT * FROM student";
            String update = "UPDATE student SET c_id = 5 WHERE c_name = 'bbb'";
            String delete = "DELETE FROM student WHERE c_id = 5";

            // Load the JDBC driver
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

            // Establish connection
            Connection con = DriverManager.getConnection("jdbc:odbc:test", "hb", "");

            // Create statement
            Statement stmt = con.createStatement();

            // Drop table if it exists
            try {
                stmt.execute(dropTable);
            } catch (SQLException e) {
                // Ignore if the table doesn't exist
            }

            // Create table
            stmt.execute(createTable);
            System.out.println("Table created successfully.");

            // Insert data
            int count1 = stmt.executeUpdate(insert1);
            System.out.println(count1 + " row(s) inserted.");
            int count2 = stmt.executeUpdate(insert2);
            System.out.println(count2 + " row(s) inserted.");
            int count3 = stmt.executeUpdate(insert3);
            System.out.println(count3 + " row(s) inserted.");

            // Display data
            ResultSet rs = stmt.executeQuery(select);
            System.out.println("\nid\tname");
            while (rs.next()) {
                String id = rs.getString("c_id");
                String name = rs.getString("c_name");
                System.out.println(id + "\t" + name);
            }
            rs.close();

            // Update data
            int count4 = stmt.executeUpdate(update);
            System.out.println(count4 + " row(s) updated.");

            // Delete data
            int count5 = stmt.executeUpdate(delete);
            System.out.println(count5 + " row(s) deleted.");

            // Close connection
            con.close();
        } catch (Exception ex) {
            System.out.println("Error occurred: " + ex.getMessage());
        }
    }
}
