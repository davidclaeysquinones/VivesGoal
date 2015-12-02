/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transactie;

import databag.PloegBag;
import datatype.Ploeg;
import database.dataDB;
import exception.ApplicationException;


/**
 *
 * @author david
 */
public class PloegTrans implements PloegTransInterface{

   private dataDB database = new dataDB();
    @Override
    public Integer ploegToevoegen(Ploeg p) throws Exception {
        if(database.zoekPloeg(p.getNaam())!=null)
                {
                    throw new ApplicationException("Deze ploeg bestaat al");
                }
        else
        {
            PloegBag ploeg=convertPloegToPloegBag(p);
            database.toevoegenPloeg(ploeg);
            return database.zoekPloeg(ploeg).getId();
        }
            
    }

    @Override
    public void trainerVerwijderenVanPloeg(int ploegId) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void trainerKoppelenAanPloeg(int trainerId, int ploegId) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private PloegBag convertPloegToPloegBag(Ploeg p)
    {
        PloegBag ploegbag=new PloegBag();
        ploegbag.setNaam(p.getNaam());
        ploegbag.setCategorie(p.getCategorie());
        return ploegbag;
    }
    private Ploeg convertPloegBagToPloeg(PloegBag p)
    {
        Ploeg ploeg = new Ploeg(p.getNaam(),p.getCategorie());
        return ploeg;
    }
}
