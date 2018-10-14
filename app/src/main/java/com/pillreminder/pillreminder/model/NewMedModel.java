package com.pillreminder.pillreminder.model;

import java.sql.Time;
import java.util.Date;

public class NewMedModel {
    int ID;
    String medName,medFormName,medImageID,dosageCount,dosageType;
    String  isWithRegardOrNot;

    long startDate,endDate;

    String frequency,timesInDay;
    long intakeTime1,intakeTime2,intakeTime3;

    String isRefillReminderOn;
    String remindBeforeCount;
    long refillReminderTime;

    String isIntakeNotifOn;
    String notifText;

    String totalPills;

    String currDate;
    String time1,time2,time3,refillTimeText,taken_time,taken_date,skip_time,skip_date,status="";

    int cID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public String getMedFormName() {
        return medFormName;
    }

    public void setMedFormName(String medFormName) {
        this.medFormName = medFormName;
    }

    public String getMedImageID() {
        return medImageID;
    }

    public void setMedImageID(String medImageID) {
        this.medImageID = medImageID;
    }

    public String getDosageCount() {
        return dosageCount;
    }

    public void setDosageCount(String dosageCount) {
        this.dosageCount = dosageCount;
    }

    public String getDosageType() {
        return dosageType;
    }

    public void setDosageType(String dosageType) {
        this.dosageType = dosageType;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getTimesInDay() {
        return timesInDay;
    }

    public void setTimesInDay(String timesInDay) {
        this.timesInDay = timesInDay;
    }

//    public String getIntakeTime() {
//        return intakeTime;
//    }

//    public void setIntakeTime(String intakeTime) {
//        this.intakeTime = intakeTime;
//    }


    public String getRemindBeforeCount() {
        return remindBeforeCount;
    }

    public void setRemindBeforeCount(String remindBeforeCount) {
        this.remindBeforeCount = remindBeforeCount;
    }

    public long getRefillReminderTime() {
        return refillReminderTime;
    }

    public void setRefillReminderTime(long refillReminderTime) {
        this.refillReminderTime = refillReminderTime;
    }

    public String getNotifText() {
        return notifText;
    }

    public void setNotifText(String notifText) {
        this.notifText = notifText;
    }

    public String getIsWithRegardOrNot() {
        return isWithRegardOrNot;
    }

    public void setIsWithRegardOrNot(String isWithRegardOrNot) {
        this.isWithRegardOrNot = isWithRegardOrNot;
    }

    public String getIsRefillReminderOn() {
        return isRefillReminderOn;
    }

    public void setIsRefillReminderOn(String isRefillReminderOn) {
        this.isRefillReminderOn = isRefillReminderOn;
    }

    public String getIsIntakeNotifOn() {
        return isIntakeNotifOn;
    }

    public void setIsIntakeNotifOn(String isIntakeNotifOn) {
        this.isIntakeNotifOn = isIntakeNotifOn;
    }

    public long getIntakeTime1() {
        return intakeTime1;
    }

    public void setIntakeTime1(long intakeTime1) {
        this.intakeTime1 = intakeTime1;
    }

    public long getIntakeTime2() {
        return intakeTime2;
    }

    public void setIntakeTime2(long intakeTime2) {
        this.intakeTime2 = intakeTime2;
    }

    public long getIntakeTime3() {
        return intakeTime3;
    }

    public void setIntakeTime3(long intakeTime3) {
        this.intakeTime3 = intakeTime3;
    }

    public String getTotalPills() {
        return totalPills;
    }

    public void setTotalPills(String totalPills) {
        this.totalPills = totalPills;
    }

    public String getCurrDate() {
        return currDate;
    }

    public void setCurrDate(String currDate) {
        this.currDate = currDate;
    }

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }

    public String getTime3() {
        return time3;
    }

    public void setTime3(String time3) {
        this.time3 = time3;
    }

    public String getRefillTimeText() {
        return refillTimeText;
    }

    public void setRefillTimeText(String refillTimeText) {
        this.refillTimeText = refillTimeText;
    }

    public int getcID() {
        return cID;
    }

    public void setcID(int cID) {
        this.cID = cID;
    }

    public String getTaken_time() {
        return taken_time;
    }

    public void setTaken_time(String taken_time) {
        this.taken_time = taken_time;
    }

    public String getTaken_date() {
        return taken_date;
    }

    public void setTaken_date(String taken_date) {
        this.taken_date = taken_date;
    }

    public String getSkip_time() {
        return skip_time;
    }

    public void setSkip_time(String skip_time) {
        this.skip_time = skip_time;
    }

    public String getSkip_date() {
        return skip_date;
    }

    public void setSkip_date(String skip_date) {
        this.skip_date = skip_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
