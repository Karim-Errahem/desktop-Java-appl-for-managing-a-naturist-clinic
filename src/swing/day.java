/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import mini_projet_errahem_karim.connexion;

/**
 *
 * @author Karim
 */

public class day extends JComboBox {
    
    
  public void donner (int id , JTextField T1 , JTextField T2 , JTextField T3 )
  {
  if (id != 0) {
         String s = this.getSelectedItem().toString();
     int  selected = Integer.parseInt(s);
        connexion c = new connexion();
        String query = "SELECT breakfast AS breakfast, dinner AS dinner, lunch AS lunch FROM dietplanmeals WHERE dietplan_id  = ? AND day = ?";
        try {
            PreparedStatement pstmt = c.con.prepareStatement(query);
            pstmt.setInt(1, id);
            pstmt.setInt(2, selected);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String brea = rs.getString("breakfast");
                String dinner = rs.getString("dinner");
                String lunch = rs.getString("lunch");
                T1.setText(brea);
                T2.setText(lunch);
                T3.setText(dinner);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 
  
  
  }
}
