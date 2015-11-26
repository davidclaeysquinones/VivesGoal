/*
 * KlantDB.java
 *
 * Created on 29 maart 2005, 13:59
 */
package database;

/**
 *
 * @author David
 */
import vivesgoal.databag.PersoonBag;
import vivesgoal.databag.PloegBag;
import vivesgoal.Categorie;
import exception.ApplicationException;
import exception.DBException;
import java.sql.*;
import java.util.*;

/**
 * Bevat alle functionaliteit op de database-tabel klant. - schrappen van een
 * klant - toevoegen van een klant - wijzigen van klant - zoeken van alle
 * klanten gesorteerd op naam, voornaam - zoeken van alle ingeschreven klanten
 * gesorteerd op naam, voornaam - zoeken van alle uitgeschreven klanten
 * gesorteerd op naam, voornaam - zoeken van een klant op id - zoeken van een
 * klant op naam, voornaam, adres, postcode en gemeente Deze class levert
 * business-objecten op.
 */
public class dataDB {

   public dataDB() {
   }

 
   public PersoonBag zoekPersoon(int id) throws DBException, ApplicationException {
      PersoonBag returnPersoon = null;
      // connectie tot stand brengen (en automatisch sluiten)
      try (Connection conn = ConnectionManager.getConnection();) {
         // preparedStatement opstellen (en automtisch sluiten)
         try (PreparedStatement stmt = conn.prepareStatement(
            "select id, naam, voornaam, geboortedatum, trainer,opmerking from persoon where id = '?'");) {
            stmt.setInt(1, id);
            // execute voert het SQL-statement uit
            stmt.execute();
            // result opvragen (en automatisch sluiten)
            try (ResultSet r = stmt.getResultSet()) {
               // van de klant uit de database een KlantBag-object maken
               PersoonBag k = new PersoonBag();

               // er werd een persoon gevonden
               if (r.next()) {
                  k.setId(r.getInt("id"));
                  k.setNaam(r.getString("naam"));
                  k.setVoornaam(r.getString("voornaam"));
                  k.setGeboortedatum(r.getDate("geboortedatum"));
                  k.setTrainer(r.getBoolean("trainer"));
                  k.setOpmerking(r.getString("opmerking"));
                  returnPersoon = k;
               }

               return returnPersoon;
            } catch (SQLException sqlEx) {
               throw new DBException("SQL-exception in zoekPersoon(int id) - resultset" + sqlEx);
            }
         } catch (SQLException sqlEx) {
            throw new DBException("SQL-exception in zoekPersoon(int id)- statement"+ sqlEx);
         }
      } catch (SQLException sqlEx) {
         throw new DBException(
            "SQL-exception in zoekPersoon(int id)- connection");
      }
   }
   public PersoonBag zoekPersoon(String naam,String voornaam) throws DBException, ApplicationException {
      PersoonBag returnPersoon = null;
      // connectie tot stand brengen (en automatisch sluiten)
      try (Connection conn = ConnectionManager.getConnection();) {
         // preparedStatement opstellen (en automtisch sluiten)
         try (PreparedStatement stmt = conn.prepareStatement(
            "select id, naam, voornaam, geboortedatum, trainer,opmerking from persoon where voornaam =? and naam=?");) {
            stmt.setString(1,voornaam );
            stmt.setString(2, naam);
             
            // execute voert het SQL-statement uit
            stmt.execute();
            // result opvragen (en automatisch sluiten)
            try (ResultSet r = stmt.getResultSet()) {
               // van de klant uit de database een KlantBag-object maken
               PersoonBag k = new PersoonBag();

               // er werd een persoon gevonden
               if (r.next()) {
                  k.setId(r.getInt("id"));
                  k.setNaam(r.getString("naam"));
                  k.setVoornaam(r.getString("voornaam"));
                  k.setGeboortedatum(r.getDate("geboortedatum"));
                  k.setTrainer(r.getBoolean("trainer"));
                  k.setOpmerking(r.getString("opmerking"));
                  returnPersoon = k;
               }

               return returnPersoon;
            } catch (SQLException sqlEx) {
               throw new DBException("SQL-exception in zoekPersoon(String naam,String voornaam) - resultset" + sqlEx);
            }
         } catch (SQLException sqlEx) {
            throw new DBException("SQL-exception in zoekPersoon(String naam,String voornaam) - statement"+ sqlEx);
         }
      } catch (SQLException sqlEx) {
         throw new DBException(
            "SQL-exception in zoekPersoon(String naam,String voornaam)t - connection");
      }
   }

   public PloegBag zoekPloeg(int id) throws DBException, ApplicationException {
      PloegBag returnPloeg = null;
      // connectie tot stand brengen (en automatisch sluiten)
      try (Connection conn = ConnectionManager.getConnection();) {
         // preparedStatement opstellen (en automtisch sluiten)
         try (PreparedStatement stmt = conn.prepareStatement(
            "select id, naam,categorie,trainer from ploeg where id = ?");) {
            stmt.setInt(1, id);
            // execute voert het SQL-statement uit
            stmt.execute();
            // result opvragen (en automatisch sluiten)
            try (ResultSet r = stmt.getResultSet()) {
               // van de klant uit de database een KlantBag-object maken
               PloegBag k = new PloegBag();

               // er werd een ploeg gevonden
               if (r.next()) {
                  k.setId(r.getInt("id"));
                  k.setNaam(r.getString("naam"));
                  k.setCategorie(r.getObject("categorie",Categorie.class));
                  
                  returnPloeg = k;
               }

               return returnPloeg;
            } catch (SQLException sqlEx) {
               throw new DBException("SQL-exception in zoekPloeg(int id) - resultset" + sqlEx);
            }
         } catch (SQLException sqlEx) {
            throw new DBException("SQL-exception in zoekPloeg(int id) - statement"+ sqlEx);
         }
      } catch (SQLException sqlEx) {
         throw new DBException(
            "SQL-exception in zoekPloeg(int id) - connection");
      }
   }

   public PloegBag zoekPloeg(String naam) throws DBException, ApplicationException {
      PloegBag returnPloeg = null;
      // connectie tot stand brengen (en automatisch sluiten)
      try (Connection conn = ConnectionManager.getConnection();) {
         // preparedStatement opstellen (en automtisch sluiten)
         try (PreparedStatement stmt = conn.prepareStatement(
            "select id, naam,categorie,trainer from ploeg where naam = ?");) {
            stmt.setString(1, naam);
            // execute voert het SQL-statement uit
            stmt.execute();
            // result opvragen (en automatisch sluiten)
            try (ResultSet r = stmt.getResultSet()) {
               // van de klant uit de database een KlantBag-object maken
               PloegBag k = new PloegBag();

               // er werd een ploeg gevonden
               if (r.next()) {
                  k.setId(r.getInt("id"));
                  k.setNaam(r.getString("naam"));
                  k.setCategorie(r.getObject("categorie",Categorie.class));
                  
                  returnPloeg = k;
               }

               return returnPloeg;
            } catch (SQLException sqlEx) {
               throw new DBException("SQL-exception in zoekPloeg(String naam) - resultset" + sqlEx);
            }
         } catch (SQLException sqlEx) {
            throw new DBException("SQL-exception in zoekPloeg(String naam) - statement"+ sqlEx);
         }
      } catch (SQLException sqlEx) {
         throw new DBException(
            "SQL-exception in zoekPloeg(String naam) - connection");
      }
   }

//   public ArrayList<PloegBag> zoekAllePloegen()throws DBException
//   {
//       ArrayList ploegen = new ArrayList();
//      // connectie tot stand brengen (en automatisch sluiten)
//      try (Connection conn = ConnectionManager.getConnection();) {
//         // preparedStatement opstellen (en automtisch sluiten)
//         try (PreparedStatement stmt = conn.prepareStatement(
//            "select * from ploeg");) {
//            
//            // execute voert het SQL-statement uit
//            stmt.execute();
//            // result opvragen (en automatisch sluiten)
//            try (ResultSet r = stmt.getResultSet()) {
//              while (r.next()) {
//
//                     PloegBag p = new PloegBag();
//                     p.setId(r.getInt("id"));
//                     p.setNaam(r.getString("naam"));
//                     p.setCategorie(r.getString("categorie"));
//                     p.setGeboortedatum(r.getDate("geboortedatum"));
//                     p.setTrainer(r.getBoolean("trainer"));
//                     p.setOpmerking(r.getString("opmerking"));
//                     ploegen.add(p);
//
//               return returnPloeg;
//            } catch (SQLException sqlEx) {
//               throw new DBException("SQL-exception in zoekAllePloegen() - resultset" + sqlEx);
//            }
//         } catch (SQLException sqlEx) {
//            throw new DBException("SQL-exception in zoekAllePloegen() - statement"+ sqlEx);
//         }
//      } catch (SQLException sqlEx) {
//         throw new DBException(
//            "SQL-exception in zoekPloeg(String naam) - connection");
//      }
//   }
   public ArrayList<PersoonBag> zoekAlleTrainers() throws DBException, ApplicationException {

      ArrayList<PersoonBag> kl = new ArrayList<>();
      // connectie tot stand brengen (en automatisch sluiten)
      try (Connection conn = ConnectionManager
              .getConnection();) {

         // preparedStatement opstellen (en automtisch sluiten)
         try (PreparedStatement stmt = conn.
            prepareStatement(
               "select id, naam, voornaam, geboortedatum,trainer,opmerking from persoon where TRAINER =true order by naam, voornaam");) {
               // execute voert elke sql-statement uit, executeQuery enkel de eenvoudige
               stmt.execute();
               // result opvragen (en automatisch sluiten)
               try (ResultSet r = stmt.getResultSet()) {
                  
                  while (r.next()) {

                     PersoonBag k = new PersoonBag();
                     k.setId(r.getInt("id"));
                     k.setNaam(r.getString("naam"));
                     k.setVoornaam(r.getString("voornaam"));
                     k.setGeboortedatum(r.getDate("geboortedatum"));
                     k.setTrainer(r.getBoolean("trainer"));
                     k.setOpmerking(r.getString("opmerking"));
                     kl.add(k);
                  }
                  return kl;
               } catch (SQLException sqlEx) {
                  throw new DBException(
                     "SQL-exception in zoekAlleTrainers - resultset"+ sqlEx);
               }
            } catch (SQLException sqlEx) {
               throw new DBException(
                  "SQL-exception in zoekAlleTrainers - statement"+ sqlEx);
            }
      } catch (SQLException sqlEx) {
         throw new DBException(
            "SQL-exception in zoekAlleTrainers - connection"+ sqlEx);
      }

   }
   
    
   
   public ArrayList<PersoonBag> zoekAlleSpelers() throws DBException, ApplicationException {

      ArrayList<PersoonBag> kl = new ArrayList<>();
      // connectie tot stand brengen (en automatisch sluiten)
      try (Connection conn = ConnectionManager
              .getConnection();) {

         // preparedStatement opstellen (en automtisch sluiten)
         try (PreparedStatement stmt = conn.
            prepareStatement(
               "select id, naam, voornaam, geboortedatum,trainer,opmerking from persoon where TRAINER = false order by naam, voornaam");) {
               // execute voert elke sql-statement uit, executeQuery enkel de eenvoudige
               stmt.execute();
               // result opvragen (en automatisch sluiten)
               try (ResultSet r = stmt.getResultSet()) {
                  
                  while (r.next()) {

                     PersoonBag k = new PersoonBag();
                     k.setId(r.getInt("id"));
                     k.setNaam(r.getString("naam"));
                     k.setVoornaam(r.getString("voornaam"));
                     k.setGeboortedatum(r.getDate("geboortedatum"));
                     k.setTrainer(r.getBoolean("trainer"));
                     k.setOpmerking(r.getString("opmerking"));
                     kl.add(k);
                  }
                  return kl;
               } catch (SQLException sqlEx) {
                  throw new DBException(
                     "SQL-exception in zoekAlleSpelers - resultset"+ sqlEx);
               }
            } catch (SQLException sqlEx) {
               throw new DBException(
                  "SQL-exception in zoekAlleSpelers - statement"+ sqlEx);
            }
      } catch (SQLException sqlEx) {
         throw new DBException(
            "SQL-exception in zoekAlleSpelers - connection"+ sqlEx);
      }

   }

   /**
     * @param ploegnaam
    * @return lijst personen die speler zijn
    * @throws exception.DBException Exception die duidt op een verkeerde
    * installatie van de database. - fout in sql-statement (zou nooit mogen
    * optreden als de database geldig is) - JDBC_ODBC-driver fout (zou nooit
    * mogen optreden als de driver goed geinstalleerd is)
     * @throws exception.ApplicationException
    *
    */
   
   public ArrayList<PersoonBag> zoekSpelersPloeg(String ploegnaam) throws DBException,ApplicationException {

      ArrayList<PersoonBag> kl = new ArrayList<>();
      // connectie tot stand brengen (en automatisch sluiten)
      try (Connection conn = ConnectionManager.getConnection();) {
         // preparedStatement opstellen (en automtisch sluiten)
         try (PreparedStatement stmt = conn.prepareStatement("select id, naam, voornaam, geboortedatum, trainer from persoon where trainer=\"false\" and id in (select speler from ploegpersoon where ploeg in(select id from ploeg where naam='?')) order by naam,voornaam");) {
            stmt.setString(1, ploegnaam);
            // execute voert elke sql-statement uit, executeQuery enkel de eenvoudige
            stmt.execute();
            // result opvragen (en automatisch sluiten)
            try (ResultSet r = stmt.getResultSet()) {
               // van alle klanten uit de database KlantBag-objecten maken
               // en in een KlantVector steken

               while (r.next()) {
                  PersoonBag k = new PersoonBag();
                  k.setId(r.getInt("id"));
                  k.setNaam(r.getString("naam"));
                  k.setVoornaam(r.getString("voornaam"));
                  k.setGeboortedatum(r.getDate("geboortedatum"));
                  k.setTrainer(r.getBoolean("trainer"));
                  kl.add(k);
               }
               return kl;
            } catch (SQLException sqlEx) {
               throw new DBException(
                  "SQL-exception in zoekAlleKlanten - resultset"+ sqlEx);
            }
         } catch (SQLException sqlEx) {
            throw new DBException("SQL-exception in zoekAlleKlanten - statement"+ sqlEx);
         }
      } catch (SQLException sqlEx) {
         throw new DBException(
            "SQL-exception in zoekAlleKlanten - connection"+ sqlEx);
      }
   }
   
   public ArrayList<PersoonBag> zoekSpelersPloeg(int id) throws DBException,ApplicationException {

      ArrayList<PersoonBag> kl = new ArrayList<>();
      // connectie tot stand brengen (en automatisch sluiten)
      try (Connection conn = ConnectionManager.getConnection();) {
         // preparedStatement opstellen (en automtisch sluiten)
         try (PreparedStatement stmt = conn.prepareStatement("select id, naam, voornaam, geboortedatum, trainer from persoon where trainer=\"false\" and id in (select speler from ploegpersoon where ploeg='?') order by naam,voornaam");) {
            stmt.setInt(1, id);
            // execute voert elke sql-statement uit, executeQuery enkel de eenvoudige
            stmt.execute();
            // result opvragen (en automatisch sluiten)
            try (ResultSet r = stmt.getResultSet()) {
               // van alle klanten uit de database KlantBag-objecten maken
               // en in een KlantVector steken

               while (r.next()) {
                  PersoonBag k = new PersoonBag();
                  k.setId(r.getInt("id"));
                  k.setNaam(r.getString("naam"));
                  k.setVoornaam(r.getString("voornaam"));
                  k.setGeboortedatum(r.getDate("geboortedatum"));
                  k.setTrainer(r.getBoolean("trainer"));
                  kl.add(k);
               }
               return kl;
            } catch (SQLException sqlEx) {
               throw new DBException(
                  "SQL-exception in zoekAlleKlanten - resultset"+ sqlEx);
            }
         } catch (SQLException sqlEx) {
            throw new DBException("SQL-exception in zoekAlleKlanten - statement"+ sqlEx);
         }
      } catch (SQLException sqlEx) {
         throw new DBException(
            "SQL-exception in zoekAlleKlanten - connection"+ sqlEx);
      }
   }

   public PersoonBag getTrainer (int ploegid) throws DBException
   {
        try (Connection conn = ConnectionManager.getConnection();) {

         // preparedStatement opstellen (en automtisch sluiten)
         try (PreparedStatement stmt = conn.
            prepareStatement(
               "select id,naam,voornaam,geboortedatum,trainer,opmerking from persoon where id in (select trainer from ploeg where id='?')");) {
               // execute voert elke sql-statement uit, executeQuery enkel de eenvoudige
               stmt.execute();
               // result opvragen (en automatisch sluiten)
               try (ResultSet r = stmt.getResultSet()) {
                  PersoonBag k = new PersoonBag();
                  while (r.next()) {

                     
                     k.setId(r.getInt("id"));
                     k.setNaam(r.getString("naam"));
                     k.setVoornaam(r.getString("voornaam"));
                     k.setGeboortedatum(r.getDate("geboortedatum"));
                     k.setTrainer(r.getBoolean("trainer"));
                     k.setOpmerking(r.getString("opmerking"));
                     
                  }
                  return k;
               } catch (SQLException sqlEx) {
                  throw new DBException(
                     "SQL-exception in zoekIngeschrevenKlanten - resultset"+ sqlEx);
               }
            } catch (SQLException sqlEx) {
               throw new DBException(
                  "SQL-exception in zoekIngeschrevenKlanten - statement"+ sqlEx);
            }
      } catch (SQLException sqlEx) {
         throw new DBException(
            "SQL-exception in zoekIngeschrevenKlanten - connection"+ sqlEx);
      }
   }
   
    public PersoonBag getTrainer (String ploegnaam) throws DBException
   {
        try (Connection conn = ConnectionManager.getConnection();) {

         // preparedStatement opstellen (en automtisch sluiten)
         try (PreparedStatement stmt = conn.
            prepareStatement(
               "select id,naam,voornaam,geboortedatum,trainer,opmerking from persoon where id in (select trainer from ploeg where id in (select id from ploeg where naam='?'))");) {
               // execute voert elke sql-statement uit, executeQuery enkel de eenvoudige
               stmt.execute();
               // result opvragen (en automatisch sluiten)
               try (ResultSet r = stmt.getResultSet()) {
                  PersoonBag k = new PersoonBag();
                  while (r.next()) {

                     
                     k.setId(r.getInt("id"));
                     k.setNaam(r.getString("naam"));
                     k.setVoornaam(r.getString("voornaam"));
                     k.setGeboortedatum(r.getDate("geboortedatum"));
                     k.setTrainer(r.getBoolean("trainer"));
                     k.setOpmerking(r.getString("opmerking"));
                     
                  }
                  return k;
               } catch (SQLException sqlEx) {
                  throw new DBException(
                     "SQL-exception in zoekIngeschrevenKlanten - resultset"+ sqlEx);
               }
            } catch (SQLException sqlEx) {
               throw new DBException(
                  "SQL-exception in zoekIngeschrevenKlanten - statement"+ sqlEx);
            }
      } catch (SQLException sqlEx) {
         throw new DBException(
            "SQL-exception in zoekIngeschrevenKlanten - connection"+ sqlEx);
      }
   }
   
   
   public void verwijderPersoon(int id) throws DBException {

      // connectie tot stand brengen (en automatisch sluiten)
      try (Connection conn = ConnectionManager.getConnection();) {
         // preparedStatement opstellen (en automtisch sluiten)
        
          try (PreparedStatement stmt = conn.prepareStatement(
            "DELETE FROM ploegpersoon WHERE speler=?");) {
            stmt.setInt(1, id);
            // execute voert elke sql-statement uit, executeQuery enkel de select
            stmt.execute();
         }
          try (PreparedStatement stmt = conn.prepareStatement(
            "update ploeg set trainer=null WHERE trainer=?");) {
            stmt.setInt(1, id);
            // execute voert elke sql-statement uit, executeQuery enkel de select
            stmt.execute();
            
         } 
          try (PreparedStatement stmt = conn.prepareStatement(
            "DELETE FROM persoon WHERE id=?");) {
            stmt.setInt(1, id);
            // execute voert elke sql-statement uit, executeQuery enkel de select
            stmt.execute();
            
         }
          catch (SQLException sqlEx) {
            throw new DBException("SQL-exception in verwijderPersoon(int id) - statement"+ sqlEx);
         }
      } catch (SQLException sqlEx) {
         throw new DBException(
            "SQL-exception in verwijderPersoon(int id) - connection"+ sqlEx);
      }
   }
   
   public void verwijderPersoon(String naam,String voornaam) throws DBException {

      // connectie tot stand brengen (en automatisch sluiten)
      try (Connection conn = ConnectionManager.getConnection();) {
         // preparedStatement opstellen (en automtisch sluiten)
        
          try (PreparedStatement stmt = conn.prepareStatement(
            "DELETE FROM ploegpersoon WHERE speler in (select id from persoon where naam=? and voornaam=?)");) {
            stmt.setString(1, naam);
            stmt.setString(2, voornaam);
            // execute voert elke sql-statement uit, executeQuery enkel de select
            stmt.execute();
         }
          try (PreparedStatement stmt = conn.prepareStatement(
            "update ploeg set trainer=null WHERE trainer in (select id from persoon where naam=? and voornaam=?)");) {
            stmt.setString(1, naam);
            stmt.setString(2, voornaam);
            // execute voert elke sql-statement uit, executeQuery enkel de select
            stmt.execute();
          }
          try (PreparedStatement stmt = conn.prepareStatement(
            "DELETE FROM persoon WHERE naam=? and voornaam=?");) {
            stmt.setString(1, naam);
            stmt.setString(2, voornaam);
            // execute voert elke sql-statement uit, executeQuery enkel de select
            stmt.execute();
         } 
          
          catch (SQLException sqlEx) {
            throw new DBException("SQL-exception in verwijderPersoon(String naam,String voornaam) - statement"+ sqlEx);
         }
      } catch (SQLException sqlEx) {
         throw new DBException(
            "SQL-exception in verwijderPersoon(String naam,String voornaam) - connection"+ sqlEx);
      }
   }
   
   public void verwijderPersoon(PersoonBag p) throws DBException {

      // connectie tot stand brengen (en automatisch sluiten)
      try (Connection conn = ConnectionManager.getConnection();) {
         // preparedStatement opstellen (en automtisch sluiten)
        
          try (PreparedStatement stmt = conn.prepareStatement(
            "DELETE FROM ploegpersoon WHERE speler=?");) {
            stmt.setInt(1, p.getId());
            
            // execute voert elke sql-statement uit, executeQuery enkel de select
            stmt.execute();
         }
          try (PreparedStatement stmt = conn.prepareStatement(
            "update ploeg set trainer=null WHERE trainer=?");) {
            stmt.setInt(1, p.getId());
            // execute voert elke sql-statement uit, executeQuery enkel de select
            stmt.execute();
          }
          try (PreparedStatement stmt = conn.prepareStatement(
            "DELETE FROM persoon WHERE id=?);")) {
            stmt.setInt(1, p.getId());
            // execute voert elke sql-statement uit, executeQuery enkel de select
            stmt.execute();
         } 
          
          catch (SQLException sqlEx) {
            throw new DBException("SQL-exception in verwijderPersoon(String naam,String voornaam) - statement"+ sqlEx);
         }
      } catch (SQLException sqlEx) {
         throw new DBException(
            "SQL-exception in verwijderPersoon(String naam,String voornaam) - connection"+ sqlEx);
      }
   }

   public void toevoegenPersoon(PersoonBag p) throws DBException {

      // connectie tot stand brengen (en automatisch sluiten)
      try (Connection conn = ConnectionManager.getConnection();) {
         // preparedStatement opstellen (en automtisch sluiten)
         try (PreparedStatement stmt = conn.prepareStatement(
            "INSERT INTO `persoon` (Naam, Voornaam, Geboortedatum, TRAINER, Opmerking) VALUES (?,?,?,?,?);");) {
            stmt.setString(1, p.getNaam());
            stmt.setString(2, p.getVoornaam());
            stmt.setDate(3,new java.sql.Date(p.getGeboortedatum().getTime()) );
            stmt.setBoolean(4, p.getTrainer());
            stmt.setString(5,p.getOpmerking());
            stmt.execute();

            
         } catch (SQLException sqlEx) {
            throw new DBException("SQL-exception in toevoegenPersoon(PersoonBag p) - statement "+ sqlEx);
         }
      } catch (SQLException sqlEx) {
         throw new DBException(
            "SQL-exception in toevoegenPersoon(PersoonBag p) - connection "+ sqlEx);
      }

   }
   
   public void toevoegenPloeg(PloegBag p) throws DBException {

      // connectie tot stand brengen (en automatisch sluiten)
      try (Connection conn = ConnectionManager.getConnection();) {
         // preparedStatement opstellen (en automtisch sluiten)
         try (PreparedStatement stmt = conn.prepareStatement(
            "INSERT INTO ploeg (`Naam`, `Categorie`, `trainer`) VALUES (?,?,?)");) {
            stmt.setString(1, p.getNaam());
            stmt.setString(2, p.getCategorie().getTekst());
            stmt.setInt(3,p.getTrainer());
            
            stmt.execute();

            
         } catch (SQLException sqlEx) {
            throw new DBException("SQL-exception in toevoegenPloeg(PloegBag p) - statement"+ sqlEx);
         }
      } catch (SQLException sqlEx) {
         throw new DBException(
            "SQL-exception in toevoegenPloeg(PloegBag p) - connection"+ sqlEx);
      }

   }

   public void toevoegenSpelerPloeg(int ploegid,PersoonBag p) throws DBException
   {
       // connectie tot stand brengen (en automatisch sluiten)
      try (Connection conn = ConnectionManager.getConnection();) {
         // preparedStatement opstellen (en automtisch sluiten)
         try (PreparedStatement stmt = conn.prepareStatement(
            "INSERT INTO ploegpersoon (ploeg, speler) VALUES (?,?);");) {         
            stmt.setInt(1, ploegid);
            stmt.setInt(2, p.getId());
            
            stmt.execute();

            
         } catch (SQLException sqlEx) {
            throw new DBException("SQL-exception in toevoegenSpelerPloeg(int ploegid,PersoonBag p) - statement"+ sqlEx);
         }
      } catch (SQLException sqlEx) {
         throw new DBException(
            "SQL-exception in toevoegenSpelerPloeg(int ploegid,PersoonBag p) - connection"+ sqlEx);
      }
   }
   
   public void toevoegenSpelerPloeg(String ploegnaam,PersoonBag p) throws DBException
   {
       // connectie tot stand brengen (en automatisch sluiten)
      try (Connection conn = ConnectionManager.getConnection();) {
         // preparedStatement opstellen (en automtisch sluiten)
         try (PreparedStatement stmt = conn.prepareStatement(
            "INSERT INTO ploegpersoon (`speler`,'ploeg') VALUES (?,select id from ploeg where naam=?);");) {
            stmt.setInt(1, p.getId());
            stmt.setString(2, ploegnaam);
            
            stmt.execute();

            
         } catch (SQLException sqlEx) {
            throw new DBException("SQL-exception in toevoegenSpelerPloeg(String naam,PersoonBag p) - statement"+ sqlEx);
         }
      } catch (SQLException sqlEx) {
         throw new DBException(
            "SQL-exception in toevoegenSpelerPloeg(String naam,PersoonBag p) - connection"+ sqlEx);
      }
   }
   
   public void toevoegenSpelerPloeg(PloegBag ploeg,PersoonBag persoon) throws DBException
   {
       // connectie tot stand brengen (en automatisch sluiten)
      try (Connection conn = ConnectionManager.getConnection();) {
         // preparedStatement opstellen (en automtisch sluiten)
         try (PreparedStatement stmt = conn.prepareStatement(
            "INSERT INTO ploegpersoon (speler,ploeg) VALUES (?,?);");) {
            stmt.setInt(1, persoon.getId());
            stmt.setInt(2, ploeg.getId());
            
            stmt.execute();

            
         } catch (SQLException sqlEx) {
            throw new DBException("SQL-exception in toevoegenSpelerPloeg(PloegBag ploeg,PersoonBag persoon) - statement"+ sqlEx);
         }
      } catch (SQLException sqlEx) {
         throw new DBException(
            "SQL-exception in toevoegenSpelerPloeg(PloegBag ploeg,PersoonBag persoon) - connection"+ sqlEx);
      }
   }
   
   public void verwijderSpelerPloeg(int id) throws DBException {

      // connectie tot stand brengen (en automatisch sluiten)
      try (Connection conn = ConnectionManager.getConnection();) {
         // preparedStatement opstellen (en automtisch sluiten)
        
          try (PreparedStatement stmt = conn.prepareStatement(
            "DELETE FROM ploegpersoon WHERE persoon='?'");) {
            stmt.setInt(1, id);
            // execute voert elke sql-statement uit, executeQuery enkel de select
            stmt.execute();
         }
          catch (SQLException sqlEx) {
            throw new DBException("SQL-exception in verwijderKlant - statement"+ sqlEx);
         }
      } catch (SQLException sqlEx) {
         throw new DBException(
            "SQL-exception in verwijderKlant - connection"+ sqlEx);
      }
   }
   
   public void verwijderSpelerPloeg(PersoonBag p) throws DBException {

      // connectie tot stand brengen (en automatisch sluiten)
      try (Connection conn = ConnectionManager.getConnection();) {
         // preparedStatement opstellen (en automtisch sluiten)
        
          try (PreparedStatement stmt = conn.prepareStatement(
            "DELETE FROM ploegpersoon WHERE persoon='?'");) {
            stmt.setInt(1, p.getId());
            // execute voert elke sql-statement uit, executeQuery enkel de select
            stmt.execute();
         }
          catch (SQLException sqlEx) {
            throw new DBException("SQL-exception in verwijderKlant - statement"+ sqlEx);
         }
      } catch (SQLException sqlEx) {
         throw new DBException(
            "SQL-exception in verwijderKlant - connection"+ sqlEx);
      }
   }
   public void verwijderSpelerPloeg(String naam,String voornaam) throws DBException {

      // connectie tot stand brengen (en automatisch sluiten)
      try (Connection conn = ConnectionManager.getConnection();) {
         // preparedStatement opstellen (en automtisch sluiten)
        
          try (PreparedStatement stmt = conn.prepareStatement(
            "DELETE FROM ploegpersoon WHERE persoon=(select id from persoon where naam='?' and voornaam='?')");) {
            stmt.setString(1, naam);
            stmt.setString(2, voornaam);
            // execute voert elke sql-statement uit, executeQuery enkel de select
            stmt.execute();
         }
          catch (SQLException sqlEx) {
            throw new DBException("SQL-exception in verwijderKlant - statement"+ sqlEx);
         }
      } catch (SQLException sqlEx) {
         throw new DBException(
            "SQL-exception in verwijderKlant - connection"+ sqlEx);
      }
   }
   public void toevoegenTrainerPloeg(int persoonid,int ploegid) throws DBException
   {
        // connectie tot stand brengen (en automatisch sluiten)
      try (Connection conn = ConnectionManager.getConnection();) {
         // preparedStatement opstellen (en automtisch sluiten)
         try (PreparedStatement stmt = conn.prepareStatement(
            "UPDATE ploeg set trainer='?' where id='?'");) {
            stmt.setInt(1, persoonid);
            stmt.setInt(2, ploegid);
            
            stmt.execute();

            
         } catch (SQLException sqlEx) {
            throw new DBException("SQL-exception in toevoegenTrainerPloeg(int persoonid,int ploegid) - statement"+ sqlEx);
         }
      } catch (SQLException sqlEx) {
         throw new DBException(
            "SQL-exception in toevoegenTrainerPloeg(int persoonid,int ploegid) - connection"+ sqlEx);
      }
   }
   
   public void toevoegenTrainerPloeg(PersoonBag persoon,PloegBag ploeg) throws DBException
   {
        // connectie tot stand brengen (en automatisch sluiten)
      try (Connection conn = ConnectionManager.getConnection();) {
         // preparedStatement opstellen (en automtisch sluiten)
         try (PreparedStatement stmt = conn.prepareStatement(
            "UPDATE ploeg set trainer='?' where id='?'");) {
            stmt.setInt(1, persoon.getId());
            stmt.setInt(2, ploeg.getId());
            
            stmt.execute();

            
         } catch (SQLException sqlEx) {
            throw new DBException("SQL-exception in toevoegenTrainerPloeg(PersoonBag persoon,PloegBag ploeg) - statement"+ sqlEx);
         }
      } catch (SQLException sqlEx) {
         throw new DBException(
            "SQL-exception in toevoegenTrainerPloeg(PersoonBag persoon,PloegBag ploeg) - connection"+ sqlEx);
      }
   }
   public void toevoegenTrainerPloeg(String naam, String voornaam,String ploegnaam) throws DBException
   {
        // connectie tot stand brengen (en automatisch sluiten)
      try (Connection conn = ConnectionManager.getConnection();) {
         // preparedStatement opstellen (en automtisch sluiten)
         try (PreparedStatement stmt = conn.prepareStatement(
            "UPDATE ploeg set trainer=(select id from persoon where naam='?' and voornaam='?') where id=(select id from ploeg where naam='?')");) {
            stmt.setString(1, naam);
            stmt.setString(2, voornaam);
            stmt.setString(3, ploegnaam);
            
            stmt.execute();

            
         } catch (SQLException sqlEx) {
            throw new DBException("SQL-exception in toevoegenTrainerPloeg(String naam, String voornaam,String ploegnaam) - statement"+ sqlEx);
         }
      } catch (SQLException sqlEx) {
         throw new DBException(
            "SQL-exception in toevoegenTrainerPloeg(String naam, String voornaam,String ploegnaam) - connection"+ sqlEx);
      }
   }
   
   public void verwijderTrainerPloeg(int ploegid)throws DBException
   {
       
        // connectie tot stand brengen (en automatisch sluiten)
      try (Connection conn = ConnectionManager.getConnection();) {
         // preparedStatement opstellen (en automtisch sluiten)
         try (PreparedStatement stmt = conn.prepareStatement(
            "UPDATE ploeg set trainer=null where id=?");) {
            stmt.setInt(1, ploegid);
            
            stmt.execute();

            
         } catch (SQLException sqlEx) {
            throw new DBException("SQL-exception in verwijderTrainerPloeg((int ploegid)) - statement"+ sqlEx);
         }
      } catch (SQLException sqlEx) {
         throw new DBException(
            "SQL-exception in verwijderTrainerPloeg(int ploegid) - connection"+ sqlEx);
      }
   }
   
    public void verwijderTrainerPloeg(String ploegnaam)throws DBException
   {
       
        // connectie tot stand brengen (en automatisch sluiten)
      try (Connection conn = ConnectionManager.getConnection();) {
         // preparedStatement opstellen (en automtisch sluiten)
         try (PreparedStatement stmt = conn.prepareStatement(
            "UPDATE ploeg set trainer=null where naam=?");) {
            stmt.setString(1, ploegnaam);
            
            stmt.execute();

            
         } catch (SQLException sqlEx) {
            throw new DBException("SQL-exception in verwijderTrainerPloeg((int ploegid)) - statement"+ sqlEx);
         }
      } catch (SQLException sqlEx) {
         throw new DBException(
            "SQL-exception in verwijderTrainerPloeg(int ploegid) - connection"+ sqlEx);
      }
   }
   public void wijzigenPersoon(PersoonBag p) throws DBException {

      // connectie tot stand brengen (en automatisch sluiten)
      try (Connection conn = ConnectionManager.getConnection();) {
         // preparedStatement opstellen (en automtisch sluiten)
         try (PreparedStatement stmt = conn.
            prepareStatement("update persoon set naam = ?, "
               + "voornaam =?, "
               + "geboortedatum = ?, "
               + "trainer = ?, "
               + "where id = ?");) {

            stmt.setString(1, p.getNaam());
            stmt.setString(2, p.getVoornaam());
            stmt.setDate(3, new java.sql.Date(p.getGeboortedatum().getTime()));
            stmt.setBoolean(4, p.getTrainer());
            stmt.setInt(6, p.getId());

            // execute voert elke sql-statement uit, executeQuery enkel de select
            stmt.execute();
         } catch (SQLException sqlEx) {
            throw new DBException("SQL-exception in wijzigenKlant"+ sqlEx);
         }
      } catch (SQLException sqlEx) {
         throw new DBException(
            "SQL-exception in wijzigenKlant - connection"+ sqlEx);
      }
   }
   
}
