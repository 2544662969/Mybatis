package com.zhjg.mybatis.pojo;

public class Pet {

	private int id;
	private String name;
	private Person person;//1:1
	
	@Override
	public String toString() {
		System.out.println("Pet [id=" + id + ", name=" + name + ", person=" + person.getName() + "]");
		return null;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	
}
