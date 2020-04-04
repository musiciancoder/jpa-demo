package net.itinajero.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.itinajero.model.Vacante;

//RECORDAR QUE JPAREPOSITORY EXTIENDE DE CRUDREPOSITORY
public interface VacantesRepository extends JpaRepository<Vacante, Integer> {

}
