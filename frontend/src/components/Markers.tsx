import React, { useState } from 'react';
import L from 'leaflet';
import { Circle, Marker, Popup, useMap, useMapEvents } from "react-leaflet";

export function Markers() {
    const map = useMap()
    const [position, setPosition] = useState(map.getCenter())
    const [radius, setRadius] = useState(500)

    useMapEvents({
        click(e) {
            setPosition(e.latlng)
            map.flyTo(e.latlng, map.getZoom())
        },
    })

    const icon = L.icon({ iconUrl: "/images/marker-icon.png", shadowUrl: "/images/marker-shadow.png" });

    return (
        <div>
            <Circle
                center={position}
                radius={radius}
                stroke={false}
            />
        </div>
    )
}