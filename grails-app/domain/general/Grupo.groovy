package general

class Grupo {

	String nombre

    static constraints = {
    	nombre(blank:false)
    }
    
    static mapping = {
        table 'grupos'
    }
    
    String toString() {
    	return nombre
    }
}
