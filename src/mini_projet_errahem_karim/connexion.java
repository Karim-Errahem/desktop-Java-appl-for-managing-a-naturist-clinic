/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mini_projet_errahem_karim;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Karim
 */
public class connexion {

    public Connection con;
        public connexion(){
    try{
   DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/projetjava","root",""); 
       
        
}
    catch(SQLException e1) {        System.out.println("--> SQLException : " + e1) ;      }
       catch(Exception e2) {  System.out.println("--> Exception : " + e2) ;}
}
        Connection ObtenirConnexion(){
        return con;
    }

    Statement prepareStatement(String requete) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}

