import java.util.ArrayList;
import java.util.List;


public class ClientHandler {
	
	private List<Client> clients = new ArrayList<>();
	
	public void addClient(Client c){
		clients.add(c);
	}
	
	public List<Client> getClients(){
		return clients;
	}
	
	public Client getByUserName(String userName){
		for(Client c: clients){
			if(c.getUserName().equalsIgnoreCase(userName)){
				return c;
			}
		}
		return null;
	}
}
