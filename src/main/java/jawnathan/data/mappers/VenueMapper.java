package jawnathan.data.mappers;

import jawnathan.models.Venue;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VenueMapper implements RowMapper<Venue> {
    @Override
    public Venue mapRow(ResultSet resultSet, int i) throws SQLException {
        Venue venue = new Venue();
        venue.setVenueId(resultSet.getInt("venue_id"));
        venue.setUrl(resultSet.getString("url"));
        venue.setAddress(resultSet.getString("address"));
        venue.setCity(resultSet.getString("city"));
        venue.setState(resultSet.getString("state"));
        venue.setZipcode(resultSet.getString("zipcode"));
        return venue;
    }
}
