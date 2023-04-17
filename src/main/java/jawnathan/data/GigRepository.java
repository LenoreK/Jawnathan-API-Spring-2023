package jawnathan.data;

import jawnathan.models.Gig;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GigRepository {
    List<Gig> findAll();

    Gig findById(int gigId);

    Gig add(Gig gig);

    boolean update(Gig gig);

    @Transactional
    boolean deleteById(int gigId);
}
