package net.vv2.PersonalFinance.domain;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Healthy
 * @author J.Sky bosichong@qq.com
 * @create 2017-06-13 22:50
 **/

public class Income {
    private Integer id;
    private String months;
    private String detail;
    private Float rec_amount;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date rec_date;
    private String remarks;
    private String rec_datesw;

    public Income() {

    }


    public Income(Integer id, String months, String detail, Float rec_amount, Date rec_date, String remarks) {
        this.id = id;
        this.months = months;
        this.detail = detail;
        this.rec_amount = rec_amount;
        this.rec_date = rec_date;
        this.remarks = remarks;
    }

    /*
    public Income(Integer id, String months, String detail, Float rec_amount, java.sql.Date rec_date, String remarks) {
        this.id = id;
        this.months = months;
        this.detail = detail;
        this.rec_amount = rec_amount;
        this.rec_date = rec_date;
        this.remarks = remarks;
    }
    */



    public Income(Integer id, String months, String detail, Float rec_amount, Date rec_date) {
        this.id = id;
        this.months = months;
        this.detail = detail;
        this.rec_amount = rec_amount;
        this.rec_date = rec_date;
    }

    public Income(String months, String detail, Float rec_amount, Date rec_date, String remarks) {
        this.months = months;
        this.detail = detail;
        this.rec_amount = rec_amount;
        this.rec_date = rec_date;
        this.remarks = remarks;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMonths() {
        return months;
    }

    public void setMonths(String months) {
        this.months = months;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Float getRec_amount() {
        return rec_amount;
    }

    public void setRec_amount(Float rec_amount) {
        this.rec_amount = rec_amount;
    }

    public Date getRec_date() {
        return rec_date;
    }

    public void setRec_date(Date rec_date) {
        this.rec_date = rec_date;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRec_datesw() {
		return rec_datesw;
	}


	public void setRec_datesw(String rec_datesw) {
		this.rec_datesw = rec_datesw;
	}


	@Override
    public String toString() {
        return "Income{" +
                "id=" + id +
                ", months='" + months + '\'' +
                ", detail='" + detail + '\'' +
                ", rec_amount=" + rec_amount +
                ", rec_date=" + rec_date +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
