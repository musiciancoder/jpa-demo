package net.itinajero;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.itinajero.model.Categoria;
import net.itinajero.repository.CategoriasRepository;

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner{

	//INYECCION DE DEPENDENCIAS PARA CREAR UN REPOSITORIO repo
	@Autowired
	private CategoriasRepository repo;//este es el repositorio, q contiene todos los metodos CRUD
	
	
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
		this.existeById();
		
	}
	
	private void existeById() {
		boolean existe = repo.existsById(5);
		System.out.println(existe);
	}
	
	private void buscarTodos() {
	
		Iterable<Categoria>categorias=repo.findAll();
		for(Categoria cat:categorias) {
			System.out.println(cat);
		}
	}
	
	
	private void encontrarPorIds() {
		List<Integer>ids=new LinkedList<Integer>();
		ids.add(1);
		ids.add(4);
		ids.add(10);
		Iterable<Categoria>categorias=repo.findAllById(ids);
		for(Categoria cat:categorias) {
			System.out.println(cat);
		}
	}
	
	private void eliminarTodos() {
		repo.deleteAll();
		System.out.println("Todos eliminados!");
		
	}
	
	private void contar() {
		long count = repo.count();
		System.out.println("Total de registros: "+count);
		
	}
	
	
	public void guardar() {
		Categoria cat = new Categoria();
		//cat.setId(id); Esto no va, porque en la clase modelo Categoria pusimos q era llave primaria y autoincrementable
		cat.setNombre("Finanzas");
		cat.setDescripcion("Trabajos relacionados con finanzas y contablilidad");
		repo.save(cat); //con esto guardamos el registro en la tabla MYSQL sin necesidad de escribir codigo SQL
		System.out.println(" ");
		System.out.println(cat);
		System.out.println(" ");
	}
	
	private void eliminar() {
		int idCategoria =1;
		repo.deleteById(idCategoria);
	}
	
	private void modificar() {
		Optional<Categoria>optional=repo.findById(2); //Optional es una clase que puede ser encontrada o no en la BBDD
	if(optional.isPresent()){
		Categoria catTmp= optional.get();
		catTmp.setNombre("Ing de Software");
			catTmp.setDescripcion("Desarrollo de sistemas");
			repo.save(catTmp);
			System.out.println("Categoria modificada");
	}else {
		System.out.println("Categoria no encontrada");
	}
	}
	
	
	private void buscarPorId() {
		Optional<Categoria>optional=repo.findById(5); //Optional es una clase que puede ser encontrada o no en la BBDD
	if(optional.isPresent()){
		System.out.println(optional.get());
	}else {
		System.out.println("no se encuentra en BBDD");
	}
	}
	


}
