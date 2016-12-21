package modelo;
/*
 * Esta Clase se comunica con la tabla tareas de la base de datos.
 * @author: Silvia Reolid Dolz y Lluís Antoni Ferrer Muñoz
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

public class DatosTareas {
	String id;
	String fecha;
	
	private JsonArray arrayJsonTareas;
	
	public String setTareas(String nombre,String fecha,String descripcion,String codAsig,String codProf){
		URL url;
		String parametro=null;
		try {
			url = new URL("http://agendavirtual.ddns.net/api/api.php?accion=insert&tabla=tareas&valores=null,%27"+nombre.replace(" ","%20")+"%27,%27"+fecha+"%27,%27"+descripcion.replace(" ","%20")+"%27,%27"+codProf+"%27,%27"+codAsig+"%27");
			InputStream is =url.openStream();
			JsonReader rdr = Json.createReader(is);
			JsonObject obj = rdr.readObject();
			String para = obj.get("parametro1").toString();
			parametro=para.substring(1,3);
			
			return parametro;
			
		}catch (MalformedURLException e) {
		}catch (IOException e) {
		}
			return parametro;
	}
	
	public JsonArray getTareas(String id, String fecha){
		try {
			URL url = new URL("http://agendavirtual.ddns.net/api/api.php?accion=get&tabla=tareas%20inner%20join%20realiza%20on%20realiza.id_tarea=tareas.idtareas%20where%20realiza.id_alumno=%27"+id+"%27%20AND%20tareas.fecha=%27"+fecha+"%27%20&valor=*");
			InputStream is = url.openStream();
			JsonReader rdr = Json.createReader(is);
			JsonObject obj = rdr.readObject();
		
			arrayJsonTareas = obj.getJsonArray("0");
				return arrayJsonTareas;
	
			}catch (IOException e) {
			}
				return arrayJsonTareas;
	}
	
	public JsonArray getTareasProf(String id, String fecha){
		try {
			URL url = new URL("http://agendavirtual.ddns.net/api/api.php?accion=get&tabla=tareas%20where%20id_profesor=%27"+id+"%27%20AND%20fecha=%27"+fecha+"%27&valor=*");
			InputStream is = url.openStream();
			JsonReader rdr = Json.createReader(is);
			JsonObject obj = rdr.readObject();
		
			arrayJsonTareas = obj.getJsonArray("0");
				return arrayJsonTareas;
	
			}catch (IOException e) {
			}
				return arrayJsonTareas;
	}
	public void updateTarea(int valor, String id,String tarea){
		
		try {
			URL url = new URL("http://agendavirtual.ddns.net/api/api.php?accion=realiza&idalumno="+id+"&idtarea="+tarea+"&realizada="+valor+"");
			InputStream is = url.openStream();
		} catch (MalformedURLException e) {
			
		} catch (IOException e) {
		}
	}
	public JsonArray getTarAsig (String idtarea){
		try{
			URL url = new URL("http://agendavirtual.ddns.net/api/api.php?accion=get&tabla=tareas%20inner%20join%20asignatura%20on%20tareas.id_asignatura=asignatura.idasignatura%20where%20tareas.idtareas=%22"+idtarea+"%22&valor=tareas.nombre,tareas.descripcion,asignatura.nombre%20as%20nom_asig");
			InputStream is = url.openStream();
			JsonReader rdr = Json.createReader(is);
			JsonObject obj = rdr.readObject();
		
			arrayJsonTareas = obj.getJsonArray("0");
				return arrayJsonTareas;

		}catch (IOException e) {
		}
			return arrayJsonTareas;
	}
}
