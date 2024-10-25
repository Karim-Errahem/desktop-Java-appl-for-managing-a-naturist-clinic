/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JComboBox;
import mini_projet_errahem_karim.connexion;

/**
 *
 * @author Karim
 */
public class lunch extends JComboBox {
    
 public int nbcal =0;
   public   int nbfat = 0;
    public  int nbpro = 0;
     private ArrayList<String> mealNamesbreakfast = new ArrayList<>();
public void loadMealNames(String type) {
    connexion c = new connexion();
    String query = "SELECT name, type FROM meals WHERE type = ?";
    try {
        PreparedStatement pstmt = c.con.prepareStatement(query);
        pstmt.setString(1, type);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            String name = rs.getString("name");
            mealNamesbreakfast.add(name);
        }

        // Fermeture des ressources JDBC
        rs.close();
        pstmt.close();

    } catch (Exception e) {
        e.printStackTrace();
    } 
}
  public void populateComboBox() {
        for (String mealName : mealNamesbreakfast) {
            this.addItem(mealName);
        }}
    

  private int getMealIdByName(String mealName) {
    connexion c = new connexion();
    String query = "SELECT id FROM meals WHERE name = ?";
    try {
        PreparedStatement pstmt = c.con.prepareStatement(query);
        pstmt.setString(1, mealName);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("id");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return -1; // Retourne -1 si le repas n'est pas trouvé
} 
   public void calculerInformationsNutritionnellesRepas() {
    // Vérifier si la JComboBox est initialisée et a un élément sélectionné
    if (this.getSelectedItem() != null) {
        // Réinitialiser les totaux à zéro avant de recalculer
       int totalCalories = 0;
       int totalFats = 0;
       int totalProteins = 0;

        // Récupérer le repas sélectionné dans la JComboBox
        String selectedMeal = this.getSelectedItem().toString();

        // Récupérer les informations nutritionnelles du repas sélectionné
        int[] mealInfo = getNutritionalInfoByMealName(selectedMeal);

        // Mettre à jour les totaux avec les informations du repas sélectionné
        totalCalories = mealInfo[0];
        totalFats = mealInfo[1];
        totalProteins = mealInfo[2];
    
    this.nbcal=totalCalories;
    this.nbfat=totalFats;
    this.nbpro=totalProteins;
    }}
   private int[] getNutritionalInfoByMealName(String mealName) {
    connexion c = new connexion();
    String query = "SELECT SUM(fi.calories * mi.quantity) AS totalCalories, " +
                   "SUM(fi.fats * mi.quantity) AS totalFats, " +
                   "SUM(fi.proteins * mi.quantity) AS totalProteins " +
                   "FROM meals m " +
                   "INNER JOIN mealitems mi ON m.id = mi.meal_id " +
                   "INNER JOIN fooditems fi ON mi.food_item_id = fi.id " +
                   "WHERE m.name = ?";
    try {
        PreparedStatement pstmt = c.con.prepareStatement(query);
        pstmt.setString(1, mealName);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            int totalCalories = rs.getInt("totalCalories");
            int totalFats = rs.getInt("totalFats");
            int totalProteins = rs.getInt("totalProteins");
            return new int[]{totalCalories, totalFats, totalProteins};
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return new int[]{0, 0, 0};  // Return [0, 0, 0] if nutritional info not found
}
}

