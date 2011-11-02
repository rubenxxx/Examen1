package general

class Usuario {

	transient springSecurityService
        
        String nombre
	String apellido
	Date fechaNacimiento 
	String correo
	String telefono
	String username
	String password
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	static constraints = {
	nombre(blank:false)
		apellido blank:false
		correo blank:false, email: true
		fechaNacimiento ()
		telefono blank:false //(matches:"[0-9]{10}", blank:false)
		username blank: false, unique: true
		password blank: false
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Rol> getAuthorities() {
		UsuarioRol.findAllByUsuario(this).collect { it.rol } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
}
