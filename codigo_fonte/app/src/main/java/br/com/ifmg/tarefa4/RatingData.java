package br.com.ifmg.tarefa4;

import android.os.Parcel;
import android.os.Parcelable;

public class RatingData implements Parcelable {

    private int ratingFood;
    private int ratingDelivery;
    private int ratingCorrect;
    private String comment;

    public RatingData() { }

    public RatingData(Parcel p) {
        this.ratingFood = p.readInt();
        this.ratingDelivery = p.readInt();
        this.ratingCorrect = p.readInt();
        this.comment = p.readString();
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ratingFood);
        dest.writeInt(ratingDelivery);
        dest.writeInt(ratingCorrect);
        dest.writeString(comment);
    }

    public static final Creator<RatingData> CREATOR = new Creator<RatingData>() {
        @Override
        public RatingData createFromParcel(Parcel in) {
            return new RatingData(in);
        }

        @Override
        public RatingData[] newArray(int size) {
            return new RatingData[size];
        }
    };

    public int getRatingFood() {
        return ratingFood;
    }

    public void setRatingFood(int ratingFood) {
        this.ratingFood = ratingFood;
    }

    public int getRatingDelivery() {
        return ratingDelivery;
    }

    public void setRatingDelivery(int ratingDelivery) {
        this.ratingDelivery = ratingDelivery;
    }

    public int getRatingCorrect() {
        return ratingCorrect;
    }

    public void setRatingCorrect(int ratingCorrect) {
        this.ratingCorrect = ratingCorrect;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
