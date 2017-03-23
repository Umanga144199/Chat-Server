import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;


public class ClientListener extends Thread {
	private Socket socket;
	private BufferedReader reader;
	private PrintStream ps;
	private Client client;
	private ClientHandler handler;
	
	
	public ClientListener(Socket socket, ClientHandler handler) throws IOException {
		this.socket = socket;
		ps = new PrintStream(socket.getOutputStream());
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.handler = handler;
	}
	@Override
	public void run() {
		try{
			doLogin();String line="";
			while(!(line = reader.readLine()).equalsIgnoreCase("Exit")){
				String[] tokens = line.split(";;");
				if(tokens[0].equalsIgnoreCase("list")){
					StringBuilder friends = new StringBuilder();
					for(Client c:handler.getClients()){
					friends.append(c.getUserName()).append("\r\n");
					}
					privateMessage(client, friends.toString());
					
				}if(tokens[0].equalsIgnoreCase("pm")){	
					if(tokens.length>-3){
						Client c = handler.getByUserName(tokens[1]);
						if(c!=null){
							String msg= "PM from" + client.getUserName() + " says>" + tokens[2];
							privateMessage(c, msg);
						}
					}
				}else{
				String message=(client.getUserName() + " says >" + line);
				broadcastMessage(message);
			}
		}
		
		}catch(IOException ioe){
		
	}
	
	}
	private void doLogin() throws IOException{
		ps.println("Welcome to the QFX:");
		ps.println("Enter your name");
		String username = reader.readLine();
		System.out.println(username);
		client= new Client(username, socket);
		handler.addClient(client);
		ps.println("Thank you "+ username);
	}
	
	public void broadcastMessage(String message) throws IOException{
		for(Client c: handler.getClients()){
			if(!c.equals(client)){
			PrintStream ps = new PrintStream(c.getSocket().getOutputStream());
			ps.println(message);
		}
	}
	}
	
	public void privateMessage(Client client, String message) throws IOException{
		PrintStream ps = new PrintStream(client.getSocket().getOutputStream());
		ps.println(message);
	}
}
