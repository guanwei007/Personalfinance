package com.example.personalfinance;

public class AllNoteBean {
    String _id;
    String note;
    public AllNoteBean(String _id,String note){
        super();
        this._id = _id;
        this.note = note;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    public String toString(){
        return _id+note;
    }
}
