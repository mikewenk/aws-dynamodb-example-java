package com.purplemanatee.sample.model;

import com.purplemanatee.sample.enumeration.StatusEnum;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Builder
public class DataHolder {

    private String jsonFile;
    private Long id;
    private StatusEnum status;

}
