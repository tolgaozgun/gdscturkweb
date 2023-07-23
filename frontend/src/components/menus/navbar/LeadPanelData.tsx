
import {
    IconGauge,
    IconAdjustments,
    IconSchool,
    IconUsersGroup,
    IconFlag,
    IconTree,
    IconChalkboard,
    IconBrandCampaignmonitor,
    IconQuestionMark,
    IconUserCircle,
    IconHome,
  } from '@tabler/icons-react';

export const LeadPanelData: PanelItem[] = [
    { label: 'Back to Home Page', icon: IconHome, link: '/' },
    { label: 'Dashboard', icon: IconGauge, link: '/panel/lead/dashboard' },
    {
      label: 'My Core Team',
      initiallyOpened: true,
      icon: IconUsersGroup,
      links: [
        { label: 'List Users', link: '/panel/lead/core-team/list' },
        { label: 'Invite a User', link: '/panel/lead/core-team/invite' },
      ],
    },
    {
      label: 'My Buddy Team',
      initiallyOpened: true,
      icon: IconChalkboard,
      links: [
        { label: 'View Buddy Team', link: '/panel/lead/buddy-teams/list' },
        { label: 'Attendance', link: '/panel/lead/buddy-teams/attendance' },
        { label: 'List All Buddy Teams', link: '/panel/lead/buddy-teams/add' },
      ],
    },
    {
      label: 'Campaigns',
      icon: IconBrandCampaignmonitor,
      links: [
        { label: 'List Current Campaigns', link: '/panel/lead/campaigns/list' },
        { label: 'List All Campaigns', link: '/panel/lead/campaigns/list' },
        { label: 'Add a Campaign', link: '/panel/lead/campaigns/add' },
      ],
    },
    {
      label: 'Leads',
      icon: IconUserCircle,
      links: [
        { label: 'Leads Map', link: '/map/' },
      ],
    },
    {
      label: 'Universities',
      icon: IconSchool,
      links: [
        { label: 'List Universities', link: '/panel/lead/universities/list' },
      ],
    },
    {
      label: 'Cities',
      icon: IconTree,
      links: [
        { label: 'List Cities', link: '/panel/lead/cities/list' },
      ],
    },
    {
      label: 'Countries',
      icon: IconFlag,
      links: [
        { label: 'List Countries', link: '/panel/lead/countries/list' },
      ],
    },
    {
      label: 'Questions',
      icon: IconQuestionMark,
      links: [
        { label: 'List Questions', link: '/panel/lead/questions/list' },
        { label: 'Ask a Question', link: '/panel/lead/questions/add' },
      ],
    },
    { label: 'Settings', icon: IconAdjustments, link: '/panel/lead/settings' },
  ];