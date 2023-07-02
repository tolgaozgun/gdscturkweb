
import {
    IconGauge,
    IconAdjustments,
    IconSchool,
    IconUsersGroup,
    IconFlag,
    IconTree,
    IconChalkboard,
    IconBrandCampaignmonitor,
  } from '@tabler/icons-react';

export const panelData = [
    { label: 'Dashboard', icon: IconGauge, link: 'panel/dashboard' },
    {
      label: 'Users',
      icon: IconUsersGroup,
      initiallyOpened: true,
      links: [
        { label: 'List Users', link: '/panel/users/list' },
        { label: 'Add a User', link: '/panel/users/add' },
      ],
    },
    {
      label: 'Buddy Teams',
      icon: IconChalkboard,
      links: [
        { label: 'List Buddy Teams', link: '/panel/buddy-teams/list' },
        { label: 'Add a Buddy Team', link: '/panel/buddy-teams/add' },
      ],
    },
    {
      label: 'Campaigns',
      icon: IconBrandCampaignmonitor,
      links: [
        { label: 'List Campaigns', link: '/panel/campaigns/list' },
        { label: 'Add a Campaign', link: '/panel/campaigns/add' },
      ],
    },
    {
      label: 'Universities',
      icon: IconSchool,
      links: [
        { label: 'List Universities', link: '/panel/universities/list' },
        { label: 'Add a University', link: '/panel/universities/add' },
      ],
    },
    {
      label: 'Cities',
      icon: IconTree,
      links: [
        { label: 'List Cities', link: '/panel/cities/list' },
        { label: 'Add a City', link: '/panel/cities/add' },
      ],
    },
    {
      label: 'Countries',
      icon: IconFlag,
      links: [
        { label: 'List Countries', link: '/panel/countries/list' },
        { label: 'Add a Country', link: '/panel/countries/add' },
      ],
    },
    { label: 'Settings', icon: IconAdjustments, link: 'panel/settings' },
  ];