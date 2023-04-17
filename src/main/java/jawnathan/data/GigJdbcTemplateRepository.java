package jawnathan.data;

import jawnathan.data.mappers.GigMapper;
import jawnathan.models.Gig;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
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

    @Override
    public Gig add(Gig gig) {
        final String sql = "insert into gig (gig_date, details, start_time, end_time, venue_id) values (?,?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, Date.valueOf(gig.getDate()));
            ps.setString(2, gig.getDetails());
            ps.setTime(3, Time.valueOf(gig.getStartTime()));
            ps.setTime(4, Time.valueOf(gig.getEndTime()));
            ps.setInt(5, gig.getVenueId());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        gig.setGigId(keyHolder.getKey().intValue());
        return gig;
    }

    @Override
    public boolean update(Gig gig){
        final String sql = "update gig set "
                + "gig_date = ?, "
                + "details = ?, "
                + "start_time = ?, "
                + "end_time = ?, "
                + "venue_id = ? "
                + "where gig_id = ?";

        return jdbcTemplate.update(sql, gig.getDate(), gig.getDetails(), gig.getStartTime(), gig.getEndTime(), gig.getVenueId(), gig.getGigId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(int gigId){
        jdbcTemplate.update("delete from person_gig_role where gig_id = ?", gigId);
        return jdbcTemplate.update("delete from gig where gig_id = ?", gigId) > 0;
    }
}
