package com.websystique.springbatch;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnexionBD 
{
	   public Connection connexion;
	   public Statement instruction;
	   public ResultSet résultat;
	   
	   public ConnexionBD() 
	   {
		   try 
		   {
			   Class.forName("com.mysql.jdbc.Driver").newInstance();
			   connexion = DriverManager.getConnection("jdbc:mysql://localhost/websystique", "root", "");
			   instruction = connexion.createStatement();
		   } 
		   catch (Exception ex) 
		   {
	         System.err.println("Problème de pilote"+ex.getMessage());
		   }
	   }
	   
	   public void lire(String requête) 
	   {
		   try 
		   {
			   résultat = instruction.executeQuery(requête);      
	       } 
	       catch (SQLException ex) 
	       {
	    	   System.err.println("Requête incorrecte "+requête);
	       }
	   }
	   
	   public void miseAJour(String requête) 
	   {
	       	try 
	       	{         
	       		instruction.executeUpdate(requête);      
	       	} 
	       	catch (SQLException ex) 
	       	{
	       		System.err.println("Requête incorrecte "+requête);
	       	}
	   }
	   
	   public boolean suivant() 
	   {
	       	try 
	       	{
	       		return résultat.next();
	       	} 
	       	catch (SQLException ex) 
	       	{
	       		return false;
	       	}
	   }
	   public String getString(String str)
	   {
		   	String res="";
		   	try 
		   	{
		   		res=résultat.getString(str);
		   	} 
		   	catch (SQLException e)
		   	{			
		   		e.printStackTrace();
		   	}
		   return res;
	   }
	   public void arrêt()
	   {
		   try 
		   {
			   connexion.close();
		   } 
		   catch (SQLException ex) 
		   {
			   System.err.println("Erreur sur l'arrêt de la connexion à la base de données");
		   }
	   }
	}

