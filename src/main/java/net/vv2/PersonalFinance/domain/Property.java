package net.vv2.PersonalFinance.domain;
import java.util.Date;

/**
 * Healthy
 * @author J.Sky bosichong@qq.com
 * @create 2017-06-13 22:50
 **/

public class Property {
    private Integer id;
    private Date record_date;
    private Float wechat1;
    private Float wechat2;
    private Float alipay1;
    private Float alipay2;
    private Float zhaoshang;
    private Float jiaotong;
    private Float jianshe;
    private Float beijing;
    private Float maomingyouzheng;
    private Float beijingyouzheng;
    private Float gongshang;
    private Float all_sum;
    private String remarks;
    public Property(){

    }

    public Property(Date record_date, Float wechat1, Float wechat2, Float alipay1, Float alipay2, Float zhaoshang, Float jiaotong, Float jianshe, Float beijing, Float maomingyouzheng, Float beijingyouzheng, Float gongshang) {
        this.record_date = record_date;
        this.wechat1 = wechat1;
        this.wechat2 = wechat2;
        this.alipay1 = alipay1;
        this.alipay2 = alipay2;
        this.zhaoshang = zhaoshang;
        this.jiaotong = jiaotong;
        this.jianshe = jianshe;
        this.beijing = beijing;
        this.maomingyouzheng = maomingyouzheng;
        this.beijingyouzheng = beijingyouzheng;
        this.gongshang = gongshang;
    }

    public Property(Date record_date, Float wechat1, Float wechat2, Float alipay1, Float alipay2, Float zhaoshang, Float jiaotong, Float jianshe, Float beijing, Float maomingyouzheng, Float beijingyouzheng, Float gongshang, String remarks) {
        this.record_date = record_date;
        this.wechat1 = wechat1;
        this.wechat2 = wechat2;
        this.alipay1 = alipay1;
        this.alipay2 = alipay2;
        this.zhaoshang = zhaoshang;
        this.jiaotong = jiaotong;
        this.jianshe = jianshe;
        this.beijing = beijing;
        this.maomingyouzheng = maomingyouzheng;
        this.beijingyouzheng = beijingyouzheng;
        this.gongshang = gongshang;
        this.remarks = remarks;
    }

    public Property(Date record_date, Float wechat1, Float wechat2, Float alipay1, Float alipay2, Float zhaoshang, Float jiaotong, Float jianshe, Float beijing, Float maomingyouzheng, Float beijingyouzheng, Float gongshang, Float all_sum, String remarks) {
        this.record_date = record_date;
        this.wechat1 = wechat1;
        this.wechat2 = wechat2;
        this.alipay1 = alipay1;
        this.alipay2 = alipay2;
        this.zhaoshang = zhaoshang;
        this.jiaotong = jiaotong;
        this.jianshe = jianshe;
        this.beijing = beijing;
        this.maomingyouzheng = maomingyouzheng;
        this.beijingyouzheng = beijingyouzheng;
        this.gongshang = gongshang;
        this.all_sum = all_sum;
        this.remarks = remarks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getRecord_date() {
        return record_date;
    }

    public void setRecord_date(Date record_date) {
        this.record_date = record_date;
    }

    public Float getWechat1() {
        return wechat1;
    }

    public void setWechat1(Float wechat1) {
        this.wechat1 = wechat1;
    }

    public Float getWechat2() {
        return wechat2;
    }

    public void setWechat2(Float wechat2) {
        this.wechat2 = wechat2;
    }

    public Float getAlipay1() {
        return alipay1;
    }

    public void setAlipay1(Float alipay1) {
        this.alipay1 = alipay1;
    }

    public Float getAlipay2() {
        return alipay2;
    }

    public void setAlipay2(Float alipay2) {
        this.alipay2 = alipay2;
    }

    public Float getZhaoshang() {
        return zhaoshang;
    }

    public void setZhaoshang(Float zhaoshang) {
        this.zhaoshang = zhaoshang;
    }

    public Float getJiaotong() {
        return jiaotong;
    }

    public void setJiaotong(Float jiaotong) {
        this.jiaotong = jiaotong;
    }

    public Float getJianshe() {
        return jianshe;
    }

    public void setJianshe(Float jianshe) {
        this.jianshe = jianshe;
    }

    public Float getBeijing() {
        return beijing;
    }

    public void setBeijing(Float beijing) {
        this.beijing = beijing;
    }

    public Float getMaomingyouzheng() {
        return maomingyouzheng;
    }

    public void setMaomingyouzheng(Float maomingyouzheng) {
        this.maomingyouzheng = maomingyouzheng;
    }

    public Float getBeijingyouzheng() {
        return beijingyouzheng;
    }

    public void setBeijingyouzheng(Float beijingyouzheng) {
        this.beijingyouzheng = beijingyouzheng;
    }

    public Float getGongshang() {
        return gongshang;
    }

    public void setGongshang(Float gongshang) {
        this.gongshang = gongshang;
    }

    public Float getAll_sum() {
        return all_sum;
    }

    public void setAll_sum(Float all_sum) {
        this.all_sum = all_sum;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Property{" +
                "id=" + id +
                ", record_date=" + record_date +
                ", wechat1=" + wechat1 +
                ", wechat2=" + wechat2 +
                ", alipay1=" + alipay1 +
                ", alipay2=" + alipay2 +
                ", zhaoshang=" + zhaoshang +
                ", jiaotong=" + jiaotong +
                ", jianshe=" + jianshe +
                ", beijing=" + beijing +
                ", maomingyouzheng=" + maomingyouzheng +
                ", beijingyouzheng=" + beijingyouzheng +
                ", gongshang=" + gongshang +
                ", all_sum=" + all_sum +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
