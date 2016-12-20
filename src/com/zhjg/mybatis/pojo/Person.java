package com.zhjg.mybatis.pojo;

import java.util.List;

public class Person {

	private int id;
	private String name;
	private int age;
	private List<Pet> pets; //N:1
	
	@Override
	public String toString() {
		System.out.println("---------------------------------");
		System.out.println("id:"+this.id);
		System.out.println("name:"+this.name);
		System.out.println("age:"+this.age);
		
		if(this.pets != null && this.pets.size() > 0){
			System.out.println("pets:");
			for (Pet pet : pets) {
				System.out.println(pet.getName());
			}
		}else{
			System.out.println("without any pet !");
		}
		System.out.println("---------------------------------");
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public List<Pet> getPets() {
		return pets;
	}
	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}
}
