package com.demo.collections;

public class Course {
    private String cid;

    private String cname;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Course(String cid, String cname) {
        this.cid = cid;
        this.cname = cname;
    }
}
