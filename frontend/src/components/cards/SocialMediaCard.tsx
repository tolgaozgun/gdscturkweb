
import {
	ActionIcon,
	Card,
	Flex,
	Group,
	Menu,
	Space,
	Text,
	Title,
	createStyles,
	rem,
    Image,
    Container,
} from '@mantine/core';
import { IconDots, IconEye, IconFileZip, IconTrash } from '@tabler/icons-react';
import { UserModel } from '../../types';
import { University } from '../../types/UniversityTypes';
import { SocialMediaLinks } from '../../types/ProfileTypes';

const useStyle = createStyles(theme => ({
	section: {
		padding: theme.spacing.md,
		borderTop: `${rem(1)} solid ${
			theme.colorScheme === 'dark' ? theme.colors.dark[4] : theme.colors.gray[3]
		}`,
	},
}));

interface SocialMediaCardProps {
    socialMediaLinks: SocialMediaLinks;
    withBorder?: boolean;
}

export function SocialMediaCard({socialMediaLinks, withBorder}: SocialMediaCardProps) {
	const { classes } = useStyle();

	return (
		<Card 
            withBorder={withBorder}
            radius="md">
                <Group position='apart'>
                    
            </Group>
		</Card>
	);
}