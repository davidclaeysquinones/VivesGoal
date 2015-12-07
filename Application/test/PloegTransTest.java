/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import datatype.Categorie;
import databag.Ploeg;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import transactie.PloegTrans;


/**
 *
 * @author david
 */
public class PloegTransTest {
    PloegTrans transactie;
    public PloegTransTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
      transactie=new PloegTrans();
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
    public void ploegToevoegen() throws Exception
    {
        Ploeg p =new Ploeg();
        p.setNaam("maandag");
        p.setCategorie(Categorie.U6);
        int i =transactie.ploegToevoegen(p);
        System.out.println(i);
    }
    
    @Test
    public void trainerVerwijderen() throws Exception
    {
        Ploeg p = new Ploeg();
        p.setNaam("dinsdag");
        p.setCategorie(Categorie.U8);
        int ploeg =transactie.ploegToevoegen(p);
        
    }
    
    
    
}
