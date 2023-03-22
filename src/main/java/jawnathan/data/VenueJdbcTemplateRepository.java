package jawnathan.data;

import jawnathan.data.mappers.VenueMapper;
import jawnathan.models.Venue;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class VenueJdbcTemplateRepository implements VenueRepository{

    private final JdbcTemplate jdbcTemplate;

    public VenueJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Venue findById(int venueId){
        final String sql = "select venue_id, venue_name, url, address, city, state, zipcode from venue where venue_id = ?;";

        return jdbcTemplate.query(sql, new VenueMapper(), venueId).stream()
                .findFirst()
                .orElse(null);
    }
}
