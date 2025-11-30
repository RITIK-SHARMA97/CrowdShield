package com.ritik.CrowdShield.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HeatMapResponse {

    private int [] [] heatmapGrid;

    private int densityLevel;

}


//heatmap Each small cell = a zone in the area.
// This is how  dashboard will paint red, yellow, green areas.

//A single number telling overall crowd density
// An overall density level (0=Low, 1=Medium, 2=High)