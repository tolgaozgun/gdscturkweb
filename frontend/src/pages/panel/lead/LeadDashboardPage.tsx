import { Group, Paper, SimpleGrid, createStyles, Text, Card, Progress, Grid } from "@mantine/core";
import { PageContainer } from "../../../components/PageContainer";
import { ProfileCard } from "../../../components/cards/ProfileCard";
import { OverviewCard } from "../../../components/cards/OverviewCard";
import useGetLeadDashboard from "../../../hooks/user/useGetLeadDashboard";
import useAxiosSecure from "../../../hooks/auth/useAxiosSecure";
import LoadingLottie from "../../../components/common/other/LoadingLottie";


const useStyles = createStyles((theme) => ({
  root: {
    padding: `calc(${theme.spacing.xl} * 1.5)`,
  },

  label: {
    fontFamily: `Greycliff CF, ${theme.fontFamily}`,
  },
}));



const LeadDashboardPage = () => {
  const axiosSecure = useAxiosSecure();
  const { classes } = useStyles();


	const {
		data: dashboardData,
		isLoading: isDashboardLoading,
		// isError: isUniversitiesError,
	} = useGetLeadDashboard(axiosSecure);

  let data = [
    {
      title: "Buddy Team Size",
      value: "Unable to retrieve data",
    },
    {
      title: "Total Activites Completed",
      value: "Unable to retrieve data",
    },
    {
      title: "Time as a Leader",
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
    {
      title: "Total Buddy Team Meetings",
      value: "Unable to retrieve data",
    },
  ]
  let labels = ['September', 'October', 'November', 'December', 'January', 'February', 'March', 'April', 'May', 'June', 'July', 'August'];
  let monthlyEventCount = 0;
  let noOfBuddyTeamMeetings = 0;
  let totalAttendance = 0;
  let datasetEvents = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
  let datasetAttendances = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
  if (dashboardData?.data) {

    // today's date
    const today = new Date();
    let pastEvents: Date[] = []
    const currentMonth = today.getMonth();
    let futureEvents: Date[] = []

    if (dashboardData.data.eventDates) {
      // seperate dashboardData.data.eventDates into past and future events
      pastEvents = dashboardData.data.eventDates.filter((date) => {
        return new Date(date) < today;
      });

      futureEvents = dashboardData.data.eventDates.filter((date) => {
        return new Date(date) >= today;
      });

      monthlyEventCount = dashboardData.data.eventDates.filter((date) => {
        return new Date(date).getMonth() === currentMonth;
      }).length;


      // get the number of events in each month starting from september
      datasetEvents = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
      datasetAttendances = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
      dashboardData.data.eventDates.forEach((date) => {
        const month = new Date(date).getMonth();
        datasetEvents[(month - 8) % 12]++;
      });
    }

    if (dashboardData.data.buddyMeetings && dashboardData.data.buddyMeetings instanceof Map) { 

      // get the number of attended meetings in each month starting from september
      dashboardData.data.buddyMeetings.forEach((attended: boolean, date: Date) => {
        noOfBuddyTeamMeetings++;
        if (!attended) return;
        const month = new Date(date).getMonth();
        datasetAttendances[(month - 8) % 12]++;
        totalAttendance++;
      });
    }


    data = [
      {
        title: "Buddy Team Size",
        value: dashboardData.data.buddyTeamSize ? dashboardData.data.buddyTeamSize.toString() : "Not in a buddy team",
      },
      {
        title: "Total Activites Completed",
        value: pastEvents.length.toString(),
      },
      {
        title: "Time as a Leader",
        value: "To be added",
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
        title: "Total Buddy Team Meetings",
        value: noOfBuddyTeamMeetings.toString(),
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
              <ProfileCard user={dashboardData?.data?.lead?.user!} university={dashboardData?.data?.lead?.university!} withBorder />
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
                  {totalAttendance} / {noOfBuddyTeamMeetings}
                </Text>
                <Progress value={77} mt="md" size="lg" radius="xl" />
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

export default LeadDashboardPage;