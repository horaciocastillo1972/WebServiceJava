/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hc.WS.Rest.util;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import java.sql.ResultSet;

/**
 *
 * @author hcastillo
 */
public class ToJSON {

    public JSONArray toJSONArray(ResultSet rs) throws Exception {

        JSONArray json = new JSONArray();

        try {
            java.sql.ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next()) {
                int numCol = rsmd.getColumnCount();

                JSONObject obj = new JSONObject();

                for (int i = 1; i < numCol+1; i++) {
                    String colname = rsmd.getColumnName(i);

                    if (rsmd.getColumnType(i) == java.sql.Types.ARRAY) {
                        obj.put(colname, rs.getArray(colname));
                    } else if (rsmd.getColumnType(i) == java.sql.Types.BIGINT) {
                        obj.put(colname, rs.getInt(colname));
                    } else if (rsmd.getColumnType(i) == java.sql.Types.BOOLEAN) {
                        obj.put(colname, rs.getBoolean(colname));
                    } else if (rsmd.getColumnType(i) == java.sql.Types.BLOB) {
                        obj.put(colname, rs.getBlob(colname));
                    } else if (rsmd.getColumnType(i) == java.sql.Types.DOUBLE) {
                        obj.put(colname, rs.getDouble(colname));
                    } else if (rsmd.getColumnType(i) == java.sql.Types.FLOAT) {
                        obj.put(colname, rs.getFloat(colname));
                    } else if (rsmd.getColumnType(i) == java.sql.Types.INTEGER) {
                        obj.put(colname, rs.getInt(colname));
                    } else if (rsmd.getColumnType(i) == java.sql.Types.NVARCHAR) {
                        obj.put(colname, rs.getNString(colname));
                    } else if (rsmd.getColumnType(i) == java.sql.Types.VARCHAR) {
                        obj.put(colname, rs.getString(colname));
                    } else if (rsmd.getColumnType(i) == java.sql.Types.TINYINT) {
                        obj.put(colname, rs.getInt(colname));
                    } else if (rsmd.getColumnType(i) == java.sql.Types.SMALLINT) {
                        obj.put(colname, rs.getInt(colname));
                    } else if (rsmd.getColumnType(i) == java.sql.Types.DATE) {
                        obj.put(colname, rs.getDate(colname));
                    } else if (rsmd.getColumnType(i) == java.sql.Types.TIMESTAMP) {
                        obj.put(colname, rs.getTimestamp(colname));
                    } else if (rsmd.getColumnType(i) == java.sql.Types.NUMERIC) {
                        obj.put(colname, rs.getBigDecimal(colname));
                    } else {
                        obj.put(colname, rs.getObject(colname));
                    }

                }

                json.put(obj);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

}
