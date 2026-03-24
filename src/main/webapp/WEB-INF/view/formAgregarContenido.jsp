<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css"/>
<link type="img/x-icon" rel="icon" href="${pageContext.request.contextPath}/resources/imgs/icon.ico"/>
<title>${valorTitulo}</title>
</head>
<body>

	<nav class="navbar">

		<div class="nav-logo">
			<a href="${pageContext.request.contextPath}/"> 
			<img src="${pageContext.request.contextPath}/resources/imgs/icon.ico" alt="RunaViewer Logo">
			</a>
		</div>

		<div class="nav-menu">
		<c:url var="linkPeliculas" value="/principal/cargarTipo">
			<c:param name="tipo" value="1"/>
		</c:url>
		<c:url var="linkSeries" value="/principal/cargarTipo">
			<c:param name="tipo" value="2"/>
		</c:url>
		<c:url var="linkAnime" value="/principal/cargarCategoria">
			<c:param name="categoria" value="Anime"/>
		</c:url>
		<c:url var="linkOVA" value="/principal/cargarCategoria">
			<c:param name="categoria" value="OVA"/>
		</c:url>
		<c:url var="linkDocumentales" value="/principal/cargarCategoria">
			<c:param name="categoria" value="Documental"/>
		</c:url>
		<c:url var="linkCartoon" value="/principal/cargarCategoria">
			<c:param name="categoria" value="Cartoon"/>
		</c:url>
			<div class="dropdown-contenedor">
				<button class="boton-menu">Explorar</button>
				<ul class="nav-links-desplegable">
					<li><a href="${linkPeliculas}">Películas</a></li>
					<li><a href="${linkSeries}">Series</a></li>
					<li><a href="${linkAnime}">Anime</a></li>
					<li><a href="${linkOVA}">OVAS</a></li>
					<li><a href="${linkDocumentales}">Documentales</a></li>
					<li><a href="${linkCartoon}">Cartoons</a></li>
				</ul>
			</div>
		</div>

		<div class="nav-search">
			<form action="${pageContext.request.contextPath}/principal/buscar" method="GET">
				<input type="text" placeholder="Buscar..." name="keyword">
				<button type="submit">🔍</button>
			</form>
		</div>

		<div class="nav-user-menu">
		<c:url var="linkAccion" value="/principal/cargarCategoria">
			<c:param name="categoria" value="Accion" />
		</c:url>
		<c:url var="linkTerror" value="/principal/cargarCategoria">
			<c:param name="categoria" value="Terror"/>
		</c:url>
		<c:url var="linkCienciaFiccion" value="/principal/cargarCategoria">
			<c:param name="categoria" value="Ciencia Ficción"/>
		</c:url>
		<c:url var="linkRomance" value="/principal/cargarCategoria">
			<c:param name="categoria" value="Romance"/>
		</c:url>
		<c:url var="linkSuspenso" value="/principal/cargarCategoria">
			<c:param name="categoria" value="Suspenso"/>
		</c:url>
			<div class="dropdown-contenedor">
				<button class="boton-menu">Categorías</button>
				<ul class="nav-links-desplegable genres-right">
					<li><a href="${linkAccion}">Acción</a></li>
					<li><a href="${linkTerror}">Terror</a></li>
					<li><a href="${linkCienciaFiccion}">Ciencia Ficción</a></li>
					<li><a href="${linkRomance}">Romance</a></li>
					<li><a href="${linkSuspenso}">Suspenso</a></li>
				</ul>
			</div>
		</div>

		<div class="nav-config-menu">
		<c:url var="linkFiltro" value="/principal/filtro"/>
		<c:url var="linkFormAgregar" value="/principal/formAgregarContenido"/>
			<div class="dropdown-contenedor">
				<button class="boton-menu"></button>
					<img src="<c:url value='/resources/imgs/botonEngranajeRosa.png'/>" alt="icon" style="width: 35px; 
						vertical-align: middle; margin-right: 30px; transform: scale(1.05);">				
				<ul class="nav-links-desplegable config-right">
					<li><a href="${linkFiltro}">Tabla de Contenido</a></li>
					<li><a href="${linkFormAgregar}">Agregar Contenido</a></li>
				</ul>
			</div>
		</div>

	</nav>

	<h3>${valorTitulo} Contenido</h3>
	<br>
	
	
	<form:form action="agregarContenido" modelAttribute="contenido" method="POST">
		<form:hidden path="id"/>
		<table>
			<tr>
				<td>Titulo</td>
				<td>
					<form:input path="titulo"/>
					<form:errors path="titulo" style="color:red"/>
				</td>
				
			</tr>
			<tr>
				<td>Sinopsis</td>
				<td>
					<form:input path="sinopsis"/>
					<form:errors path="sinopsis" style="color:red"/>
				</td>
			</tr>
			<tr>
				<td>Categorias</td>
				<td>
					<div class="contenedor-checkboxes">
					<form:checkboxes path="categorias" items="${listaCategorias}" itemValue="id" 
                					itemLabel="nombreCategoria" element="div" class="checkbox-item"/>
                	</div>
					<form:errors path="categorias" style="color:red"/>
				</td>
			</tr>
			<tr>
				<td>Año lanzamiento</td>
				<td>
					<form:input path="anio" placeholder="1999"/>
					<form:errors path="anio" style="color:red"/>
				</td>
				
			</tr>
			<tr>
				<td>Duracion Minutos</td>
				<td>
					<form:input path="duracion" placeholder="168"/>
					<form:errors path="duracion" style="color:red"/>	
				</td>
			</tr>
			<tr>
				<td>Formato archivo</td>
				<td>
					<form:input path="formato" placeholder="mkv, mp4, avi..."/>
					<form:errors path="formato" style="color:red"/>
				</td>
			</tr>
			<tr>
				<td>Ruta archivo</td>
				<td>
					<form:input path="rutaArchivo" placeholder="D:/contenido/archivos/"/>
					<form:errors path="rutaArchivo" style="color:red"/>
				</td>
			</tr>
			<tr>
				<td>Tipo contenido</td>
				<td> 
				<form:select path="idTipo.id"> 
					<form:option value="" label="-- Seleccione un tipo --" />
            		<form:options items="${listaTipos}" itemValue="id" itemLabel="tipo" />
        		</form:select>
        		<form:errors path="idTipo.id" style="color:red"/>
				</td>
			</tr>
			<tr>
				<td>TheMovieDB Id</td>
				<td>
					<form:input path="tmdbId" placeholder="54534"/>
					<form:errors path="tmdbId" style="color:red"/>
				</td>
			</tr>
		</table>
		<div class="submit">
			<input type="submit" value="${valorTitulo}">
		</div>
	</form:form>
	
</body>
</html>