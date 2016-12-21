package modelo;
/*
 * Esta Clase se encarga de comprobar los datos del usuario que se ha conectado y si son correctos los  guarda.
 * @author:Lluís Antoni Ferrer Muñoz
 */

import java.io.InputStream;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import vista.FramePeq;


public class DatosUsuario {
	
	String id;
	String contrasena;
	String nombre;
	String apellidos;
	String foto;
	String contPadres;
	public static String tipo;
	public static int idioma;
	DatosHashMap etiquetaDatos;
	
	boolean conectado;
	
	public DatosUsuario(String nombre,String contra,String tipo,int idioma){
		this.idioma=idioma;
		id=nombre;
		this.contrasena=contra;
		this.tipo=tipo;
		this.etiquetaDatos = DatosHashMap.getinstance();
	if(tipo=="Alumno"){
		try{
			URL url = new URL("http://agendavirtual.ddns.net/api/api.php?accion=get&tabla=alumno%20where%20idalumno=%27"+id+"%27%20AND%20contrasena=%27"+contrasena+"%27%20&valor=*");
			InputStream is =url.openStream();
			JsonReader rdr = Json.createReader(is);
			JsonObject obj = rdr.readObject();
			JsonArray arrayJson = obj.getJsonArray("0");
			JsonObject objeFinal = arrayJson.getJsonObject(0);
			
			this.conectado=true;
			this.nombre= objeFinal.getString("nombre");
			this.apellidos= objeFinal.getString("apellidos");
			this.foto= objeFinal.getString("foto");
			this.contPadres= objeFinal.getString("cont_padres");
			
		}catch(Exception e){
			FramePeq peque= new FramePeq();
			peque.setVisible (true);
			peque.abreVistaError(etiquetaDatos.devuelveEtiquetas("eerrorlogin", idioma),peque);
		}
	}
	if(tipo=="Profesor"){
		try{
			URL url = new URL("http://agendavirtual.ddns.net/api/api.php?accion=get&tabla=profesor%20where%20idprofesor=%27"+id+"%27%20AND%20contrasena=%27"+contrasena+"%27%20&valor=*");
			InputStream is =url.openStream();
			JsonReader rdr = Json.createReader(is);
			JsonObject obj = rdr.readObject();
			JsonArray arrayJson = obj.getJsonArray("0");
			JsonObject objeFinal = arrayJson.getJsonObject(0);
						
			this.conectado=true;
			this.nombre= objeFinal.getString("nombre");
			this.apellidos= objeFinal.getString("apellidos");
			this.foto= objeFinal.getString("foto");
			
		}catch(Exception e){
			FramePeq peque= new FramePeq();
			peque.setVisible (true);
			peque.abreVistaError(etiquetaDatos.devuelveEtiquetas("eerrorlogin", idioma),peque);
		}
	}

}
	
	//metodo que devuelve un array de String que contiene todos los datos del usuario conectado
	public String[] misDatos(){
		
		this.etiquetaDatos = DatosHashMap.getinstance();
		String[] datos=new String[8];
		if(tipo=="Alumno"){
			try{
				URL url = new URL("http://agendavirtual.ddns.net/api/api.php?accion=get&tabla=alumno%20where%20idalumno=%27"+id+"%27%20&valor=*");
				InputStream is =url.openStream();
				JsonReader rdr = Json.createReader(is);
				JsonObject obj = rdr.readObject();
				JsonArray arrayJson = obj.getJsonArray("0");
				JsonObject objeFinal = arrayJson.getJsonObject(0);
				
				datos[0]= objeFinal.getString("nombre");
				datos[1]= objeFinal.getString("apellidos");
				datos[2]= objeFinal.getString("telefono");
				datos[3]= objeFinal.getString("fecha_nac");
				datos[4]= objeFinal.getString("direccion");
				datos[5]= objeFinal.getString("localidad");
				datos[6]= objeFinal.getString("cp");
				datos[7]= objeFinal.getString("email");
				
				return datos;
				
			}catch(Exception e){
				
				FramePeq peque= new FramePeq();
				peque.setVisible (true);
				peque.abreVistaError(etiquetaDatos.devuelveEtiquetas("eerrorlogin", idioma),peque);
			}
			}
		if(tipo=="Profesor"){
			try{
				//URL url = new URL("http://192.168.1.69/api/api.php?accion=get&tabla=profesor%20where%20idprofesor=%27"+id+"%27%20&valor=*");
				URL url = new URL("http://agendavirtual.ddns.net/api/api.php?accion=get&tabla=profesor%20where%20idprofesor=%27"+id+"%27%20&valor=*");
				InputStream is =url.openStream();
				JsonReader rdr = Json.createReader(is);
				JsonObject obj = rdr.readObject();
				JsonArray arrayJson = obj.getJsonArray("0");
				JsonObject objeFinal = arrayJson.getJsonObject(0);
				
				datos[0]= objeFinal.getString("nombre");
				datos[1]= objeFinal.getString("apellidos");
				datos[2]= objeFinal.getString("telefono");
				datos[3]= objeFinal.getString("fech_nac");
				datos[4]= objeFinal.getString("direccion");
				datos[5]= objeFinal.getString("localidad");
				datos[6]= objeFinal.getString("cp");
				datos[7]= objeFinal.getString("email");
				
				return datos;
				
			}catch(Exception e){
				FramePeq peque= new FramePeq();
				peque.setVisible (true);
				peque.abreVistaError(etiquetaDatos.devuelveEtiquetas("eerrorlogin", idioma),peque);
			}
		}
		
			return datos;
	}
	
	
	
	//getters para poder reutilizar la información
	


	public String getId() {
		return id;
	}


	public String getContrasena() {
		return contrasena;
	}


	public String getNombre() {
		return nombre;
	}


	public String getApellidos() {
		return apellidos;
	}


	public String getFoto() {
		return foto;
	}


	public String getContPadres() {
		return contPadres;
	}

	public boolean getConectado(){
		return conectado;
	}
	
	
	
}
