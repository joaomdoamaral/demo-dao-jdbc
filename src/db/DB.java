package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB
{
	private static Connection conn = null;
	
	public static Connection getConnection() // metodo pra gerar conexão com o banco de dados
	{
		if (conn == null)
		{
			try
			{
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props);
			}
			catch (SQLException e)
			{
				throw new DbException(e.getMessage());
			}
			
		}
		return conn;
	}
	
	
	public static void closeConnection() //metodo para fechar a conexao
	{
		if (conn != null)
		{
			try
			{
				conn.close();
			}
			catch (SQLException e)
			{
				throw new DbException(e.getMessage());
			}
		}
	}
	
	private static Properties loadProperties() //Metodo pra carregar as propriedades do dp.proprierts
	{
		try (FileInputStream fs = new FileInputStream("db.properties"))
		{
			Properties props = new Properties();
			props.load(fs);
			return props;
		}
		catch (IOException e)
		{
			throw new DbException(e.getMessage());
		}
		
	}
	
	public static void closeStatmente(Statement st)
	{
		if (st != null) {
			try {
				st.close();
			}catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	public static void closeResultSet(ResultSet rs)
	{
		if (rs != null) {
			try {
				rs.close();
			}catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
}
