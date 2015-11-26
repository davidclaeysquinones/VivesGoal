/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vivesgoal;

import java.util.ArrayList;

/**
 *
 * @author david
 */
public class Ploeg {
    private ArrayList<Persoon> spelers;
    private Persoon trainer;
    
    
    public Ploeg()
    {
        spelers=new ArrayList<>();
    }
    
    public Ploeg(Persoon trainer)
    {
        this.trainer=trainer;
    }
}
