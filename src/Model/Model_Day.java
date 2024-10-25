/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.swing.Icon;

/**
 *
 * @author Karim
 */
public class Model_Day {

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public String getPetit_déjeuner() {
        return petit_déjeuner;
    }

    public void setPetit_déjeuner(String petit_déjeuner) {
        this.petit_déjeuner = petit_déjeuner;
    }

    public String getDéjeuner() {
        return déjeuner;
    }

    public void setDéjeuner(String déjeuner) {
        this.déjeuner = déjeuner;
    }

    public String getDîner() {
        return dîner;
    }

    public void setDîner(String dîner) {
        this.dîner = dîner;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
 

    public Model_Day(Icon icon, String title, String values, String description,String num) {
        this.icon = icon;
        this.petit_déjeuner = title;
        this.déjeuner = values;
        this.dîner = description;
        this.num=num;
    }

    

    private Icon icon;
    private String petit_déjeuner;
    private String déjeuner;
    private String dîner;
    private String num;
}

