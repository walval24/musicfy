package it.digitazon.musicfy.presentation.dto;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ArtistDTO {

    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
    private long id;
    private String name;
    private String alias;
    private boolean isDeleted;
    private String birthDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Date convertBirthDate() throws ParseException {

        return dateFormat.parse(this.birthDate);

    }

    public void convertDateToString(Date date) {
        if (date == null) {
            this.birthDate = null;
        } else {


            this.birthDate = dateFormat.format(date);
        }
    }
}
