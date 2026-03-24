package net.runa.viewer.service;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import net.runa.viewer.entity.Contenido;

@Service
@PropertySource("classpath:application.properties")
public class TmdbService {
	
	private static final Logger logger = LoggerFactory.getLogger(TmdbService.class);
	private static final String BASE_URL = "https://api.themoviedb.org/3";
	private static final String POSTER_BASE_URL = "https://image.tmdb.org/t/p/w500";
	
	@Value("${tmdb.api.key}")
	private String apiKey;
	
	private final RestTemplate restTemplate;

	public TmdbService() {
		this.restTemplate = new RestTemplate();
		
		// ESTO ARREGLA EL ERROR: Agregamos el convertidor de JSON manualmente
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		messageConverters.add(new MappingJackson2HttpMessageConverter());
		this.restTemplate.setMessageConverters(messageConverters);
	}
	
	public int actualizarTodosPosterPath(List<Contenido> contenidosConPosterNull) {
		if (apiKey == null || apiKey.trim().isEmpty() || apiKey.contains("${")) {
			System.err.println("CRÍTICO: La API Key no se cargó correctamente.");
			return 0;
		}

		int actualizados = 0;
		for (Contenido contenido : contenidosConPosterNull) {
			System.out.println(">>> PROCESANDO: " + contenido.getTitulo() + " (ID TMDB: " + contenido.getTmdbId() + ")");
			
			try {
				boolean esSerie = (contenido.getIdTipo().getId() == 2L);
				String posterUrl = obtenerPosterDesdeAPI(contenido.getTmdbId(), esSerie);
				
				if (posterUrl == null) {
					System.out.println("DEBUG: Reintentando como tipo opuesto...");
					posterUrl = obtenerPosterDesdeAPI(contenido.getTmdbId(), !esSerie);
				}
				
				if (posterUrl != null) {
					contenido.setPosterPath(posterUrl);
					actualizados++;
					System.out.println("LOGRADO: Poster obtenido -> " + posterUrl);
				} else {
					System.out.println("FALLÓ DEFINITIVAMENTE: ID " + contenido.getTmdbId());
				}
				
			} catch (Exception e) {
				logger.error("Error en bucle para '{}': {}", contenido.getTitulo(), e.getMessage());
			}
		}
		return actualizados;
	}
	
	public String obtenerPosterDesdeAPI(Integer tmdbId, boolean esSerie) {
		if (tmdbId == null || tmdbId <= 0) return null;
		
		String tipo = esSerie ? "tv" : "movie";
		String url = String.format("%s/%s/%d?api_key=%s&language=es-ES", BASE_URL, tipo, tmdbId, apiKey);
		
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("User-Agent", "Mozilla/5.0");
			headers.set("Accept", "application/json");
			HttpEntity<String> entity = new HttpEntity<>(headers);
			
			// Cambiamos a Map.class genérico para evitar conflictos de tipos
			ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
			
			if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
				return extraerPosterPath(response.getBody());
			}
			
		} catch (HttpClientErrorException e) {
			System.out.println("ERROR API (" + tipo + "): " + e.getStatusCode());
			return null;
		} catch (Exception e) {
			System.out.println("ERROR INESPERADO: " + e.getMessage());
			return null;
		}
		return null;
	}
	
	private String extraerPosterPath(Map respuesta) {
		if (respuesta != null && respuesta.get("poster_path") != null) {
			String path = respuesta.get("poster_path").toString();
			if (!path.equals("null") && !path.isEmpty()) {
				return POSTER_BASE_URL + path;
			}
		}
		return null;
	}

	public Map<String, Object> obtenerDetallesDesdeAPI(Integer tmdbId, boolean esSerie) {
		if (tmdbId == null || tmdbId <= 0) return null;
		try {
			String tipo = esSerie ? "tv" : "movie";
			String url = String.format("%s/%s/%d?api_key=%s&language=es-ES", BASE_URL, tipo, tmdbId, apiKey);
			HttpHeaders headers = new HttpHeaders();
			headers.set("User-Agent", "Mozilla/5.0");
			HttpEntity<String> entity = new HttpEntity<>(headers);
			ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
			return (Map<String, Object>) response.getBody();
		} catch (Exception e) {
			return null;
		}
	}
}