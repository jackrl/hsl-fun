import { Stop } from "../types/stop"


export async function getStopsInRadius(lat: number, lng: number, radius: number): Promise<Array<Stop>> {
    const stopsResponse = {
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

    return stopsResponse.stops
}