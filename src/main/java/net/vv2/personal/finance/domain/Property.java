package net.vv2.personal.finance.domain;
import java.util.Date;

/**
 * Healthy
 * @author zhongfs zhongfengshan@yahoo.com
 * @create 2017-06-13 22:50
 **/

public class Property {
    private Integer id;
    private Date record_date;
    private String wechat1;
    private String wechat2;
    private String alipay1;
    private String alipay2;
    private String zhaoshang;
    private String jiaotong;
    private String jianshe;
    private String beijing;
    private String maomingyouzheng;
    private String beijingyouzheng;
    private String gongshang;
    private String all_sum;
    private String remarks;
    public Property(){

    }

    public Property(Date record_date, String wechat1, String wechat2, String alipay1, String alipay2, String zhaoshang, String jiaotong, String jianshe, String beijing, String maomingyouzheng, String beijingyouzheng, String gongshang) {
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

    public Property(Date record_date, String wechat1, String wechat2, String alipay1, String alipay2, String zhaoshang, String jiaotong, String jianshe, String beijing, String maomingyouzheng, String beijingyouzheng, String gongshang, String remarks) {
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

    public Property(Date record_date, String wechat1, String wechat2, String alipay1, String alipay2, String zhaoshang, String jiaotong, String jianshe, String beijing, String maomingyouzheng, String beijingyouzheng, String gongshang, String all_sum, String remarks) {
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

    public String getWechat1() {
        return wechat1;
    }

    public void setWechat1(String wechat1) {
        this.wechat1 = wechat1;
    }

    public String getWechat2() {
        return wechat2;
    }

    public void setWechat2(String wechat2) {
        this.wechat2 = wechat2;
    }

    public String getAlipay1() {
        return alipay1;
    }

    public void setAlipay1(String alipay1) {
        this.alipay1 = alipay1;
    }

    public String getAlipay2() {
        return alipay2;
    }

    public void setAlipay2(String alipay2) {
        this.alipay2 = alipay2;
    }

    public String getZhaoshang() {
        return zhaoshang;
    }

    public void setZhaoshang(String zhaoshang) {
        this.zhaoshang = zhaoshang;
    }

    public String getJiaotong() {
        return jiaotong;
    }

    public void setJiaotong(String jiaotong) {
        this.jiaotong = jiaotong;
    }

    public String getJianshe() {
        return jianshe;
    }

    public void setJianshe(String jianshe) {
        this.jianshe = jianshe;
    }

    public String getBeijing() {
        return beijing;
    }

    public void setBeijing(String beijing) {
        this.beijing = beijing;
    }

    public String getMaomingyouzheng() {
        return maomingyouzheng;
    }

    public void setMaomingyouzheng(String maomingyouzheng) {
        this.maomingyouzheng = maomingyouzheng;
    }

    public String getBeijingyouzheng() {
        return beijingyouzheng;
    }

    public void setBeijingyouzheng(String beijingyouzheng) {
        this.beijingyouzheng = beijingyouzheng;
    }

    public String getGongshang() {
        return gongshang;
    }

    public void setGongshang(String gongshang) {
        this.gongshang = gongshang;
    }

    public String getAll_sum() {
        return all_sum;
    }

    public void setAll_sum(String all_sum) {
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
