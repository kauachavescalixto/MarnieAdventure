package marnie.graphics;

import database.UsersDAO;

public class PreGameConnection {

	public UsersDAO usersdao;
	
	public PreGameConnection() {
		
		usersdao = new UsersDAO();
		
	}
	
}
