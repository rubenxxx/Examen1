<%@ page import="general.Rol" %>



<div class="fieldcontain ${hasErrors(bean: rolInstance, field: 'authority', 'error')} required">
	<label for="authority">
		<g:message code="rol.authority.label" default="Authority" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="authority" required="" value="${rolInstance?.authority}"/>
</div>

