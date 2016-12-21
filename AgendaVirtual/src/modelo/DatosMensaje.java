package modelo;
/*
 * Esta Clase se comunica con la tabla mensajes de la base de datos.
 * @author: Adrián Rodríguez Yáñez y Pablo Magaña Úbeda
 */

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.swing.JOptionPane;

import vista.FrameMediano;
import vista.VistaNuevoMensaje;

public class DatosMensaje {

	private int idioma;
	
	private JsonArray arrayJsonMensaje;

	public JsonArray getMensajeAlumno(String id){
		try {
			URL url = new URL("http://agendavirtual.ddns.net/api/api.php?accion=get&tabla=mensajes%20inner%20join%20profesor%20on%20mensajes.emisor=profesor.idprofesor%20inner%20join%20alumno%20on%20mensajes.destinatario=alumno.idalumno%20where%20destinatario=%27"+id+"%27&valor=mensajes.idmensajes,profesor.nombre%20as%20nom_prof,profesor.apellidos%20as%20ape_prof,alumno.nombre%20as%20nom_alu,alumno.apellidos%20as%20ape_alu,mensajes.asunto,mensajes.cuerpo");
			InputStream is;
			is = url.openStream();
			JsonReader rdr = Json.createReader(is);
			JsonObject obj = rdr.readObject();
			JsonArray arrayJsonMensaje = obj.getJsonArray("0");
			return arrayJsonMensaje;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
			return arrayJsonMensaje;
	}
	
	public JsonArray getMensajeProfesor(String id){
		try {
			URL url = new URL("http://agendavirtual.ddns.net/api/api.php?accion=get&tabla=mensajes%20inner%20join%20profesor%20on%20mensajes.emisor=profesor.idprofesor%20inner%20join%20alumno%20on%20mensajes.destinatario=alumno.idalumno%20where%20emisor=%27"+id+"%27&valor=mensajes.idmensajes,profesor.nombre%20as%20nom_prof,profesor.apellidos%20as%20ape_prof,alumno.nombre%20as%20nom_alu,alumno.apellidos%20as%20ape_alu,mensajes.asunto,mensajes.cuerpo");
			InputStream is;
			is = url.openStream();
			JsonReader rdr = Json.createReader(is);
			JsonObject obj = rdr.readObject();
			JsonArray arrayJsonMensaje = obj.getJsonArray("0");
			
			return arrayJsonMensaje;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
			return arrayJsonMensaje;
	}
	
	public String setEnviarMensaje(String datosUsuario,String idalumno,String asunto,String nuevoMensaje){
				URL url;
				String parametro = null;
				try {
					url = new URL("http://agendavirtual.ddns.net/api/api.php?accion=insert&tabla=mensajes&valores=null,%27"+datosUsuario+"%27,%27"+idalumno+"%27,%27"+asunto.replace(" ","%20")+"%27,%27"+nuevoMensaje.replace(" ","%20")+"%27");				
					InputStream is =url.openStream();
					JsonReader rdr = Json.createReader(is);
					JsonObject obj = rdr.readObject();
					String para = obj.get("parametro1").toString();
					parametro = para.substring(1,3);
					return parametro;
					
				}catch (MalformedURLException e) {
				}catch (IOException e) {
				}
					return parametro;
		}
	
	public JsonArray getMensajeP(String id){
		try {
			URL url = new URL("http://agendavirtual.ddns.net/api/api.php?accion=get&tabla=mensajes%20inner%20join%20profesor%20on%20mensajes.emisor=profesor.idprofesor%20inner%20join%20alumno%20on%20mensajes.destinatario=alumno.idalumno%20where%20idmensajes=%27"+id+"%27&valor=mensajes.idmensajes,profesor.nombre%20as%20nom_prof,profesor.apellidos%20as%20ape_prof,alumno.nombre%20as%20nom_alu,alumno.apellidos%20as%20ape_alu,mensajes.asunto,mensajes.cuerpo");
			InputStream is;
			is = url.openStream();
			JsonReader rdr = Json.createReader(is);
			JsonObject obj = rdr.readObject();
			JsonArray arrayJsonMensaje = obj.getJsonArray("0");
			return arrayJsonMensaje;
		} catch (MalformedURLException e) {
		} catch (IOException e) {
			
		}	
		
			return arrayJsonMensaje;
	}
	public JsonArray getMensajeA(String id){
		try {
			URL url = new URL("http://agendavirtual.ddns.net/api/api.php?accion=get&tabla=mensajes%20inner%20join%20profesor%20on%20mensajes.emisor=profesor.idprofesor%20inner%20join%20alumno%20on%20mensajes.destinatario=alumno.idalumno%20where%20idmensajes=%27"+id+"%27&valor=mensajes.idmensajes,profesor.nombre%20as%20nom_prof,profesor.apellidos%20as%20ape_prof,alumno.nombre%20as%20nom_alu,alumno.apellidos%20as%20ape_alu,mensajes.asunto,mensajes.cuerpo");
			InputStream is;
			is = url.openStream();
			JsonReader rdr = Json.createReader(is);
			JsonObject obj = rdr.readObject();
			JsonArray arrayJsonMensaje = obj.getJsonArray("0");
			return arrayJsonMensaje;
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}	
		
			return arrayJsonMensaje;
	}

}
