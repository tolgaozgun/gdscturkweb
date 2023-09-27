import { useState } from 'react';
import {
  createStyles,
  Container,
  Avatar,
  UnstyledButton,
  Group,
  Text,
  Menu,
  Tabs,
  Burger,
  rem,
  Image,
  Flex
} from '@mantine/core';
import { useDisclosure } from '@mantine/hooks';
import {
  IconLogout,
  IconSettings,
  IconChevronDown,
  IconUser,
} from '@tabler/icons-react';
import GDSCLogo from '../../assets/gdsc-logo.png';
import { useNavigate } from 'react-router';
import { User, UserType } from '../../types';
import { LanguagePicker } from '../common/LanguagePicker';

const useStyles = createStyles((theme) => ({
  header: {
    paddingTop: theme.spacing.sm,
    backgroundColor: theme.colorScheme === 'dark' ? theme.colors.dark[6] : theme.colors.gray[0],
    borderBottom: `${rem(1)} solid ${
      theme.colorScheme === 'dark' ? 'transparent' : theme.colors.gray[2]
    }`,
      },

  mainSection: {
    paddingBottom: theme.spacing.sm,
  },

  user: {
    color: theme.colorScheme === 'dark' ? theme.colors.dark[0] : theme.black,
    padding: `${theme.spacing.xs} ${theme.spacing.sm}`,
    borderRadius: theme.radius.sm,
    transition: 'background-color 100ms ease',

    '&:hover': {
      backgroundColor: theme.colorScheme === 'dark' ? theme.colors.dark[8] : theme.white,
    },

    [theme.fn.smallerThan('xs')]: {
      display: 'none',
    },
  },
  rightAlignedTabs: {
    marginLeft: 'auto',
  },

  burger: {
    [theme.fn.largerThan('xs')]: {
      display: 'none',
    },
  },

  userActive: {
    backgroundColor: theme.colorScheme === 'dark' ? theme.colors.dark[8] : theme.white,
  },

  tabs: {
    [theme.fn.smallerThan('sm')]: {
      display: 'none',
    },
  },

  tabsList: {
    borderBottom: '0 !important',
  },

  tab: {
    fontWeight: 500,
    height: rem(38),
    backgroundColor: 'transparent',

    '&:hover': {
      backgroundColor: theme.colorScheme === 'dark' ? theme.colors.dark[5] : theme.colors.gray[1],
    },

    '&[data-active]': {
      backgroundColor: theme.colorScheme === 'dark' ? theme.colors.dark[7] : theme.white,
      borderColor: theme.colorScheme === 'dark' ? theme.colors.dark[7] : theme.colors.gray[2],
    },
  },
}));

interface SecondHeaderProps {
  user: User | null | undefined;
  tabs: { name: string; link: string }[];
  isLoggedIn?: boolean;
}

export function AppHeader({ user, tabs, isLoggedIn = true }: SecondHeaderProps) {
  const { classes, cx } = useStyles();
  const [opened, { toggle }] = useDisclosure(false);
  const [userMenuOpened, setUserMenuOpened] = useState(false);
  const navigate = useNavigate();
  
  const handleTabClick = (link: string) => {
    navigate(link);
  }

  const handleHomePageRedirect = () => {
    navigate("/");
  }

  const handleLogout = () => {
    navigate('/logout');
  }

  const items = tabs.map((tab) => (
    <Tabs.Tab value={tab.name} key={tab.name} onClick={() => {handleTabClick(tab.link)}}>
      {tab.name}
    </Tabs.Tab>
  ));

  let userTypeSlug = "";
  let userTypeString = "";
  switch(user?.userType) {
    default:
    case UserType.Lead:
      userTypeSlug = "lead";
      userTypeString = "Lead";
      break;
    case UserType.Facilitator:
      userTypeSlug = "facilitator";
      userTypeString = "Facilitator";
      break;
    case UserType.CoreTeamMember:
      userTypeSlug = "core-team";
      userTypeString = "Core Team";
      break;
    case UserType.Admin:
      userTypeSlug = "admin";
      userTypeString = "Admin";
      break;
    case UserType.Googler:
      userTypeSlug = "googler";
      userTypeString = "Googler";
      break;
  }


  
  let userBar = <></>;

  if (!isLoggedIn || !user) {
    userBar = (
      <>
      <Group 
        spacing={1.0}>
        <UnstyledButton
            className={cx(classes.user)}
            onClick={() => {navigate("/login")}}
          >
            Sign In
        </UnstyledButton>
        <UnstyledButton
            className={cx(classes.user)}
            onClick={() => {navigate("/register")}}
          >
            Register
        </UnstyledButton>
        </Group>
      </>
    )
  } else {


    userBar = (
      <>
          <Burger opened={opened} onClick={toggle} className={classes.burger} size="sm" />

          <Menu
            width={260}
            position="bottom-end"
            transitionProps={{ transition: 'pop-top-right' }}
            onClose={() => setUserMenuOpened(false)}
            onOpen={() => setUserMenuOpened(true)}
            withinPortal
          >
            <Menu.Target>
              <UnstyledButton
                className={cx(classes.user, { [classes.userActive]: userMenuOpened })}
              >
                <Group spacing={7}>
                  <Avatar src={user.profileImage} alt={user.name + " " + user.surname} radius="xl" size={20} />
                  <Text weight={500} size="sm" sx={{ lineHeight: 1 }} mr={3}>
                    {user.name}
                  </Text>
                  <IconChevronDown size={rem(12)} stroke={1.5} />
                </Group>
              </UnstyledButton>
            </Menu.Target>
            <Menu.Dropdown>

              <Menu.Label>Settings</Menu.Label>
                <Menu.Item icon={<IconUser size="0.9rem" stroke={1.5} />} onClick={() => {handleTabClick("/panel/" + userTypeSlug + "/settings/user")}}>
                    User Settings
                </Menu.Item>
                <Menu.Item icon={<IconSettings size="0.9rem" stroke={1.5} />} onClick={() => {handleTabClick("/panel/" + userTypeSlug + "/settings/" + userTypeSlug)}}>
                    {userTypeString} Settings
                </Menu.Item>
                <Menu.Item icon={<IconLogout size="0.9rem" stroke={1.5} />} onClick={handleLogout}>Logout</Menu.Item>
            </Menu.Dropdown>
          </Menu>
          </>
          
  )
}


  return (
    <div className={classes.header}>
      <Container className={classes.mainSection}>
        <Group position="apart">
          
          <Image src={GDSCLogo} onClick={handleHomePageRedirect} height={28} width={60} />
          { userBar }
        </Group>
      </Container>
      <Container>
        <Tabs
          defaultValue="Home"
          variant="outline"
          classNames={{
            root: classes.tabs,
            tabsList: classes.tabsList,
            tab: classes.tab,
          }}
        >
          <Tabs.List>
            {items}
            <Flex
              direction="row"
              ml="auto"
              justify="flex-end"
            >
              {user && user.userType == UserType.Lead &&
              <Tabs.Tab value="Lead Panel" key="Lead Panel" onClick={() => {handleTabClick("/panel/lead")}}>
                Lead Panel
              </Tabs.Tab>
              }
              {user && user.userType == UserType.Facilitator &&
              <Tabs.Tab value="Facilitator Panel" key="Facilitator Panel" onClick={() => {handleTabClick("/panel/facilitator")}}>
                Facilitator Panel
              </Tabs.Tab>
              }
              {user && user.userType == UserType.CoreTeamMember &&
              <Tabs.Tab value="Core Team Panel" key="Core Team Panel" onClick={() => {handleTabClick("/panel/core-team")}}>
                Core Team Panel
              </Tabs.Tab>
              }
              {user && user.userType == UserType.Admin &&
              <Tabs.Tab value="Admin Panel" key="Admin Panel" onClick={() => {handleTabClick("/panel/admin")}}>
                Admin Panel
              </Tabs.Tab>
              }
            </Flex>
          </Tabs.List>
        </Tabs>
      </Container>
    </div>
  );
}