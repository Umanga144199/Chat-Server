import java.net.Socket;


public class Client {

		private String userName;
		private Socket socket;
	
		public Client(String userName, Socket socket) {
		
			this.userName = userName;
			this.socket = socket;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public Socket getSocket() {
			return socket;
		}

		public void setSocket(Socket socket) {
			this.socket = socket;
		}

		
		
}
