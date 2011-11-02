import general.*

class BootStrap {


//    def springSecurityService

    def init = { servletContext ->
//        log.info("Inicializando aplicacion")
//
//        log.info "Validando roles"
        
        //if (general.Rol.count() != 3 ){
            def rolAdmin = general.Rol.findByAuthority('ROLE_ADMIN')
            if (!rolAdmin) {
                rolAdmin = new general.Rol(authority: 'ROLE_ADMIN').save(flush:true)
            }
            def rolVendedor = general.Rol.findByAuthority('ROLE_VENDEDOR')
            if (!rolVendedor) {
                rolVendedor = new general.Rol(authority: 'ROLE_VENDEDOR').save(flush:true)
            }
            def rolUser = general.Rol.findByAuthority('ROLE_USER')
            if (!rolUser) {
                rolUser = new general.Rol(authority: 'ROLE_USER').save(flush:true)
            }
        //}
//
//        log.info "Validando usuarios"
        def admin = general.Usuario.findByUsername('admin')
        if (!admin) {
            admin = new general.Usuario(
                
                   nombre: 'ruben',
	           apellido:'gomez',
	           fechaNacimiento: new Date(),
	           correo:'test@gmail.com',
	           telefono:'12456789',
                   username:'admin',
                   password:'admin',
                   enabled: true
            ).save(flush:true)
            general.UsuarioRol.create(admin, rolAdmin, true)
        }
//
//        log.info("Aplicacion inicializada")
    }

    def destroy = {
    }
}
