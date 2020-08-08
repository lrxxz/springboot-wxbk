package org.lrx.entity;

public class UniversityLike {
    private String likeMid;
    private String likeUid;
    private UniversityMatter universityMatter;
    private UniversityUser universityUser;

    @Override
    public String toString() {
        return "UniversityLike{" +
                "likeMid='" + likeMid + '\'' +
                ", likeUid='" + likeUid + '\'' +
                ", universityMatter=" + universityMatter +
                ", universityUser=" + universityUser +
                '}';
    }

    public UniversityLike(String likeMid, String likeUid, UniversityMatter universityMatter, UniversityUser universityUser) {
        this.likeMid = likeMid;
        this.likeUid = likeUid;
        this.universityMatter = universityMatter;
        this.universityUser = universityUser;
    }

    public UniversityLike() {
    }

    public UniversityUser getUniversityUser() {
        return universityUser;
    }

    public void setUniversityUser(UniversityUser universityUser) {
        this.universityUser = universityUser;
    }

    public String getLikeMid() {
        return likeMid;
    }

    public void setLikeMid(String likeMid) {
        this.likeMid = likeMid;
    }

    public String getLikeUid() {
        return likeUid;
    }

    public void setLikeUid(String likeUid) {
        this.likeUid = likeUid;
    }

    public UniversityMatter getUniversityMatter() {
        return universityMatter;
    }

    public void setUniversityMatter(UniversityMatter universityMatter) {
        this.universityMatter = universityMatter;
    }
}
