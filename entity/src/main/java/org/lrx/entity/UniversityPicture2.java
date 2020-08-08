package org.lrx.entity;

public class UniversityPicture2 {

    private String universityId;
    private String universityPicture;

    @Override
    public String toString() {
        return "UniversityPicture2{" +
                "universityId='" + universityId + '\'' +
                ", universityPicture='" + universityPicture + '\'' +
                '}';
    }

    public UniversityPicture2(String universityId, String universityPicture) {
        this.universityId = universityId;
        this.universityPicture = universityPicture;
    }

    public UniversityPicture2() {
    }

    public String getUniversityId() {
        return universityId;
    }

    public void setUniversityId(String universityId) {
        this.universityId = universityId;
    }

    public String getUniversityPicture() {
        return universityPicture;
    }

    public void setUniversityPicture(String universityPicture) {
        this.universityPicture = universityPicture;
    }
}
