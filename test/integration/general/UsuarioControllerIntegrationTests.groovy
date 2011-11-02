package general

import static org.junit.Assert.*
import org.junit.*
import grails.test.*
import grails.test.mixin.*


class UsuarioControllerIntegrationTests {

    @Test
    void debieraMostrarListadeUsuarios() {
		for(i in 1..20) {
            def usuario = new Usuario (
                nombre : "TEST$i"
                , apellido : "TEST$i"
				, correo : "test@test.com"                
				, fechaNacimiento: new Date()
                , telefono : "1234567890"
            ).save()
        }
		
		def controller = new UsuarioController()
        controller.index()
        assertEquals '/usuario/list', controller.response.redirectedUrl
        
        def model = controller.list()
        assertEquals 10, model.usuarioList.size()
        assert 20 <= model.usuarioTotal
    }
    
	@Test
    void debieraCrearUsuario() {
        def controller = new UsuarioController()
        def model = controller.create()
        assert model
        assert model.usuario

        controller.params.nombre = 'TEST1'
        controller.params.apellido = 'TEST1'
        controller.params.correo = 'test@test.com'
        controller.params.fechaNacimiento = new Date()
        controller.params.telefono = '1234567890'
        controller.save()
        assert controller.response.redirectedUrl.startsWith('/usuario/show')
    }
    
    @Test
    void debieraActualizarUsuario() {
        def usuario = new Usuario (
                nombre : "TEST1"
                , apellido : "TEST1"
				, correo : "test@test.com"                
				, fechaNacimiento: new Date()
                , telefono : "1234567890"
            ).save()

        def controller = new UsuarioController()
        controller.params.id = usuario.id
        def model = controller.show()
        assert model.usuario
        assertEquals 'TEST1', model.usuario.nombre

        controller.params.id = usuario.id
        model = controller.edit()
        assert model.usuario
        assertEquals 'TEST1', model.usuario.nombre

        controller.params.id = usuario.id
        controller.params.version = usuario.version
        controller.params.nombre = 'TEST2'
        controller.update()
        assertEquals "/usuario/show/${usuario.id}".toString(), controller.response.redirectedUrl
        //assert controller.response.redirectedUrl.startsWith("/usuario/show/${usuario.id}")

        usuario.refresh()
        assertEquals 'TEST2', usuario.nombre
    }
    
    @Test
    void debieraEliminarUsuario() {
        def usuario = new Usuario (
                nombre : "TEST1"
                , apellido : "TEST1"
				, correo : "test@test.com"                
				, fechaNacimiento: new Date()
                , telefono : "1234567890"
            ).save()

        def controller = new UsuarioController()
        controller.params.id = usuario.id
        def model = controller.show()
        assert model.usuario
        assertEquals 'TEST1', model.usuario.nombre

        controller.params.id = usuario.id
        controller.delete()
        assertEquals "/usuario/list", controller.response.redirectedUrl

        model = Usuario.get(usuario.id)
        assert !model
    }
	
}
