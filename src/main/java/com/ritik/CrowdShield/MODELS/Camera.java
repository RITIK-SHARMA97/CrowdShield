package com.ritik.CrowdShield.MODELS;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Camera {

    @Id
    @GeneratedValue
    private Long id;
    private String locationName; // where is the camera
    private String rtspUrl; // the videolink of that camera
    private boolean isActive;// is the camera on or off
}


//These cameras are stored in the database so your app knows:

//Which cameras exist

//Their location
//Their RTSP stream

//If they are active