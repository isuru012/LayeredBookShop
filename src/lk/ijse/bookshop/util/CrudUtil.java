package lk.ijse.bookshop.util;



import lk.ijse.bookshop.db.DBConnection;

import java.sql.PreparedStatement;

import java.sql.SQLException;

public class CrudUtil {
    public static <X>X execute(String sql, Object...args) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DBConnection.getDBConnection().getConnection().prepareStatement(sql);

        for (int i = 0; i < args.length; i++) {
            pstm.setObject((i+1), args[i]);
        }

        if(sql.startsWith("SELECT") || sql.startsWith("select")) {
            return (X) pstm.executeQuery();
        }
        return (X)(Boolean)(pstm.executeUpdate() > 0);
    }
}
