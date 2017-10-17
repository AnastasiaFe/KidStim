package ua.nure.fedorenko.kidstim.entity;


import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;
import java.util.Date;

import ua.nure.fedorenko.kidstim.utils.CustomDateSerializer;


public class ChildDTO extends UserDTO implements Serializable {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ChildDTO childDTO = (ChildDTO) o;

        if (dateOfBirth != childDTO.dateOfBirth) return false;
        if (gender != childDTO.gender) return false;
        if (points != childDTO.points) return false;
        return photo != null ? photo.equals(childDTO.photo) : childDTO.photo == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (dateOfBirth ^ (dateOfBirth >>> 32));
        result = 31 * result + gender;
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        result = 31 * result + points;
        return result;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    private long dateOfBirth;

    private int gender;

    private String photo;

    private int points;

    public long getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(long dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

}
