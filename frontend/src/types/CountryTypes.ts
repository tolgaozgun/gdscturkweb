export type Country = {
    countryId: number;
    name: string;
    flagImage: string;
}

export type CreateCountry = {
    name: string;
    flagImage: string;
}

export type UpdateCountry = {
    countryId: number;
    name?: string;
    flagImage?: string;
}
