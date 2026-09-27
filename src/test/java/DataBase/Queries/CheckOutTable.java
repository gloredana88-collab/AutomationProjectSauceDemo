package DataBase.Queries;

import ObjectData.CheckOutObjects;

import java.sql.SQLException;
import java.sql.Statement;

public class CheckOutTable extends CommonTable{

    public CheckOutTable() throws SQLException {
    }
//        public synchronized void insertTableRow(CheckOutObjects data) throws SQLException {
//            Statement statement = dbConnection.getConnection().createStatement();
//            String query = "insert into Users(fullname, email, current_address, permanent_address) " +
//                    "values ('" + data.getUserName() + "'" + "," +
//                    "'" + data.getUserEmail() + "'" + "," +
//                    "'" + data.getCurrentAdress() + "'" + "," +
//                    "'" + data.getPermanentAdress() + "'" + ");";
//            statement.execute(query);
//        }

}
