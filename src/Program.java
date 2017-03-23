import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Program {
		
	public static void main(String[] args){
		int port = 9000;
		try{
		ServerSocket server = new ServerSocket(port);
		ClientHandler handler = new ClientHandler();
		System.out.println("Server is running at "+ port);
		while(true){
			Socket socket = server.accept();
			ClientListener listener = new ClientListener(socket, handler);
			listener.start();
			System.out.println("Connection request from " + socket.getInetAddress());
		
		}
		
		
		}catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
		}
}
