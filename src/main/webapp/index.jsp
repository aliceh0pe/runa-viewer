<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css" />
<link type="img/x-icon" rel="icon" href="${pageContext.request.contextPath}/resources/img/icon.ico" />
<title>Inicio</title>
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
	
	<div class="carrusel-container">
		<div class="carrusel-track">
			<div class="carrusel-item">
				<img src="${pageContext.request.contextPath}/resources/imgs/Touhou17.jpg" alt="Banner 1">
				<div class="carrusel-info">
					<h2>Título de la Peli</h2>
					<p>Una breve descripción épica para que se antoje verla.</p>
				</div>
			</div>
			<div class="carrusel-item">
				<img src="${pageContext.request.contextPath}/resources/imgs/astolfo15.jpg" alt="Banner 2">
				<div class="carrusel-info">
					<h2>Nuevo Anime</h2>
					<p>¡No te pierdas el estreno de la temporada!</p>
				</div>
			</div>
			<div class="carrusel-item">
				<img src="${pageContext.request.contextPath}/resources/imgs/Touhou18.jpg" alt="Banner 3">
				<div class="carrusel-info">
					<h2>Que bonito esta quedando...</h2>
					<p>Esto esta muy uwu</p>
				</div>
			</div>
		</div>
	</div>

</body>
</html>