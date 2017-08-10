package pao.de.queijo.omdbmtc.data.model;

/**
 * @author Lucas Campos
 * @since 1.0.0
 */

public class Movie {

    private boolean Response;
    private String Title;
    private String Year;
    private String Genre;

    public boolean isResponse() {
        return Response;
    }

    public String getTitle() {
        return Title;
    }

    public String getYear() {
        return Year;
    }

    public String getGenre() {
        return Genre;
    }
}
