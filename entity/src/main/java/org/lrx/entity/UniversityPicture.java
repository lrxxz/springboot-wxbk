package org.lrx.entity;

public class UniversityPicture {
    private String pMid;
    private String pAdress;

    @Override
    public String toString() {
        return "UniversityPicture{" +
                "pMid=" + pMid +
                ", pAdress='" + pAdress + '\'' +
                '}';
    }

    public String getpMid() {
        return pMid;
    }

    public void setpMid(String pMid) {
        this.pMid = pMid;
    }

    public String getpAdress() {
        return pAdress;
    }

    public void setpAdress(String pAdress) {
        this.pAdress = pAdress;
    }

    public UniversityPicture(String pMid, String pAdress) {
        this.pMid = pMid;
        this.pAdress = pAdress;
    }

    public UniversityPicture() {
    }
}
