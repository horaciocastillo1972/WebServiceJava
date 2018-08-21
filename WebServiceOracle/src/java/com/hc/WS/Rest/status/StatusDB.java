/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hc.WS.Rest.status;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;


import java.sql.*;
import com.hc.WS.Rest.dao.*;

/**
 * REST Web Service
 *
 * @author hcastillo
 */
@Path("/status")
public class StatusDB {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of StatusDB
     */
    public StatusDB() {
    }

    private static final String api_version = "00.01.00"; // version de la api.
    
    
    @GET
    @Produces(MediaType.TEXT_HTML)
    
    public String returnTitle(){
        return "<h3> Java Web Services (c) Horacio Castillo.</h3>";
    }
    
    @Path("/version")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String returnVersion(){
        return "<h4> Version: </h4>" + api_version;
    }
    
    @Path("/database")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String returnDBStatus() throws Exception {
    
        PreparedStatement query = null;
        String myString = null;
        String resultado = null;
        Connection conn = null;
        
        try {
            conn = OracleDS.OracleDSConn().getConnection();
            
            query = conn.prepareStatement("Select to_char(sysdate,'dd-mm-yyyy hh24:mi:ss') DATETIME from sys.dual");
            ResultSet rs = query.executeQuery();
            
            while (rs.next()) {
                myString = rs.getString("DATETIME");
                
            }
            query.close(); //cierre de conexion no olvidar en ningun lado 
            
            resultado = "<h2> Estado de la Base de Datos: </h2>" +
                        "<h2> DB APOLO Date / Time: " + myString + "</h2>";
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            if(conn != null ) conn.close();
        }
        
        return resultado;
    }
}
