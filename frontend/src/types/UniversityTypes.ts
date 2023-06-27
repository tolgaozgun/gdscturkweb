import { City, Country } from "./LocationTypes";

export type University = {
    universityId: number;
    latitude: number;
    longitude: number;
    name: string;
    city: City;
    country: Country;
}

