<%@ page import="general.Producto" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'producto.label', default: 'Producto')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
		<r:require module="jquery-ui" />
	</head>
	<body>
		<a href="#edit-producto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="edit-producto" class="content scaffold-edit" role="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${producto}">
			<ul class="errors" role="alert">
				<g:eachError bean="${producto}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form method="post" >
				<g:hiddenField name="id" value="${producto?.id}" />
				<g:hiddenField name="version" value="${producto?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
					<div class="fieldcontain ${hasErrors(bean: producto, field: 'grupo', 'error')} required">
						<label for="grupo">
							<g:message code="producto.grupo.label" default="Grupo" />
							<span class="required-indicator">*</span>
						</label>
						<g:hiddenField id="grupoId" name="grupo.id" value="${producto?.grupo?.id}" />
						<g:textField id="gruponombre" name="gruponombre" value=""/>
					</div>
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
	<r:script>
      $(document).ready(function() {
        $("input#gruponombre").autocomplete({
          source: '${createLink(action:'buscarGrupos')}',
          select: function(event, ui) {
            $("input#grupoId").val(ui.item.id);
          }
        });
      });
    </r:script>
</html>
