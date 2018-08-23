package com.kgainc.empmanager.entity;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="employee")
public class Employee {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@NotNull(message="is required")
	@Size(min=1, message="is required")
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="middle_name")
	private String middleName;
	
	@NotNull(message="is required")
	@Size(min=1, message="is required")
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="suffix")
	private String suffix;
	
	@Column(name="email")
	private String email;
	
	@Column(name="desk_ext")
	private String deskExt;
	
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="cell_number")
	private EmployeeCell cellNumber;
	
	@Column(name="title")
	private String title;
	
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@Column(name="start_date")
	private Date startDate;
	
	@Column(name="active")
	private Boolean active;
	
	@Column(name="shirt_size")
	private String shirtSize;
	
	@OneToOne(mappedBy="employee", cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	private EmployeeTeam employeeTeam;
	
	public Employee() {
				
	}
	
	public Employee(String firstName, String middleName, String lastName, String suffix, String email, String title, Date startDate, Boolean active, String shirtSize, String deskExt, EmployeeCell cellNumber) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.suffix = suffix;
		this.email = email;
		this.deskExt = deskExt;
		this.cellNumber = cellNumber;
		this.title = title;
		this.startDate = startDate;
		this.active = active;
		this.shirtSize = shirtSize;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getShirtSize() {
		return shirtSize;
	}

	public void setShirtSize(String shirtSize) {
		this.shirtSize = shirtSize;
	}

	public String getDeskExt() {
		return deskExt;
	}

	public void setDeskExt(String deskExt) {
		this.deskExt = deskExt;
	}

	public EmployeeCell getCellNumber() {
		return cellNumber;
	}

	public void setCellNumber(EmployeeCell cellNumber) {
		this.cellNumber = cellNumber;
	}
	
	public EmployeeTeam getEmployeeTeam() {
		return employeeTeam;
	}

	public void setEmployeeTeam(EmployeeTeam employeeTeam) {
		this.employeeTeam = employeeTeam;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id
					+ ", firstName=" + firstName
					+ ", middleName=" + middleName
					+ ",lastName=" + lastName
					+ ", suffix=" + suffix
					+ ", email=" + email
					+ ", title=" + title
					+ ", start_date=" + startDate
					+ ", active=" + active
					+ ", shirtSize=" + shirtSize
					+ ", deskExt=" + deskExt
					+ ", cellNumber=" + cellNumber
					+ "]";
	}
	
}
