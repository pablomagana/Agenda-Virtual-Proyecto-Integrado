package modelo;
/*
 * Esta Clase se comunica con la tabla calificaciones de la base de datos.
 * @author: Robert Villamayor Pastor y Pablo Magaña Úbeda
 */

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class DatosCalificaciones{
	String id;
	String fecha;
	
	private JsonArray arrayJsonCalificaciones;
	
	public DatosCalificaciones(){
		
		
	}
	public String setCalificaciones(String id_profesor,String id_alumno,String id_asignatura,String nota,String comentarios){
		
		String parametro = null;
		try {
			URL url = new URL("http://agendavirtual.ddns.net/api/api.php?accion=insert&tabla=calificaciones&valores=null,%27"+id_profesor+"%27,%27"+id_alumno+"%27,%27"+id_asignatura+"%27,%27"+nota+"%27,%27"+comentarios.replace(" ","%20")+"%27");			
			InputStream is =url.openStream();
			JsonReader rdr = Json.createReader(is);
			JsonObject obj = rdr.readObject();
			String para = obj.get("parametro1").toString();
			parametro=para.substring(1,3);
			return parametro;
		}catch (MalformedURLException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return parametro;
	}
	
	public JsonArray getCalificacionesProfesor(String id_alumno){
		try {
			URL url = new URL("http://agendavirtual.ddns.net/api/api.php?accion=get&tabla=calificaciones%20where%20id_profesor=%27"+id_alumno+"%27&valor=*");
			InputStream is = url.openStream();
			JsonReader rdr = Json.createReader(is);
			JsonObject obj = rdr.readObject();
			
			arrayJsonCalificaciones = obj.getJsonArray("0");
				return arrayJsonCalificaciones;
	
			}catch (IOException e) {
			 }
				return arrayJsonCalificaciones;
	}
	

	public JsonArray getCalificacionesAlumno(String id){
		try {
			URL url = new URL("http://agendavirtual.ddns.net/api/api.php?accion=get&tabla=calificaciones%20inner%20join%20asignatura%20on%20asignatura.idasignatura=calificaciones.id_asignatura%20inner%20join%20profesor%20on%20profesor.idprofesor=calificaciones.id_profesor%20where%20calificaciones.id_alumno=%27"+id+"%27&valor=profesor.nombre%20as%20nom_prof,profesor.apellidos%20as%20ape_prof,asignatura.nombre%20as%20nom_asignatura,calificaciones.nota%20as%20nota,calificaciones.comentarios%20as%20comentarios");
			InputStream is = url.openStream();
			JsonReader rdr = Json.createReader(is);
			JsonObject obj = rdr.readObject();
			arrayJsonCalificaciones = obj.getJsonArray("0");
				return arrayJsonCalificaciones;
	
			}catch (IOException e) {
			 }
				return arrayJsonCalificaciones;
	}
	
	
}



