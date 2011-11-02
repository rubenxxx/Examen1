<%@ page import="general.Grupo" %>



<div class="fieldcontain ${hasErrors(bean: grupo, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="grupo.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${grupo?.nombre}"/>
</div>

