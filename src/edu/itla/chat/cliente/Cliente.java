package edu.itla.chat.cliente;
import java.io.*;
import java.util.Scanner;
import java.net.Socket;

public class Cliente {
	
	Scanner lectorTeclado = new Scanner(System.in);
	ObjectInputStream mensajeEntrante;
	ObjectOutputStream salida;
	String mensajeRecibido;
	String mensajeSalida;
	Socket socketCliente;
	
	public void iniciarCliente(String direccion, int puerto) {
	
		try {
			socketCliente = new Socket("localhost", puerto);
			//System.out.print
			flujoDeDatos();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void flujoDeDatos() {
			
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						mensajeEntrante = new ObjectInputStream(socketCliente.getInputStream());
						mensajeRecibido = (String) mensajeEntrante.readObject();
						System.out.println("SERVIDOR => " + mensajeRecibido); 
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
				}
			}
		}).start();
		
		while (true) {
			
			try {
				salida = new ObjectOutputStream(socketCliente.getOutputStream());
				mensajeSalida = lectorTeclado.nextLine();
				salida.writeObject(mensajeSalida);
				salida.flush();
				System.out.println(mensajeSalida);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}
}