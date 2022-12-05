package com.example.assigntry;

public class StudentModal {
    private String ID, FullName;

    public StudentModal(String ID, String FullName) {
        this.ID = ID;
        this.FullName = FullName;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setFullName(String FullName)
    {
        this.FullName = FullName;
    }
    public String getFullName()
    {
        return FullName;
    }
}
