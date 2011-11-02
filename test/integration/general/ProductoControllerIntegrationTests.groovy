package general

import static org.junit.Assert.*
import org.junit.*


class ProductoControllerIntegrationTests {

    @Test
    void debieraMostrarListaDeProductos() {
            def proveedor = new Proveedor (
                nombre : "TEST1"
                , apellido : "TEST1"
                , rfc : "TESTTESTTEST"
                , curp : "TESTTESTTESTTEST11"
				, correo : "test@test.com"                
				, fechaNacimiento: new Date()
                , telefono : "1234567890"
            ).save()
            
            def grupo = new Grupo (
                nombre : "TEST1"
            ).save()
		
		for(i in 1..20) {
            def producto = new Producto (
                codigo: "TEST-$i"
                , nombre : "TEST-$i"
                , precio : new BigDecimal(100)
                , proveedor : proveedor
                , grupo : grupo
            ).save()
    	}
    	
    	def controller = new ProductoController()
        controller.index()
        assertEquals '/producto/list', controller.response.redirectedUrl

        def model = controller.list()
        assertEquals 10, model.productoList.size()
        assert 20 <= model.productoTotal
    }
    
    @Test
    void debieraCrearProducto() {
    	def proveedor = new Proveedor (
                nombre : "TEST1"
                , apellido : "TEST1"
                , rfc : "TESTTESTTEST"
                , curp : "TESTTESTTESTTEST11"
				, correo : "test@test.com"                
				, fechaNacimiento: new Date()
                , telefono : "1234567890"
        ).save()
        
        def grupo = new Grupo (
                nombre : "TEST1"
            ).save()
        
        def producto = new Producto (
                codigo: "TEST1"
                , nombre : "TEST1"
                , precio : new BigDecimal(100)
                , proveedor : proveedor
                , grupo : grupo
        ).save()
        
        def controller = new ProductoController()
        def model = controller.create()
        assert model
        assert model.producto
		
		controller.params.codigo = 'TEST1'
        controller.params.nombre = 'TEST1'
        controller.params.precio = new BigDecimal(100)
        controller.params.proveedor = proveedor
        controller.params.grupo = grupo
        controller.save()
        assert controller.response.redirectedUrl.startsWith('/producto/show')
    }
    
    @Test
    void debieraActualizarProducto() {
        def proveedor = new Proveedor (
                nombre : "TEST1"
                , apellido : "TEST1"
                , rfc : "TESTTESTTEST"
                , curp : "TESTTESTTESTTEST11"
				, correo : "test@test.com"                
				, fechaNacimiento: new Date()
                , telefono : "1234567890"
        ).save()
        
        def grupo = new Grupo (
                nombre : "TEST1"
            ).save()
        
        def producto = new Producto (
                codigo: "TEST1"
                , nombre : "TEST1"
                , precio : new BigDecimal(100)
                , proveedor : proveedor
                , grupo : grupo
        ).save()

        def controller = new ProductoController()
        controller.params.id = producto.id
        def model = controller.show()
        assert model.producto
        assertEquals 'TEST1', model.producto.nombre

        controller.params.id = producto.id
        model = controller.edit()
        assert model.producto
        assertEquals 'TEST1', model.producto.nombre

        controller.params.id = producto.id
        controller.params.version = producto.version
        controller.params.nombre = 'TEST2'
        controller.update()
        assertEquals "/producto/show/${producto.id}".toString(), controller.response.redirectedUrl
        //assert controller.response.redirectedUrl.startsWith("/producto/show/${producto.id}")

        producto.refresh()
        assertEquals 'TEST2', producto.nombre
    }
	
	@Test
    void debieraEliminarProducto() {
        def proveedor = new Proveedor (
                nombre : "TEST1"
                , apellido : "TEST1"
                , rfc : "TESTTESTTEST"
                , curp : "TESTTESTTESTTEST11"
				, correo : "test@test.com"
				, fechaNacimiento: new Date()
                , telefono : "1234567890"
            ).save()
            
         def grupo = new Grupo (
                nombre : "TEST1"
            ).save()

         def producto = new Producto (
                codigo: "TEST1"
                , nombre : "TEST1"
                , precio : new BigDecimal(100)
                , proveedor : proveedor
                , grupo : grupo
        ).save()

        def controller = new ProductoController()
        controller.params.id = producto.id
        def model = controller.show()
        assert model.producto
        assertEquals 'TEST1', model.producto.nombre

        controller.params.id = producto.id
        controller.delete()
        assertEquals "/producto/list", controller.response.redirectedUrl

        model = Producto.get(producto.id)
        assert !model
    }
    
    @Test
    void debieraBuscarGrupo(){
    	
    }
    
}
