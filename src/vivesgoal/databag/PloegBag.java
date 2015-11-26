/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vivesgoal.databag;

import vivesgoal.Categorie;

/**
 *
 * @author david
 */
public class PloegBag {
    private int id;
    private String naam;
    private Categorie categorie;
    private int trainer;
    
    public int getId()
    {
        return id;
    }
    
    public String getNaam()
    {
        return naam;
    }
    
    public Categorie getCategorie()
    {
        return categorie;
    }
    
    public int getTrainer()
    {
        return trainer;
    }
    
    public void setId(int id)
    {
        this.id=id;
    }
    
    public void setNaam(String naam)
    {
        this.naam=naam;
    }
    
    public void setCategorie(Categorie categorie)
    {
        this.categorie=categorie;
    }
    
    
    /**
     *
     * @param id
     */
    public void setTrainer(int id) 
    {
        this.id=id;
    }
}
