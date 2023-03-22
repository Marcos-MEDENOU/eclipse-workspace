package bj.highfive.usermanagment.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bj.highfive.usermanagment.bean.User;

public class UserDao {

	/****
	 * CREAD ==> crée une ressources (createUser)
	 * READ ==>Lire une ressource (getUserById),(getAllUser)
	 * UPDATE==>maj une ressource
	 * DELETE==>supprimer une ressource
	 */
	
	public Connection getConnection() {
		Connection con = null;
		
		//spécifier le driver 
		try {
			Class.forName("com.mysql.jdbc.Driver");//database de type mysql
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_app", "root", "");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//getConnexion est la methode qui se connecte a la bd grace a la DriverManager
		/***
		 * getConnection(dsn,username,password);
		 * dsn=data source name
		 * exp php: $conn= new PDO("mysql:host=localhost; dbname=user_app;port=3306;charset=UTF-8");
		 * $conn= new PDO($dsn, "root","");
		 **/	
		return con;
		
		
	}
	
	public boolean createUser(User user){
		boolean result=false;
		
		//etaoe: recuperation de l'objet de connection
		
				Connection connection = getConnection();
		try {
		
		PreparedStatement ps = connection.prepareStatement("INSERT INTO `user_app`.users (`name`, `email`,`country`) VALUES (?,?,?)");
		
		ps.setString(1,user.getName());
		ps.setString(2, user.getEmail());
		ps.setString(3,user.getCountry());
		
		result= ps.execute();
		
		connection.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result; 
	}
	
	
	
}
