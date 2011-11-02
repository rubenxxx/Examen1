package general

import static org.junit.Assert.*
import org.junit.*

class GrupoControllerIntegrationTests {

    @Test
    void debieraMostrarListadeGrupos() {
		for(i in 1..20) {
            def grupo = new Grupo (
                nombre : "TEST$i"
            ).save()
        }
		
		def controller = new GrupoController()
        controller.index()
        assertEquals '/grupo/list', controller.response.redirectedUrl
        
        def model = controller.list()
        assertEquals 10, model.grupoList.size()
        assert 20 <= model.grupoTotal
    }
    
	@Test
    void debieraCrearGrupo() {
        def controller = new GrupoController()
        def model = controller.create()
        assert model
        assert model.grupo

        controller.params.nombre = 'TEST1'
        controller.save()
        assert controller.response.redirectedUrl.startsWith('/grupo/show')
    }
    
    @Test
    void debieraActualizarGrupo() {
        def grupo = new Grupo (
                nombre : "TEST1"
            ).save()

        def controller = new GrupoController()
        controller.params.id = grupo.id
        def model = controller.show()
        assert model.grupo
        assertEquals 'TEST1', model.grupo.nombre

        controller.params.id = grupo.id
        model = controller.edit()
        assert model.grupo
        assertEquals 'TEST1', model.grupo.nombre

        controller.params.id = grupo.id
        controller.params.version = grupo.version
        controller.params.nombre = 'TEST2'
        controller.update()
        assertEquals "/grupo/show/${grupo.id}".toString(), controller.response.redirectedUrl
        //assert controller.response.redirectedUrl.startsWith("/grupo/show/${grupo.id}")

        grupo.refresh()
        assertEquals 'TEST2', grupo.nombre
    }
    
    @Test
    void debieraEliminarGrupo() {
        def grupo = new Grupo (
                nombre : "TEST1"
            ).save()

        def controller = new GrupoController()
        controller.params.id = grupo.id
        def model = controller.show()
        assert model.grupo
        assertEquals 'TEST1', model.grupo.nombre

        controller.params.id = grupo.id
        controller.delete()
        assertEquals "/grupo/list", controller.response.redirectedUrl

        model = Grupo.get(grupo.id)
        assert !model
    }
}
