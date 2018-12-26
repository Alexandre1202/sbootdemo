package br.com.ab.sbootdemo.user.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class UserModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotEmpty
	private String userName;
	@NotEmpty
	private String fullName;
	@NotEmpty
	private String email;
	
	@OneToMany(mappedBy="userModel", orphanRemoval=true, cascade=CascadeType.PERSIST)
	private List<AddressModel> addressModel; 
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<AddressModel> getAddressModel() {
		return addressModel;
	}
	public void setAddressModel(List<AddressModel> addressModel) {
		this.addressModel = addressModel;
	}

}
