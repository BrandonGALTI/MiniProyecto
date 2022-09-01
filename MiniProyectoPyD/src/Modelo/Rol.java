package Modelo;

public class Rol {
	
	private int id_rol;
	private String nombre_Rol;
	private String descrpicion_Rol;
	
	public Rol() {	};
	public Rol(String nombre_Rol, String descrpicion_Rol) {
		super();
		this.nombre_Rol = nombre_Rol;
		this.descrpicion_Rol = descrpicion_Rol;
	}
	public Rol(int id_rol, String nombre_Rol, String descrpicion_Rol) {
		super();
		this.id_rol = id_rol;
		this.nombre_Rol = nombre_Rol;
		this.descrpicion_Rol = descrpicion_Rol;
	}
	public int getId_rol() {
		return id_rol;
	}
	public void setId_rol(int id_rol) {
		this.id_rol = id_rol;
	}
	public String getNombre_Rol() {
		return nombre_Rol;
	}
	public void setNombre_Rol(String nombre_Rol) {
		this.nombre_Rol = nombre_Rol;
	}
	public String getDescrpicion_Rol() {
		return descrpicion_Rol;
	}
	public void setDescrpicion_Rol(String descrpicion_Rol) {
		this.descrpicion_Rol = descrpicion_Rol;
	}
}