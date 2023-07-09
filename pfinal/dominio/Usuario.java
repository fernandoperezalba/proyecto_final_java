package pfinal.dominio;

public class Usuario {
	
	/**Nombre del Usuario*/
	private String nombre;
	/**Minutos del tiempo record del Usuario*/
	private int minutos;
	/**Segundos del tiempo record del Usuario*/
	private int segundos;
	
	public Usuario(String nombre, int minutos, int segundos) {
		this.nombre = nombre;
		this.minutos = minutos;
		this.segundos = segundos;
	}

    public Usuario(String nombre) {
        this.nombre = nombre;
    }
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getMinutos() {
		return minutos;
	}
	
	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}
	
	public int getSegundos() {
		return segundos;
	}
	
	public void setSegundos(int segundos) {
		this.segundos = segundos;
	}

    @Override
    public boolean equals(Object o) {
        if(o instanceof Usuario)
        {
            Usuario u = (Usuario) o;
            if (nombre.equals(u.getNombre()))
                return true;
            else
                return false;
        }
        return false;
    }
        
}