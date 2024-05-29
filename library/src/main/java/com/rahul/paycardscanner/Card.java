package com.rahul.paycardscanner;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import com.rahul.paycardscanner.utils.CardUtils;

import java.io.Serializable;
import java.util.Objects;

public class Card implements Serializable, Parcelable {

    public static final Creator<Card> CREATOR = new Creator<Card>() {
        @Override
        public Card createFromParcel(Parcel source) {
            return new Card(source);
        }

        @Override
        public Card[] newArray(int size) {
            return new Card[size];
        }
    };
    private static final long serialVersionUID = 1L;
    private final String mCardNumber;
    @Nullable
    private final String mCardHolder;
    @Nullable
    private final String mExpirationDate;

    public Card(String number, @Nullable String holder, @Nullable String expirationDate) {
        this.mCardNumber = number;
        this.mCardHolder = holder;
        this.mExpirationDate = expirationDate;
    }

    protected Card(Parcel in) {
        this.mCardNumber = in.readString();
        this.mCardHolder = in.readString();
        this.mExpirationDate = in.readString();
    }

    /**
     * @return card number (only digits)
     */
    public String getCardNumber() {
        return mCardNumber;
    }

    public String getCardNumberRedacted() {
        return CardUtils.getCardNumberRedacted(mCardNumber);
    }

    /**
     * @return card holder name
     */
    @Nullable
    public String getCardHolderName() {
        return mCardHolder;
    }

    /**
     * @return card expiration date in "MM/yy" format
     */
    @Nullable
    public String getExpirationDate() {
        return mExpirationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (!Objects.equals(mCardNumber, card.mCardNumber))
            return false;
        if (!Objects.equals(mCardHolder, card.mCardHolder))
            return false;
        return Objects.equals(mExpirationDate, card.mExpirationDate);
    }

    @Override
    public int hashCode() {
        int result = mCardNumber != null ? mCardNumber.hashCode() : 0;
        result = 31 * result + (mCardHolder != null ? mCardHolder.hashCode() : 0);
        result = 31 * result + (mExpirationDate != null ? mExpirationDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Card{" +
                "mCardNumber='" + getCardNumberRedacted() + '\'' +
                ", mCardHolder='" + mCardHolder + '\'' +
                ", mExpirationDate='" + mExpirationDate + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mCardNumber);
        dest.writeString(this.mCardHolder);
        dest.writeString(this.mExpirationDate);
    }
}
