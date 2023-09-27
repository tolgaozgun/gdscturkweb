import { Topic } from "./TopicTypes";
import { University } from "./UniversityTypes";

export type Activity = {
    eventId: number;
    organizers: Array<University>;
    title: string;
    description: string;
    address: string;
    startDate: Date;
    endDate?: Date;
    link: string;
    topics: Array<Topic>;
}