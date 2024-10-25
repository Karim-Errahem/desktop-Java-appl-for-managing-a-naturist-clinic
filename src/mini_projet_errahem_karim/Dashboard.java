/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mini_projet_errahem_karim;

import event.EventMenuSelected;
import form.Ajoute_repas;
import form.Form1;
import form.Form2;
import form.Form3;
import form.Form_Home;
import form.Mon_compte;
import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;


/**
 *
 * @author Karim
 */
public class Dashboard extends javax.swing.JFrame {

    /**
     * Creates new form Dashboard
     */
    private Ajoute_repas repas ;
     private Form_Home home;
    private Form1 form1;
    private Form2 form2;
    private Form3 form3;
    private boolean user;
     private static int userID;
     private Mon_compte compte;
    public Dashboard(int userID) {
        /** 
        connexion co = new connexion();
        
         try {
        PreparedStatement pstmt2 = co.con.prepareStatement("SELECT * FROM users WHERE id=?");
            pstmt2.setInt(1,userID);
            ResultSet res2 = pstmt2.executeQuery();
            if (res2.next()) {
                user = true;
            } } catch (SQLException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
    }**/
      
        
        initComponents();
        this.userID = userID;
        utilisateur.setVisible(false);
        labeluser();
         setBackground(new Color(0, 0, 0, 0));
        home = new Form_Home();
        form1 = new Form1();
        form2 = new Form2(userID);
        form3 = new Form3();
        repas =new Ajoute_repas();
          compte = new Mon_compte(userID);
        menu.initMoving(Dashboard.this);
        menu.addEventMenuSelected(new EventMenuSelected() {
            @Override
            public void selected(int index) {
                if (index == 0) {
                    setForm(home);
                } else if (index == 1) {
                    setForm(form1);
                } else if (index == 6) {
                    if ("personnel".equals(typeuser())){
                  interdi();
                    setForm(home);
                    
                    }else if ("nutritionist".equals(typeuser())) {
                        
                    setForm(form2);}
                } else if (index == 7) {
                    form3.loadDites();
                    setForm(form3);
                }
                else if (index == 8){
                setForm(repas);
                }
                else if (index == 11) {
                   supprimercompte(); 
                   
                } 
                else if (index == 12) {
                   setForm(compte); 
                   
                }
                else if (index == 13){
                    /*logout*/
                logout();
                
                } 
                  
            }
        });
       
        setForm(new Form_Home());/*}
        else {
            new Login ().setVisible(true);
        
        }*/
    }
    public void interdi(){
  JOptionPane.showMessageDialog(this,"Vous n'avez pas l'autorisation d'accéder ici","Error",JOptionPane.ERROR_MESSAGE);
    
    
    }
    public void labeluser(){
    if ("personnel".equals(typeuser())){
     utilisateur.setText("Personnel");
     utilisateur.setVisible(true);
    }else{
    utilisateur.setText("Nutritionist");
     utilisateur.setVisible(true);
    }
    
    
    }
    public String typeuser (){
        String type;
     connexion co = new connexion();
     try {
            
              String query = "SELECT role AS Role FROM users where id = ?"; 
                    PreparedStatement pstmt1 = co.con.prepareStatement(query);
         
            pstmt1.setInt(1, userID);
                 ResultSet resultSet = pstmt1.executeQuery();
                  if (resultSet.next()) {
                      return resultSet.getString("Role");
                  }
                  
              
        } catch (SQLException e) {
            e.printStackTrace();
        }
         return null;
      
    
    
    
    
    }
     public void supprimercompte(){
       int option = JOptionPane.showConfirmDialog(this, "Êtes-vous sûr de vouloir supprimer votre compte?", "Confirmation", JOptionPane.YES_NO_OPTION);
    
    if (option == JOptionPane.YES_OPTION) {
        connexion co = new connexion();
        String query = "DELETE FROM users WHERE id = ?";
        try {
            PreparedStatement pstmt = co.con.prepareStatement(query);
            pstmt.setInt(1, userID);
            
            int rowsAffected = pstmt.executeUpdate();
            pstmt.close();
            co.con.close();
            
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Compte supprimé avec succès");
      
                this.setVisible(false);  
                new Login().setVisible(true);  
            } else {
                JOptionPane.showMessageDialog(this, "Échec de la suppression du compte");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Une erreur est survenue lors de la suppression du compte", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }else {
    
    setForm(home);
    }
     
     
     }
 private void setForm(JComponent com) {
        mainPanel.removeAll();
        mainPanel.add(com);
        mainPanel.repaint();
        mainPanel.revalidate();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new swing.PanelBorder();
        menu = new component.Menu();
        close = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        mainPanel = new javax.swing.JPanel();
        utilisateur = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        panelBorder1.setBackground(new java.awt.Color(242, 242, 242));
        panelBorder1.setPreferredSize(new java.awt.Dimension(900, 650));

        close.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        close.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        close.setText("x");
        close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeMouseClicked(evt);
            }
        });

        jScrollPane2.setBorder(null);

        mainPanel.setLayout(new java.awt.BorderLayout());
        jScrollPane2.setViewportView(mainPanel);

        utilisateur.setBackground(new java.awt.Color(242, 242, 242));
        utilisateur.setForeground(new java.awt.Color(0, 0, 255));
        utilisateur.setText("jLabel1");

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(utilisateur, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                        .addGap(660, 660, 660)
                        .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(utilisateur)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, 1158, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseClicked
     System.exit(0);
    }//GEN-LAST:event_closeMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
   for (double i = 0.0; i <=1.0; i = i+0.1){
            String val = i+ "";
            float f = Float.valueOf(val);
            this.setOpacity(f);
            try{
                Thread.sleep(50);
            }catch(Exception e){
                
            }}
    }//GEN-LAST:event_formWindowOpened

    /**
     * @param args the command line arguments
     */
   public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
     * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
    try {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
        java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
   java.awt.EventQueue.invokeLater(new Runnable() {
    public void run() {
        new Dashboard(userID).setVisible(true); 
    }
});

}
private void logout() {
    this.setVisible(false);  
    new Login().setVisible(true);  
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel close;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel mainPanel;
    private component.Menu menu;
    private swing.PanelBorder panelBorder1;
    private javax.swing.JLabel utilisateur;
    // End of variables declaration//GEN-END:variables
}
