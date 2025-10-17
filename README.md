What is JDBC?

JDBC (Java Database Connectivity) is an API (Application Programming Interface) that lets Java programs connect to and interact with databases.
You use JDBC to:

Connect to a database (like MySQL)

Execute SQL queries (INSERT, UPDATE, SELECT, DELETE)

Retrieve results

Close connections

1. Steps in a JDBC Program

Here are the 5 main steps to connect Java to MySQL using JDBC:

Step 1: Import the JDBC package

You need:

import java.sql.*;

Step 2: Load the MySQL Driver

This step loads the driver class that allows Java to talk to MySQL.

Class.forName("com.mysql.cj.jdbc.Driver");


The com.mysql.cj.jdbc.Driver class comes from the MySQL Connector/J library (you include it via Maven or manually add mysql-connector-java-x.x.x.jar).

Step 3: Create the Connection

Use the DriverManager class to connect to your database.

Connection con = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/tree?useSSL=false",
    "root",
    "your_password"
);


jdbc:mysql:// → tells Java to use the MySQL protocol

localhost:3306 → MySQL runs locally on port 3306

tree → your database name

useSSL=false → disables SSL (optional but common for local testing)

Step 4: Create and Execute SQL Statements

You can run queries using either:

Statement (for static SQL)

PreparedStatement (for dynamic or parameterized SQL — safer!)

Example using PreparedStatement:

String sql = "INSERT INTO users(name, email, password) VALUES (?, ?, ?)";
PreparedStatement ps = con.prepareStatement(sql);
ps.setString(1, "Swapnil");
ps.setString(2, "swapnil@gmail.com");
ps.setString(3, "12345");
ps.executeUpdate();

Step 5: Close the Connection

Always close everything to prevent memory leaks.

ps.close();
con.close();
Example: Simple JDBC Program
import java.sql.*;

public class DBExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/tree?useSSL=false";
        String user = "root";
        String password = "Swapnil@1706";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);

            String query = "SELECT * FROM users";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println(rs.getString("name") + " - " + rs.getString("email"));
            }

            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
