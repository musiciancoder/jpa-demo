package net.itinajero;

import java.util.Date;
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
import net.itinajero.model.Perfil;
import net.itinajero.model.Usuario;
import net.itinajero.model.Vacante;
import net.itinajero.repository.CategoriasRepository;
import net.itinajero.repository.PerfilesRepository;
import net.itinajero.repository.UsuariosRepository;
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
	
	@Autowired
	private PerfilesRepository repoPerfiles;
	
	@Autowired
	private UsuariosRepository repoUsuarios;
	
	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception { //estamos sobreescribiendo metodo de la interfaz 
		//guardar();nn
		//buscarPorId();
		//this.modificar();
//this.eliminar(14);
		//contar();
		//this.eliminarTodos();
		//this.encontrarPorIds();
		//this.buscarTodos();
		//this.existeById();
		//buscarTodosJpa();
		//buscarTodosOrdenadosJpa();
		//buscarTodosPaginacionJPA();		
		//buscarTodosPaginacionOrdenadosJPA();
	//buscarVacantes ();
		//guardarVacante();
		//crearPerfilesAplicacion();
		//crearUsuarioConDosPerfiles();
		//buscarUsuario() ;
		//buscarVacantesPorEstatus();
		//buscarVacantesPorDestacadoEstatus() ;
		//buscarVacantesSalario() ;
		buscarVacantesVariosEstatus()
;		}
	
	//EJECUCION DE UN QUERY METHOD IN
		private void buscarVacantesVariosEstatus() {
			String [] estatus = new String [] {"Eliminada", "Creada"};
			List<Vacante>lista=repoVacantes.findByEstatusIn(estatus);// querymethod declarado en interfaz VacantesRepository
			System.out.println("Registros encontrados: "+lista.size());
			for (Vacante v : lista) {
				System.out.println(v.getId()+ ": " +v.getNombre()+": "+v.getEstatus());
				
			}
		}
	
	
	//EJECUCION DE UN QUERY METHOD BETWEEN
	private void buscarVacantesSalario() {
		List<Vacante>lista=repoVacantes.findBySalarioBetweenOrderBySalario(7000.0,14000.0);// querymethod declarado en interfaz VacantesRepository
		System.out.println("Registros encontrados: "+lista.size());
		for (Vacante v : lista) {
			System.out.println(v.getId()+ ": " +v.getNombre()+": "+v.getSalario());
			
		}
	}
	
	//EJECUCION DE UN QUERY METHOD AND
	private void buscarVacantesPorDestacadoEstatus() {
		List<Vacante>lista=repoVacantes.findByDestacadoAndEstatusOrderByIdDesc(1, "Aprobada");// querymethod declarado en interfaz VacantesRepository
		System.out.println("Registros encontrados: "+lista.size());
		for (Vacante v : lista) {
			System.out.println(v.getId()+ ": " +v.getNombre()+": "+v.getEstatus());
			
		}
	}
	
	//EJECUCION DE UN QUERY METHOD FINDBY
	private void buscarVacantesPorEstatus() {
		List<Vacante>lista=repoVacantes.findByEstatus("Aprobada");//findByEstatus es querymethod declarado en interfaz VacantesRepository
		System.out.println("Registros encontrados: "+lista.size());
		for (Vacante v : lista) {
			System.out.println(v.getId()+ ": " +v.getNombre()+": "+v.getEstatus());
			
		}
	}
	

	
	
	public void buscarUsuario() {
		Optional<Usuario>optional = repoUsuarios.findById(6);
		if(optional.isPresent()) {
			Usuario u = optional.get();
			System.out.println("Usuario: " +u.getNombre());
			System.out.println("Perfiles asignados");
			for (Perfil p : u.getPerfiles()) {
				System.out.println(p.getPerfil());
			}
		} else {
				System.out.println("Usuario no encontrado");		
			
		}
	}
	
	//CREAR USUARIO CON DOS PERFILES
	private void crearUsuarioConDosPerfiles() {
		Usuario user = new Usuario();
		user.setNombre("Ivan");
		user.setEmail("ivan@gmail.com");
		user.setFechaRegistro(new Date());
		user.setUsername("itinajero");
		user.setPassword("12345");
		user.setEstatus(1);
		
		Perfil per1 = new Perfil();
		per1.setId(2);
		
		Perfil per2 = new Perfil();
		per2.setId(3);
		
		user.agregar(per1);
		user.agregar(per2);
		
		repoUsuarios.save(user);
		
	}
	
	private void crearPerfilesAplicacion() {
		repoPerfiles.saveAll(getPerfilesAplicacion()); //Iterable implements LinkedList
	}
	
	private void guardarVacante() {
		Vacante vacante = new Vacante();
		vacante.setNombre("Profesor de Castellano");
		vacante.setDescripcion("Escuela primaria");
		vacante.setFecha(new Date());
		vacante.setSalario(8500.0);
		vacante.setEstatus("Creada");
		vacante.setDestacado(0);
		vacante.setImagen("escuela.png");
		vacante.setDetalles("Los requisitos para optar al cargo de profesor de Castellano");
		Categoria cat = new Categoria();
		cat.setId(15);//solo con proporcionar el id ya se establece la relacion
		vacante.setCategoria(cat);
		repoVacantes.save(vacante);
		//this.buscarVacantes();
	}
	
	private void buscarVacantes (){
		List<Vacante>lista=repoVacantes.findAll(Sort.by("nombre"));
		for(Vacante v: lista) {
			System.out.println(v.getId() + " " + v.getNombre()+ " -Categoria: " + v.getCategoria().getNombre() + "idCategoria: " + v.getCategoria().getId());   // Notese que v.getCategoria().getNombre()); es posible por las anotaciones @OneToOne y @JoinColumn en la clase modelo
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
	
	private void eliminar(int ido) {
		
		repoCategorias.deleteById(ido);
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
	
	
	/**
	 * Metodo que regresa una lista de objetos de tipo Perfil que representa los diferentes PERFILES 
	 * O ROLES que tendremos en nuestra aplicación de Empleos
	 * @return
	 */
	private List<Perfil> getPerfilesAplicacion(){		
		List<Perfil> lista = new LinkedList<Perfil>();
		Perfil per1 = new Perfil();
		per1.setPerfil("SUPERVISOR2");
		
		Perfil per2 = new Perfil();
		per2.setPerfil("ADMINISTRADOR2");
		
		Perfil per3 = new Perfil();
		per3.setPerfil("USUARIO2");
		
		lista.add(per1);
		lista.add(per2);
		lista.add(per3);
		
		return lista;
	}

}
