package DataBase.Queries;

import ObjectData.CheckOutObjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CheckOutTable extends CommonTable{

    public CheckOutTable() throws SQLException {
    }
//        public synchronized void insertTableRow(CheckOutObjects data) throws SQLException {
//            Statement statement = dbConnection.getConnection().createStatement();
//            String query = "insert into UsersCheckOutData(firstname, lastname, zipcode) " +
//                    "values ('" +
//                    data.getEnterFirstName() + "'" + "," + "'" +
//                    data.getEnterLastName() + "'" + "," + "'" +
//                    data.getEnterZipCode() + "'" + ");";
//            statement.execute(query);
//        }

    public synchronized void insertTableRow(CheckOutObjects data) throws SQLException {

        String query = "INSERT INTO UsersCheckOutData (firstname, lastname, zipcode) VALUES (?, ?, ?)";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, data.getEnterFirstName());
            statement.setString(2, data.getEnterLastName());
            statement.setString(3, data.getEnterZipCode());

            int rowsInserted = statement.executeUpdate();

            System.out.println("Rows inserted: " + rowsInserted);
            System.out.println("First Name: " + data.getEnterFirstName());
            System.out.println("Last Name: " + data.getEnterLastName());
            System.out.println("Zip Code: " + data.getEnterZipCode());
        }
    }

}
