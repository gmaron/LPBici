

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>FORMULARIO</title>
</head>
<body>


<f:view>
  <h:form id="regUsuario">
    
    <h:inputText  converterMessage="Ingrese un nombre" id="nombre" value="#{PersonaBean.usr.nombre}" required="true"/>  	
  	<h:inputText converterMessage="Ingrese un apellido" id="apellido" value="#{PersonaBean.usr.apellido}" required="true"/>
    
    
    <h:commandLink styleClass="btn btn-success">
    	 PERSISTIR
</h:commandLink>
    
    <!--<h:commandButton action="#{PersonaBean.altaUsuario}" value="Registrar" />-->
    
  </h:form> 
</f:view>


</body>
</html>