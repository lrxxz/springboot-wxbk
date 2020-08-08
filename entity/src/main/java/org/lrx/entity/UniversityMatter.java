package org.lrx.entity;

import java.util.List;

public class UniversityMatter {
    private String mId;
    private  String mKind;
    private String mHead;
    private String mText;
    private String mLike;
    private String mUid;
    private String mTime;
    private List<UniversityPicture> universityPictureList;
    private UniversityUser universityUser;

    @Override
    public String toString() {
        return "UniversityMatter{" +
                "mId='" + mId + '\'' +
                ", mKind='" + mKind + '\'' +
                ", mHead='" + mHead + '\'' +
                ", mText='" + mText + '\'' +
                ", mLike='" + mLike + '\'' +
                ", mUid='" + mUid + '\'' +
                ", mTime='" + mTime + '\'' +
                ", universityPictureList=" + universityPictureList +
                ", universityUser=" + universityUser +
                '}';
    }

    public UniversityMatter(String mId, String mKind, String mHead, String mText, String mLike, String mUid, String mTime, List<UniversityPicture> universityPictureList, UniversityUser universityUser) {
        this.mId = mId;
        this.mKind = mKind;
        this.mHead = mHead;
        this.mText = mText;
        this.mLike = mLike;
        this.mUid = mUid;
        this.mTime = mTime;
        this.universityPictureList = universityPictureList;
        this.universityUser = universityUser;
    }

    public UniversityMatter() {
    }

    public UniversityUser getUniversityUser() {
        return universityUser;
    }

    public void setUniversityUser(UniversityUser universityUser) {
        this.universityUser = universityUser;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmKind() {
        return mKind;
    }

    public void setmKind(String mKind) {
        this.mKind = mKind;
    }

    public String getmHead() {
        return mHead;
    }

    public void setmHead(String mHead) {
        this.mHead = mHead;
    }

    public String getmText() {
        return mText;
    }

    public void setmText(String mText) {
        this.mText = mText;
    }

    public String getmLike() {
        return mLike;
    }

    public void setmLike(String mLike) {
        this.mLike = mLike;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    public String getmUid() {
        return mUid;
    }

    public void setmUid(String mUid) {
        this.mUid = mUid;
    }

    public List<UniversityPicture> getUniversityPictureList() {
        return universityPictureList;
    }

    public void setUniversityPictureList(List<UniversityPicture> universityPictureList) {
        this.universityPictureList = universityPictureList;
    }
}
