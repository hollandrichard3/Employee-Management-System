package com.kgainc.empmanager.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="team")
public class Team {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@NotNull(message="is required")
	@Size(min=1, message="is required")
	@Column(name="team_name")
	private String teamName;
	
	@OneToOne(cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH})
	@JoinColumn(name="manager_id")
	private Employee manager;
	
	@OneToMany(fetch=FetchType.LAZY, cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
			name="employees_teams",
			joinColumns=@JoinColumn(name="team_id"),
			inverseJoinColumns=@JoinColumn(name="employee_id")
			)
	private List<Employee> employees = new ArrayList<Employee>();
	
	public Team() {
		
	}

	public Team(String teamName) {
		this.teamName = teamName;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}
	
	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", teamName=" + teamName + "]";
	}
	
}
