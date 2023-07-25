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
    name: string;
    latitude: number;
    longitude: number;
    cityId: number;
    countryId: number;
}

export type UpdateUniversity = {
    latitude?: number;
    longitude?: number;
    name?: string;
    city?: City;
    country?: Country;
}
