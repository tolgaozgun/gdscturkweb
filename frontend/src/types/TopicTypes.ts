export type Topic = {
    topicId: number;
    name: string;
    description: string;
    iconUrl: string;
    color: string;
}

export type CreateTopic = {
    name: string;
    description: string;
    iconUrl: string;
    color: string;
}

export type UpdateTopic = {
    name?: string;
    description?: string;
    iconUrl?: string;
    color?: string;
}
