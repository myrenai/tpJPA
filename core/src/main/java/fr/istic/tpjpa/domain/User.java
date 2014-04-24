package fr.istic.tpjpa.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
	@NamedQuery(name="User.byUserId", query="select m from User m where userId = :userId")
})
public class User implements Serializable{

	private static final long serialVersionUID = 6107730830653024039L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(unique=true)
	private String userId;
	private String userPw;
	private String firstName;
	private String lastName;
	
	@OneToMany(fetch = FetchType.LAZY, 
			mappedBy="user", cascade = {CascadeType.REMOVE })
	private Collection<Blog> blogs;
	
	public static User build(){
		return new User();
	}
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="street", column=@Column(name="HOME_STREET")),
		@AttributeOverride(name="city", column=@Column(name="HOME_CITY")),
		@AttributeOverride(name="codePostal", column=@Column(name="HOME_CODE_POSTAL")),
		@AttributeOverride(name="nation", column=@Column(name="HOME_NATION")),
	})
	private Address homeAddress;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="street", column=@Column(name="OFFICE_STREET")),
		@AttributeOverride(name="city", column=@Column(name="OFFICE_CITY")),
		@AttributeOverride(name="codePostal", column=@Column(name="OFFICE_CODE_POSTAL")),
		@AttributeOverride(name="nation", column=@Column(name="OFFICE_NATION")),
	})
	private Address officeAddress;
	
	@Temporal(TemporalType.DATE)
	private Date birthDate;

	public int getId() {
		return id;
	}

	public User setId(int id) {
		this.id = id;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public User setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public String getUserPw() {
		return userPw;
	}

	public User setUserPw(String userPw) {
		this.userPw = userPw;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public User setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public User setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public User setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
		return this;
	}

	public Address getOfficeAddress() {
		return officeAddress;
	}

	public User setOfficeAddress(Address officeAddress) {
		this.officeAddress = officeAddress;
		return this;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public User setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
		return this;
	}


	public String toString() {
		return "User [id=" + id + ", userId=" + userId + ", userPw=" + userPw
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", homeAddress=" + homeAddress + ", officeAddress="
				+ officeAddress + ", birthDate=" + birthDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((homeAddress == null) ? 0 : homeAddress.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result
				+ ((officeAddress == null) ? 0 : officeAddress.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((userPw == null) ? 0 : userPw.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (homeAddress == null) {
			if (other.homeAddress != null)
				return false;
		} else if (!homeAddress.equals(other.homeAddress))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (officeAddress == null) {
			if (other.officeAddress != null)
				return false;
		} else if (!officeAddress.equals(other.officeAddress))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userPw == null) {
			if (other.userPw != null)
				return false;
		} else if (!userPw.equals(other.userPw))
			return false;
		return true;
	}

	

	



}
