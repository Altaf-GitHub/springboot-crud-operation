package springboot.crud.operation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import springboot.crud.operation.model.Employee;
import springboot.crud.operation.service.EmployeeService;

@RestController
@RequestMapping("/api/employees") //this is base URL for this controller 
@Tag(name = "CRUD REST APIs for User Resource",
 description = "CRUD REST APIs - Create User, Update User, Get User, Get All Users, Delete User")
public class EmployeeController {
	
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	//build create employee REST API
	//POST/  http://localhost:8080/api/employees
	   @Operation(
	            summary = "Create User REST API",
	            description = "Create User REST API is used to save user in a database"
	    )
	    @ApiResponse(
	            responseCode = "201",
	            description = "HTTP Status 201 CREATED"
	    )
	@PostMapping
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
		return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
		
	}
	
	//build get all employee REST API
//GET/	http://localhost:8080/api/employees
	   @Operation(
	            summary = "Get All Users REST API",
	            description = "Get All Users REST API is used to get a all the users from the database"
	    )
	    @ApiResponse(
	            responseCode = "200",
	            description = "HTTP Status 200 SUCCESS"
	    )
	@GetMapping
	public List<Employee> getAllEmployees(){
		return employeeService.getAllEmployees();
		
	}
	//build get employee by ID REST API
	//GET/	http://localhost:8080/api/employees/1
	  @Operation(
	            summary = "Get User By ID REST API",
	            description = "Get User By ID REST API is used to get a single user from the database"
	    )
	    @ApiResponse(
	            responseCode = "200",
	            description = "HTTP Status 200 SUCCESS"
	    )
	@GetMapping("{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id")long employeeId){
		return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
	}
	
	//build update employee REST API
	//GET/	http://localhost:8080/api/employees/1
	  @Operation(
	            summary = "Update User REST API",
	            description = "Update User REST API is used to update a particular user in the database"
	    )
	    @ApiResponse(
	            responseCode = "200",
	            description = "HTTP Status 200 SUCCESS"
	    )
	@PutMapping("{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id,
			@RequestBody Employee employee){
		return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
	}
	
	//build delete employee REST API
//	//GET/	http://localhost:8080/api/employees/1
	  @Operation(
	            summary = "Delete User REST API",
	            description = "Delete User REST API is used to delete a particular user from the database"
	    )
	    @ApiResponse(
	            responseCode = "200",
	            description = "HTTP Status 200 SUCCESS"
	    )
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id){
		//delete employee from DB
		employeeService.deleteEmployee(id);
		return new ResponseEntity<String>("Employee deleted successfully!.", HttpStatus.OK);
	}

}
