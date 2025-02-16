package com.veena.bookmyshow.dtos;

import com.veena.bookmyshow.models.Show;
import lombok.Data;

@Data
public class CreateShowResponseDTO {
    private ResponseStatus responseStatus;
    private Show show;
}
