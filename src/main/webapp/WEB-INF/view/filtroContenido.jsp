<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css"/>
<link type="img/x-icon" rel="icon" href="${pageContext.request.contextPath}/resources/imgs/icon.ico"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/expandirSinopsis.js"></script>
<title>Filtro Contenido</title>
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
		<c:url var="linkActualizarPoster" value="/principal/actualizarPosterPath"/>
			<div class="dropdown-contenedor">
				<button class="boton-menu"></button>
					<img src="<c:url value='/resources/imgs/botonEngranajeRosa.png'/>" alt="icon" style="width: 35px; 
						vertical-align: middle; margin-right: 30px; transform: scale(1.05);">				
				<ul class="nav-links-desplegable config-right">
					<li><a href="${linkFiltro}">Tabla de Contenido</a></li>
					<li><a href="${linkFormAgregar}">Agregar Contenido</a></li>
					<li><a href="${linkActualizarPoster}">Actualizar Posters</a></li>
				</ul>
			</div>
		</div>

	</nav>
	
	<h3>${valorTitulo} Contenido</h3>
	
	<table style="margin-top: 30px">
		<tr>
			<th>Titulo</th>
			<th>Sinopsis</th>
			<th>Año</th>
			<th>Duracion</th>
			<th>Categorias</th>
			<th>Fecha Registro</th>
			<th>Tipo Contenido</th>
			<th>Modificar</th>
			<th>Eliminar</th>
		</tr>
		
		<c:forEach var="contenidoTemp" items="${contenido}">
			<c:url var="linkActualizar" value="/principal/actualizar">
				<c:param name="contenidoId" value="${contenidoTemp.id}"/>
			</c:url>
			<c:url var="linkEliminar" value="/principal/eliminar">
				<c:param name="id" value="${contenidoTemp.id}"/>
			</c:url>
			<tr>
				<td>${contenidoTemp.titulo}</td>
				<td>
					<div style="max-width: 300px; position: relative;">
						<p class="sinopsis-compacta">${contenidoTemp.sinopsis}</p>
						<span class="leer-mas" onclick="toggleSinopsis(this)">Leer más</span>
					</div>
				</td>
				<td>${contenidoTemp.anio}</td>
				<td>${contenidoTemp.duracion} min</td>
				<td>${contenidoTemp.categorias} </td>
				<td>${contenidoTemp.fechaRegistro}</td>
				<td>${contenidoTemp.idTipo}</td>
				<td><a href="${linkActualizar}"><input type="button" value="Actualizar"></a></td>
				<td><a href="${linkEliminar}"><input type="button" value="Eliminar" 
					   onclick="if(!(confirm('Vas a eliminar un registro. ¿Estas seguro?'))) return false"></a></td>
			</tr>
		</c:forEach>
	</table>
	<div class="boton-agregar" style="margin-top:30px;">
		<input type="button" value="Agregar Contenido" onclick="window.location.href='formAgregarContenido'; return false;"/>
	</div>
	<div class="imagen">
		<img name="astolfo" alt="imagen anime astolfo femboy" src="${pageContext.request.contextPath}/resources/imgs/astolfo08.png">
	</div>
</body>
</html>