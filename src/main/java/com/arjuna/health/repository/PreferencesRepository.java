package com.arjuna.health.repository;

import com.arjuna.health.domain.Preferences;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Preferences entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PreferencesRepository extends JpaRepository<Preferences, Long> {

    @Query("select preferences from Preferences preferences where preferences.user.login = ?#{principal.username}")
    List<Preferences> findByUserIsCurrentUser();

}
