package org.lrx.entity;

public class UniversityComment {
    private Integer comId;
    private String comMid;
    private String comUid;
    private String comText;
    private String comTime;
    private UniversityUser universityUser;

    @Override
    public String toString() {
        return "UniversityComment{" +
                "comId=" + comId +
                ", comMid='" + comMid + '\'' +
                ", comUid='" + comUid + '\'' +
                ", comText='" + comText + '\'' +
                ", comTime='" + comTime + '\'' +
                ", universityUser=" + universityUser +
                '}';
    }

    public String getComMid() {
        return comMid;
    }

    public Integer getComId() {
        return comId;
    }

    public void setComId(Integer comId) {
        this.comId = comId;
    }

    public void setComMid(String comMid) {
        this.comMid = comMid;
    }

    public String getComUid() {
        return comUid;
    }

    public void setComUid(String comUid) {
        this.comUid = comUid;
    }

    public String getComText() {
        return comText;
    }

    public void setComText(String comText) {
        this.comText = comText;
    }

    public String getComTime() {
        return comTime;
    }

    public void setComTime(String comTime) {
        this.comTime = comTime;
    }

    public UniversityUser getUniversityUser() {
        return universityUser;
    }

    public void setUniversityUser(UniversityUser universityUser) {
        this.universityUser = universityUser;
    }

    public UniversityComment(Integer comId, String comMid, String comUid, String comText, String comTime, UniversityUser universityUser) {
        this.comId = comId;
        this.comMid = comMid;
        this.comUid = comUid;
        this.comText = comText;
        this.comTime = comTime;
        this.universityUser = universityUser;
    }

    public UniversityComment() {
    }
}
