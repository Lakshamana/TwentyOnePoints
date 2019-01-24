package com.arjuna.health.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Points.
 */
@Entity
@Table(name = "points")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "points")
public class Points implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "jhi_date", nullable = false)
    private LocalDate date;

    @NotNull
    @Column(name = "exercise", nullable = false)
    private Boolean exercise;

    @NotNull
    @Column(name = "meals", nullable = false)
    private Boolean meals;

    @NotNull
    @Column(name = "alcohol", nullable = false)
    private Boolean alcohol;

    @Column(name = "notes")
    private String notes;

    @ManyToOne
    @JsonIgnoreProperties("")
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Points date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean isExercise() {
        return exercise;
    }

    public Points exercise(Boolean exercise) {
        this.exercise = exercise;
        return this;
    }

    public void setExercise(Boolean exercise) {
        this.exercise = exercise;
    }

    public Boolean isMeals() {
        return meals;
    }

    public Points meals(Boolean meals) {
        this.meals = meals;
        return this;
    }

    public void setMeals(Boolean meals) {
        this.meals = meals;
    }

    public Boolean isAlcohol() {
        return alcohol;
    }

    public Points alcohol(Boolean alcohol) {
        this.alcohol = alcohol;
        return this;
    }

    public void setAlcohol(Boolean alcohol) {
        this.alcohol = alcohol;
    }

    public String getNotes() {
        return notes;
    }

    public Points notes(String notes) {
        this.notes = notes;
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public User getUser() {
        return user;
    }

    public Points user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Points points = (Points) o;
        if (points.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), points.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Points{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", exercise='" + isExercise() + "'" +
            ", meals='" + isMeals() + "'" +
            ", alcohol='" + isAlcohol() + "'" +
            ", notes='" + getNotes() + "'" +
            "}";
    }
}
