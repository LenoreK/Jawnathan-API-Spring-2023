package jawnathan.data;

import jawnathan.data.mappers.GigMapper;
import jawnathan.models.Gig;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GigJdbcTemplateRepository implements GigRepository {

    private final JdbcTemplate jdbcTemplate;

    public GigJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Gig> findAll() {
        // limit until we develop a paging solution
        final String sql = "select gig_id, gig_date, details, start_time, end_time, venue_id from gig;";
        return jdbcTemplate.query(sql, new GigMapper());
    }

    @Override
    public Gig findById(int gigId){
        final String sql = "select gig_id, gig_date, details, start_time, end_time, venue_id "
                + "from gig "
                + "where gig_id = ?;";
        return jdbcTemplate.query(sql, new GigMapper(), gigId).stream()
                .findFirst()
                .orElse(null);
    }
}
