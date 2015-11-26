/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import exception.ApplicationException;
import exception.DBException;
import java.util.ArrayList;
import java.util.Date;
import vivesgoal.Categorie;
import vivesgoal.databag.PersoonBag;
import vivesgoal.databag.PloegBag;

/**
 *
 * @author david
 */
public class DBfuntionsTest {
    public static void main(String args[]) {
        
        PersoonBag een=new PersoonBag();
//        een.setId(1);
        een.setVoornaam("David");
        een.setNaam("Claeys");
        een.setGeboortedatum(new Date(1995,4,13));
        een.setTrainer(false);
        
        PersoonBag twee=new PersoonBag();
//        twee.setId(2);
        twee.setVoornaam("Cristina");
        twee.setNaam("Claeys");
        twee.setGeboortedatum(new Date(1999,10,1));
        twee.setTrainer(false);
        
        PersoonBag drie=new PersoonBag();
//        drie.setId(3);
        drie.setVoornaam("Papi");
        drie.setNaam("Chulo");
        drie.setGeboortedatum(new Date(1989,8,1));
        drie.setTrainer(true);
        
        PersoonBag vier=new PersoonBag();
//        vier.setId(4);
        vier.setVoornaam("Mami");
        vier.setNaam("Chula");
        vier.setGeboortedatum(new Date(1979,6,2));
        vier.setTrainer(true);
        
        ArrayList speler=new ArrayList();
        ArrayList trainer=new ArrayList();
        dataDB database = new dataDB();
        
        try{
            database.toevoegenPersoon(een);
            database.toevoegenPersoon(twee);
            database.toevoegenPersoon(drie);
            database.toevoegenPersoon(vier);
            
        }
        catch(DBException e)
        {
            System.out.println(e.getMessage());
        }
        
        try{
            speler=database.zoekAlleSpelers();
            trainer=database.zoekAlleTrainers();
        }
        catch(DBException | ApplicationException  e1)
        {
            System.out.println("fout bij ophalen gegevens"+e1.getMessage());
        }
        
        System.out.println("Spelers");
        for (int i=0;i<speler.size();i++) {
            System.out.println(speler.get(i).toString());
        }
        System.out.println("\n"+"Trainers");
        for(int i=0;i<trainer.size();i++)
        {
            System.out.println(trainer.get(i).toString());
        }
        System.out.println("\n");
        
        PloegBag ploeg=new PloegBag();
        ploeg.setNaam("los papis");
        ploeg.setCategorie(Categorie.U6);
        try{
            ploeg.setTrainer((database.zoekPersoon(drie.getNaam(),drie.getVoornaam())).getId());
                  }
        catch ( DBException|ApplicationException e)
        {
            System.out.println("fout bij opzoeken trainer "+e.getMessage());
        }
        
        try{
            database.toevoegenPloeg(ploeg);
            System.out.println("ploeg toegevoegd");  
        }
        catch ( DBException e)
        {
            System.out.println("fout bij toevoegen ploeg "+e.getMessage());
        }
        
        try{
            database.toevoegenSpelerPloeg(ploeg,database.zoekPersoon(een.getNaam(), een.getVoornaam()));
        }
        catch ( DBException|ApplicationException e)
        {
            System.out.println("fout bij toevoegen speler "+e.getMessage());
        }
        
        System.out.println("\n"+"verwijderen spelers");
        for(int i=0;i<speler.size();i++)
        {
            PersoonBag a = (PersoonBag) speler.get(i);
            try{
            database.verwijderPersoon(a.getId());
        }
        catch(DBException   e1)
        {
            System.out.println("fout bij verwijderen speler"+e1.getMessage());
        }
        }
        System.out.println("succesfull");
        
        System.out.println("\n"+"verwijderen trainers");
        for(int i=0;i<trainer.size();i++)
        {
            PersoonBag a = (PersoonBag) trainer.get(i);
            try{
            database.verwijderPersoon(a.getId());
        }
        catch(DBException   e1)
        {
            System.out.println("fout bij verwijderen trainer"+e1.getMessage());
        }
        }
        System.out.println("succesfull");
    }
    

}
