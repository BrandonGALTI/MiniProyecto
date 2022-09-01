package Modelo;

import java.time.LocalDate;

public class Personas {
	private String documento = "";
	private String apellido1 = "";
	private String apellido2 = "";
	private String nombre1 = "";
	private String nombre2 = "";
	private java.sql.Date FechaNac = null;
	private String clave = "";
	private String mail = "";
	//private EnumRol TipoUsuario;
	private int ID_ROL=0;
	private static Personas Instancia = new Personas();
	
	private Personas() {	}
	
	public static Personas getInstancia() {
		return Instancia;
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
	public java.sql.Date getFechaNac() {
		return FechaNac;
	}
	public void setFechaNac(java.sql.Date fechaNac) {
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
	

//	public EnumRol getTipoUsuario() {
//		return TipoUsuario;
//	}
//
//	public void setTipoUsuario(EnumRol tipoUsuario) {
//		TipoUsuario = tipoUsuario;
//	}

	@Override
	public String toString() {
		return "persona [documento=" + documento + ", apellido1=" + apellido1 + ", apellido2=" + apellido2
				+ ", nombre1=" + nombre1 + ", nombre2=" + nombre2 + ", FechaNac=" + FechaNac + ", clave=" + clave
				+ ", mail=" + mail + "]";
}
}
