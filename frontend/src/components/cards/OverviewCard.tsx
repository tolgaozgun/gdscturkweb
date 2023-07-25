import { Card, Stack, Title } from '@mantine/core';
import {
	CategoryScale,
	ChartData,
	Chart as ChartJS,
	Legend,
	LineElement,
	LinearScale,
	PointElement,
	Tooltip,
} from 'chart.js';
import { Line } from 'react-chartjs-2';

ChartJS.register(CategoryScale, LinearScale, PointElement, LineElement, Tooltip, Legend);


interface OverviewCardProps {
	mt?: string;
	withBorder?: boolean;
	h?: string;
	w?: string;
	options: any;
	data: ChartData<"line">;
	name: string;
}


// What is the type of options


export function OverviewCard({mt, withBorder, h, w, options, data, name}: OverviewCardProps) {
	return (
		<Card radius="md" withBorder={withBorder} mt={mt} h={h} w={w}>
			<Stack h="100%" justify="between">
				<Title order={5}>{name}</Title>
				<Line options={options} data={data} />
			</Stack>
		</Card>
	);
}