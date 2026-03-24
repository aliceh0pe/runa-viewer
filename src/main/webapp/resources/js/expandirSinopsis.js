function toggleSinopsis(enlace) {
	const parrafo = enlace.previousElementSibling;
	if (parrafo.classList.contains('sinopsis-expandida')) {
		parrafo.classList.remove('sinopsis-expandida');
		enlace.innerText = 'Leer más';
	} else {
		parrafo.classList.add('sinopsis-expandida');
		enlace.innerText = 'Leer menos';
	}
}