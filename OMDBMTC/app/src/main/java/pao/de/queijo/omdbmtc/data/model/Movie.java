package pao.de.queijo.omdbmtc.data.model;

/**
 * @author Lucas Campos
 * @since 1.0.0
 */

public class Movie {

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
}
