package com.kgainc.empmanager.controller;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kgainc.empmanager.entity.Employee;
import com.kgainc.empmanager.entity.EmployeeTeam;
import com.kgainc.empmanager.entity.Role;
import com.kgainc.empmanager.entity.Team;
import com.kgainc.empmanager.service.EmployeeService;
import com.kgainc.empmanager.service.EmployeeTeamService;
import com.kgainc.empmanager.service.RoleService;
import com.kgainc.empmanager.service.TeamService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	// add an initbinder ... to convert trim input strings
	// remove leading and trailing whitespace
	// resolve issue for our validation
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true); // true means, if all white-space, trim down to null
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor); // registers newly created stringTrimmerEditor to String class for all Strings incoming into this controller.
		
	}
	
	// inject employee service
	@Autowired
	private EmployeeService employeeService;

	// inject employeeTeam service
	@Autowired
	private TeamService teamService;	
	
	// inject employeeTeam service
	@Autowired
	private EmployeeTeamService employeeTeamService;
	
	// inject role service
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/list")
	public String listEmployees(Model theModel) {
	
		// get employees from the service
		List<Employee> theEmployees = employeeService.getEmployees();
		
		// add the employees to the model
		theModel.addAttribute("employees", theEmployees);		
		
		return "list-employees";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		EmployeeTeam theEmployeeTeam = new EmployeeTeam();
		Employee theEmployee = new Employee();
		theEmployeeTeam.setEmployee(theEmployee);

		// get roles and teams from the service for selection list
		List<Role> theRoles = roleService.getRoles();
		List<Team> theTeams = teamService.getTeams();
		
		theModel.addAttribute("employeeTeam", theEmployeeTeam);
		theModel.addAttribute("roleList", theRoles);
		theModel.addAttribute("teamList", theTeams);
		
		return "employee-form";
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@Valid @ModelAttribute("employeeTeam") EmployeeTeam theEmployeeTeam, BindingResult theBindingResult) {
		
		if(theBindingResult.hasErrors()) {
			return "employee-form";
		}
		else {
			// save the employee using our service
			employeeService.saveEmployee(theEmployeeTeam.getEmployee());
			employeeTeamService.saveEmployeeTeam(theEmployeeTeam);
			return "redirect:/employee/list";
		}
		
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId,
									Model theModel) {
		
		
		// get the employee from the service
		EmployeeTeam theEmployeeTeam = employeeTeamService.getEmployeeTeam(theId);
		
		// get employees from the service
		List<Role> theRoles = roleService.getRoles();
		List<Team> theTeams = teamService.getTeams();
		
		// set employee as a model attribute to pre-populate the form
		theModel.addAttribute("employeeTeam", theEmployeeTeam);
		theModel.addAttribute("roleList", theRoles);
		theModel.addAttribute("teamList", theTeams);
		
		// send over to our form
		return "employee-form";
	}
	
	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("employeeId") int theId) {
		
		if(theId != 20) { //ensure that no one backends and deletes the UNASSIGNED team.
			// delete the employee
			employeeService.deleteEmployee(theId);
		}
		
		return "redirect:/employee/list";
	}
	
	@PostMapping("/search")
	public String searchEmployees(@RequestParam("theSearchName")
								  String theSearchName,
								  Model theModel) {
		// search employees from the service
		List<Employee> theEmployees =
				employeeService.searchEmployees(theSearchName);
		
		// add the employees to the model
		theModel.addAttribute("employees",theEmployees);
		
		return "list-employees";
	}
	
}
