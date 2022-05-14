package fr.insa.BankWebServices.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity		// Annotation PA pour préparer cet objet au stockage dans une database JPA
public class Client {

	private @Id @GeneratedValue Long id; // primary key et instancier automatiquement
	private String name;	
	private int age;	
	private double solde;
	
	public Client() {}
	
	public Client(String name, int age, double solde) {
		this.name = name;
		this.age = age;
		this.solde = solde;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getSolde() {
		return solde;
	}
	public void setSolde(double solde) {
		this.solde = solde;
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, id, name, solde);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return age == other.age && Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Double.doubleToLongBits(solde) == Double.doubleToLongBits(other.solde);
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", age=" + age + ", solde=" + solde + "]";
	}
	
	
}
