package models;

import java.sql.Date;

public class Car {
	private final String id;
	private Date prod_date;
	private String color;
	private int power;
	private Person person;
	
	public Car(String id, Date prod_date, String color, int power, Person person) {
		super();
		this.id = id;
		this.prod_date = prod_date;
		this.color = color;
		this.power = power;
		this.person = person;
	}

	public String getId() {
		return id;
	}

	public Date getProd_date() {
		return prod_date;
	}

	public void setProd_date(Date prod_date) {
		this.prod_date = prod_date;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	
}
