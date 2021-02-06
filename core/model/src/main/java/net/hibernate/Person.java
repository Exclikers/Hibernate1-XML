package net.hibernate;

import java.util.Date; 
import java.util.Set;

public class Person {

	private int personId;
	private String title;
	private String firstName;
	private String lastName;
	private String middleName;
	private String suffix;

	private int streetNumber;
	private String barangay;
	private String municipality;
	private int zipCode;

	private Date birthdate;
	private double gradeWeightedAverage;
	private Date dateHired;
	private String employmentStatus;

	private Set<Contacts> contacts;
	private Set<Roles> roles;

	public Person() {}

	public Person(String title, String firstName, String lastName, String middleName, String suffix, 
				 int streetNumber, String barangay, String municipality, int zipCode, 
				 Date birthdate, double gradeWeightedAverage, Date dateHired, String employmentStatus) {

		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.suffix = suffix;
		this.streetNumber = streetNumber;
		this.barangay = barangay;
		this.municipality = municipality;
		this.zipCode = zipCode;
		this.birthdate = birthdate;
		this.gradeWeightedAverage = gradeWeightedAverage;
		this.dateHired = dateHired;
		this.employmentStatus = employmentStatus;
	}

	public int getPersonId() {
		return personId;
	}
	public void setPersonId( int personId ) {
	 	this.personId = personId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle( String title ) {
	 	this.title = title;
	}
	public String getFirstName() {
		return firstName;
	}
	 public void setFirstName( String firstName ) {
	 	this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	 public void setLastName( String lastName ) {
	 	this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	 public void setMiddleName( String middleName ) {
	 	this.middleName = middleName;
	}
	public String getSuffix() {
		return suffix;
	}
	 public void setSuffix( String suffix ) {
	 	this.suffix = suffix;
	}
	public int getStreetNumber() {
		return streetNumber;
	}
	 public void setStreetNumber( int streetNumber ) {
	 	this.streetNumber = streetNumber;
	}
	public String getBarangay() {
		return barangay;
	}
	 public void setBarangay( String barangay ) {
	 	this.barangay = barangay;
	}
	public String getMunicipality() {
		return municipality;
	}
	 public void setMunicipality( String municipality ) {
	 	this.municipality = municipality;
	}
	public int getZipCode() {
		return zipCode;
	}
	 public void setZipCode( int zipCode ) {
	 	this.zipCode = zipCode;
	}	
	public Date getBirthdate() {
		return birthdate;
	}
	 public void setBirthdate( Date birthdate ) {
	 	this.birthdate = birthdate;
	}
	public double getGradeWeightedAverage() {
		return gradeWeightedAverage;
	}
	 public void setGradeWeightedAverage( double gradeWeightedAverage ) {
	 	this.gradeWeightedAverage = gradeWeightedAverage;
	}
	public Date getDateHired() {
		return dateHired;
	}
	 public void setDateHired( Date dateHired ) {
	 	this.dateHired = dateHired;
	}
	public String getEmploymentStatus() {
		return employmentStatus;
	}
	 public void setEmploymentStatus( String employmentStatus ) {
	 	this.employmentStatus = employmentStatus;
	}
	public void setContacts(Set<Contacts> contacts) {
		this.contacts = contacts;
	}
	public Set<Contacts> getContacts() {
		return this.contacts;
	}
	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}
	public Set<Roles> getRoles() {
		return this.roles;
	}
}