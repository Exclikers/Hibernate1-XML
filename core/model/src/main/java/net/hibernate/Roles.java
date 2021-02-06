package net.hibernate;

import java.util.Set;

public class Roles {

	private int roleId;
    private String role;
    
    private Set<Person> person;
 
    public Roles() {}
 
    public Roles(String role) {
        this.role = role;
    }
    
	
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	} 
	
	public int getRoleId() {
		return this.roleId;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return this.role;
	}
	
	public void setPerson(Set<Person> person) {
		this.person = person;
	}
	
	public Set<Person> getPerson() {
		return person;
	}
	
	@Override
    public String toString() {
        return "Roles: \n" + 
		"Role id: " + this.roleId + "," +
		"'" + this.role + "'\n";
    }

}