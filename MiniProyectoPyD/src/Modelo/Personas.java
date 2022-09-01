package Modelo;

import java.sql.Date;
import java.time.LocalDate;

public class Personas {
	private int id_persona;
	private String documento = "";
	private String apellido1 = "";
	private String apellido2 = "";
	private String nombre1 = "";
	private String nombre2 = "";
	private LocalDate FechaNac = null;
	private String clave = "";
	private String mail = "";
	//private EnumRol TipoUsuario;
	private int ID_ROL=0;
	private static Personas Usuario = new Personas();
	
	public Personas() {	}
	
	
	public Personas(String documento, String apellido1, String apellido2, String nombre1, String nombre2, LocalDate fechaNac,
			String clave, String mail, int iD_ROL,int id_persona) {
		this.documento = documento;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.nombre1 = nombre1;
		this.nombre2 = nombre2;
		this.FechaNac = fechaNac;
		this.clave = clave;
		this.mail = mail;
		this.ID_ROL = iD_ROL;
		this.id_persona=id_persona;
	}
	public static void setInstancia(Personas p) {
		Usuario = p;
	}

	public static Personas getInstancia() {
		return Usuario;
	}
	
	public int getId_persona() {
		return id_persona;
	}


	public void setId_persona(int id_persona) {
		this.id_persona = id_persona;
	}


	public int getID_ROL() {
		return ID_ROL;
	}

	public void setID_ROL(int iD_ROL) {
		ID_ROL = iD_ROL;
	}

	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public String getNombre1() {
		return nombre1;
	}
	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}
	public String getNombre2() {
		return nombre2;
	}
	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}
	public LocalDate getFechaNac() {
		return FechaNac;
	}
	public void setFechaNac(LocalDate fechaNac) {
		FechaNac = fechaNac;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	

	@Override
	public String toString() {
		return "persona [documento=" + documento + ", apellido1=" + apellido1 + ", apellido2=" + apellido2
				+ ", nombre1=" + nombre1 + ", nombre2=" + nombre2 + ", FechaNac=" + FechaNac + ", clave=" + clave
				+ ", mail=" + mail + "]";
}
}
