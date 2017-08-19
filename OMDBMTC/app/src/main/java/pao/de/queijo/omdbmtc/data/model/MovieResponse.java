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

    public MovieResponse() { }

    public MovieResponse(List<Movie> search, boolean response) {
        Search = search;
        Response = response;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieResponse that = (MovieResponse) o;

        if (Response != that.Response) return false;
        return Search != null ? Search.equals(that.Search) : that.Search == null;

    }

    @Override
    public int hashCode() {
        int result = Search != null ? Search.hashCode() : 0;
        result = 31 * result + (Response ? 1 : 0);
        return result;
    }
}
