<%@ page import="general.Usuario" %>


<div class="fieldcontain ${hasErrors(bean: usuario, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="usuario.nombre.label" default="nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${usuario?.nombre}"/>
</div>


<div class="fieldcontain ${hasErrors(bean: usuario, field: 'apellido', 'error')} required">
	<label for="apellido">
		<g:message code="usuario.apellido.label" default="apellido" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="apellido" required="" value="${usuario?.apellido}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'fechaNacimiento', 'error')} required">
	<label for="fechaNacimiento">
		<g:message code="usuario.fechaNacimiento.label" default="Fecha Nacimiento" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaNacimiento" precision="day"  value="${usuarioInstance?.fechaNacimiento}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: usuario, field: 'correo', 'error')} required">
	<label for="correo">
		<g:message code="usuario.correo.label" default="correo" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="correo" required="" value="${usuario?.correo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: usuario, field: 'telefono', 'error')} required">
	<label for="telefono">
		<g:message code="usuario.telefono.label" default="telefono" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="telefono" required="" value="${usuario?.telefono}"/>
</div>



<div class="fieldcontain ${hasErrors(bean: usuario, field: 'username', 'error')} required">
	<label for="username">
		<g:message code="usuario.username.label" default="Username" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="username" required="" value="${usuario?.username}"/>
</div>



<div class="fieldcontain ${hasErrors(bean: usuario, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="usuario.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:passwordField name="password" required="" value="${usuario?.password}"/>
</div>


<div class="fieldcontain ${hasErrors(bean: usuario, field: 'accountExpired', 'error')} ">
	<label for="accountExpired">
		<g:message code="usuario.accountExpired.label" default="Account Expired" />
		
	</label>
	<g:checkBox name="accountExpired" value="${usuario?.accountExpired}" />
</div>

<div class="fieldcontain ${hasErrors(bean: usuario, field: 'accountLocked', 'error')} ">
	<label for="accountLocked">
		<g:message code="usuario.accountLocked.label" default="Account Locked" />
		
	</label>
	<g:checkBox name="accountLocked" value="${usuario?.accountLocked}" />
</div>

<div class="fieldcontain ${hasErrors(bean: usuario, field: 'enabled', 'error')} ">
	<label for="enabled">
		<g:message code="usuario.enabled.label" default="Enabled" />
		
	</label>
	<g:checkBox name="enabled" value="${usuario?.enabled}" />
</div>

<div class="fieldcontain ${hasErrors(bean: usuario, field: 'passwordExpired', 'error')} ">
	<label for="passwordExpired">
		<g:message code="usuario.passwordExpired.label" default="Password Expired" />
		
	</label>
	<g:checkBox name="passwordExpired" value="${usuario?.passwordExpired}" />
</div>

<g:if test="${roles}">
    <div class="fieldcontain ${hasErrors(bean: usuario, field: 'authorities', 'error')} ">
        <g:set var="contador" value="${1}" />
        <g:each var="entry" in="${roles}">
            <label for="${entry.key.authority}">
                <g:if test="${contador++ == 1}">
                    <g:message code="usuario.authorities.label" default="Authorities" />
                </g:if>
            </label>
            <g:checkBox name="${entry.key.authority}" value="${entry.value}"/> ${entry.key.authority}<br/>
        </g:each>
    </div>
</g:if>
