package ru.vstu.kinosite.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.vstu.kinosite.model.Movie;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class MovieRepo {

    private final JdbcTemplate jdbcTemplate;

    public MovieRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Movie> findAll() {
        String findAllQuery = "select * from movie";

        return jdbcTemplate.query(findAllQuery, new BeanPropertyRowMapper<>(Movie.class));
    }

    public int save(Movie movie) {
        String saveQuery = "insert into movie(name, year, description, poster) values (?,?,?,?)";

        return jdbcTemplate.update(
                saveQuery, movie.getName(), movie.getYear(), movie.getDescription(), movie.getPoster()
        );
    }

    public Movie findById(Long id) {
        String findQuery = "select * from movie where id = ?";

        return jdbcTemplate.queryForObject(findQuery, new BeanPropertyRowMapper<>(Movie.class), id);
    }

    public int update(Movie movie) {
        String updateQuery = "update movie set name = ?, year = ?, description = ?, poster = ? where id = ?";

        return jdbcTemplate.update(
                updateQuery, movie.getName(), movie.getYear(), movie.getDescription(), movie.getPoster(), movie.getId()
        );
    }

    public int delete(Movie movie) {
        return jdbcTemplate.update("delete from movie where id = ?", movie.getId());
    }

    public List<Movie> findByNameAndYear(String filterName, Integer filterYear) {
        String findByNameAndYearQuery = "select * from movie where lower(name) like lower(?) and year = ?";

        return jdbcTemplate.query(
                findByNameAndYearQuery, new BeanPropertyRowMapper<>(Movie.class), filterName + "%", filterYear
        );
    }

    public List<Movie> findByName(String filterName) {
        String findByNameQuery = "select * from movie where lower(name)  like lower(?)";

        return jdbcTemplate.query(findByNameQuery, new BeanPropertyRowMapper<>(Movie.class), filterName + "%");
    }

    public List<Movie> findByYear(Integer filterYear) {
        String findByYearQuery = "select * from movie where year = ?";

        return jdbcTemplate.query(findByYearQuery, new BeanPropertyRowMapper<>(Movie.class), filterYear);
    }
}
