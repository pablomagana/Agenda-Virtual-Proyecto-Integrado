package modelo;
/*
 * Esta Clase se comunica con la tabla asignaturas de la base de datos.
 * @author: Pablo Magaña Úbeda
 */

import java.io.InputStream;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class DatosAsignatura {

	private JsonArray arrayJson;

	public JsonArray getasignatura(String id,String clase){
			try{
			//URL a la que hago la consulta
			URL url = new URL("http://agendavirtual.ddns.net/api/api.php?accion=get&tabla=asignatura%20where%20nom_clase=%27"+clase+"%27%20&valor=*");
			//abro un canal y lo almaceno en un inputStream
			InputStream is =url.openStream();
			//creo un lector de Json y leo el Json que e recibido en el input
			JsonReader rdr = Json.createReader(is);
			//leo el objeto Json de el reader y lo paso a un objeto Json
			JsonObject obj = rdr.readObject();
			//tenemos un objeto que tiene un array de varios objetos por defecto el que cogemos es el "0" y lo almacenamos en un jsonarray
			 arrayJson = obj.getJsonArray("0");
			 return arrayJson;
		
			}catch(Exception e){
			}
				return arrayJson;
	}
		
	
	public JsonArray getAsignaturaProf(String id,String clase){
		try{
		//URL a la que hago la consulta
		URL url = new URL("http://agendavirtual.ddns.net/api/api.php?accion=get&tabla=asignatura%20inner%20join%20profesor%20on%20profesor.idprofesor=asignatura.id_prof%20where%20profesor.idprofesor=%27"+id+"%27%20AND%20asignatura.nom_Clase=%27"+clase+"%27%20group%20by%20asignatura.idasignatura&valor=asignatura.*");
		//abro un canal y lo almaceno en un inputStream
		InputStream is =url.openStream();
		//creo un lector de Json y leo el Json que e recibido en el input
		JsonReader rdr = Json.createReader(is);
		//leo el objeto Json de el reader y lo paso a un objeto Json
		JsonObject obj = rdr.readObject();
		//tenemos un objeto que tiene un array de varios objetos por defecto el que cogemos es el "0" y lo almacenamos en un jsonarray
		 arrayJson = obj.getJsonArray("0");
		 return arrayJson;
	
		}catch(Exception e){
		}
			return arrayJson;
	}
	
	public String getAsignaturaSimple(String id){
		String nombreasig=null;
		try{
			//URL a la que hago la consulta
			URL url = new URL("http://agendavirtual.ddns.net/api/api.php?accion=get&tabla=asignatura%20where%20idasignatura=%27"+id+"%27&valor=nombre");
			//abro un canal y lo almaceno en un inputStream
			InputStream is =url.openStream();
			//creo un lector de Json y leo el Json que e recibido en el input
			JsonReader rdr = Json.createReader(is);
			//leo el objeto Json de el reader y lo paso a un objeto Json
			JsonObject obj = rdr.readObject();
			//tenemos un objeto que tiene un array de varios objetos por defecto el que cogemos es el "0" y lo almacenamos en un jsonarray
			 arrayJson = obj.getJsonArray("0");
			 nombreasig=arrayJson.getJsonObject(0).getString("nombre");
			 return nombreasig;
	
		}catch(Exception e){
			return nombreasig;
		}
		
	}
}
