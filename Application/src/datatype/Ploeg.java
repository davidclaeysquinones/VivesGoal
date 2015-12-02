/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datatype;

import databag.PloegBag;



/**
 *
 * @author david
 */
public class Ploeg {
    private String naam;
    private Categorie categorie;

    public Ploeg(String naam,Categorie categorie)
    {
        this.naam=naam;
        this.categorie=categorie;
    }
    public Ploeg()
    {
        
    }
    /**
     * @return the naam
     */
    public String getNaam() {
        return naam;
    }

    /**
     * @param naam the naam to set
     */
    public void setNaam(String naam) {
        this.naam = naam;
    }

    /**
     * @return the categorie
     */
    public Categorie getCategorie() {
        return categorie;
    }

    /**
     * @param categorie the categorie to set
     */
    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
    
     public PloegBag convertPloegToPloegBag()
    {
        PloegBag ploegbag=new PloegBag();
        ploegbag.setNaam(getNaam());
        ploegbag.setCategorie(getCategorie());
        return ploegbag;
    }
    
}
