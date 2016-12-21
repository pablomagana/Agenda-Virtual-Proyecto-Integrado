package modelo;
/*
 * Esta Clase almacena el valor una etiqueta en varios idiomas, en este caso dos.
 * @author: Pablo Maga�a �beda
 */
import java.util.HashMap;
import java.util.Map;

public class DatosHashMap {
	Map<String,String[]> etiquetas=new HashMap<String,String[]>();
	private static DatosHashMap instance=null;
	private String[] stringArray;
	
	private DatosHashMap(){
		Etiquetas();
	}
	
	public static DatosHashMap getinstance(){
		if(instance==null){
			instance=new DatosHashMap();
		}
		return instance;
	}
	
	public void Etiquetas(){
		
		//login
		etiquetas.put("evalidar",new String[] {"Validar","Log in"});
		etiquetas.put("eusuario",new String[] {"Usuario","User"});
		etiquetas.put("econtrasena",new String[] {"Contrase�a","Password"});
		etiquetas.put("eaccesadm",new String[] {"Acceso administrador","Administrator acess"});
		
		//alumno
		etiquetas.put("ealumno",new String[] {"Alumno","Student"});
		
		//comun
		etiquetas.put("econfiguracion",new String[] {"Configuraci�n","Configuration"});
		etiquetas.put("ecalificaciones",new String[] {"Calificaciones","Qualifications"});
		etiquetas.put("emensajes",new String[] {"Mensajes","Messages"});
		etiquetas.put("eatras", new String[] {"Atr�s","Back"});
		etiquetas.put("eentrar", new String[] {"Entrar","Enter"});
		
		//profesor
		etiquetas.put("eprofesor",new String[] {"Profesor","Teacher"});
		etiquetas.put("eevento",new String[] {"Evento","Event"});
		etiquetas.put("etarea",new String[] {"Tarea","Homework"});
		
		//tareas
		etiquetas.put("etipo", new String[] {"Tipo","Kind"});
		
		//calificaciones
		etiquetas.put("easignatura",new String[] {"Asignatura","Subject"});
		etiquetas.put("eclase",new String[] {"Clase","Classroom"});
		etiquetas.put("ecurso",new String[] {"Curso","Year"});
		etiquetas.put("enota",new String[] {"Nota","Mark"});
		etiquetas.put("ecomentarios", new String[] {"Comentarios","Comments"});
		etiquetas.put("eCalEv", new String[] {"Calficaci�n enviada","Qualification sent"});
		etiquetas.put("eenviar",new String[] {"Enviar","Submit"});
		etiquetas.put("ecancelar",new String[] {"Cancelar","Cancel"});
		
		//error
		etiquetas.put("eaceptar",new String[] {"Aceptar","Accept"});
		etiquetas.put("eerror", new String[] {"Selecciona un tipo de usuario","Select kind user"});
		etiquetas.put("eerrorlogin", new String[] {"Usuario o contrase�a mal introducido","Wrong user or wrong password"});
		etiquetas.put("eerrorcont", new String[] {"Contrase�a mal introducida","Wrong password"});
		
		//notificaciones
		etiquetas.put("eNotEv", new String[] {"Evento enviado","Event sent"});
		etiquetas.put("eNottar", new String[] {"Tarea enviada","Homework sent"});
		etiquetas.put("eNotEvMal", new String[] {"Fallo en el envio","Error while sending"});
		etiquetas.put("eMensBien", new String[] {"Mensaje enviado correctamente","Your message has been sent correctly"});
		etiquetas.put("eMensMal", new String[] {"Ha habido un error al enviar el mensaje","There has been an error sending the message"});
		etiquetas.put("eNingunAlumno", new String[] {"No tienes alumnos asignados","No students"});
		etiquetas.put("eNoAlumnos", new String[] {"No puedes enviar un mensaje sin alumnos","You cannot send a message without students"});
		etiquetas.put("eNoCursos", new String[] {"No hay cursos asignados","There is not school year assign"});
		
		//mensajes
		etiquetas.put("eidentificador", new String[] {"Identificador ","Identifier"});
		etiquetas.put("easunto", new String[] {"Asunto ","Subject "});
		etiquetas.put("edestinatario",new String[] {"Destinatario","Address"} );
		etiquetas.put("efecha", new String[] {"Fecha","Date"});
		etiquetas.put("erecibidode", new String[] {"Recibido de","Received of"});
		etiquetas.put("efechaenvio",new String[] {"Fecha envio","Shipping date"});
		etiquetas.put("enuevomensaje", new String[] {"Nuevo mensaje","New Message"});
		etiquetas.put("ecuerpo",new String[] {"Mensaje","Message"});
		//control parental
		etiquetas.put("econtrolparental", new String[] {"Control Parental","Parental Control"});
		
		//mis Datos
		etiquetas.put("emisdatos", new String[] {"Mis datos","My personal dates"});
		etiquetas.put("enombre", new String[] {"Nombre","Name"});
		etiquetas.put("eapellidos", new String[] {"Apellidos","Surnames"});
		etiquetas.put("etelefono", new String[] {"Tel�fono","Telephone"});
		etiquetas.put("eemail", new String[] {"Correo","E-mail"});
		etiquetas.put("edireccion", new String[] {"Direcci�n","Address"});
		etiquetas.put("efnac", new String[] {"Fecha de nacimiento","Date of birth"});
		etiquetas.put("ecp", new String[] {"C�digo postal","Code"});
		etiquetas.put("elocalidad", new String[] {"Localidad","Town"});
		
		//calendario
		etiquetas.put("edia", new String[] {"D�A","DAY"});
		etiquetas.put("esemana", new String[] {"SEMANA","WEEK"});
		etiquetas.put("emes", new String[] {"MES","MONTH"});
		
		etiquetas.put("elunes", new String[] {"Lunes","Monday"});
		etiquetas.put("emartes", new String[] {"Martes","Tuesday"});
		etiquetas.put("emiercoles", new String[] {"Mi�rcoles","Wednesday"});
		etiquetas.put("ejueves", new String[] {"Jueves","Thursday"});
		etiquetas.put("eviernes", new String[] {"Viernes","Friday"});
		etiquetas.put("esabado", new String[] {"S�bado","Saturday"});
		etiquetas.put("edomingo", new String[] {"Domingo","Sunday"});
		etiquetas.put("etareas", new String[] {"Tareas","Homework"});
		etiquetas.put("eeventos", new String[] {"Eventos","Events"});
		
		//calendario imagenes
		etiquetas.put("eimglunes", new String[] {"/images/lunes.png","/images/Monday.png"});
		etiquetas.put("eimgmartes", new String[] {"/images/martes.png","/images/Tuesday.png"});
		etiquetas.put("eimgmiercoles", new String[] {"/images/miercoles.png","/images/Wednesday.png"});
		etiquetas.put("eimgjueves", new String[] {"/images/jueves.png","/images/Thursday.png"});
		etiquetas.put("eimgviernes", new String[] {"/images/viernes.png","/images/friday.png"});
		etiquetas.put("eimgsabado", new String[] {"/images/sabad.png","/images/Saturday.png"});
		etiquetas.put("eimgdomingo", new String[] {"/images/domingo.png","/images/Sunday.png"});
		etiquetas.put("elema", new String[] {"/images/lemarecto.png","/images/lemadefing.png"});
		
		//tareas y eventos
		
		etiquetas.put("edescripcion", new String[] {"Descripci�n","Description"});
		etiquetas.put("esinalumnos", new String[] {"Sin alumnos","no students"});
		etiquetas.put("enomev", new String[] {"Nombre del evento","Event name"});
		
		//tipo
		etiquetas.put("eexamen", new String[] {"Examen","Exam"});
		etiquetas.put("etrabajo", new String[] {"Trabajo","Work"});
		etiquetas.put("eexcursion", new String[] {"Excursi�n","Trip"});
	}
	/*
	 * Este m�todo devuelve un String al pasarle como par�metro el nombre de una etiqueta y un int que representa la numeraci�n del idioma
	 */
	public String devuelveEtiquetas(String e,int i){
		stringArray=etiquetas.get(e);
		return stringArray[i];
	}
}
