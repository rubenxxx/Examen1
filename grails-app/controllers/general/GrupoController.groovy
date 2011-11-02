package general

import org.springframework.dao.DataIntegrityViolationException

class GrupoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [grupoList: Grupo.list(params), grupoTotal: Grupo.count()]
    }

    def create() {
        [grupo: new Grupo(params)]
    }

    def save() {
        def grupo = new Grupo(params)
        if (!grupo.save(flush: true)) {
            render(view: "create", model: [grupo: grupo])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'grupo.label', default: 'Grupo'), grupo.id])
        redirect(action: "show", id: grupo.id)
    }

    def show() {
        def grupo = Grupo.get(params.id)
        if (!grupo) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'grupo.label', default: 'Grupo'), params.id])
            redirect(action: "list")
            return
        }

        [grupo: grupo]
    }

    def edit() {
        def grupo = Grupo.get(params.id)
        if (!grupo) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'grupo.label', default: 'Grupo'), params.id])
            redirect(action: "list")
            return
        }

        [grupo: grupo]
    }

    def update() {
        def grupo = Grupo.get(params.id)
        if (!grupo) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'grupo.label', default: 'Grupo'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (grupo.version > version) {
                grupo.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'grupo.label', default: 'Grupo')] as Object[],
                          "Another user has updated this Grupo while you were editing")
                render(view: "edit", model: [grupo: grupo])
                return
            }
        }

        grupo.properties = params

        if (!grupo.save(flush: true)) {
            render(view: "edit", model: [grupo: grupo])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'grupo.label', default: 'Grupo'), grupo.id])
        redirect(action: "show", id: grupo.id)
    }

    def delete() {
        def grupo = Grupo.get(params.id)
        if (!grupo) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'grupo.label', default: 'Grupo'), params.id])
            redirect(action: "list")
            return
        }

        try {
            grupo.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'grupo.label', default: 'Grupo'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'grupo.label', default: 'Grupo'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
