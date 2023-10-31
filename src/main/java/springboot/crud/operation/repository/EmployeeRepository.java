package springboot.crud.operation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springboot.crud.operation.model.Employee;
@Repository// we dont need to add @Repository Because JpaRepository internally provide @Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	

}
