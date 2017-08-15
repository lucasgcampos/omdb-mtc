package pao.de.queijo.omdbmtc.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Lucas Campos
 * @since 1.0.0
 */

public class Movie implements Parcelable {

    private String Title;
    private String Year;
    private String imdbID;
    private String Poster;

    public String getTitle() {
        return Title;
    }

    public String getYear() {
        return Year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getPoster() {
        return Poster;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (Title != null ? !Title.equals(movie.Title) : movie.Title != null) return false;
        if (Year != null ? !Year.equals(movie.Year) : movie.Year != null) return false;
        if (imdbID != null ? !imdbID.equals(movie.imdbID) : movie.imdbID != null) return false;
        return Poster != null ? Poster.equals(movie.Poster) : movie.Poster == null;

    }

    @Override
    public int hashCode() {
        int result = Title != null ? Title.hashCode() : 0;
        result = 31 * result + (Year != null ? Year.hashCode() : 0);
        result = 31 * result + (imdbID != null ? imdbID.hashCode() : 0);
        result = 31 * result + (Poster != null ? Poster.hashCode() : 0);
        return result;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Title);
        dest.writeString(this.Year);
        dest.writeString(this.imdbID);
        dest.writeString(this.Poster);
    }

    public Movie() {
    }

    protected Movie(Parcel in) {
        this.Title = in.readString();
        this.Year = in.readString();
        this.imdbID = in.readString();
        this.Poster = in.readString();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
