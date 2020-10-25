import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*Http server example.
Please remember, this is just an example there are better and faster ways to code the server
Use it at your own risk
*/

/*
netstat -tulpn
kill <pid>
*/

public class Server {

	public static void main(String[] args) {
		
		int port = 3001;
		
		try {
			int clientNumber = 1;
			ServerSocket server = new ServerSocket(port);
			
			System.out.println("Server is listening at port " + port);
			Socket clientSocket  = server.accept();
			System.out.println("Server accepted a connection - Handling client");
			
			InputStream inputStream = clientSocket.getInputStream();
			OutputStream outputStream = clientSocket.getOutputStream();
			
			StringBuilder request = new StringBuilder();
			
			int data = inputStream.read();
			int counter = 0;
			
			while(data != -1) {
				if(((char) data) == '\r' || ((char) data) == '\n') {
					counter++;
					if (counter == 4)
						break;
				} else {
					counter = 0;
				}
				request.append((char) data);
				data = inputStream.read();
			}
			
			System.out.println("\nRequest From client");
			System.out.println(request);
			
			//String body = "{\"Client\" : \"" + (clientNumber++) + "\"}";
			String body = "<!DOCTYPE html><html><body><h2>Assignment 2 Server Example</h2>"
					+ "<p> You are client number: " + (clientNumber++) +"</p>"
					+ "<button>Click me</button>"
					+ "</body>"
					+ "</html>";

			String response = 	"HTTP/1.0 200 ok\r\n"
							 	+ "Content-Length: " + body.length() + "\r\n"
							 	//+ "Content-Disposition: inline "\r\n"
							 	//+ "Content-Disposition: attachment; filename=\"ThisWorks.json\"" + "\r\n"
								//+ "Content-Type: application/json\r\n\r\n"
								+ "Content-Type: text/html\r\n\r\n"
			 					+ body;
			
			System.out.println("Response sent to client\n" + response);
			
			outputStream.write(response.getBytes());
			outputStream.flush();
			clientSocket.close();
			server.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

//Supporint Multiple Clients
//One thread per each client connection
//While(true){
	//accept a connection;
	//create a thread to deal with the client;
	//Manage read/write opeartions prroperly;
//}
