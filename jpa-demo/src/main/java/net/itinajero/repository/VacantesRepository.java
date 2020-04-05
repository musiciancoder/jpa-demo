package net.itinajero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.itinajero.model.Vacante;

//RECORDAR QUE JPAREPOSITORY EXTIENDE DE CRUDREPOSITORY
public interface VacantesRepository extends JpaRepository<Vacante, Integer> {

	//QueryMethod. Con esto queda disponible este metodo para ser llamado por iny. de dependencias.
	List<Vacante>findByEstatus(String estatus);
}
