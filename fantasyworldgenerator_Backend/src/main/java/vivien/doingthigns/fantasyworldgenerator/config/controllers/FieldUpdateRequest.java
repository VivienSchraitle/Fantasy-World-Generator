package vivien.doingthigns.fantasyworldgenerator.config.controllers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FieldUpdateRequest {
    private String fieldName;
    private int factionId;

}