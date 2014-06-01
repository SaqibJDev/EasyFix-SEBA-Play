package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class TestTable extends Model{
	public String name;

	public TestTable(String name) {
		super();
		this.name = name;
	}
	
}
