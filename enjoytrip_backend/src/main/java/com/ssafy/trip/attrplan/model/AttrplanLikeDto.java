package com.ssafy.trip.attrplan.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttrplanLikeDto {
    int id;

    // join
    int user_id;
    int plans_id;

    public AttrplanLikeDto(int id, int user_id, int plans_id) {
        this.id = id;
        this.user_id = user_id;
        this.plans_id = plans_id;
    }

    public AttrplanLikeDto(int user_id, int plans_id) {
        this.user_id = user_id;
        this.plans_id = plans_id;
    }

    @Override
    public String toString() {
        return "AttrplanLikeDto{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", plans_id=" + plans_id +
                '}';
    }
}
