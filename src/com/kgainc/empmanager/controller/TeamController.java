package com.kgainc.empmanager.controller;

import java.util.List;

import javax.validation.Valid;

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
import com.kgainc.empmanager.entity.Team;
import com.kgainc.empmanager.service.EmployeeService;
import com.kgainc.empmanager.service.EmployeeTeamService;
import com.kgainc.empmanager.service.TeamService;

@Controller
@RequestMapping("/team")
public class TeamController {

	// add an initbinder ... to convert trim input strings
	// remove leading and trailing whitespace
	// resolve issue for our validation
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true); // true means, if all white-space, trim down to null
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor); // registers newly created stringTrimmerEditor to String class for all Strings incoming into this controller.
		
	}
	
	// need to inject our team service
	@Autowired
	private TeamService teamService;
	
	// inject employee service
	@Autowired
	private EmployeeService employeeService;
	
	// inject employeeTeam service
	@Autowired
	private EmployeeTeamService employeeTeamService;
	
	@GetMapping("/list")
	public String listTeams(Model theModel) {
	
		// get teams from the service
		List<Team> theTeams = teamService.getTeams();
		
		// add the teams to the model
		theModel.addAttribute("teams", theTeams);
		
		return "list-teams";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		EmployeeTeam theEmployeeTeam = new EmployeeTeam();
		Team theTeam = new Team();
		theEmployeeTeam.setTeam(theTeam);
		
		theModel.addAttribute("team", theTeam);
		
		return "team-form";
	}
	
	@PostMapping("/saveTeam")
	public String saveTeam(@Valid @ModelAttribute("team") Team theTeam, BindingResult theBindingResult) {
		
		if(theBindingResult.hasErrors()) {
			return "team-form";
		}
		else {
		// save the team using our service
		teamService.saveTeam(theTeam);
		return "redirect:/team/list";
		}
		
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("teamId") int theId,
									Model theModel) {
		
		// get the team from the service
		// get list of AsstManagers on team
		Team theTeam = teamService.getTeam(theId);
		List<Employee> theAsstManagers = employeeTeamService.getAsstManagers(theId);
		
		// set model attributes
		theModel.addAttribute("team", theTeam);
		theModel.addAttribute("asstManagers", theAsstManagers);
		
		// send over to our form
		return "team-form";
	}
	
	@GetMapping("/delete")
	public String deleteTeam(@RequestParam("teamId") int theId) {
		
		// delete the team
		teamService.deleteTeam(theId);
		
		return "redirect:/team/list";
	}
	
	@PostMapping("/search")
	public String searchTeams(@RequestParam("theSearchName")
								  String theSearchName,
								  Model theModel) {
		// search teams from the service
		List<Team> theTeams =
				teamService.searchTeams(theSearchName);
		
		// add the teams to the model
		theModel.addAttribute("teams",theTeams);
		
		return "list-teams";
	}
	
}
