import { Navbar, Group, Code, ScrollArea, createStyles, rem, MediaQuery, Image } from '@mantine/core';
import { LinksGroup } from './NavbarLinksGroup';
import Logo from '../../../assets/gdsc-logo.png';
import UserMenuItem from '../../UserMenuItem';
import { useLocation, useNavigate } from 'react-router';
import { useUser } from '../../../contexts/UserContext';



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


  footer: {
    marginLeft: `calc(${theme.spacing.md} * -1)`,
    marginRight: `calc(${theme.spacing.md} * -1)`,
    borderTop: `${rem(1)} solid ${
      theme.colorScheme === 'dark' ? theme.colors.dark[4] : theme.colors.gray[3]
    }`,
  },
}));

interface PanelNavbarProps {
  hidden: boolean;
  panelName: string;
  panelData: PanelItem[];
}
;
export function PanelNavbar({hidden, panelName, panelData}: PanelNavbarProps) {
  const { classes } = useStyles();
  const {user} = useUser();
  const navigate = useNavigate();
  const links = panelData.map((item) => <LinksGroup {...item} key={item.label} />);

  const handleHomePageRedirect = () => {
    navigate('/');
  }

  return (
            <Navbar 
              hidden={hidden}
              hiddenBreakpoint="md" 
              width={{ sm: 300 }} 
              p="md" 
              className={classes.navbar}>
                {/* <Navbar.Section className={classes.header}>
                    <Group position="apart">
                      <Image width={rem(80)} src={Logo} onClick={handleHomePageRedirect} />
                      <Code sx={{ fontWeight: 700 }}>{panelName}</Code>
                    </Group>
                </Navbar.Section> */}

                <Navbar.Section grow className={classes.links} component={ScrollArea} >
                    <div>{links}</div>
                </Navbar.Section>

                <Navbar.Section className={classes.footer}>
                  <UserMenuItem user={user}/>
                </Navbar.Section>
            </Navbar>
            
  );
}