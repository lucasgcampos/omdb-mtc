package pao.de.queijo.omdbmtc.data.model;

/**
 * @author Lucas Campos
 * @since 1.0.0
 */

public class MovieDetail {

    private String Title;
    private String Plot;
    private String Genre;
    private String Poster;
    private boolean Response;

    public String getTitle() {
        return Title;
    }

    public String getPlot() {
        return Plot;
    }

    public String getGenre() {
        return Genre;
    }

    public String getPoster() {
        return Poster;
    }

    public boolean getResponse() {
        return Response;
    }
}