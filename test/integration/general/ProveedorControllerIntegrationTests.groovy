package general

import static org.junit.Assert.*
import org.junit.*
import grails.test.*
import grails.test.mixin.*


class ProveedorControllerIntegrationTests {

    @Test
    void debieraMostrarListaDeProveedores() {
		for(i in 1..20) {
            def proveedor = new Proveedor (
                nombre : "TEST-$i"
                , apellido : "TEST-$i"
                , rfc : "TESTTESTTES$i"
                , curp : "TESTTESTTESTTEST11"
				, correo : "test@test.com"                
				, fechaNacimiento: new Date()
                , telefono : "1234567890"
            ).save()
    	}
    	
    	def controller = new ProveedorController()
        controller.index()
        assertEquals '/proveedor/list', controller.response.redirectedUrl

        def model = controller.list()
        assertEquals 10, model.proveedorList.size()
        assert 20 <= model.proveedorTotal
    }
    
    @Test
    void debieraCrearProveedor() {
        def controller = new ProveedorController()
        def model = controller.create()
        assert model
        assert model.proveedor

        controller.params.nombre = 'TEST1'
        controller.params.apellido = 'TEST1'
        controller.params.rfc = 'TESTTESTTEST'
        controller.params.curp = 'TESTTESTTESTTESTT1'
        controller.params.correo = 'test@test.com'
        controller.params.fechaNacimiento = new Date()
        controller.params.telefono = '1234567890'
        controller.save()
        assert controller.response.redirectedUrl.startsWith('/proveedor/show')
    }
    
    @Test
    void debieraActualizarProveedor() {
        def proveedor = new Proveedor (
                nombre : "TEST1"
                , apellido : "TEST1"
                , rfc : "TESTTESTTEST"
                , curp : "TESTTESTTESTTEST11"
				, correo : "test@test.com"                
				, fechaNacimiento: new Date()
                , telefono : "1234567890"
            ).save()

        def controller = new ProveedorController()
        controller.params.id = proveedor.id
        def model = controller.show()
        assert model.proveedor
        assertEquals 'TEST1', model.proveedor.nombre

        controller.params.id = proveedor.id
        model = controller.edit()
        assert model.proveedor
        assertEquals 'TEST1', model.proveedor.nombre

        controller.params.id = proveedor.id
        controller.params.version = proveedor.version
        controller.params.nombre = 'TEST2'
        controller.update()
        assertEquals "/proveedor/show/${proveedor.id}".toString(), controller.response.redirectedUrl
        //assert controller.response.redirectedUrl.startsWith("/proveedor/show/${proveedor.id}")

        proveedor.refresh()
        assertEquals 'TEST2', proveedor.nombre
    }
    
    @Test
    void debieraEliminarProveedor() {
        def proveedor = new Proveedor (
                nombre : "TEST1"
                , apellido : "TEST1"
                , rfc : "TESTTESTTEST"
                , curp : "TESTTESTTESTTEST11"
				, correo : "test@test.com"
				, fechaNacimiento: new Date()
                , telefono : "1234567890"
            ).save()

        def controller = new ProveedorController()
        controller.params.id = proveedor.id
        def model = controller.show()
        assert model.proveedor
        assertEquals 'TEST1', model.proveedor.nombre

        controller.params.id = proveedor.id
        controller.delete()
        assertEquals "/proveedor/list", controller.response.redirectedUrl

        model = Proveedor.get(proveedor.id)
        assert !model
    }
    
}
