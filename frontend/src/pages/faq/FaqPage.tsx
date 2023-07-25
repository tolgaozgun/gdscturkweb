import {
    createStyles,
    Title,
    Container,
    Text,
    UnstyledButton,
    Overlay,
    SimpleGrid,
    rem,
  } from '@mantine/core';
import useGetAllQuestionCategories from '../../hooks/question/useGetAllQuestionCategories';
import useAxiosSecure from '../../hooks/auth/useAxiosSecure';
import LoadingPage from '../LoadingPage';
  
  const useStyles = createStyles((theme) => ({
    wrapper: {
      paddingTop: theme.spacing.md,
      paddingBottom: `calc(${theme.spacing.xl} * 2)`,
    },
  
    header: {
      height: rem(400),
      boxSizing: 'border-box',
      backgroundImage: `linear-gradient(135deg, ${theme.colors[theme.primaryColor][4]} 0%, ${
        theme.colors[theme.primaryColor][6]
      } 100%)`,
      backgroundSize: 'cover',
      display: 'flex',
      alignItems: 'flex-end',
      justifyContent: 'space-between',
      position: 'relative',
      padding: `calc(${theme.spacing.xl} * 1.5) calc(${theme.spacing.xl} * 2)`,
      borderRadius: theme.radius.lg,
      marginBottom: theme.spacing.lg,
  
      [theme.fn.smallerThan(1080)]: {
        height: 'auto',
        flexDirection: 'column-reverse',
        alignItems: 'initial',
        padding: theme.spacing.xl,
      },
    },
  
    title: {
      color: theme.white,
      position: 'relative',
      zIndex: 1,
      fontSize: rem(46),
      fontWeight: 800,
      letterSpacing: rem(-0.5),
      fontFamily: `Greycliff CF, ${theme.fontFamily}`,
  
      [theme.fn.smallerThan(1080)]: {
        fontSize: rem(22),
        textAlign: 'center',
        marginTop: theme.spacing.xl,
      },
    },
  
    titleOverlay: {
      zIndex: 0,
      position: 'absolute',
      color: theme.white,
      fontWeight: 900,
      opacity: 0.1,
      fontSize: rem(320),
      lineHeight: 1,
      top: rem(10),
      left: rem(32),
      pointerEvents: 'none',
      fontFamily: `Greycliff CF, ${theme.fontFamily}`,
  
      [theme.fn.smallerThan(1080)]: {
        display: 'none',
      },
    },
  
    contact: {
      padding: `calc(${theme.spacing.xl} * 1.5)`,
      backgroundColor: theme.white,
      borderRadius: theme.radius.lg,
      boxShadow: theme.shadows.md,
  
      [theme.fn.smallerThan(1080)]: {
        padding: theme.spacing.xl,
      },
    },
  
    contactTitle: {
      color: theme.black,
      marginBottom: theme.spacing.xl,
      lineHeight: 1,
    },
  
    categoryCard: {
      height: rem(160),
      position: 'relative',
      backgroundSize: '100%',
      backgroundPosition: 'center',
      color: theme.white,
      borderRadius: theme.radius.lg,
      padding: theme.spacing.xl,
      overflow: 'hidden',
      transition: 'background-size 300ms ease',
  
      '&:hover': {
        backgroundSize: '105%',
      },
    },
  
    categoryLabel: {
      color: theme.white,
      zIndex: 2,
      position: 'relative',
    },
  }));
  
  export function FaqPage() {
    const { classes } = useStyles();
    const axiosSecure = useAxiosSecure();

    const {
      data: allQuestionCategories,
      isLoading: isQuestionCategoriesLoading,
      // isError: isLeadsError,
    } = useGetAllQuestionCategories(axiosSecure);
  
    const items = allQuestionCategories?.data!.map((category) => (
      <UnstyledButton
        style={{ backgroundImage: `url(${category.image})` }}
        className={classes.categoryCard}
        key={category.name}
      >
        <Overlay color="#000" opacity={0.6} zIndex={1} />
        <Text size="xl" align="center" weight={700} className={classes.categoryLabel}>
          {category.name}
        </Text>
      </UnstyledButton>
    ));



    if (isQuestionCategoriesLoading || !allQuestionCategories) {
      return <LoadingPage/>
    }
  
    return (
      <Container className={classes.wrapper} size="lg">
        <div className={classes.header}>
          <Title className={classes.title}>Frequently Asked Questions</Title>
          <Title className={classes.titleOverlay} role="presentation">
            FAQ
          </Title>
  
          {/* <div className={classes.contact}>
            <Text size="xl" weight={500} className={classes.contactTitle}>
              Contact us
            </Text>
  
            <ContactIconsList /> 
          </div> */}
        </div>
  
        <SimpleGrid cols={3} breakpoints={[{ maxWidth: 'sm', cols: 1 }]}>
          {items}
        </SimpleGrid>
      </Container>
    );
  }