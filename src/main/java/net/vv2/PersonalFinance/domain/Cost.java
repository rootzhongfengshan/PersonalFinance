package net.vv2.PersonalFinance.domain;
import java.util.Date;

/**
 * Healthy
 * @author J.Sky bosichong@qq.com
 * @create 2017-06-13 22:50
 **/

public class Cost {



    private Integer id;
    private Date consume_date;
    private String consume_name;
    private Float project_fee;
    private Float consume_fee;
    private String consume_type;
    private String consume_category;
    private String pay_way;
    private String remarks;

    public Cost(){

    }

    public Cost(Integer id, Date consume_date, String consume_name, Float project_fee, Float consume_fee, String consume_type, String consume_category, String pay_way, String remarks) {
        this.id = id;
        this.consume_date = consume_date;
        this.consume_name = consume_name;
        this.project_fee = project_fee;
        this.consume_fee = consume_fee;
        this.consume_type = consume_type;
        this.consume_category = consume_category;
        this.pay_way = pay_way;
        this.remarks = remarks;
    }


    public Cost(Date consume_date, String consume_name, Float project_fee, Float consume_fee, String consume_type, String consume_category, String pay_way, String remarks) {
        this.consume_date = consume_date;
        this.consume_name = consume_name;
        this.project_fee = project_fee;
        this.consume_fee = consume_fee;
        this.consume_type = consume_type;
        this.consume_category = consume_category;
        this.pay_way = pay_way;
        this.remarks = remarks;
    }

    public Cost(Date consume_date, String consume_name, Float project_fee, Float consume_fee, String consume_type, String consume_category, String pay_way) {
        this.consume_date = consume_date;
        this.consume_name = consume_name;
        this.project_fee = project_fee;
        this.consume_fee = consume_fee;
        this.consume_type = consume_type;
        this.consume_category = consume_category;
        this.pay_way = pay_way;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getConsume_date() {
        return consume_date;
    }

    public void setConsume_date(Date consume_date) {
        this.consume_date = consume_date;
    }

    public String getConsume_name() {
        return consume_name;
    }

    public void setConsume_name(String consume_name) {
        this.consume_name = consume_name;
    }

    public Float getProject_fee() {
        return project_fee;
    }

    public void setProject_fee(Float project_fee) {
        this.project_fee = project_fee;
    }

    public Float getConsume_fee() {
        return consume_fee;
    }

    public void setConsume_fee(Float consume_fee) {
        this.consume_fee = consume_fee;
    }

    public String getConsume_type() {
        return consume_type;
    }

    public void setConsume_type(String consume_type) {
        this.consume_type = consume_type;
    }

    public String getConsume_category() {
        return consume_category;
    }

    public void setConsume_category(String consume_category) {
        this.consume_category = consume_category;
    }

    public String getPay_way() {
        return pay_way;
    }

    public void setPay_way(String pay_way) {
        this.pay_way = pay_way;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Cost{" +
                "id=" + id +
                ", consume_date=" + consume_date +
                ", consume_name='" + consume_name + '\'' +
                ", project_fee=" + project_fee +
                ", consume_fee=" + consume_fee +
                ", consume_type='" + consume_type + '\'' +
                ", consume_category='" + consume_category + '\'' +
                ", pay_way='" + pay_way + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
