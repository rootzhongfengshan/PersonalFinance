package net.vv2.personal.finance.domain;

import java.util.Date;

/**
 * @author zhongfs zhongfengshan@yahoo.com
 * @create 2017-06-13 22:50
 **/

public class Budget {

    private Integer id;

    private Date budgetMonth;

    private String budgetName;

    private String budgetValue;

    private String budgetRemark;


    public Budget() {

    }

    public Budget(Integer id, Date budgetMonth, String budgetName, String budgetValue, String budgetRemark) {
        this.id = id;
        this.budgetMonth = budgetMonth;
        this.budgetName = budgetName;
        this.budgetValue = budgetValue;
        this.budgetRemark = budgetRemark;
    }

    public Budget(Integer id, Date budgetMonth, String budgetName, String budgetValue) {
        this.id = id;
        this.budgetMonth = budgetMonth;
        this.budgetName = budgetName;
        this.budgetValue = budgetValue;
    }

    public Budget(Date budgetMonth, String budgetName, String budgetValue) {
        this.id = id;
        this.budgetMonth = budgetMonth;
        this.budgetName = budgetName;
        this.budgetValue = budgetValue;
    }


    public Budget(Date budgetMonth, String budgetName, String budgetValue, String budgetRemark) {
        this.budgetMonth = budgetMonth;
        this.budgetName = budgetName;
        this.budgetValue = budgetValue;
        this.budgetRemark = budgetRemark;
    }


    public Budget(Integer id, Date budgetMonth, String budgetName) {
        this.id = id;
        this.budgetMonth = budgetMonth;
        this.budgetName = budgetName;
    }


    @Override
    public String toString() {
        return "BudgetConfig{" +
                "id=" + id +
                ", budgetMonth='" + budgetMonth + '\'' +
                ", budgetName='" + budgetName + '\'' +
                ", budgetValue='" + budgetValue + '\'' +
                ", budgetRemark='" + budgetRemark + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getBudgetMonth() {
        return budgetMonth;
    }

    public void setBudgetMonth(Date budgetMonth) {
        this.budgetMonth = budgetMonth;
    }

    public String getBudgetName() {
        return budgetName;
    }

    public void setBudgetName(String budgetName) {
        this.budgetName = budgetName;
    }

    public String getBudgetRemark() {
        return budgetRemark;
    }

    public void setBudgetRemark(String budgetRemark) {
        this.budgetRemark = budgetRemark;
    }

    public String getBudgetValue() {
        return budgetValue;
    }

    public void setBudgetValue(String budgetValue) {
        this.budgetValue = budgetValue;
    }
}
