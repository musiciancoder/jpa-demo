package net.itinajero;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import net.itinajero.model.Categoria;
import net.itinajero.model.Vacante;
import net.itinajero.repository.CategoriasRepository;
import net.itinajero.repository.VacantesRepository;

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner{

	//INYECCION DE DEPENDENCIAS PARA CREAR UN REPOSITORIO repo
	@Autowired
	private CategoriasRepository repoCategorias;//este es el repositorio, q contiene todos los metodos CRUD en CrudRepository y otros métodos tambien tipo CRUD en JPARepositoy( mas especificos).
	//Recordar que la interfaz JpaRepository extiende la interfaz CrudRepository
	
	//INYECCION DE DEPENDENCIAS PARA CREAR UN REPOSITORIO repo
	@Autowired
	private VacantesRepository repoVacantes;
	
	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception { //estamos sobreescribiendo metodo de la interfaz 
		//guardar();nn
		//buscarPorId();
		//this.modificar();
//this.eliminar();
		//contar();
		//this.eliminarTodos();
		//this.encontrarPorIds();
		//this.buscarTodos();
		//this.existeById();
		//buscarTodosJpa();
		//buscarTodosOrdenadosJpa();
		//buscarTodosPaginacionJPA();		
		//buscarTodosPaginacionOrdenadosJPA();
		 buscarVacantes ();
	}
	
	private void buscarVacantes (){
		List<Vacante>vacantes=repoVacantes.findAll(Sort.by("nombre").descending());
		for(Vacante v:vacantes) {
			System.out.println(v.getId() + " " + v.getNombre());
		}
	}
	
	
	private void buscarTodosPaginacionOrdenadosJPA() { 
		Page <Categoria>page= repoCategorias.findAll(PageRequest.of(0, 5, Sort.by("nombre"))); //registros del 0 al 5, ordenados por atributo nombre
		System.out.println("Total registros: " + page.getTotalElements());
		System.out.println("Total paginas: " + page.getTotalPages());
		for(Categoria c: page.getContent()) {
			System.out.println(c.getId() + " " + c.getNombre());
		}
	}
	
	private void buscarTodosPaginacionJPA() { 
		Page <Categoria>page= repoCategorias.findAll(PageRequest.of(0, 5)); //registros del 0 al 5
		System.out.println("Total registros: " + page.getTotalElements());
		System.out.println("Total paginas: " + page.getTotalPages());
		for(Categoria c: page.getContent()) {
			System.out.println(c.getId() + " " + c.getNombre());
		}
	}
	
	
	private void buscarTodosOrdenadosJpa() {
		//List<Categoria>categorias=repo.findAll(Sort.by("nombre")); //devuelve los registros ordenados por algun atributo (en este caso "nomnre")
		List<Categoria>categorias=repoCategorias.findAll(Sort.by("nombre").descending());
		for(Categoria cat:categorias) {
			System.out.println(cat.getId() + " " + cat.getNombre());
		}
	}
	
	//PRECAUCION: BORRA TODA LA TABLA!!
	private void borrarTodoEnLote() {
		repoCategorias.deleteAllInBatch();
	}
	
	private void buscarTodosJpa() {
		List<Categoria>categorias=repoCategorias.findAll();
		for(Categoria cat:categorias) {
			System.out.println(cat.getId() + " " + cat.getNombre());
		}
	}
	
	
	private void existeById() {
		boolean existe = repoCategorias.existsById(5);
		System.out.println(existe);
	}
	
	private void buscarTodos() {
	
		Iterable<Categoria>categorias=repoCategorias.findAll();
		for(Categoria cat:categorias) {
			System.out.println(cat);
		}
	}
	
	
	private void encontrarPorIds() {
		List<Integer>ids=new LinkedList<Integer>();
		ids.add(1);
		ids.add(4);
		ids.add(10);
		Iterable<Categoria>categorias=repoCategorias.findAllById(ids);
		for(Categoria cat:categorias) {
			System.out.println(cat);
		}
	}
	
	private void eliminarTodos() {
		repoCategorias.deleteAll();
		System.out.println("Todos eliminados!");
		
	}
	
	private void contar() {
		long count = repoCategorias.count();
		System.out.println("Total de registros: "+count);
		
	}
	
	
	public void guardar() {
		Categoria cat = new Categoria();
		//cat.setId(id); Esto no va, porque en la clase modelo Categoria pusimos q era llave primaria y autoincrementable
		cat.setNombre("Finanzas");
		cat.setDescripcion("Trabajos relacionados con finanzas y contablilidad");
		repoCategorias.save(cat); //con esto guardamos el registro en la tabla MYSQL sin necesidad de escribir codigo SQL
		System.out.println(" ");
		System.out.println(cat);
		System.out.println(" ");
	}
	
	private void eliminar() {
		int idCategoria =1;
		repoCategorias.deleteById(idCategoria);
	}
	
	private void modificar() {
		Optional<Categoria>optional=repoCategorias.findById(2); //Optional es una clase que puede ser encontrada o no en la BBDD
	if(optional.isPresent()){
		Categoria catTmp= optional.get();
		catTmp.setNombre("Ing de Software");
			catTmp.setDescripcion("Desarrollo de sistemas");
			repoCategorias.save(catTmp);
			System.out.println("Categoria modificada");
	}else {
		System.out.println("Categoria no encontrada");
	}
	}
	
	
	private void buscarPorId() {
		Optional<Categoria>optional=repoCategorias.findById(5); //Optional es una clase que puede ser encontrada o no en la BBDD
	if(optional.isPresent()){
		System.out.println(optional.get());
	}else {
		System.out.println("no se encuentra en BBDD");
	}
	}
	


}
