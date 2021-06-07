package database;
import java.sql.*;
import javax.swing.JOptionPane;
public class BD {
	
	public Connection c = null;
	private final String driver = "com.mysql.jdbc.Driver";
	private final String dbname = "db_jogo";
	private final String url = "jdbc:mysql://localhost:3306/"+dbname;
	private final String login = "root";
	private final String senha = "";
	private Statement statement;
	private Connection connection;
	
	public boolean getConnection(){
		
	
		
		try{
			Class.forName(driver);
			c = DriverManager.getConnection(url,login,senha);
			return true;
		}
		catch (ClassNotFoundException erro){
			JOptionPane.showMessageDialog(null,"Driver não encontrado\n" + erro.toString());
			return false;
		}
		catch (SQLException erro){		
			
			JOptionPane.showMessageDialog(null,"Problema de conexão com a fonte de dados\n" + erro.toString());
			return false;
		}
		
	}
	public void close(){
		try{
			c.close();
			System.out.println("Desconectou");
		}catch(SQLException erro){
			
		}
	}
	
}
