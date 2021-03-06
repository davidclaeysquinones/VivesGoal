/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databag;

import datatype.Categorie;
import datatype.Ploeg;
import exception.ApplicationException;

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
     public void setCategorie(String categorie) throws ApplicationException {
        try {
            this.categorie = Categorie.valueOf(categorie);
        }
        catch (IllegalArgumentException e) 
        {
            throw new ApplicationException("Geen geldige status voor Klant");
        }
    }
    
    
    /**
     *
     * @param id
     */
    public void setTrainer(int id) 
    {
        trainer=id;
    }
    
    @Override
    public String toString()
    {
        String output ="id : "+id+" naam : "+naam;
        return output;
    }
    
    public Ploeg convertPloegBagToPloeg()
    {
        Ploeg ploeg = new Ploeg(getNaam(),getCategorie());
        return ploeg;
    }
    
}
