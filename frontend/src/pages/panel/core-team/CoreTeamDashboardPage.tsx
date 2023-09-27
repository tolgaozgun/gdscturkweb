import { Group, Paper, SimpleGrid, createStyles, Text, Card, Progress, Grid } from "@mantine/core";
import { PageContainer } from "../../../components/PageContainer";
import { ProfileCard } from "../../../components/cards/ProfileCard";
import { OverviewCard } from "../../../components/cards/OverviewCard";
import useAxiosSecure from "../../../hooks/auth/useAxiosSecure";
import LoadingLottie from "../../../components/common/other/LoadingLottie";
import useGetCoreTeamDashboard from "../../../hooks/user/useGetCoreTeamDashboard";
import useGetEventsByCurrentUserUniversity from "../../../hooks/event/useGetEventsByCurrentUserUniversity";
import { Activity } from "../../../types/EventTypes";


const useStyles = createStyles((theme) => ({
  root: {
    padding: `calc(${theme.spacing.xl} * 1.5)`,
  },

  label: {
    fontFamily: `Greycliff CF, ${theme.fontFamily}`,
  },
}));



const CoreTeamDashboardPage = () => {
  const axiosSecure = useAxiosSecure();
  const { classes } = useStyles();


	const {
		data: dashboardData,
		isLoading: isDashboardLoading,
		// isError: isUniversitiesError,
	} = useGetCoreTeamDashboard(axiosSecure);


  const {
    data: eventsData,
    isLoading: isEventsLoading,
    // isError: isUniversitiesError,
  } = useGetEventsByCurrentUserUniversity(axiosSecure);

  let data = [
    {
      title: "Total Activites Completed",
      value: "Unable to retrieve data",
    },
    {
      title: "Time as a Core Team Member",
      value: "Unable to retrieve data",
    },
    {
      title: "Core Team Size",
      value: "Unable to retrieve data",
    },
    {
      title: "Upcoming Events",
      value: "Unable to retrieve data",
    },
  ]
  let labels = ['September', 'October', 'November', 'December', 'January', 'February', 'March', 'April', 'May', 'June', 'July', 'August'];
  let monthlyEventCount = 0;
  let datasetEvents = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
  let datasetAttendances = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
  if (dashboardData?.data) {

    // today's date
    const today = new Date();
    let pastEvents: Activity[] = []
    const currentMonth = today.getMonth();
    let futureEvents: Activity[] = []
    
    if (eventsData?.data) {
      // seperate dashboardData.data.eventDates into past and future events
      pastEvents = eventsData.data.filter((event) => {
        return new Date(event.startDate) < today;
      });

      futureEvents = eventsData.data.filter((event) => {
        return new Date(event.startDate) >= today;
      });

      monthlyEventCount = eventsData.data.filter((event) => {
        return new Date(event.startDate).getMonth() === currentMonth;
      }).length;



      // get the number of events in each month starting from september
      datasetEvents = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
      datasetAttendances = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
      eventsData.data.forEach((event) => {
        const month = new Date(event.startDate).getMonth();
        datasetEvents[(month - 8) % 12]++;
      });
    }


    data = [
      {
        title: "Total Activites Completed",
        value: pastEvents.length.toString(),
      },
      {
        title: "Time as a Core Team Member",
        value: dashboardData.data.promotedAt ? `${Math.floor((new Date().getTime() - new Date(dashboardData.data.promotedAt).getTime()) / (1000 * 3600 * 24 * 30))} months` : "? months",
      },
      {
        title: "Core Team Size",
        value: dashboardData.data.coreTeamSize.toString(),
      },
      {
        title: "Upcoming Events",
        value: futureEvents.length.toString(),
      },
      {
        title: "Upcoming Events",
        value: futureEvents.length.toString(),
      },
      {
        title: "Upcoming Events",
        value: futureEvents.length.toString(),
      }
    ]
  }

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
        data: datasetAttendances,
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

    if (isDashboardLoading || !dashboardData) {
      return <LoadingLottie />;
    }

    return (
      <PageContainer title="Dashboard">
        <div className={classes.root}>
          <Grid>
            <Grid.Col xs={12} md={6}>
              <ProfileCard user={dashboardData?.data?.coreTeamMember?.user!} university={dashboardData?.data?.coreTeamMember?.university!} withBorder />
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
                  {monthlyEventCount} / 2
                </Text>
                <Progress value={monthlyEventCount/2} mt="md" size="lg" radius="xl" />
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
                  0 / 0
                </Text>
                <Progress value={0} mt="md" size="lg" radius="xl" />
              </Card>
            </Grid.Col>
          </Grid>
          <SimpleGrid cols={3} mt="md" breakpoints={[{ maxWidth: 'sm', cols: 1 }]}>
            {stats}

            <OverviewCard withBorder data={dataEvents} options={options} name="Past Events"/>
            <OverviewCard withBorder data={dataAbsences} options={options} name="Buddy Team Meeting Attendances"/>
            <OverviewCard withBorder data={dataEvents} options={options} name="Upcoming Events"/>
          </SimpleGrid>
        </div>
      </PageContainer>
    );
}

export default CoreTeamDashboardPage;