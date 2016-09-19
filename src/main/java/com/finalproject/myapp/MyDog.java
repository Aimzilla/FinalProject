package com.finalproject.myapp;

public class MyDog {
	
	private String id;
	private String name;
	private String breed;
	private String sex;
	private String age;
	private String desc;
	private String shelterID;
	public String getShelterID() {
		return shelterID;
	}
	public void setShelterID(String shelterID) {
		this.shelterID = shelterID;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public MyDog(String id, String name, String breed, String sex, String age, String size, String options,
			String photo) {
		
		setId(id);
		setName(name);
		setBreed(breed);
		setSex(sex);
		setAge(age);
		setSize(size);
		setOptions(options);
		setPhoto(photo);
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBreed() {
		return breed;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	private String size;
	private String options;
	private String photo;
}
