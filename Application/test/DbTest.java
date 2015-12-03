/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import databag.PersoonBag;
import databag.PloegBag;
import database.dataDB;
import datatype.Categorie;
import exception.ApplicationException;
import exception.DBException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author david
 */
public class DbTest {
    private dataDB database;
    public DbTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        database=new dataDB();
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void ToevoegenPersonen() throws Exception
    {
        PersoonBag een=new PersoonBag();
        een.setVoornaam("David");
        een.setNaam("Claeys");
       
        een.setGeboortedatum(1995,4,13);
       
        System.out.println(een.getGeboortedatum());
        een.setTrainer(false);
      
        
        PersoonBag twee=new PersoonBag();
        twee.setVoornaam("Cristina");
        twee.setNaam("Claeys");
       
        twee.setGeboortedatum(1999,10,1);
       
        twee.setTrainer(false);
        
        PersoonBag drie=new PersoonBag();
        drie.setVoornaam("Papi");
        drie.setNaam("Chulo");
        
        drie.setGeboortedatum(1989,8,1);
       
        drie.setTrainer(true);
        
        PersoonBag vier=new PersoonBag();
        vier.setVoornaam("Mami");
        vier.setNaam("Chula");
        vier.setGeboortedatum(1979,6,2);
        
        vier.setTrainer(true);
                
        
        database.toevoegenPersoon(een);
        database.toevoegenPersoon(twee);
        database.toevoegenPersoon(drie);
        database.toevoegenPersoon(vier);
            
        
    }
    
    @Test
    public void AllePersonen() throws Exception
    {
        
        ArrayList speler=new ArrayList();
        ArrayList trainer=new ArrayList();
        
        speler=database.zoekAlleSpelers();
        trainer=database.zoekAlleTrainers();
       
      
    }
    
    @Test
    public void afdrukkenSpelers() throws Exception
    {
         ArrayList speler=new ArrayList();
         speler=database.zoekAlleSpelers();
             
        System.out.println("Spelers");
        for (int i=0;i<speler.size();i++) {
            System.out.println(speler.get(i).toString());
        }

    }
    
    @Test
    public void afdrukkenTrainers() throws Exception
    {
        ArrayList trainer;
        
        trainer=database.zoekAlleTrainers();     
      
        System.out.println("\n"+"Trainers");
        for(int i=0;i<trainer.size();i++)
        {
            System.out.println(trainer.get(i).toString());
        }
    }
    
    @Test 
    public void toevoegenPloegMetTrainer() throws Exception
    {
        
        PersoonBag drie=new PersoonBag();
        drie.setVoornaam("Papi");
        drie.setNaam("Chulo");
        
        drie.setGeboortedatum(1989,8,1);
       
        drie.setTrainer(true);
        
        PloegBag ploeg=new PloegBag();
        ploeg.setNaam("los papis");
        ploeg.setCategorie(Categorie.U6);
        
        PersoonBag p = database.zoekPersoon(drie.getNaam(),drie.getVoornaam());
        ploeg.setTrainer(p.getId());
        System.out.println("\n"+"instellen trainer"+"\n");
         
         database.toevoegenPloeg(ploeg); 
       
       
    }
    
    @Test
    public void toevoegenPloeg() throws Exception
    {
        PloegBag ploeg=new PloegBag();
        ploeg.setNaam("los papasitos");
        ploeg.setCategorie(Categorie.U6);
       
        database.toevoegenPloeg(ploeg);
        
        
    }
    
    @Test
    public void koppelenTrainerAanPloeg() throws Exception
    {
        PersoonBag drie=new PersoonBag();
        drie.setVoornaam("Rubensito");
        drie.setNaam("Chulito");
        
        drie.setGeboortedatum(1998,8,1);
        
        drie.setTrainer(true);
        
        database.toevoegenPersoon(drie);
        
       PloegBag ploeg;
        
        ploeg = database.zoekPloeg("los papis");
        database.toevoegenTrainerPloeg(drie, ploeg);
       
    }
    
    @Test
    public void koppelenSpeler() throws Exception
    {
        PersoonBag een=new PersoonBag();
        een.setVoornaam("David");
        een.setNaam("Claeys");
       
   
        PersoonBag twee=new PersoonBag();
        twee.setVoornaam("Cristina");
        twee.setNaam("Claeys");
        
     
        
        PloegBag ploeg=new PloegBag();
        ploeg.setNaam("los papis");
        ploeg.setCategorie(Categorie.U6);
       
        database.toevoegenSpelerPloeg(database.zoekPloeg(ploeg.getNaam()),database.zoekPersoon(een.getNaam(), een.getVoornaam()));
        PloegBag p=database.zoekPloeg(ploeg.getNaam());
        database.toevoegenSpelerPloeg(ploeg,database.zoekPersoon(twee.getNaam(),twee.getVoornaam()) );
        
        
    }
    
    @Test 
    public void ontkoppelenSpeler() throws Exception
    {
        PersoonBag een=new PersoonBag();
        een.setVoornaam("David");
        een.setNaam("Claeys");
        
        een.setGeboortedatum(1995,4,13);
       
       
        een.setTrainer(false);
       
        database.verwijderSpelerPloeg(een.getNaam(),een.getVoornaam());
        
        
    }
    
    
}
