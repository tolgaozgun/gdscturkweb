import { City } from "./CityTypes";
import { Country } from "./CountryTypes";

export type University = {
    universityId: number;
    latitude: number;
    longitude: number;
    name: string;
    city: City;
    country: Country;
}

export type CreateUniversity = {
    latitude: number;
    longitude: number;
    name: string;
    city: City;
    country: Country;
}

export type UpdateUniversity = {
    latitude?: number;
    longitude?: number;
    name?: string;
    city?: City;
    country?: Country;
}
