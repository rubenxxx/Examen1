package general



import org.junit.*
import grails.test.mixin.*
import javax.servlet.http.HttpServletResponse

@TestFor(UsuarioRolController)
@Mock(UsuarioRol)
class UsuarioRolControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/usuarioRol/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.usuarioRolInstanceList.size() == 0
        assert model.usuarioRolInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.usuarioRolInstance != null
    }

    void testSave() {
        controller.save()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.save()

        assert model.usuarioRolInstance != null
        assert view == '/usuarioRol/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/usuarioRol/show/1'
        assert controller.flash.message != null
        assert UsuarioRol.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/usuarioRol/list'


        populateValidParams(params)
        def usuarioRol = new UsuarioRol(params)

        assert usuarioRol.save() != null

        params.id = usuarioRol.id

        def model = controller.show()

        assert model.usuarioRolInstance == usuarioRol
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/usuarioRol/list'


        populateValidParams(params)
        def usuarioRol = new UsuarioRol(params)

        assert usuarioRol.save() != null

        params.id = usuarioRol.id

        def model = controller.edit()

        assert model.usuarioRolInstance == usuarioRol
    }

    void testUpdate() {

        controller.update()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/usuarioRol/list'

        response.reset()


        populateValidParams(params)
        def usuarioRol = new UsuarioRol(params)

        assert usuarioRol.save() != null

        // test invalid parameters in update
        params.id = usuarioRol.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/usuarioRol/edit"
        assert model.usuarioRolInstance != null

        usuarioRol.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/usuarioRol/show/$usuarioRol.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        usuarioRol.clearErrors()

        populateValidParams(params)
        params.id = usuarioRol.id
        params.version = -1
        controller.update()

        assert view == "/usuarioRol/edit"
        assert model.usuarioRolInstance != null
        assert model.usuarioRolInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/usuarioRol/list'

        response.reset()

        populateValidParams(params)
        def usuarioRol = new UsuarioRol(params)

        assert usuarioRol.save() != null
        assert UsuarioRol.count() == 1

        params.id = usuarioRol.id

        controller.delete()

        assert UsuarioRol.count() == 0
        assert UsuarioRol.get(usuarioRol.id) == null
        assert response.redirectedUrl == '/usuarioRol/list'
    }
}
