package general

import org.springframework.dao.DataIntegrityViolationException

class ProveedorController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [proveedorList: Proveedor.list(params), proveedorTotal: Proveedor.count()]
    }

    def create() {
        [proveedor: new Proveedor(params)]
    }

    def save() {
        def proveedor = new Proveedor(params)
        if (!proveedor.save(flush: true)) {
            render(view: "create", model: [proveedor: proveedor])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), proveedor.id])
        redirect(action: "show", id: proveedor.id)
    }

    def show() {
        def proveedor = Proveedor.get(params.id)
        if (!proveedor) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), params.id])
            redirect(action: "list")
            return
        }

        [proveedor: proveedor]
    }

    def edit() {
        def proveedor = Proveedor.get(params.id)
        if (!proveedor) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), params.id])
            redirect(action: "list")
            return
        }

        [proveedor: proveedor]
    }

    def update() {
        def proveedor = Proveedor.get(params.id)
        if (!proveedor) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (proveedor.version > version) {
                proveedor.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'proveedor.label', default: 'Proveedor')] as Object[],
                          "Another user has updated this Proveedor while you were editing")
                render(view: "edit", model: [proveedor: proveedor])
                return
            }
        }

        proveedor.properties = params

        if (!proveedor.save(flush: true)) {
            render(view: "edit", model: [proveedor: proveedor])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), proveedor.id])
        redirect(action: "show", id: proveedor.id)
    }

    def delete() {
        def proveedor = Proveedor.get(params.id)
        if (!proveedor) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), params.id])
            redirect(action: "list")
            return
        }

        try {
            proveedor.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
