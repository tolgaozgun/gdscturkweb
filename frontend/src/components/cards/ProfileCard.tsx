
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

const useStyle = createStyles(theme => ({
	section: {
		padding: theme.spacing.md,
		borderTop: `${rem(1)} solid ${
			theme.colorScheme === 'dark' ? theme.colors.dark[4] : theme.colors.gray[3]
		}`,
	},
}));

interface ProfileCardProps {
    user: UserModel;
    university: University;
    withBorder?: boolean;
}

export function ProfileCard({user, university, withBorder}: ProfileCardProps) {
	const { classes } = useStyle();

	return (
		<Card 
            withBorder={withBorder}
            radius="md">
                <Group position='apart'>
            {/* <Grid> */}
                {/* <Grid.Col xs={12} md={6}> */}
			<Card.Section className={classes.section}>
                <Image 
                    width={100}
                    height={100}
                    radius="xl"
                    src={user.profileImage} />

				<Space h="md" />

				<Flex direction="column">
					<Title order={5}>{user.name} {user.surname}</Title>
                    <Text fz="sm" fw="300">
                         {user.userType}
                    </Text>
					<Space h="xs" />
					<Text fz="sm" c="dimmed" fw="500">
						{user.email}
					</Text>
					<Space h="4" />
					<Text fz="sm" c="dimmed" fw="500">
                        {university.name}
					</Text>
				</Flex>
			</Card.Section>
            {/* </Grid.Col> */}
            {/* <Grid.Col xs={12} md={6}> */}
            <Flex align="end" justify="end" direction="column">
                <Container>
                    <Text c="dimmed" tt="uppercase" fw={700} fz="xs">
                        Last Login
                    </Text>
                    <Text fw={700} fz="xl">
                        {user.lastLoginDate ? user.lastLoginDate.toString() : 'Never'}
                    </Text>
                </Container>
                <Container mt={30}>
                    <Text c="dimmed" tt="uppercase" fw={700} fz="xs">
                        City
                    </Text>
                    <Text fw={700} fz="xl">
                        {university.city.name}
                    </Text>
                </Container>
                <Container mt={30}>
                    <Text c="dimmed" tt="uppercase" fw={700} fz="xs">
                        Country
                    </Text>
                    <Text fw={700} fz="xl">
                        {university.country.name}
                    </Text>
                </Container>
            </Flex>
            <Menu withinPortal shadow="sm">
                <Menu.Target>
                    <ActionIcon>
                        <IconDots size="1rem" />
                    </ActionIcon>
                </Menu.Target>

                <Menu.Dropdown>
                    <Menu.Item icon={<IconFileZip size={rem(14)} />}>Action One</Menu.Item>
                    <Menu.Item icon={<IconEye size={rem(14)} />}>Action Two</Menu.Item>
                    <Menu.Item icon={<IconTrash size={rem(14)} />} color="red">
                        Action Three
                    </Menu.Item>
                </Menu.Dropdown>
            </Menu>
            </Group>

            {/* </Grid.Col> */}
            {/* </Grid> */}

			{/* <Card.Section className={classes.section}>
				<Group position="apart" grow>
					<Stack spacing={4}>
						<Text fz="sm" fw="500">
							Balance
						</Text>
						<Title order={3}>$9821</Title>
					</Stack>
					<Stack spacing={4}>
						<Text fz="sm" fw="500">
							Chain
						</Text>
						<Title order={3}>Etherum</Title>
					</Stack>
				</Group>
			</Card.Section>

			<Card.Section className={classes.section}>
				<Group position="center">
					<Button variant="light">Deposit</Button>
					<Button>Buy/Sell</Button>
				</Group>
			</Card.Section> */}
		</Card>
	);
}