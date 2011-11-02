package general



import org.junit.*
import grails.test.mixin.*
import javax.servlet.http.HttpServletResponse

@TestFor(RolController)
@Mock(Rol)
class RolControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/rol/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.rolInstanceList.size() == 0
        assert model.rolInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.rolInstance != null
    }

    void testSave() {
        controller.save()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.save()

        assert model.rolInstance != null
        assert view == '/rol/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/rol/show/1'
        assert controller.flash.message != null
        assert Rol.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/rol/list'


        populateValidParams(params)
        def rol = new Rol(params)

        assert rol.save() != null

        params.id = rol.id

        def model = controller.show()

        assert model.rolInstance == rol
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/rol/list'


        populateValidParams(params)
        def rol = new Rol(params)

        assert rol.save() != null

        params.id = rol.id

        def model = controller.edit()

        assert model.rolInstance == rol
    }

    void testUpdate() {

        controller.update()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/rol/list'

        response.reset()


        populateValidParams(params)
        def rol = new Rol(params)

        assert rol.save() != null

        // test invalid parameters in update
        params.id = rol.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/rol/edit"
        assert model.rolInstance != null

        rol.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/rol/show/$rol.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        rol.clearErrors()

        populateValidParams(params)
        params.id = rol.id
        params.version = -1
        controller.update()

        assert view == "/rol/edit"
        assert model.rolInstance != null
        assert model.rolInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/rol/list'

        response.reset()

        populateValidParams(params)
        def rol = new Rol(params)

        assert rol.save() != null
        assert Rol.count() == 1

        params.id = rol.id

        controller.delete()

        assert Rol.count() == 0
        assert Rol.get(rol.id) == null
        assert response.redirectedUrl == '/rol/list'
    }
}
