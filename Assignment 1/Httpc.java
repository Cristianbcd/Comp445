import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

public class Httpc {

	public static void main(String[] args) {

				
		 getExample();
		 //post1Example();
		//post2Example();
		//post3Example();
		//getRedirectExample();
	}
	
	public static void httpc(String path, String host, String query) {
		try {
			Socket socket = new Socket(host, 80);
			
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			
			String request = "";
			if(query == null) {
				request = "GET " + path + " HTTP/1.0\r\n\r\n";
			} else {
				request = "GET " + path + "?" +query +" HTTP/1.0\r\n\r\n";
			}

			//http://postman-echo.com:100/wikypedia/article/1?key=value
			
			outputStream.write(request.getBytes());
			outputStream.flush();
			
			StringBuilder response = new StringBuilder();
			
			int data = inputStream.read();
			
			while(data != -1) {
				response.append((char) data);
				data = inputStream.read();
			}
			
			System.out.println(request);
			System.out.println(response);
			socket.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void post1Example() {
		try {
			Socket socket = new Socket("httpbin.org", 80);
			
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			
			String body = "key1=value1&key2=value2";
			
			String request = "POST /post HTTP/1.0\r\n"
							+ "Content-Type:application/x-www-form-urlencoded\r\n"
							+ "Content-Length: " + body.length() +"\r\n"
							+ "\r\n"
							+ body;
			
			outputStream.write(request.getBytes());
			outputStream.flush();
			
			StringBuilder response = new StringBuilder();
			
			int data = inputStream.read();
			
			while(data != -1) {
				response.append((char) data);
				data = inputStream.read();
			}
			
			System.out.println(response);
			socket.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void post2Example() {
		try {
			Socket socket = new Socket("httpbin.org", 80);
			
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			
			String body = "--limit\r\n"
							+ "Content-Disposition: form-data; name=\"key1\"\r\n"
							+ "\r\n"
							+ "value1\r\n"
							+ "--limit\r\n"
							+ "Content-Disposition: form-data; name=\"key2\"\r\n"
							+ "\r\n"
							+ "value2\r\n"
							+ "--limit--\r\n";
			
			String request = "POST /post HTTP/1.0\r\n"
							+ "Content-Type:multipart/form-data; boundary=\"limit\"\r\n"
							+ "Content-Length: "+ body.length() +"\r\n"
							+ "\r\n"
							+ body;
			
			outputStream.write(request.getBytes());
			outputStream.flush();
			
			StringBuilder response = new StringBuilder();
			
			int data = inputStream.read();
			
			while(data != -1) {
				response.append((char) data);
				data = inputStream.read();
			}
			
			System.out.println(response);
			socket.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void post3Example() {
		try {
			Socket socket = new Socket("httpbin.org", 80);
			
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			
			String body = "{"
							+ "\"key1\":value1,"
							+ "\"key2\":value2"
							+ "}";
			
			String request = "POST /post?info=info HTTP/1.0\r\n"
							+ "Content-Type:application/json\r\n"
							+ "Content-Length: " + body.length() +"\r\n"
							+ "\r\n"
							+ body;
			
			outputStream.write(request.getBytes());
			outputStream.flush();
			
			StringBuilder response = new StringBuilder();
			
			int data = inputStream.read();
			
			while(data != -1) {
				response.append((char) data);
				data = inputStream.read();
			}
			
			System.out.println(response);
			socket.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getExample() {
		try {
			Socket socket = new Socket("127.0.0.1", 3000);
			
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			
			String request = "GET " +  "/status/418 HTTP/1.0\r\n\r\n";
			
			//http://postman-echo.com:100/wikypedia/article/1?key=value
			
			outputStream.write(request.getBytes());
			outputStream.flush();
			StringBuilder response = new StringBuilder();
			
			int data = inputStream.read();
			
			while(data != -1) {
				response.append((char) data);
				data = inputStream.read();
			}
			
			System.out.println(response);
			socket.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getRedirectExample() {
		try {
			Socket socket = new Socket("google.com", 80);
			
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			
			String request = "GET / HTTP/1.0\r\nHost: www.google.com\r\n\r\n";
			//String request = "GET / HTTP/1.0\r\n\r\n";
			
			outputStream.write(request.getBytes());
			outputStream.flush();
			
			StringBuilder response = new StringBuilder();
			
			int data = inputStream.read();
			
			while(data != -1) {
				response.append((char) data);
				data = inputStream.read();
			}
			
			System.out.println(response);
			socket.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}