/**
*	UDP Server Program
*	Listens on two UDP ports
*	Receives a line of input from a UDP client
* Sends that line to the other client
* Clients message back and forth until they exit the program
*
*	@author: Julien Fournell
@	version: 0.1
*/

import java.io.*;
import java.net.*;

class ChatServer {
  public static void main(String args[]) throws Exception
    {

    DatagramSocket serverSocket = null;
	  
	try
		{
			serverSocket = new DatagramSocket(9876);
      System.out.println("Starting Server");
		}
	
	catch(Exception e)
		{
			System.out.println("Failed to open UDP socket");
      System.out.println("Closing Server");
			System.exit(0);
		}

      byte[] receiveData = new byte[1024];
      byte[] sendData  = new byte[1024];

      
      int message = 100;
      while(message < 200) 
      {
        DatagramPacket initialPacket = new DatagramPacket(receiveData, receiveData.length);
        String startData = new String(initialPacket.getData());
        if (startData=="HELLO Red" || startData=="HELLO Blue") {
          
        }
        InetAddress IPAddress = initialPacket.getAddress();
        int port = initialPacket.getPort();
        sendData = message;
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
        message+=100;
      }


      while(true)
        {

          DatagramPacket receivePacket =
             new DatagramPacket(receiveData, receiveData.length);
           serverSocket.receive(receivePacket);

          InetAddress IPAddress = receivePacket.getAddress();

          int port = receivePacket.getPort();

          String capitalizedSentence = sentence.toUpperCase();

          sendData = receivePacket.getData();

          DatagramPacket sendPacket =
             new DatagramPacket(sendData, sendData.length, IPAddress,
                               port);

          serverSocket.send(sendPacket);
        }
    }
}