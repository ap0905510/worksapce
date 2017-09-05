package com.yw.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 作者：create by YW
 * 日期：2017.02.21 15:59
 * 描述：呼信用户信息表
 */
@Entity
public class HxUsers {

    @Id
    private Long id;

    private Long userId;

    private String sex;

    private String nname;

    private String msisdn;

    private String iconUrl;

    private String type;

    private String version;

    @Generated(hash = 463822413)
    public HxUsers(Long id, Long userId, String sex, String nname, String msisdn,
            String iconUrl, String type, String version) {
        this.id = id;
        this.userId = userId;
        this.sex = sex;
        this.nname = nname;
        this.msisdn = msisdn;
        this.iconUrl = iconUrl;
        this.type = type;
        this.version = version;
    }

    @Generated(hash = 896779534)
    public HxUsers() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNname() {
        return nname;
    }

    public void setNname(String nname) {
        this.nname = nname;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "HxUsers{" +
                "id=" + id +
                ", userId=" + userId +
                ", sex='" + sex + '\'' +
                ", nname='" + nname + '\'' +
                ", msisdn='" + msisdn + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", type='" + type + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
