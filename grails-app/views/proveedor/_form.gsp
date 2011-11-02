<%@ page import="general.Proveedor" %>



<div class="fieldcontain ${hasErrors(bean: proveedor, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="proveedor.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${proveedor?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: proveedor, field: 'apellido', 'error')} required">
	<label for="apellido">
		<g:message code="proveedor.apellido.label" default="Apellido" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="apellido" required="" value="${proveedor?.apellido}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: proveedor, field: 'rfc', 'error')} required">
	<label for="rfc">
		<g:message code="proveedor.rfc.label" default="Rfc" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="rfc" maxlength="13" required="" value="${proveedor?.rfc}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: proveedor, field: 'curp', 'error')} required">
	<label for="curp">
		<g:message code="proveedor.curp.label" default="Curp" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="curp" maxlength="18" required="" value="${proveedor?.curp}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: proveedor, field: 'correo', 'error')} required">
	<label for="correo">
		<g:message code="proveedor.correo.label" default="Correo" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="email" name="correo" required="" value="${proveedor?.correo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: proveedor, field: 'telefono', 'error')} required">
	<label for="telefono">
		<g:message code="proveedor.telefono.label" default="Telefono" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="telefono" pattern="${proveedor.constraints.telefono.matches}" required="" value="${proveedor?.telefono}"/>
</div>

