package modelo;

/*
 * Esta Clase se comunica con la tabla eventos de la base de datos.
 * @author: Silvia Reolid Dolz y Lluís Antoni Ferrer Muñoz
 */
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class DatosEventos {
	private JsonArray arrayJsonEventos;
	public String setEventos(String nombre,String tipo,String fecha,String descripcion,String codAsig,String codProf){
				URL url;
				String parametro=null;
				try {
					url = new URL("http://agendavirtual.ddns.net/api/api.php?accion=insert&tabla=eventos&valores=null,%27"+nombre.replace(" ","%20")+"%27,%27"+tipo+"%27,%27"+fecha+"%27,%27"+descripcion.replace(" ","%20")+"%27,%27"+codAsig+"%27,%27"+codProf+"%27");
					InputStream is =url.openStream();
					JsonReader rdr = Json.createReader(is);
					JsonObject obj = rdr.readObject();
					String para = obj.get("parametro1").toString();
					parametro=para.substring(1,3);
					System.out.println(para);
					return parametro;
					
				}catch (MalformedURLException e) {
					return parametro;
				}catch (IOException e) {
					return parametro;
				}
	}
	
	public JsonArray getEventos(String id, String fecha){
		try {
			URL url = new URL("http://agendavirtual.ddns.net/api/api.php?accion=get&tabla=eventos%20inner%20join%20asignatura%20on%20eventos.cod_asig=asignatura.idasignatura%20inner%20join%20clase%20on%20asignatura.nom_Clase=clase.nombre_clase%20inner%20join%20alumno%20on%20clase.nombre_clase=alumno.nombre_clas%20where%20alumno.idalumno=%27"+id+"%27%20AND%20eventos.fecha=%27"+fecha+"%27%20&valor=eventos.*");
			InputStream is = url.openStream();
			JsonReader rdr = Json.createReader(is);
			JsonObject obj = rdr.readObject();
		
			arrayJsonEventos = obj.getJsonArray("0");
				return arrayJsonEventos;
	
			}catch (IOException e) {
			 }
				return arrayJsonEventos;
	}
	
	public JsonArray getEventosProf(String id, String fecha){
		try {
			URL url = new URL("http://agendavirtual.ddns.net/api/api.php?accion=get&tabla=eventos%20where%20cod_prof=%27"+id+"%27%20AND%20fecha=%27"+fecha+"%27%20&valor=*");
			InputStream is = url.openStream();
			JsonReader rdr = Json.createReader(is);
			JsonObject obj = rdr.readObject();
		
			arrayJsonEventos = obj.getJsonArray("0");
			return arrayJsonEventos;
	
			}catch (IOException e) {
			}
				return arrayJsonEventos;
	}
	
	public JsonArray getEvAsig (String idEvento){
		try{
			URL url = new URL("http://agendavirtual.ddns.net/api/api.php?accion=get&tabla=eventos%20inner%20join%20asignatura%20on%20eventos.cod_asig=asignatura.idasignatura%20where%20eventos.ideventos=%22"+idEvento+"%22&valor=eventos.nombre,eventos.descripcion,asignatura.nombre%20as%20nom_asig");
			InputStream is = url.openStream();
			JsonReader rdr = Json.createReader(is);
			JsonObject obj = rdr.readObject();
			arrayJsonEventos = obj.getJsonArray("0");
			return arrayJsonEventos;

			}catch (IOException e) {
			}
				return arrayJsonEventos;
	}
}


