package general



import org.junit.*
import grails.test.mixin.*
import javax.servlet.http.HttpServletResponse

@TestFor(GrupoController)
@Mock(Grupo)
class GrupoControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/grupo/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.grupoInstanceList.size() == 0
        assert model.grupoInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.grupoInstance != null
    }

    void testSave() {
        controller.save()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.save()

        assert model.grupoInstance != null
        assert view == '/grupo/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/grupo/show/1'
        assert controller.flash.message != null
        assert Grupo.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/grupo/list'


        populateValidParams(params)
        def grupo = new Grupo(params)

        assert grupo.save() != null

        params.id = grupo.id

        def model = controller.show()

        assert model.grupoInstance == grupo
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/grupo/list'


        populateValidParams(params)
        def grupo = new Grupo(params)

        assert grupo.save() != null

        params.id = grupo.id

        def model = controller.edit()

        assert model.grupoInstance == grupo
    }

    void testUpdate() {

        controller.update()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/grupo/list'

        response.reset()


        populateValidParams(params)
        def grupo = new Grupo(params)

        assert grupo.save() != null

        // test invalid parameters in update
        params.id = grupo.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/grupo/edit"
        assert model.grupoInstance != null

        grupo.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/grupo/show/$grupo.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        grupo.clearErrors()

        populateValidParams(params)
        params.id = grupo.id
        params.version = -1
        controller.update()

        assert view == "/grupo/edit"
        assert model.grupoInstance != null
        assert model.grupoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/grupo/list'

        response.reset()

        populateValidParams(params)
        def grupo = new Grupo(params)

        assert grupo.save() != null
        assert Grupo.count() == 1

        params.id = grupo.id

        controller.delete()

        assert Grupo.count() == 0
        assert Grupo.get(grupo.id) == null
        assert response.redirectedUrl == '/grupo/list'
    }
}
