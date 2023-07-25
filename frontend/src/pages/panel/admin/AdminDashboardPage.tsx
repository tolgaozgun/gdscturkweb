import { Group, Paper, SimpleGrid, createStyles, Text, Card, Progress, Grid } from "@mantine/core";
import { PageContainer } from "../../../components/PageContainer";
import { ProfileCard } from "../../../components/cards/ProfileCard";
import { OverviewCard } from "../../../components/cards/OverviewCard";


const useStyles = createStyles((theme) => ({
  root: {
    padding: `calc(${theme.spacing.xl} * 1.5)`,
  },

  label: {
    fontFamily: `Greycliff CF, ${theme.fontFamily}`,
  },
}));

const data = [
  {
    title: "Buddy Team Size",
    value: "10",
  },
  {
    title: "Total Activites Completed",
    value: "100",
  },
  {
    title: "Time as a Leader",
    value: "30 days 21 hours 39 minutes",
  },
  {
    title: "Core Team Size",
    value: "5",
  },
  {
    title: "Upcoming Events",
    value: "3",
  },
  {
    title: "Total Buddy Team Meetings",
    value: "35",
  },
]


const AdminDashboardPage = () => {
  const { classes } = useStyles();



  const labels = ['September', 'October', 'November', 'December', 'January', 'February', 'March', 'April'];
  const datasetEvents = [3, 2, 2, 1, 5, 4, 2, 4];
  const datasetAbsences = [1, 2, 1, 1, 0, 0, 0, 0];

  const dataEvents = {
    labels,
    datasets: [
      {
        label: 'Events',
        data: datasetEvents,
        tension: 0.4,
        borderColor: '#3BC9DB',
        backgroundColor: '#3BC9DB',

      },
    ],
  };

  const dataAbsences = {
    labels,
    datasets: [
      {
        label: 'Absences',
        data: datasetAbsences,
        tension: 0.4,
        borderColor: '#FF8C00',
        backgroundColor: '#FF8C00',

      },
    ],
  };

  const options = {
    responsive: true,
    smooth: true,
    plugins: {
      legend: {
        position: 'top' as const,
      },
    },
  };

    const stats = data.map((stat) => {
      return (
        <Paper withBorder p="md" radius="md" key={stat.title}>
          <Group position="apart">
            <div>
              <Text c="dimmed" tt="uppercase" fw={700} fz="xs" className={classes.label}>
                {stat.title}
              </Text>
              <Text fw={700} fz="xl">
                {stat.value}
              </Text>
            </div>
          </Group>
        </Paper>
      );
    });

    return (
      <PageContainer title="Dashboard">
        <div className={classes.root}>
          <Grid>
            <Grid.Col xs={12} md={6}>
              <ProfileCard withBorder />
            </Grid.Col>
            <Grid.Col xs={12} md={6}>
              <Card
                withBorder
                radius="md"
                padding="xl"
                sx={(theme) => ({
                  backgroundColor: theme.colorScheme === 'dark' ? theme.colors.dark[7] : theme.white,
                })}
              >
                <Text fz="xs" tt="uppercase" fw={700} c="dimmed">
                  Monthly required activities completed
                </Text>
                <Text fz="lg" fw={500}>
                  1 / 2
                </Text>
                <Progress value={50} mt="md" size="lg" radius="xl" />
              </Card>
              <Card
                withBorder
                radius="md"
                mt="sm"
                padding="xl"
                sx={(theme) => ({
                  backgroundColor: theme.colorScheme === 'dark' ? theme.colors.dark[7] : theme.white,
                })}
              >
                <Text fz="xs" tt="uppercase" fw={700} c="dimmed">
                  Weekly buddy team meetings attended
                </Text>
                <Text fz="lg" fw={500}>
                  27 / 35
                </Text>
                <Progress value={77} mt="md" size="lg" radius="xl" />
              </Card>
            </Grid.Col>
          </Grid>
          <SimpleGrid cols={3} mt="md" breakpoints={[{ maxWidth: 'sm', cols: 1 }]}>
            {stats}

            <OverviewCard withBorder data={dataEvents} options={options} name="Past Events"/>
            <OverviewCard withBorder data={dataAbsences} options={options} name="Buddy Team Meeting Absences"/>
            <OverviewCard withBorder data={dataEvents} options={options} name="Upcoming Events"/>
          </SimpleGrid>
        </div>
      </PageContainer>
    );
}

export default AdminDashboardPage;