package com.yw.httpprocessor.bean;


import java.util.List;

/**
 * 快递查询的bjavaean
 */
public class ExpressBean {
    /**
     * message : ok
     * nu : 300008026630
     * ischeck : 1
     * condition : F00
     * com : quanfengkuaidi
     * status : 200
     * state : 3
     * data : [{"time":"2013-05-27 13:48:37","ftime":"2013-05-27 13:48:37","context":"派件已【签收】,签收人是【秦胜杰】签收网点是【北京人大分部】","location":""},{"time":"2013-05-27 08:35:00","ftime":"2013-05-27 08:35:00","context":"【北京人大分部】的【刘生叶】正在派件，扫描员是【刘生叶】","location":""},{"time":"2013-05-25 15:09:47","ftime":"2013-05-25 15:09:47","context":"【北京人大分部】的【刘生叶】正在派件，扫描员是【刘生叶】","location":""},{"time":"2013-05-25 14:56:52","ftime":"2013-05-25 14:56:52","context":"快件到达【北京人大分部】,上一站是【北京分拨中心】扫描员是【刘生叶】","location":""},{"time":"2013-05-25 13:32:48","ftime":"2013-05-25 13:32:48","context":"快件在【北京分拨中心】装车,正发往【北京人大分部】扫描员是【苏丽娟】","location":""},{"time":"2013-05-25 13:26:17","ftime":"2013-05-25 13:26:17","context":"快件到达【北京分拨中心】,上一站是【北京丰台】扫描员是【刘美惠】","location":""},{"time":"2013-05-25 12:22:07","ftime":"2013-05-25 12:22:07","context":"快件在【北京丰台】装车,正发往【北京分拨中心】扫描员是【北京丰台】","location":""},{"time":"2013-05-25 12:21:53","ftime":"2013-05-25 12:21:53","context":"【北京丰台】的【北京丰台】已收件,扫描员是:【北京丰台】,","location":""}]
     */

    private String message;
    private String nu;
    private String ischeck;
    private String condition;
    private String com;
    private String status;
    private String state;
    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNu() {
        return nu;
    }

    public void setNu(String nu) {
        this.nu = nu;
    }

    public String getIscheck() {
        return ischeck;
    }

    public void setIscheck(String ischeck) {
        this.ischeck = ischeck;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * time : 2013-05-27 13:48:37
         * ftime : 2013-05-27 13:48:37
         * context : 派件已【签收】,签收人是【秦胜杰】签收网点是【北京人大分部】
         * location :
         */

        private String time;
        private String ftime;
        private String context;
        private String location;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getFtime() {
            return ftime;
        }

        public void setFtime(String ftime) {
            this.ftime = ftime;
        }

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }

}
