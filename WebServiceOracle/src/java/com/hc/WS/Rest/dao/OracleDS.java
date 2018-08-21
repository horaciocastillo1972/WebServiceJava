/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hc.WS.Rest.dao;

/**
 *
 * @author hcastillo
 */
import javax.naming.*;
import javax.sql.*;
/**
 *
 * @author hcastillo
 */
public class OracleDS {
    
    private static DataSource OracleDs = null;
    private static Context context = null;
    
    public static DataSource OracleDSConn() throws Exception {
    
        if( OracleDs != null){
            return OracleDs;
        }
        
        
        try{
            if(context == null){
                context = new InitialContext();
            }
            
             OracleDs = (DataSource) context.lookup("jdbc/OracleDS");
        
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
        return OracleDs;
    }
    
    
}
