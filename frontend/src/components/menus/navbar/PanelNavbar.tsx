import { Navbar, Group, Code, ScrollArea, createStyles, rem, MediaQuery, Image } from '@mantine/core';
import { LinksGroup } from './NavbarLinksGroup';
import Logo from '../../../assets/gdsc-logo.png';
import UserMenuItem from '../../UserMenuItem';
import { useNavigate } from 'react-router';



const useStyles = createStyles((theme) => ({
  navbar: {
    backgroundColor: theme.colorScheme === 'dark' ? theme.colors.dark[6] : theme.white,
    paddingBottom: 0,
  },

  header: {
    padding: theme.spacing.md,
    paddingTop: 0,
    marginLeft: `calc(${theme.spacing.md} * -1)`,
    marginRight: `calc(${theme.spacing.md} * -1)`,
    color: theme.colorScheme === 'dark' ? theme.white : theme.black,
    borderBottom: `${rem(1)} solid ${
      theme.colorScheme === 'dark' ? theme.colors.dark[4] : theme.colors.gray[3]
    }`,
  },

  links: {
    marginLeft: `calc(${theme.spacing.md} * -1)`,
    marginRight: `calc(${theme.spacing.md} * -1)`,
  },

  linksInner: {
    paddingTop: theme.spacing.xl,
    paddingBottom: theme.spacing.xl,
  },

  footer: {
    marginLeft: `calc(${theme.spacing.md} * -1)`,
    marginRight: `calc(${theme.spacing.md} * -1)`,
    borderTop: `${rem(1)} solid ${
      theme.colorScheme === 'dark' ? theme.colors.dark[4] : theme.colors.gray[3]
    }`,
  },
}));

interface PanelNavbarProps {
  panelName: string;
  panelData: PanelItem[];
}
;
export function PanelNavbar({panelName, panelData}: PanelNavbarProps) {
  const { classes } = useStyles();
  const navigate = useNavigate();
  const links = panelData.map((item) => <LinksGroup {...item} key={item.label} />);

  const handleHomePageRedirect = () => {
    navigate('/');
  }

  return (
    <>
        <MediaQuery smallerThan="sm" styles={{ display: 'none' }}>
            <Navbar width={{ sm: 300 }} p="md" className={classes.navbar}>
                <Navbar.Section className={classes.header}>
                    <Group position="apart">
                      <Image width={rem(80)} src={Logo} onClick={handleHomePageRedirect} />
                      <Code sx={{ fontWeight: 700 }}>{panelName}</Code>
                    </Group>
                </Navbar.Section>

                <Navbar.Section grow className={classes.links} component={ScrollArea}>
                    <div className={classes.linksInner}>{links}</div>
                </Navbar.Section>

                <Navbar.Section className={classes.footer}>
                  <UserMenuItem />
                </Navbar.Section>
            </Navbar>
            
        </MediaQuery>
    </>
  );
}