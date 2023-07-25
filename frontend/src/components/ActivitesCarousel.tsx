import { Carousel } from '@mantine/carousel';
import { useMediaQuery } from '@mantine/hooks';
import { createStyles, Paper, Text, Title, Button, useMantineTheme, rem, Stack } from '@mantine/core';
import GDSCLogo from '../assets/gdsc-logo.png'

const useStyles = createStyles((theme) => ({
  card: {
    height: rem(440),
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'space-between',
    alignItems: 'flex-start',
    backgroundSize: 'cover',
    backgroundPosition: 'center',
  },

  title: {
    fontFamily: `Greycliff CF, ${theme.fontFamily}`,
    fontWeight: 900,
    color: theme.black,
    lineHeight: 1.2,
    fontSize: rem(32),
    marginTop: theme.spacing.xs,
  },

  category: {
    color: theme.black,
    opacity: 0.7,
    fontWeight: 700,
    textTransform: 'uppercase',
  },
}));

interface CardProps {
  title: string;
  category: string;
  university: string;
}

function Card({ title, category, university }: CardProps) {
  const { classes } = useStyles();

  return (
    <Paper
      shadow="md"
      p="xl"
      radius="md"
      sx={{ backgroundImage: `url(${GDSCLogo})`,
        backgroundSize: '50%', // Adjust this value to control the zoom level
        backgroundPosition: 'center', // Center the background image
        backgroundRepeat: 'no-repeat', // Do not repeat the image
     }}
      className={classes.card}
    >
      <div>
        <Text color="dark" className={classes.category} size="xs">
          {category}
        </Text>
        <Title color="dark" order={3} className={classes.title}>
          {title}
        </Title>
      </div>
      <Stack>

        <Text color="dark" className={classes.category} size="xs">
          {university}
        </Text>
        <Button variant="outline" color="dark">
          See Activity
        </Button>
      </Stack>
    </Paper>
  );
}

const data = [
  {
    title: "Flutter 101",
    category: 'Flutter',
    university: 'Bilkent University',
  },
  {
    title: "Android Compose #1",
    category: 'Android',
    university: 'Bilkent University',
  },
  {
    title: "Gen AI with Google Cloud",
    category: 'GCP',
    university: 'Bilkent University',
  }

];

const ActivitiesCarousel = () => {
  const theme = useMantineTheme();
  const mobile = useMediaQuery(`(max-width: ${theme.breakpoints.sm})`);
  const slides = data.map((item) => (
    <Carousel.Slide key={item.title}>
      <Card {...item} />
    </Carousel.Slide>
  ));

  return (
    <Carousel
      slideSize="50%"
      breakpoints={[{ maxWidth: 'sm', slideSize: '100%', slideGap: rem(2) }]}
      slideGap="xl"
      align="start"
      slidesToScroll={mobile ? 1 : 2}
    >
      {slides}
    </Carousel>
  );
}

export default ActivitiesCarousel;