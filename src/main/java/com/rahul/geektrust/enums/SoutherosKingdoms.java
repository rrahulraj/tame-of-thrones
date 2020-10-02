package com.rahul.geektrust.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SoutherosKingdoms {

    SPACE("GORILLA"),
    LAND("PANDA"),
    WATER("OCTOPUS"),
    ICE("MAMMOTH"),
    AIR("OWL"),
    FIRE("DRAGON")
    ;

    final String emblem;

}
