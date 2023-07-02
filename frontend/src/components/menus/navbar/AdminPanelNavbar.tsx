import { Navbar, Group, Code, ScrollArea, createStyles, rem, MediaQuery, Image, Menu } from '@mantine/core';
import { UserButton } from './UserButton';
import { LinksGroup } from './NavbarLinksGroup';
import Logo from '../../../assets/gdsc-logo.png';
import { panelData } from './PanelData';
import { IconHeart, IconLogout, IconMessage, IconPlayerPause, IconSettings, IconStar, IconSwitchHorizontal, IconTrash } from '@tabler/icons-react';
import { useState } from 'react';


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

export function AdminPanelNavbar() {
  const { classes } = useStyles();
  const links = panelData.map((item) => <LinksGroup {...item} key={item.label} />);
  const [isMenuOpen, setMenuOpen] = useState(false);

  const handleProfileClick = () => {
    setMenuOpen(!isMenuOpen);
    console.log("clicked");
  }

  return (
    <>
        <MediaQuery smallerThan="sm" styles={{ display: 'none' }}>
            <Navbar width={{ sm: 300 }} p="md" className={classes.navbar}>
                <Navbar.Section className={classes.header}>
                    <Group position="apart">
                    <Image width={rem(120)} src={Logo} />
                    <Code sx={{ fontWeight: 700 }}>Admin Panel</Code>
                    </Group>
                </Navbar.Section>

                <Navbar.Section grow className={classes.links} component={ScrollArea}>
                    <div className={classes.linksInner}>{links}</div>
                </Navbar.Section>

                <Navbar.Section onClick={() => { handleProfileClick(); }} className={classes.footer}>
                    <UserButton
                    image="https://picsum.photos/200"
                    name="Tolga Ozgun"
                    email="tolgaozgunn@gmail.com"
                    />
                </Navbar.Section>
                {isMenuOpen && 
                <Menu>
                    <Menu.Item
                        icon={<IconHeart size="0.9rem" stroke={1.5} />}
                    >
                        Liked posts
                    </Menu.Item>
                    <Menu.Item
                        icon={<IconStar size="0.9rem" stroke={1.5} />}
                    >
                        Saved posts
                    </Menu.Item>
                    <Menu.Item
                        icon={<IconMessage size="0.9rem" stroke={1.5} />}
                    >
                        Your comments
                    </Menu.Item>

                    <Menu.Label>Settings</Menu.Label>
                    <Menu.Item icon={<IconSettings size="0.9rem" stroke={1.5} />}>
                        Account settings
                    </Menu.Item>
                    <Menu.Item icon={<IconSwitchHorizontal size="0.9rem" stroke={1.5} />}>
                        Change account
                    </Menu.Item>
                    <Menu.Item icon={<IconLogout size="0.9rem" stroke={1.5} />}>Logout</Menu.Item>

                    <Menu.Divider />

                    <Menu.Label>Danger zone</Menu.Label>
                    <Menu.Item icon={<IconPlayerPause size="0.9rem" stroke={1.5} />}>
                        Pause subscription
                    </Menu.Item>
                    <Menu.Item color="red" icon={<IconTrash size="0.9rem" stroke={1.5} />}>
                        Delete account
                    </Menu.Item>
                </Menu>
                }
            </Navbar>
            
        </MediaQuery>
    </>
  );
}