package general

class Proveedor {

	String nombre
	String apellido
	String rfc
	String curp
	String correo
	String telefono
	
    static constraints = {
		nombre(blank:false)
		apellido(blank:false)
		rfc(minSize:12, maxSize:13, blank:false)
		curp(minSize:18, maxSize:18, blank:false)
		correo(blank:false, email:true)
		telefono(matches:"[0-9]{10}", blank:false)
    }
    
    static mapping = {
        table 'proveedores'
    }
    
    String toString() {
        return nombre
    }
}
