import java.io.*;
import java.net.*;

class BlueClient {

	public static void main(String args[]) throws Exception{

		BufferedReader inFromUser;
		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress IPAddress = InetAddress.getByName("localhost");
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		int state = 0;
		String message = "HELLO Blue";
		String response;
		DatagramPacket sendPacket = null;
		DatagramPacket receivePacket = null;

		while (state < 3){
			sendData = new byte[1024];
			receiveData = new byte[1024];
			switch (state){
				case 0: 
					// send initial message to server and wait for response
					sendData = message.getBytes();
					sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress,9876);
					clientSocket.send(sendPacket);

					receivePacket = new DatagramPacket(receiveData, receiveData.length);
					clientSocket.receive(receivePacket);
					response = new String(receivePacket.getData());
					
					if (response.substring(0,3).equals("100")) {
						state = 1; //You are first client. wait for second client to connect
					}
					
					else if (response.substring(0,3).equals("200")) {
						state = 2; //you are second client. Wait for message from first client
					}
					
					break;
				
				case 1: 
					// Waiting for notification that the second client is ready
					receivePacket = new DatagramPacket(receiveData, receiveData.length);
					clientSocket.receive(receivePacket);
					response = new String(receivePacket.getData());

					if (response.substring(0,3).equals("200")) {
						//get message from user and send it to server
						inFromUser = new BufferedReader(new InputStreamReader(System.in));
						message = inFromUser.readLine();

						sendData = message.getBytes();
						sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress,9876);
						clientSocket.send(sendPacket);
					}
					
					state = 2; //transition to state 2: chat mode
					break;
				
				case 2:
					//Chat mode
					//receive message from other client
					receivePacket = new DatagramPacket(receiveData, receiveData.length);
					clientSocket.receive(receivePacket);
					response = new String(receivePacket.getData());

					//check for Goodbye message
					if (response.length()>= 7 && response.substring(0,7).equals("Goodbye")) {
						state = 3; //prepare to exit the while loop
						break;
					}
					//if not Goodbye, get next message from user and send it;
					System.out.println(response);
					inFromUser = new BufferedReader(new InputStreamReader(System.in));
					message = inFromUser.readLine();

					sendData = message.getBytes();
					sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress,9876);
					clientSocket.send(sendPacket);
					//stay in state 2
					break;
			} //end switch
		} // end while
		//close the socket
		clientSocket.close();
	}
}