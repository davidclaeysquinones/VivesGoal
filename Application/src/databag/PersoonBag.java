/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databag;

import exception.ApplicationException;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author david
 */
public class PersoonBag {
    private int id;
    private String naam;
    private String voornaam;
    private Date geboortedatum;
    private boolean trainer;
    private String opmerking;
    

    // defaultconstructor
    // getters
    public int getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }
    public boolean getTrainer() {
        return trainer;
    }
    public String getOpmerking() {
        return opmerking;
    }

    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }
    /**
     * 
     * @param jaar
     * @param maand 
     * @param dag
     * setGeboortedatum(2016,1,1) stelt de datum in op 1 januari 2016
     * @throws ApplicationException 
     */

    public void setGeboortedatum(int jaar,int maand,int dag) throws ApplicationException{
       Date current = new Date(System.currentTimeMillis());
       Calendar c=Calendar.getInstance();
       c.add(Calendar.MILLISECOND,86400000);
       c.set(jaar,maand-1,dag);   
       Date d=new Date(c.getTimeInMillis()) ;
        if(jaar>=0 && maand <=12 && maand>0 && dag<=31 && current.after(d))
        {           
            geboortedatum =d;
        }
        else
        {
            throw new ApplicationException("De opgegeven datum heeft een verkeerd formaat");
        }
               
    }

    public void setTrainer(boolean trainer) {
        this.trainer = trainer;
    }
    public void setOpmerking(String opmerking) {
        this.opmerking=opmerking;
    }

@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(voornaam).append(" ").
                append(naam).append(" ");
        return sb.toString();
    }
}
