package jawnathan.data;

import jawnathan.models.Gig;

import java.util.List;

public interface GigRepository {
    List<Gig> findAll();

    Gig findById(int gigId);
}
