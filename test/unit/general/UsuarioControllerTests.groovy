package general



import org.junit.*
import grails.test.mixin.*
import javax.servlet.http.HttpServletResponse

@TestFor(UsuarioController)
@Mock(Usuario)
class UsuarioControllerTests {

    void testIndex() {
        controller.index()
        assert "/usuario/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.usuarioInstanceList.size() == 0
        assert model.usuarioInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.usuarioInstance != null
    }

    void testSave() {
        controller.save()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.save()

        assert model.usuarioInstance != null
        assert view == '/usuario/create'

        response.reset()

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/usuario/show/1'
        assert controller.flash.message != null
        assert Usuario.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/usuario/list'


        def usuario = new Usuario()

        // TODO: populate domain properties

        assert usuario.save() != null

        params.id = usuario.id

        def model = controller.show()

        assert model.usuarioInstance == usuario
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/usuario/list'


        def usuario = new Usuario()

        // TODO: populate valid domain properties

        assert usuario.save() != null

        params.id = usuario.id

        def model = controller.edit()

        assert model.usuarioInstance == usuario
    }

    void testUpdate() {

        controller.update()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/usuario/list'

        response.reset()


        def usuario = new Usuario()

        // TODO: populate valid domain properties

        assert usuario.save() != null

        // test invalid parameters in update
        params.id = usuario.id

        controller.update()

        assert view == "/usuario/edit"
        assert model.usuarioInstance != null

        usuario.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/usuario/show/$usuario.id"
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/usuario/list'

        response.reset()

        def usuario = new Usuario()

        // TODO: populate valid domain properties
        assert usuario.save() != null
        assert Usuario.count() == 1

        params.id = usuario.id

        controller.delete()

        assert Usuario.count() == 0
        assert Usuario.get(usuario.id) == null
        assert response.redirectedUrl == '/usuario/list'
    }
}
