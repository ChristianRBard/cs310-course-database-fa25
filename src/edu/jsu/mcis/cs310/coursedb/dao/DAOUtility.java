package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.*;
import com.github.cliftonlabs.json_simple.*;
import java.util.ArrayList;

public class DAOUtility {
    
    public static final int TERMID_FA25 = 1;
    

    public static String getResultSetAsJson(ResultSet rs) {
        JsonArray records = new JsonArray();
        try {
            if (rs != null) {
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();

                while (rs.next()) {
                    JsonObject obj = new JsonObject();
                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = rsmd.getColumnLabel(i);
                        if (columnName == null) columnName = rsmd.getColumnName(i);
                        columnName = columnName.toLowerCase();

                        Object columnValueObj = rs.getObject(i);
                        String value = (columnValueObj == null) ? "" : columnValueObj.toString();

                        obj.put(columnName, value);
                    }
                    records.add(obj);
                }
            }
        }
        catch (Exception e) { e.printStackTrace(); }
        return Jsoner.serialize(records);  // always returns JSON string
    }
}