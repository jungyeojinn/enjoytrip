package com.ssafy.trip.attrplan.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonDeserialize(builder = AttrplanOrderDto.AttrplanOrderDtoBuilder.class)
public class AttrplanOrderDto {
    int id;
    int order;

    // join
    int plans_id;
    int attraction_info_id;

    
    public AttrplanOrderDto(int order, int plans_id, int attraction_info_id) {
        this.order = order;
        this.plans_id = plans_id;
        this.attraction_info_id = attraction_info_id;
    }
    @Builder
    public AttrplanOrderDto(int id, int order, int plans_id, int attraction_info_id) {
        this.id = id;
        this.order = order;
        this.plans_id = plans_id;
        this.attraction_info_id = attraction_info_id;
    }

    @Override
    public String toString() {
        return "AttrplanOrderDto{" +
                "id=" + id +
                ", order=" + order +
                ", plans_id=" + plans_id +
                ", attraction_info_id=" + attraction_info_id +
                '}';
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class AttrplanOrderDtoBuilder {
    }
}
