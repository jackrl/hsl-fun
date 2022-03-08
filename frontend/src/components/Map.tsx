import React from 'react';
import L from 'leaflet';
import { MapContainer, TileLayer, Marker, Popup } from 'react-leaflet'

const icon = L.icon({ iconUrl: "/images/marker-icon.png", shadowUrl: "/images/marker-shadow.png" });

const position = L.latLng(60.19803, 24.93129)


export function Map() {
    const mapContainerStyle = {
        height: "100vh",
        width: "100%",
        backgroundColor: "red",
    }
    
    return (
        <MapContainer center={position} zoom={13} scrollWheelZoom={true} style={mapContainerStyle}>
            <TileLayer
                url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
            />
            <Marker position={position} icon={icon}>
                <Popup>
                    A pretty CSS3 popup. <br /> Easily customizable.
                </Popup>
            </Marker>
        </MapContainer>
    )
}
