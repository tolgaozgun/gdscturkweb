import { Country } from "./CountryTypes";

export type City = {
    cityId: number;
    name: string;
    country: Country;
    latitude: number;
    longitude: number;
}

export type AddCity = {
    name: string;
    countryId: number;
    latitude: number;
    longitude: number;
}

export type UpdateCity = {
    name?: string;
    country?: Country;
    latitude?: number;
    longitude?: number;
}
