import axios from "axios"
import { Stop } from "../types/stop"


export async function getStopsInRadius(lat: number, lon: number, radius: number): Promise<Array<Stop>> {
    const response = await axios.post<{stops: Array<Stop>}>("http://localhost:3000/api/stops", {lat, lon, radius})

    return response.data.stops
}
