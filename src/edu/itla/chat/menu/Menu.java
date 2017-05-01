package edu.itla.chat.menu;
import java.util.Scanner;
import edu.itla.chat.servidor.Servidor;
import edu.itla.chat.cliente.Cliente;

public class Menu {
	
	int eleccion = 0;
	int puerto = 24456;
	//String direccion;
	boolean continuar = true;
	Scanner lectorTeclado = new Scanner(System.in);
	
	public Menu() {
		
		while (continuar == true) {
			
			System.out.println("Bienvenido");
			System.out.println("1. Crear servidor");
			System.out.println("2. Conectarse a un servidor");
			System.out.println("3. Salir");
			System.out.print("==> ");
			
			try {
				eleccion = lectorTeclado.nextInt();
			} catch (Exception e) {
				eleccion = 0;
			}
			
			switch (eleccion) {
				case (1):
					new Servidor().iniciarServidor(puerto);
				break;
				case (2):
					/*System.out.print("Escriba la direccion a la que desea conectarse: ");
					direccion = lectorTeclado.nextLine();*/
					new Cliente().iniciarCliente("127.0.0.1", puerto);
				break;
				case (3):
					continuar = false;
					System.out.println("Adios :(");
				break;
				default:
					System.out.println("Introduzca un valor valido");
				break;
			}
		}
	}
}	