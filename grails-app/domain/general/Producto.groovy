package general

class Producto {

	String codigo
	String nombre
	BigDecimal precio
	Proveedor proveedor
	Grupo grupo

    static constraints = {
		codigo(blank:false)
		nombre(blank:false)
		precio(scale:2, precision: 8, blank:false)
		proveedor(nullable:false)
		grupo(nullable:false)
    }
    
    static mapping = {
        table 'productos'
    }
    
    String toString() {
        return nombre
    }
}
