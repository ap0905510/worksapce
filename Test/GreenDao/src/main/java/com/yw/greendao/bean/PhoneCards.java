package com.yw.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 作者：create by YW
 * 日期：2017.02.21 15:59
 * 描述：记录通信录电话 & 打过的电话
 */
@Entity
public class PhoneCards {

    @Id(autoincrement = true)
    private Long id;

    private String msisdn;

    private String name;

    private String sex;

    @Generated(hash = 1992564254)
    public PhoneCards(Long id, String msisdn, String name, String sex) {
        this.id = id;
        this.msisdn = msisdn;
        this.name = name;
        this.sex = sex;
    }

    @Generated(hash = 1418843921)
    public PhoneCards() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PhoneCards{" +
                "id=" + id +
                ", msisdn='" + msisdn + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
