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

 		sendMessage("HELLO Blue", );
 		
 		//receive a message and put it into a string
 		String firstMessage;
 		boolean firstMessageBool = false;

 		DatagramPacket receivePacket;
 		String receivedMessge;
 		String sentence;
 		BufferedReader inFromUser;

 		while (true) {
			
			//receive a message and put it into a string
			receivePacket = new DatagramPacket(receiveData, receiveData.length);
			clientSocket.receive(receivePacket);
			receivedMessge = new String(receivePacket.getData());
 			
 			//covers if the first message is 100
 			if (receivedMessge.equals("100")) {
 				firstMessage = "100";
 				firstMessageBool = true;
 				System.out.println(firstMessage);
 				continue;
 			}

 			//covers if received message is goodbye
 			else if (receivedMessge.equals("Goodbye")) {
 				System.out.println("Goodbye");
 				clientSocket.close();
 				break;
 			}

 			else if (receivedMessge.equals("200") && firstMessageBool) {
 				System.out.println("200");
 				/*send first message*/
 			}

 			else {
 				System.out.println(receivedMessge);
 			}


			inFromUser = new BufferedReader(new InputStreamReader(System.in));
 			sentence = inFromUser.readLine();
			sendMessage(sentence, )

 		}

		clientSocket.close();
	}	
}

public static void sendMessage(String message) {
	
	//put message to be send in a packet to be sent by the socket
	byte sendData = new byte[1024];
	sendData = message.getBytes();
	DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress /*pass in?*/, /*some socket address, pass in?*/);

	/*pass clientSocket in somehow?*/
	clientSocket.send(sendPacket);
}

public static String receiveMessage() {

	//set up the socket to receive the new message
	byte[] receiveData = new byte[1024];
	DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
	
	//have the socket receive the message
	clientSocket.receive(receivePacket);
	
	//make a new string that contains the new message
	String newMessage = new String(receivePacket.getData());
	return newMessage;

}








