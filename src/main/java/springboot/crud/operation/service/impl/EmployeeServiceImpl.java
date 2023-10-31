package springboot.crud.operation.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springboot.crud.operation.exception.ResourceNotFoundException;
import springboot.crud.operation.model.Employee;
import springboot.crud.operation.repository.EmployeeRepository;
import springboot.crud.operation.service.EmployeeService;

@Service
@Transactional //it is optional Because spring boot internally provides transaction to all its methods
public class EmployeeServiceImpl implements EmployeeService {
	private EmployeeRepository employeeRepository;
	
    @Autowired //it is optional Because whenever spring boot finds spring bean it has only one constructor then spring boot will automatically  @Autowired this dependency
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}


	@Override
	public Employee saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeRepository.save(employee);
	}


	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}


	@Override
	public Employee getEmployeeById(long id) {
		// TODO Auto-generated method stub
		/*
		 * Optional<Employee> employee= employeeRepository.findById(id);
		 * if(employee.isPresent()) { return employee.get(); }else { throw new
		 * ResourceNotFoundException("Employee", "Id", id); }
		 */
		return employeeRepository.findById(id).orElseThrow(()-> 
		     new ResourceNotFoundException("Employee", "Id", id));
	}


	@Override
	public Employee updateEmployee(Employee employee, long id) {
		// TODO Auto-generated method stub
   //first we need to check whether employee with given id is exist in DB or not
		Employee 	exisitngEmployee= employeeRepository.findById(id).orElseThrow(()->
		     new ResourceNotFoundException("Employee", "Id", id));
		
		exisitngEmployee.setFirstName(employee.getFirstName());
		exisitngEmployee.setLastName(employee.getLastName());
		exisitngEmployee.setEmail(employee.getEmail());
		exisitngEmployee.setAge(employee.getAge());
		//save existing employee to the DB
		employeeRepository.save(exisitngEmployee);
		return exisitngEmployee;
	}


	@Override
	public void deleteEmployee(long id) {
		// TODO Auto-generated method stub
		//check whether a employee exist in a DB or not
		employeeRepository.findById(id).orElseThrow(()->
		                  new ResourceNotFoundException("Employee", "Id", id));
		employeeRepository.deleteById(id);
	}
	
	

}
