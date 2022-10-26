package de.hbrs.se.rabbyte.repository;

import de.hbrs.se.rabbyte.dtos.StudentDTO;
import de.hbrs.se.rabbyte.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student , Integer> {

    StudentDTO findStudentById (int id);
}
