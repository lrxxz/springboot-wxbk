package org.lrx.entity;

public class UniversityMessage {
    private String messageUid;
    private String messageUniversity;
    private String messageAcademy;
    private String messageGrade;

    @Override
    public String toString() {
        return "UniversityMessage{" +
                "messageUid='" + messageUid + '\'' +
                ", messageUniversity='" + messageUniversity + '\'' +
                ", messsageAcademy='" + messageAcademy + '\'' +
                ", messageGrade='" + messageGrade + '\'' +
                '}';
    }

    public UniversityMessage(String messageUid, String messageUniversity, String messageAcademy, String messageGrade) {
        this.messageUid = messageUid;
        this.messageUniversity = messageUniversity;
        this.messageAcademy = messageAcademy;
        this.messageGrade = messageGrade;
    }

    public UniversityMessage() {
    }

    public String getMessageUid() {
        return messageUid;
    }

    public void setMessageUid(String messageUid) {
        this.messageUid = messageUid;
    }

    public String getMessageUniversity() {
        return messageUniversity;
    }

    public void setMessageUniversity(String messageUniversity) {
        this.messageUniversity = messageUniversity;
    }

    public String getMessageAcademy() {
        return messageAcademy;
    }

    public void setMessageAcademy(String messsageAcademy) {
        this.messageAcademy = messsageAcademy;
    }

    public String getMessageGrade() {
        return messageGrade;
    }

    public void setMessageGrade(String messageGrade) {
        this.messageGrade = messageGrade;
    }
}
