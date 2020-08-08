package org.lrx.entity;

import java.util.List;

public class University {
    private String universityId;
    private String universityName;
    private String universityMessage;
    private String universityAdress;
    private List<UniversityPicture2> universityPictureList2;

    @Override
    public String toString() {
        return "University{" +
                "universityId='" + universityId + '\'' +
                ", universityName='" + universityName + '\'' +
                ", universityMessage='" + universityMessage + '\'' +
                ", universityAdress='" + universityAdress + '\'' +
                ", universityPictureList2=" + universityPictureList2 +
                '}';
    }

    public University(String universityId, String universityName, String universityMessage, String universityAdress, List<UniversityPicture2> universityPictureList2) {
        this.universityId = universityId;
        this.universityName = universityName;
        this.universityMessage = universityMessage;
        this.universityAdress = universityAdress;
        this.universityPictureList2 = universityPictureList2;
    }

    public University() {
    }

    public List<UniversityPicture2> getUniversityPictureList2() {
        return universityPictureList2;
    }

    public void setUniversityPictureList2(List<UniversityPicture2> universityPictureList2) {
        this.universityPictureList2 = universityPictureList2;
    }

    public String getUniversityId() {
        return universityId;
    }

    public void setUniversityId(String universityId) {
        this.universityId = universityId;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getUniversityMessage() {
        return universityMessage;
    }

    public void setUniversityMessage(String universityMessage) {
        this.universityMessage = universityMessage;
    }

    public String getUniversityAdress() {
        return universityAdress;
    }

    public void setUniversityAdress(String universityAdress) {
        this.universityAdress = universityAdress;
    }
}
