/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import exception.ApplicationException;
import exception.DBException;
import java.util.ArrayList;
import databag.Categorie;
import databag.PersoonBag;
import databag.PloegBag;

/**
 *
 * @author david
 */
public class DBfuntionsTest {
    public static void main(String args[]) {
        
       
   
        PersoonBag een=new PersoonBag();
        een.setVoornaam("David");
        een.setNaam("Claeys");
        try {
            een.setGeboortedatum(1995,4,13);
        } catch (ApplicationException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(een.getGeboortedatum());
        een.setTrainer(false);
      
        
        PersoonBag twee=new PersoonBag();
        twee.setVoornaam("Cristina");
        twee.setNaam("Claeys");
        try {
            twee.setGeboortedatum(1999,10,1);
        }  catch (ApplicationException ex) {
            System.out.println(ex.getMessage());
        }
        twee.setTrainer(false);
        
        PersoonBag drie=new PersoonBag();
        drie.setVoornaam("Papi");
        drie.setNaam("Chulo");
        try {
            drie.setGeboortedatum(1989,8,1);
        } catch (ApplicationException ex) {
            System.out.println(ex.getMessage());
        }
        drie.setTrainer(true);
        
        PersoonBag vier=new PersoonBag();
        vier.setVoornaam("Mami");
        vier.setNaam("Chula");
        try {
            vier.setGeboortedatum(1979,6,2);
        } catch (ApplicationException ex) {
            System.out.println(ex.getMessage());
        }
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
        
        
        PloegBag ploeg=new PloegBag();
        ploeg.setNaam("los papis");
        ploeg.setCategorie(Categorie.U6);
        try{
            PersoonBag p = database.zoekPersoon(drie.getNaam(),drie.getVoornaam());
            ploeg.setTrainer(p.getId());
            System.out.println("\n"+"instellen trainer"+"\n");
                  
        }
        catch ( DBException|ApplicationException e)
        {
            System.out.println("fout bij opzoeken trainer "+e.getMessage());
        }
        
        
        
        
        try{
            database.toevoegenPloeg(ploeg);
            System.out.println("ploeg toegevoegd"+"\n");  
        }
        catch ( DBException e)
        {
            System.out.println("fout bij toevoegen ploeg "+e.getMessage());
        }
        
        try{
            database.toevoegenSpelerPloeg(database.zoekPloeg(ploeg.getNaam()),database.zoekPersoon(een.getNaam(), een.getVoornaam()));
            PloegBag p=database.zoekPloeg(ploeg.getNaam());
            database.toevoegenSpelerPloeg(p,database.zoekPersoon(twee.getNaam(),twee.getVoornaam()) );
            System.out.println("toevoegen spelers aan ploeg"+"\n");
        }
        catch ( DBException|ApplicationException
                e)
        {
            System.out.println("fout bij toevoegen speler "+e.getMessage());
        }
        
        
     
          
       ArrayList ploegen = new ArrayList(); 
        try {
            
            ploegen = database.zoekAllePloegen();
            System.out.println("Alle ploegen zoeken"+"\n");
        }
        catch(DBException|ApplicationException e)
        {
            System.out.println("fout bij opzoeken van alle ploegen"+e.getMessage());
        } 
        
        try {
            PloegBag p=database.zoekPloeg(ploeg.getNaam());
            System.out.println("opzoeken ploeg : "+p+"\n");
        }
        catch(DBException|ApplicationException e)
        {
            System.out.println("fout bij opzoeken ploeg "+e.getMessage());
        }
        
        for(int i=0;i<ploegen.size();i++)
        {
            System.out.println(ploegen.get(i));
        }
        
        try {
            PersoonBag a = new PersoonBag();
            a.setNaam("Mano");
            a.setVoornaam("Lito");
            database.wijzigenPersoon(een.getNaam(), een.getVoornaam(), a);
            System.out.println("Wijzigen persoon"+"\n");
            
          
            
        }
        catch(DBException|ApplicationException e)
        {
            System.out.println("fout bij wijzigen persoon "+e.getMessage());
        }
        
        for(int i=0;i<speler.size();i++)
        {
            PersoonBag a = (PersoonBag) speler.get(i);
            try{
            database.verwijderPersoon(a.getId());
            if(i==0)
            {
                System.out.println("verwijderen spelers"+"\n");
            }
        }
        catch(DBException   e1)
        {
            System.out.println("fout bij verwijderen speler"+e1.getMessage());
        }
        }
        
        
        
        for(int i=0;i<trainer.size();i++)
        {
            PersoonBag a = (PersoonBag) trainer.get(i);
            try{
            database.verwijderPersoon(a.getNaam(),a.getVoornaam());
            if(i==0)
            {
                System.out.println("verwijderen trainers"+"\n");
            }
            
        }
        catch(DBException|ApplicationException   e1)
        {
            System.out.println("fout bij verwijderen trainer"+e1.getMessage());
        }
        }
       
      
        
        
        try{
            database.verwijderAllePloegen();
            System.out.println("Alle ploegen verwijderen");
        }
        catch ( DBException e)
        {
            System.out.println("fout bij verwijderen trainer"+e.getMessage());
        }
  
    }
    

}
