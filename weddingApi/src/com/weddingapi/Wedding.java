package com.weddingapi;

public class Wedding {
	public String id;
	public String person1;
	public String person2;
    public String address;
    public String description;

    public Wedding(){};

    public Wedding(String id, 
    		String person1, 
    		String person2, 
    		String address, 
    		String description) 
    {
    	this.id = id;
    	this.person1 = person1;
    	this.person2 = person2;
    	this.address = address;
    	this.description = description;
    }
    
    //Getters
    public String getId()
    {
    	return this.id;
    }
    public String getPerson1()
    {
    	return this.person1;
    }
    public String getPerson2()
    {
    	return this.person2;
    }
    public String getAddress()
    {
    	return this.address;
    }
    public String getDescription()
    {
    	return this.description;
    }
    
    //Setters
    public void setId(String id)
    {
    	this.id = id;
    }
    public void setPerson1(String person1)
    {
    	this.person1 = person1;
    }
    public void setPerson2(String person2)
    {
    	this.person2 = person2;
    }
    public void setAddress(String address)
    {
    	this.address = address;
    }
    public void setDescription(String description)
    {
    	this.description = description;
    }
}
