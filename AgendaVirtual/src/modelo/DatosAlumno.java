package modelo;

/*
 * Esta Clase se comunica con la tabla alumnos de la base de datos.
 * @author: Lluís Antoni Ferrer Muñoz y Adrián Rodríguez Yáñez
 */

import java.io.InputStream;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

	public class DatosAlumno {
		private JsonArray arrayJsonAlumno;

	/*
	 * Método encargado de devolver un JsonArray con los alumnos que integran una clase introducida en el método.
	 */
	public JsonArray getAlumno(String clase){
		try{
			//URL a la que hago la consulta
			URL url = new URL("http://agendavirtual.ddns.net/api/api.php?accion=get&tabla=alumno%20where%20nombre_clas=%27"+clase+"%27%20&valor=*");
			//abro un canal y lo almaceno en un inputStream
			InputStream is =url.openStream();
			//creo un lector de Json y leo el Json que e recibido en el input
			JsonReader rdr = Json.createReader(is);
			//leo el objeto Json de el reader y lo paso a un objeto Json
			JsonObject obj = rdr.readObject();
			//tenemos un objeto que tiene un array de varios objetos por defecto el que cogemos es el "0" y lo almacenamos en un jsonarray
			arrayJsonAlumno = obj.getJsonArray("0");
	
		}catch(Exception e){
		}
			return arrayJsonAlumno;
		}
	
}
