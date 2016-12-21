package modelo;
/*
 * Esta Clase se comunica con la tabla curso de la base de datos.
 * @author: Adrián Rodríguez Yáñez y Lluís Antoni Ferrer Muñoz
 */

import java.io.InputStream;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class DatosCurso {
	private JsonArray arrayJson;

	public JsonArray getcursos(){
			try{
				//URL a la que hago la consulta
				//URL url = new URL("http://192.168.1.69/api/api.php?accion=get&tabla=curso&valor=*");
				URL url = new URL("http://agendavirtual.ddns.net/api/api.php?accion=get&tabla=curso&valor=*");
				//abro un canal y lo almaceno en un inputStream
				InputStream is =url.openStream();
				//creo un lector de Json y leo el Json que e recibido en el input
				JsonReader rdr = Json.createReader(is);
				//leo el objeto Json de el reader y lo paso a un objeto Json
				JsonObject obj = rdr.readObject();
				//tenemos un objeto que tiene un array de varios objetos por defecto el que cogemos es el "0" y lo almacenamos en un jsonarray
				 arrayJson = obj.getJsonArray("0");
		
		}catch(Exception e){
		}
			return arrayJson;
	}
	
	
	public JsonArray getCursosProf(String idprof){
		try{
			//URL a la que hago la consulta
			//URL url = new URL("http://192.168.1.69/api/api.php?accion=get&tabla=curso%20inner%20join%20clase%20on%20clase.nombre_curso=curso.nombre%20inner%20join%20asignatura%20on%20asignatura.nom_clase=clase.nombre_clase%20inner%20join%20profesor%20on%20profesor.idprofesor=asignatura.id_prof%20where%20profesor.idprofesor=%27"+idprof+"%27%20group%20by%20curso.nombre&valor=curso.*");
			URL url = new URL("http://agendavirtual.ddns.net/api/api.php?accion=get&tabla=curso%20inner%20join%20clase%20on%20clase.nombre_curso=curso.nombre%20inner%20join%20asignatura%20on%20asignatura.nom_clase=clase.nombre_clase%20inner%20join%20profesor%20on%20profesor.idprofesor=asignatura.id_prof%20where%20profesor.idprofesor=%27"+idprof+"%27%20group%20by%20curso.nombre&valor=curso.*");
			//abro un canal y lo almaceno en un inputStream
			InputStream is =url.openStream();
			//creo un lector de Json y leo el Json que e recibido en el input
			JsonReader rdr = Json.createReader(is);
			//leo el objeto Json de el reader y lo paso a un objeto Json
			JsonObject obj = rdr.readObject();
			//tenemos un objeto que tiene un array de varios objetos por defecto el que cogemos es el "0" y lo almacenamos en un jsonarray
			 arrayJson = obj.getJsonArray("0");
	
		}catch(Exception e){
		}
			return arrayJson;
		}	
}
