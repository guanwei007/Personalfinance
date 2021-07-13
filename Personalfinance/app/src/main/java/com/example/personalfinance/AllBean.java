package com.example.personalfinance;

public class AllBean {
    String _id;
    String spend;
    String spendDate;
    String spendType;
    String spendPlace;
    String spendTips;

    public AllBean(String _id, String spend, String spendDate, String spendType, String spendPlace, String spendTips){
        super();
        this._id = _id;
        this.spend = spend;
        this.spendDate = spendDate;
        this.spendType = spendType;
        this.spendPlace = spendPlace;
        this.spendTips = spendTips;

    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getSpend() {
        return spend;
    }

    public void setSpend(String spend) {
        this.spend = spend;
    }

    public String getSpendDate() {
        return spendDate;
    }

    public void setSpendDate(String spendDate) {
        this.spendDate = spendDate;
    }

    public String getSpendType() {
        return spendType;
    }

    public void setSpendType(String spendType) {
        this.spendType = spendType;
    }

    public String getSpendPlace() {
        return spendPlace;
    }

    public void setSpendPlace(String spendPlace) {
        this.spendPlace = spendPlace;
    }

    public String getSpendTips() {
        return spendTips;
    }

    public void setSpendTips(String spendTips) {
        this.spendTips = spendTips;
    }

    public String toString(){
        return _id+spend+spendDate+spendType+spendPlace+spendTips;
    }

}
