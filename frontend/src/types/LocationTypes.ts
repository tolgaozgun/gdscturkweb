export type City = {
    cityId: number;
    name: string;
    country: Country;
    latitude: number;
    longitude: number;
}

export type Country = {
    countryId: number;
    name: string;
    flagImage: string;
}