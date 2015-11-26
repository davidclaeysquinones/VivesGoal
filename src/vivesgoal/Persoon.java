/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vivesgoal;

import java.util.Date;

/**
 *
 * @author david
 */
public class Persoon {
    private String naam;
    private String voornaam;
    private Date geboortedatum;
    private boolean trainer;
    
    public Persoon(String naam,String voornaam,Date geboortedatum,boolean trainer)
    {
        this.naam=naam;
        this.voornaam=voornaam;
        this.geboortedatum=geboortedatum;
        this.trainer=trainer;
    }
    
    public Persoon(String naam,String voornaam,int jaar,int maand,int dag,boolean trainer)
    {
        this.naam=naam;
        this.voornaam=voornaam;
        this.geboortedatum=new Date(jaar,maand,dag);
        this.trainer=trainer;
    }
    
}
