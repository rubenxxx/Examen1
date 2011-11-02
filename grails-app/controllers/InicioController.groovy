package carros

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ADMIN','ROLE_VENDEDOR','ROLE_USER'])

class InicioController {

def index = { }
}
