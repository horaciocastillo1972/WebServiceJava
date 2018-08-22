/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hc.WS.Rest.departamento;

import com.hc.WS.Rest.dao.OracleDS;
import com.hc.WS.Rest.util.ToJSON;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jettison.json.JSONArray;

/**
 *
 * @author hcastillo
 */
@Path("/datos")
public class Departamento {

    @GET
    @Path("/departamento")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getDepartamentos() throws Exception {

        PreparedStatement query = null;
        Response rb = null;
        String resultado = null;
        Connection conn = null;

        try {
            conn = OracleDS.OracleDSConn().getConnection();
            query = conn.prepareStatement("SELECT * FROM DEPARTMENTS");

            ResultSet rs = query.executeQuery();

            ToJSON converter = new ToJSON();
            JSONArray json = new JSONArray();

            json = converter.toJSONArray(rs);
            query.close(); //cierre de conexion no olvidar en ningun lado 

            resultado = json.toString();
            rb = Response.ok(resultado).build();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return rb;
    }

}
