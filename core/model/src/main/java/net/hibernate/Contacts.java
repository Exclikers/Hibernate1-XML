package net.hibernate;


public class Contacts {

	private int contactId;
    private long landline;
    private long mobile;
    private String email;
    
    private Person person;
 
    public Contacts() {}
 
    public Contacts(long landline, long mobile, 
    			String email, Person person) {

 
        this.landline = landline;
        this.mobile = mobile;
        this.email = email;
        this.person = person;
    }
 
   	public int getContactId() {
		return contactId;
	}
	public void setContactId( int contactId ) {
	 	this.contactId = contactId;
	}
   	public long getLandline() {
		return landline;
	}
	public void setLandline( long landline ) {
	 	this.landline = landline;
	}
   	public long getMobile() {
		return mobile;
	}
	public void setMobile( long mobile ) {
	 	this.mobile = mobile;
	}
   	public String getEmail() {
		return email;
	}
	public void setEmail( String email ) {
	 	this.email = email;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	
	public Person getPerson() {
		return this.person;
	}
}