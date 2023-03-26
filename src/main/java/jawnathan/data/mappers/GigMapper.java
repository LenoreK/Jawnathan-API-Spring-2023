package jawnathan.data.mappers;

import jawnathan.models.Gig;
import jawnathan.models.Venue;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GigMapper implements RowMapper<Gig> {

    @Override
    public Gig mapRow(ResultSet resultSet, int i) throws SQLException {
        Gig gig = new Gig();
        gig.setGigId(resultSet.getInt("gig_id"));
        gig.setDate(resultSet.getDate("gig_date").toLocalDate());
        gig.setDetails(resultSet.getString("details"));
        gig.setStartTime(resultSet.getTimestamp("start_time").toLocalDateTime());
        gig.setEndTime(resultSet.getTimestamp("end_time").toLocalDateTime());
        gig.setVenueId(resultSet.getInt("venue_id"));
        return gig;
    }
}
