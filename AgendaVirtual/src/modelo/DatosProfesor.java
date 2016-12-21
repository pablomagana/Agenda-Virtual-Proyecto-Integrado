package modelo;
/*
 * Esta Clase se comunica con la tabla profesores de la base de datos.
 * @author: Adrián Rodríguez Yáñez y Lluís Antoni Ferrer Muñoz
 */
import java.io.InputStream;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class DatosProfesor {
	private JsonArray arrayJsonProfesor;

	public DatosProfesor() {
	}
	
	public JsonArray l(String clase){
		String nombreProfesor="";
		try{
		//URL a la que hago la consulta
		//URL url = new URL("http://192.168.1.69/api/api.php?accion=get&tabla=alumno%20where%20nombre_clas=%27"+clase+"%27%20&valor=*");
		URL url = new URL("http://agendavirtual.ddns.net/api/api.php?accion=get&tabla=alumno%20where%20nombre_clas=%27"+clase+"%27%20&valor=*");
		//abro un canal y lo almaceno en un inputStream
		InputStream is =url.openStream();
		//creo un lector de Json y leo el Json que e recibido en el input
		JsonReader rdr = Json.createReader(is);
		//leo el objeto Json de el reader y lo paso a un objeto Json
		JsonObject obj = rdr.readObject();
		//tenemos un objeto que tiene un array de varios objetos por defecto el que cogemos es el "0" y lo almacenamos en un jsonarray
		arrayJsonProfesor = obj.getJsonArray("0");
		nombreProfesor=arrayJsonProfesor.getJsonObject(0).getString("nombre")+arrayJsonProfesor.getJsonObject(0).getString("apellidos");
	
	}catch(Exception e){
		System.out.println("fallo en el registro");
	}
		return arrayJsonProfesor;
}
	
}