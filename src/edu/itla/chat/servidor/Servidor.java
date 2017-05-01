package edu.itla.chat.servidor;
import java.util.Scanner;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
 
public class Servidor {
	
	Scanner lectorTeclado = new Scanner(System.in);
	ServerSocket servidorChat;
	Socket socketChat;
	ObjectOutputStream salida;
	ObjectInputStream entrada;
	String mensaje;
	String mensajeSalida;
	
	public void iniciarServidor(int puerto) {
		
		try {
			System.out.println("Iniciando Servidor...");
			servidorChat = new ServerSocket(puerto);
			socketChat = new Socket();
			System.out.println("Servidor Iniciado !! \nEsperando Conexion...");
			//recibirConexion();
			socketChat = servidorChat.accept();
			flujoDeDatos();
		} catch (Exception e) {
			System.err.println("No se pudo iniciar el servidor: " + e.getMessage());
		}
	}
	
	public void recibirConexion() {
		
		//new Thread(new Runnable() {
			//public void run() {
				while (true) {
					try {
						mensajeDeBienvenida(socketChat.getInetAddress().getHostName());
						flujoDeDatos();
					} catch (Exception e) {
						System.err.println("No se pudo conectar al servidor: " + e.getMessage());
					}
				} 
			//}
		//}).start();
	}
	
	public void mensajeDeBienvenida(String nombreUsuario) {
		
		try {
			salida = new ObjectOutputStream(socketChat.getOutputStream());
			salida.writeObject("Bienvenido al servidor de Erick :D");
			salida.flush();
			System.out.println("Se ha conectado el cliente: " + nombreUsuario);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void flujoDeDatos() {
		
		try {
		salida = new ObjectOutputStream(socketChat.getOutputStream());
		salida.writeObject("Bienvenido al servidor de Erick :D");
		salida.flush();
		System.out.println("Se ha conectado el cliente: " + socketChat.getInetAddress().getHostName());}
	catch (Exception e){}
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						entrada = new ObjectInputStream(socketChat.getInputStream());
						mensaje = (String) entrada.readObject();
						System.out.println(socketChat.getInetAddress().getHostName() + " : " + mensaje);
					} catch (Exception e1) {
						System.err.println(e1.getMessage());
					}
				}
			}
		}).start();
		
		while (true) {
			try {
				salida = new ObjectOutputStream(socketChat.getOutputStream());
				
				mensajeSalida = lectorTeclado.nextLine();
				salida.writeObject(mensajeSalida);
				salida.flush();
				System.out.println("SERVIDOR => " + mensajeSalida);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}
}