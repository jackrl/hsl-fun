import React, { useEffect, useState } from 'react';
import L from 'leaflet';
import { Circle, Marker, Popup, useMap, useMapEvents } from "react-leaflet";
import { Stop } from '../types/stop';
import { getStopsInRadius } from '../api/hsl';

export function Markers() {
    const map = useMap()
    const [position, setPosition] = useState(map.getCenter())
    const [radius, setRadius] = useState(500)
    const [stops, setStops] = useState<Array<Stop>>([])

    useEffect(() => {
        async function getStops() {
            const stops = await getStopsInRadius(position.lat, position.lng, radius)
            setStops(stops)
        }

        getStops()
    }, [position, radius])

    useMapEvents({
        click(e) {
            setPosition(e.latlng)
            map.flyTo(e.latlng, map.getZoom())
        },
    })

    const markerIcon = L.icon({ iconUrl: "/images/marker-icon.png", shadowUrl: "/images/marker-shadow.png" });

    return (
        <div>
            <Circle
                center={position}
                radius={radius}
                stroke={false}
            />

            {stops.map((stop, i) => {
                return (<Marker position={L.latLng(stop.lat, stop.lon)}
                                icon={markerIcon}
                                key={i}>
                            <Popup>
                                <b>{stop.name}</b><br />
                                Distance:   {stop.distance}m<br />
                                Accessible: {stop.accessible ? "Yes" : "No"}
                            </Popup>
                        </Marker>)
            })}
        </div>
    )
}