
import {
    IconGauge,
    IconAdjustments,
    IconSchool,
    IconUsersGroup,
    IconFlag,
    IconTree,
    IconBrandCampaignmonitor,
    IconQuestionMark,
    IconUserCircle,
    IconHome,
  } from '@tabler/icons-react';

export const CoreTeamPanelData: PanelItem[] = [
    { label: 'Back to Home Page', icon: IconHome, link: '/' },
    { label: 'Dashboard', icon: IconGauge, link: '/panel/core-team/dashboard' },
    {
      label: 'My Core Team',
      link: '/panel/core-team/core-team',
      icon: IconUsersGroup,
      links: [
        { label: 'List Users', link: '/panel/core-team/core-team/my' },
      ],
    },
    {
      label: 'Campaigns',
      link: '/panel/core-team/campaigns',
      icon: IconBrandCampaignmonitor,
      links: [
        { label: 'List Current Campaigns', link: '/panel/core-team/campaigns/current' },
        { label: 'List All Campaigns', link: '/panel/core-team/campaigns/all' },
      ],
    },
    {
      label: 'Leads',
      link: '/panel/core-team/leads',
      icon: IconUserCircle,
      links: [
        { label: 'Leads Map', link: '/map/' },
      ],
    },
    {
      label: 'Universities',
      link: '/panel/core-team/universities',
      icon: IconSchool,
      links: [
        { label: 'List Universities', link: '/panel/core-team/universities/list' },
      ],
    },
    {
      label: 'Cities',
      link: '/panel/core-team/cities',
      icon: IconTree,
      links: [
        { label: 'List Cities', link: '/panel/core-team/cities/list' },
      ],
    },
    {
      label: 'Countries',
      link: '/panel/core-team/countries',
      icon: IconFlag,
      links: [
        { label: 'List Countries', link: '/panel/core-team/countries/list' },
      ],
    },
    {
      label: 'Questions',
      link: '/panel/core-team/questions',
      icon: IconQuestionMark,
      links: [
        { label: 'List Questions', link: '/panel/core-team/questions/list' },
        { label: 'Ask a Question', link: '/panel/core-team/questions/ask' },
      ],
    },
    {
      label: 'Settings',
      link: '/panel/core-team/settings',
      icon: IconAdjustments,
      links: [
        { label: 'Account Settings', link: '/panel/core-team/settings/account' },
        { label: 'Ask a Question', link: '/panel/core-team/settings/profile' },
        { label: 'Preferences', link: '/panel/core-team/settings/preferences' },
      ],
    },
  ];