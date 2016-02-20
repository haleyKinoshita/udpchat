import java.io.*;
import java.net.*;

class UDPClient {
 	public static void main(String args[]) throws Exception {
 		
 		//open the socket and connect to the IP Address of the server
 		DatagramSocket clientSocket = new DatagramSocket();
 		InetAddress IPAddress = InetAddress.getByName("localhost");

 		//send the first message to the server
 		String initiate = "HELLO Blue";
 		byte[] initiateData = new byte[1024];
 		initiateData = initiate.getBytes();

 		DatagramPacket initiatePacket = new DatagramPacket(initiateData, initiateData.length, IPAddress, /*some socket address*/);
 		clientSocket.sendPacket(initiatePacket);
 		
 		//receive a message and put it into a string
 		String firstMessage;
 		boolean firstMessageBool = false;

 		while (true) {
			
			//receive a message and put it into a string
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			clientSocket.receive(receivePacket);
			String receivedMessge = new String(receivePacket.getData());
 		
 			if (receivedMessge.equals("100")) {
 				firstMessage = "100";
 				firstMessageBool = true;
 				System.out.println("100 Received.");
 				continue;
 			}

 			else if (receivedMessge.equals("Goodbye")) {
 				System.out.println("Goodbye");
 				break;
 			}

 			else if (receivedMessge.equals("200") && firstMessageBool) {
 				/*send first message*/
 			}

 			else {

 			}


			BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
 			
			byte[] sendData = new byte[1024];
			byte[] receiveData = new byte[1024];

 		}
		
		String sentence = inFromUser.readLine();
		sendData = sentence.getBytes();
		
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, /*some socket address*/);

		clientSocket.send(sendPacket);
		
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		
		clientSocket.receive(receivePacket);
		
		String modifiedSentence = new String(receivePacket.getData());
		
		System.out.println("FROM SERVER:" + modifiedSentence);
		clientSocket.close();
	}	
}