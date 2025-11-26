package com.ritik.CrowdShield.SERVICES;

import com.ritik.CrowdShield.MODELS.EvacuationRoute;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class RouteOptimizationService {

    // placeholder service logic for route optimization
    public EvacuationRoute recommendRoute(double currentDensity){
        // mock logic : choose a route based on low capacityFactor or low density

        EvacuationRoute route = new EvacuationRoute();
        route.setName("Exit Alpha");
        route.setDescription("the fastest route to the primary safe zone");
        route.setCapacityFactor(0.9);
        return  route;

    }

    public List<EvacuationRoute> getAllRoutes(){
        // mock implementation
        return List.of(
                recommendRoute(0),
                new EvacuationRoute(null,"Exit Beta","Secondary route, high traffic potential",0.3)
        );


    }

}
