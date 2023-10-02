
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
    IconConfetti,
  } from '@tabler/icons-react';

export const LeadPanelData: PanelItem[] = [
    { label: 'Back to Home Page', icon: IconHome, link: '/' },
    { label: 'Dashboard', icon: IconGauge, link: '/panel/lead/dashboard' },
    {
      label: 'My Core Team',
      link: '/panel/lead/core-team',
      icon: IconUsersGroup,
      links: [
        { label: 'List Users', link: '/panel/lead/core-team/my' },
        { label: 'Invite a User', link: '/panel/lead/core-team/invite' },
      ],
    },
    {
      label: 'My Buddy Team',
      link: '/panel/lead/buddy-teams',
      icon: IconChalkboard,
      links: [
        { label: 'View Buddy Team', link: '/panel/lead/buddy-teams/my' },
        { label: 'Attendance', link: '/panel/lead/buddy-teams/attendance' },
        { label: 'List All Buddy Teams', link: '/panel/lead/buddy-teams/all' },
      ],
    },
    {
      label: 'Events',
      link: '/panel/lead/events',
      icon: IconConfetti,
      links: [
        { label: 'My Events', link: '/panel/lead/events/my' },
        { label: 'List All Events', link: '/panel/lead/events/all' },
      ],
    },
    {
      label: 'Campaigns',
      link: '/panel/lead/campaigns', 
      icon: IconBrandCampaignmonitor,
      links: [
        { label: 'List Current Campaigns', link: '/panel/lead/campaigns/current' },
        { label: 'List All Campaigns', link: '/panel/lead/campaigns/all' },
      ],
    },
    {
      label: 'Leads',
      link: '/panel/lead/leads',
      icon: IconUserCircle,
      links: [
        { label: 'All Leads', link: '/panel/lead/leads/all' },
      ],
    },
    {
      label: 'Universities',
      link: '/panel/lead/universities',
      icon: IconSchool,
      links: [
        { label: 'All Universities', link: '/panel/lead/universities/all' },
      ],
    },
    {
      label: 'Cities',
      link: '/panel/lead/cities',
      icon: IconTree,
      links: [
        { label: 'List Cities', link: '/panel/lead/cities/list' },
      ],
    },
    {
      label: 'Countries',
      link: '/panel/lead/countries',
      icon: IconFlag,
      links: [
        { label: 'List Countries', link: '/panel/lead/countries/list' },
      ],
    },
    {
      label: 'Questions',
      link: '/panel/lead/questions',
      icon: IconQuestionMark,
      links: [
        { label: 'List Questions', link: '/panel/lead/questions/list' },
        { label: 'Ask a Question', link: '/panel/lead/questions/ask' },
      ],
    },
    {
      label: 'Settings',
      link: '/panel/lead/settings',
      icon: IconAdjustments,
      links: [
        { label: 'User Settings', link: '/panel/lead/settings/user' },
        { label: 'Lead Settings', link: '/panel/lead/settings/lead' },
        { label: 'Preferences', link: '/panel/lead/settings/preferences' },
      ],
    },
  ];