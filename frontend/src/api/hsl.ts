

export async function getStopsInRadius(lat: number, lng: number, radius: number) {
    return {
        "stops": [
            {
                "name": "Pasilan asema",
                "lat": 60.1981,
                "lon": 24.93365,
                "distance": 225,
                "accessible": true
            },
            {
                "name": "Pasilan asema",
                "lat": 60.198075,
                "lon": 24.933694,
                "distance": 261,
                "accessible": false
            },
            {
                "name": "Pasilan asema",
                "lat": 60.198002,
                "lon": 24.933908,
                "distance": 294,
                "accessible": false
            }
        ]
    }
}