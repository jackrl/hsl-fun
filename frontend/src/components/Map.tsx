import React from 'react';
import L from 'leaflet';
import { MapContainer, TileLayer, Marker, Popup } from 'react-leaflet'
import { Markers } from './Markers';

export function Map() {
    const initCenter = L.latLng(60.19803, 24.93129)

    return (
        <MapContainer center={initCenter} zoom={13} scrollWheelZoom={true} style={mapContainerStyle}>
            <TileLayer
                url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
            />
            <Markers />
        </MapContainer>
    )
}

const mapContainerStyle = {
    height: "100vh",
    width: "100%",
    backgroundColor: "red",
}
