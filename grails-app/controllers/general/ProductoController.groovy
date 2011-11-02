package general

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON

class ProductoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [productoList: Producto.list(params), productoTotal: Producto.count()]
    }

    def create() {
        [producto: new Producto(params)]
    }

    def save() {
        def producto = new Producto(params)
        if (!producto.save(flush: true)) {
            render(view: "create", model: [producto: producto])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'producto.label', default: 'Producto'), producto.id])
        redirect(action: "show", id: producto.id)
    }

    def show() {
        def producto = Producto.get(params.id)
        if (!producto) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'producto.label', default: 'Producto'), params.id])
            redirect(action: "list")
            return
        }

        [producto: producto]
    }

    def edit() {
        def producto = Producto.get(params.id)
        if (!producto) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'producto.label', default: 'Producto'), params.id])
            redirect(action: "list")
            return
        }

        [producto: producto]
    }

    def update() {
        def producto = Producto.get(params.id)
        if (!producto) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'producto.label', default: 'Producto'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (producto.version > version) {
                producto.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'producto.label', default: 'Producto')] as Object[],
                          "Another user has updated this Producto while you were editing")
                render(view: "edit", model: [producto: producto])
                return
            }
        }

        producto.properties = params

        if (!producto.save(flush: true)) {
            render(view: "edit", model: [producto: producto])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'producto.label', default: 'Producto'), producto.id])
        redirect(action: "show", id: producto.id)
    }

    def delete() {
        def producto = Producto.get(params.id)
        if (!producto) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'producto.label', default: 'Producto'), params.id])
            redirect(action: "list")
            return
        }

        try {
            producto.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'producto.label', default: 'Producto'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'producto.label', default: 'Producto'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
    
    def buscarGrupos() {
        def filtro = "%${params.term}%"
        def grupos = Grupo.findAllByNombreIlike(filtro)
        def lista = []
        for(grupo in grupos) {
            lista << [id:grupo.id, value:grupo.nombre]
        }
        render lista as grails.converters.JSON 
    }
}
