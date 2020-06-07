package net.vv2.personal.finance.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author zhongfs zhongfengshan@yahoo.com
 * @create 2017-06-13 22:50
 **/

public class BudgetConfig {

    private Integer id;

    private String budgetKey;

    private String budgetName;

    private String budgetShowName;

    private String budgetDefaultValue;


    public BudgetConfig() {

    }

    public BudgetConfig(Integer id, String budgetKey, String budgetName, String budgetShowName, String budgetDefaultValue) {
        this.id = id;
        this.budgetKey = budgetKey;
        this.budgetName = budgetName;
        this.budgetShowName = budgetShowName;
        this.budgetDefaultValue = budgetDefaultValue;
    }

    public BudgetConfig(Integer id, String budgetKey, String budgetName, String budgetShowName) {
        this.id = id;
        this.budgetKey = budgetKey;
        this.budgetName = budgetName;
        this.budgetShowName = budgetShowName;
    }

    public BudgetConfig(Integer id, String budgetKey, String budgetName) {
        this.id = id;
        this.budgetKey = budgetKey;
        this.budgetName = budgetName;
    }

    public BudgetConfig(Integer id, String budgetKey) {
        this.id = id;
        this.budgetKey = budgetKey;
    }

    @Override
    public String toString() {
        return "BudgetConfig{" +
                "id=" + id +
                ", budgetKey='" + budgetKey + '\'' +
                ", budgetName='" + budgetName + '\'' +
                ", budgetShowName='" + budgetShowName + '\'' +
                ", budgetDefaultValue='" + budgetDefaultValue + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBudgetKey() {
        return budgetKey;
    }

    public void setBudgetKey(String budgetKey) {
        this.budgetKey = budgetKey;
    }

    public String getBudgetName() {
        return budgetName;
    }

    public void setBudgetName(String budgetName) {
        this.budgetName = budgetName;
    }

    public String getBudgetShowName() {
        return budgetShowName;
    }

    public void setBudgetShowName(String budgetShowName) {
        this.budgetShowName = budgetShowName;
    }

    public String getBudgetDefaultValue() {
        return budgetDefaultValue;
    }

    public void setBudgetDefaultValue(String budgetDefaultValue) {
        this.budgetDefaultValue = budgetDefaultValue;
    }
}
