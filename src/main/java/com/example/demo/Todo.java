package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
public class Todo {
    @Id @GeneratedValue
    private Long id;
    String description;
    int created_at;
    boolean is_finished;

    public Todo() {

    }
    public Todo(Long id, String description, int created_at, boolean is_finished) {
        this.id = id;
        this.description = description;
        this.created_at = created_at;
        this.is_finished = is_finished;
    }



    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCreated_at() {
        return created_at;
    }

    public void setCreated_at(int created_at) {
        this.created_at = created_at;
    }

    public boolean isIs_finished() {
        return is_finished;
    }

    public void setIs_finished(boolean is_finished) {
        this.is_finished = is_finished;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", created_at=" + created_at +
                ", is_finished=" + is_finished +
                '}';
    }


}
