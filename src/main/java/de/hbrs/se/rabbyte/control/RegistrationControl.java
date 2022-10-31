package de.hbrs.se.rabbyte.control;

import de.hbrs.se.rabbyte.control.factory.StudentFactory;
import de.hbrs.se.rabbyte.dtos.implemented.BusinessDTOImpl;
import de.hbrs.se.rabbyte.dtos.implemented.StudentDTOImpl;
import de.hbrs.se.rabbyte.entities.Business;
import de.hbrs.se.rabbyte.entities.Student;
import de.hbrs.se.rabbyte.repository.BusinessRepository;
import de.hbrs.se.rabbyte.repository.GeneralUserRepository;
import de.hbrs.se.rabbyte.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RegistrationControl {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationControl.class);


    @Autowired
    GeneralUserRepository generalUserRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    BusinessRepository businessRepository;


    public void registerStudent(StudentDTOImpl studentDTOImpl) {

        Student newStudent = StudentFactory.createStudent(studentDTOImpl);
        this.studentRepository.save(newStudent);
    }

    public void registerBusiness(BusinessDTOImpl businessDTO) {
        Business newBusiness = StudentFactory.createBusiness(businessDTO);
        this.businessRepository.save(newBusiness);
    }
}
