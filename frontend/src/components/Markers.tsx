import React, { useEffect, useState } from 'react';
import L from 'leaflet';
import { Marker, Popup, useMap, useMapEvents } from "react-leaflet";

export function Markers() {
    const map = useMap()
    const [position, setPosition] = useState(map.getCenter())

    const mapEvents = useMapEvents({
        click(e) {
          setPosition(e.latlng)
          map.flyTo(e.latlng, map.getZoom())
        },
      })

    const icon = L.icon({ iconUrl: "/images/marker-icon.png", shadowUrl: "/images/marker-shadow.png" });

    return (
        <div>
            <Marker position={position} icon={icon}>
                <Popup>
                    A pretty CSS3 popup. <br /> Easily customizable.
                </Popup>
            </Marker>
        </div>
    )
}