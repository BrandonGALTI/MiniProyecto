package Modelo;

public class RolFuncion {

	private int id_rol_funcion;
	private int id_rol;
	private int id_funcion;
	public int getId_rol_funcion() {
		return id_rol_funcion;
	}
	public void setId_rol_funcion(int id_rol_funcion) {
		this.id_rol_funcion = id_rol_funcion;
	}
	public int getId_rol() {
		return id_rol;
	}
	public void setId_rol(int id_rol) {
		this.id_rol = id_rol;
	}
	public int getId_funcion() {
		return id_funcion;
	}
	public void setId_funcion(int id_funcion) {
		this.id_funcion = id_funcion;
	}
	public RolFuncion(int id_rol_funcion, int id_rol, int id_funcion) {
		super();
		this.id_rol_funcion = id_rol_funcion;
		this.id_rol = id_rol;
		this.id_funcion = id_funcion;
	}

	public RolFuncion() {	}
}
