/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import mini_projet_errahem_karim.connexion;

/**
 *
 * @author Karim
 */
public class dinner extends JComboBox{
    
    
int nbcal =0;

    public int getNbcal() {
        return nbcal;
    }

    public void setNbcal(int nbcal) {
        this.nbcal = nbcal;
    }

    public int getNbfat() {
        return nbfat;
    }

    public void setNbfat(int nbfat) {
        this.nbfat = nbfat;
    }

    public int getNbpro() {
        return nbpro;
    }

    public void setNbpro(int nbpro) {
        this.nbpro = nbpro;
    }

    public ArrayList<String> getMealNamesbreakfast() {
        return mealNamesbreakfast;
    }

    public void setMealNamesbreakfast(ArrayList<String> mealNamesbreakfast) {
        this.mealNamesbreakfast = mealNamesbreakfast;
    }

    public ComboBoxModel getDataModel() {
        return dataModel;
    }

    public void setDataModel(ComboBoxModel dataModel) {
        this.dataModel = dataModel;
    }

    public boolean isIsEditable() {
        return isEditable;
    }

    public void setIsEditable(boolean isEditable) {
        this.isEditable = isEditable;
    }

    public Object getSelectedItemReminder() {
        return selectedItemReminder;
    }

    public void setSelectedItemReminder(Object selectedItemReminder) {
        this.selectedItemReminder = selectedItemReminder;
    }
    int nbfat = 0;
    int nbpro = 0;
     private ArrayList<String> mealNamesbreakfast = new ArrayList<>();
  private void loadMealNames(String Type) {
    connexion c = new connexion();
    String query = "SELECT name, type FROM meals where type = '?'";
    try {
        
        PreparedStatement pstmt = c.con.prepareStatement(query);
        pstmt.setString(1, Type);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            String type = rs.getString("type");
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
   private void calculerInformationsNutritionnellesRepas(JComboBox comboBox) {
    // Vérifier si la JComboBox est initialisée et a un élément sélectionné
    if (comboBox.getSelectedItem() != null) {
        // Réinitialiser les totaux à zéro avant de recalculer
       int totalCalories = 0;
       int totalFats = 0;
       int totalProteins = 0;

        // Récupérer le repas sélectionné dans la JComboBox
        String selectedMeal = comboBox.getSelectedItem().toString();

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


