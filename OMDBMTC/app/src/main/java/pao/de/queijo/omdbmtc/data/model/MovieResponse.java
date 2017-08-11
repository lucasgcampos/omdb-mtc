package pao.de.queijo.omdbmtc.data.model;

import java.util.List;

/**
 * @author Lucas Campos
 * @since 1.0.0
 */

public class MovieResponse {

    private List<Movie> Search;
    private boolean Response;

    public List<Movie> getSearch() {
        return Search;
    }

    public boolean isResponse() {
        return Response;
    }

}
