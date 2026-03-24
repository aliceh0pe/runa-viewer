package net.runa.viewer.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.runa.viewer.entity.Categoria;
import net.runa.viewer.entity.Contenido;
import net.runa.viewer.entity.TipoContenido;
import net.runa.viewer.repository.CategoriaContenidoRepositorio;
import net.runa.viewer.repository.ContenidoRepositorio;
import net.runa.viewer.repository.TipoContenidoRepositorio;
import net.runa.viewer.service.TmdbService;

@Controller
@RequestMapping("/principal")
public class Controlador {
	
	// EXPLICACION MAS A DETALLE DE LA FUNCIONALIDAD DEL ALGORITMO !!!!!!
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    // Registramos un editor para la lista de categorías
	    binder.registerCustomEditor(List.class, "categorias", new CustomCollectionEditor(List.class) {
	        @Override
	        protected Object convertElement(Object element) {
	            if (element instanceof String || element instanceof Long) {
	                Long id = Long.valueOf(element.toString());
	                // Buscamos la categoría real en la base de datos por su ID
	                return categoriaRepo.findById(id).orElse(null);
	            }
	            return element;
	        }
	    });
	}
	
	//METODO PARA CARGAR POR TIPO PARA RECICLAR CODIGO
	@GetMapping("/cargarTipo")
	public String cargarTipo(@RequestParam("tipo") Long idTipo, Model model) {
		
		// 1. Buscamos los contenidos filtrados por el tipo (ej: "Unitario")
        // Tu Service se encargará de hacer la consulta a la BD
        List<Contenido> lista = contenidoRepo.findByTipo(idTipo);
        
        String nombreTitulo = (idTipo==1L) ? "Peliculas" : "Series"; 
        
        // 3. Pasamos los objetos a la vista (JSP)
        model.addAttribute("listaContenido", lista);
		model.addAttribute("valorTitulo", nombreTitulo);
		
		return "cargarContenido";
	}
	
	//METODO PARA POR CATEGORIA PERO USANDO COMO PARAMETRO EL VALOR DEL BOTON
	@GetMapping("/cargarCategoria")
	public String cargarCategoria(@RequestParam("categoria") String categoria, Model model) {
		
        // 1. Buscamos los contenidos filtrados por el tipo (ej: "Unitario")
        // Tu Service se encargará de hacer la consulta a la BD
        List<Contenido> lista = contenidoRepo.findByCategoria(categoria);
        
        // 3. Pasamos los objetos a la vista (JSP)
        model.addAttribute("listaContenido", lista);
		model.addAttribute("valorTitulo", categoria);
		
		System.out.println(lista.toString());
		
		return "cargarContenido";
	}

	@RequestMapping("/filtro")
	public String filtroContenido(Model model) {
		
		List<Contenido> catalogo=contenidoRepo.findAll(Sort.by(Sort.Direction.ASC,"titulo"));
		
		model.addAttribute("contenido", catalogo);
		
		model.addAttribute("valorTitulo", "Filtro de");
		
		return "filtroContenido";
	}
	
	@RequestMapping("/formAgregarContenido")
	public String muestraFormularioContenido(Model model) {
		
		Contenido contenido=new Contenido();
		
		model.addAttribute("contenido", contenido);
		
		List<TipoContenido> tipos = tipoRepo.findAll();
		
		model.addAttribute("listaTipos", tipos);
		
		List<Categoria> categorias = categoriaRepo.findAll(Sort.by(Sort.Direction.ASC, "nombreCategoria"));
		
		model.addAttribute("listaCategorias", categorias);
		
		model.addAttribute("valorTitulo", "Agregar");
		
		return "formAgregarContenido";
		
	}
	
	
	@PostMapping("/agregarContenido")
	public String agregarContenido(@Valid @ModelAttribute("contenido") Contenido contenido, 
			BindingResult resultadoValidacion, Model model) {		
		
		if(resultadoValidacion.hasErrors()) {
			
			model.addAttribute("listaTipos", tipoRepo.findAll());
			// VERIFICAR EL COMO ESTA FUNCIONANDO EL SORT A PROFUNDIDAD, TENER CLARA LA LOGICA !!!!!
			model.addAttribute("listaCategorias", categoriaRepo.findAll(Sort.by(Sort.Direction.ASC, "nombreCategoria")));
			
			if(contenido.getId()==null) {
				model.addAttribute("valorTitulo", "Agregar");
			}else{
				model.addAttribute("valorTitulo", "Actualizar");
			}
	        
	 		return "formAgregarContenido";
	 		
		}else {
			// Determinamos si es serie (id=2) o película (id=1)
			boolean esSerie = contenido.getIdTipo().getId() == 2L;
			String imagen = tmdbService.obtenerPosterDesdeAPI(contenido.getTmdbId(), esSerie);
			contenido.setPosterPath(imagen);
			
			contenidoRepo.save(contenido);
			
			return "redirect:/principal/filtro";
		}

	}
	
	@GetMapping("/actualizar")
	public String acualizar(@RequestParam("contenidoId") Long id, Model model) {
		
		model.addAttribute("valorTitulo", "Actualizar");
		
		Contenido contenido=contenidoRepo.findById(id).get();
		
		model.addAttribute("contenido", contenido);
		
		List<TipoContenido> lista = tipoRepo.findAll();
		
		model.addAttribute("listaTipos", lista);
		
		List<Categoria> categorias = categoriaRepo.findAll(Sort.by(Sort.Direction.ASC, "nombreCategoria"));
		
		model.addAttribute("listaCategorias", categorias);
		
		return "formAgregarContenido";
	}
	
	@GetMapping("/eliminar")
	public String eliminar(@RequestParam("id") Long id) {
		
		contenidoRepo.deleteById(id);
		
		return "redirect:/principal/filtro";
	}
	
	@GetMapping("/detalle")
	public String detalle(@RequestParam("detalle") Long id, @RequestParam("titulo") String titulo, Model model) {
		
		Contenido contenido=contenidoRepo.findById(id).get();
		
		model.addAttribute("contenido", contenido);
		
		model.addAttribute("valorTitulo", titulo);
		
		return "detalleContenido";
	}
	
	// CHECAR LA WEA ESTA PARA ENTENDER LA API UWU
	@GetMapping("/actualizarPosterPath")
	public String actualizarPosterPath(Model model) {
	    // 1. Buscamos en la BD solo los que no tienen póster
	    List<Contenido> contenidosSinPoster = contenidoRepo.findByPosterPathIsNull();
	    
	    if (!contenidosSinPoster.isEmpty()) {
	        // 2. El servicio llama a la API y llena los posterPath en los objetos
	        int actualizados = tmdbService.actualizarTodosPosterPath(contenidosSinPoster);
	        
	        // 3. ¡IMPORTANTE! Guardamos todos los cambios de una vez en la BD
	        contenidoRepo.saveAll(contenidosSinPoster);
	        
	        System.out.println("Se recuperaron " + actualizados + " posters de la API de TMDB.");
	    }
	    
	    return "redirect:/principal/filtro";
	}
	// ...existing code...
	
	@Autowired
    private ContenidoRepositorio contenidoRepo;
	
	@Autowired
	private TipoContenidoRepositorio tipoRepo;
	
	@Autowired
	private CategoriaContenidoRepositorio categoriaRepo;
	
	@Autowired
	private TmdbService tmdbService;
}
