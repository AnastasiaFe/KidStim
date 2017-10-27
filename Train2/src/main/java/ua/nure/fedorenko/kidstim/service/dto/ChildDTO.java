package ua.nure.fedorenko.kidstim.service.dto;

import java.io.Serializable;

public class ChildDTO extends UserDTO implements Serializable {

    private long dateOfBirth;
    private int gender;

    public long getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChildDTO)) return false;
        if (!super.equals(o)) return false;

        ChildDTO childDTO = (ChildDTO) o;

        if (getDateOfBirth() != childDTO.getDateOfBirth()) return false;
        if (getGender() != childDTO.getGender()) return false;
        if (getPoints() != childDTO.getPoints()) return false;
        return getPhoto() != null ? getPhoto().equals(childDTO.getPhoto()) : childDTO.getPhoto() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (getDateOfBirth() ^ (getDateOfBirth() >>> 32));
        result = 31 * result + getGender();
        result = 31 * result + (getPhoto() != null ? getPhoto().hashCode() : 0);
        result = 31 * result + getPoints();
        return result;
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

    private String photo;

    private int points;

}
