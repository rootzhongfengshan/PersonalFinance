package net.vv2.SelfManagement.domain;


import java.util.Date;

/**
 * Weight
 * @author J.Sky bosichong@qq.com
 * @create 2017-06-13 22:50
 **/

public class Weight {
    private Integer id;
    private Float weight;
    private Date create_time;


    public Weight(){}

    public Weight(Float weight, Date create_time) {
        this.weight = weight;
        this.create_time = create_time;
    }

    public Weight(Float weight_value) {
    	this.weight = weight_value;
	}

	public void setId(Integer id) {
        this.id = id;
    }

    
    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

   
    public Integer getId() {
        return id;
    }

    
    public Float getWeight() {
        return weight;
    }

    public Date getCreate_time() {
        return create_time;
    }

  
    @Override
    public String toString() {
        return "Weight{" +
                "id=" + id +  
                ", weight=" + weight +
                ", create_time=" + create_time +
                '}';
    }
}
