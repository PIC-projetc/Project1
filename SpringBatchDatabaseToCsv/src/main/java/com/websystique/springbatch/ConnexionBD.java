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
	   public ResultSet r�sultat;
	   
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
	         System.err.println("Probl�me de pilote"+ex.getMessage());
		   }
	   }
	   
	   public void lire(String requ�te) 
	   {
		   try 
		   {
			   r�sultat = instruction.executeQuery(requ�te);      
	       } 
	       catch (SQLException ex) 
	       {
	    	   System.err.println("Requ�te incorrecte "+requ�te);
	       }
	   }
	   
	   public void miseAJour(String requ�te) 
	   {
	       	try 
	       	{         
	       		instruction.executeUpdate(requ�te);      
	       	} 
	       	catch (SQLException ex) 
	       	{
	       		System.err.println("Requ�te incorrecte "+requ�te);
	       	}
	   }
	   
	   public boolean suivant() 
	   {
	       	try 
	       	{
	       		return r�sultat.next();
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
		   		res=r�sultat.getString(str);
		   	} 
		   	catch (SQLException e)
		   	{			
		   		e.printStackTrace();
		   	}
		   return res;
	   }
	   public void arr�t()
	   {
		   try 
		   {
			   connexion.close();
		   } 
		   catch (SQLException ex) 
		   {
			   System.err.println("Erreur sur l'arr�t de la connexion � la base de donn�es");
		   }
	   }
	}

