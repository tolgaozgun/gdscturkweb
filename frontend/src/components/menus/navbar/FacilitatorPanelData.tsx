
import {
    IconGauge,
    IconAdjustments,
    IconSchool,
    IconFlag,
    IconTree,
    IconChalkboard,
    IconBrandCampaignmonitor,
    IconQuestionMark,
    IconHome,
  } from '@tabler/icons-react';

export const FacilitatorPanelData: PanelItem[] = [
    { label: 'Back to Home Page', icon: IconHome, link: '/' },
    { label: 'Dashboard', icon: IconGauge, link: '/panel/facilitator/dashboard' },
    {
      label: 'My Buddy Team',
      link: '/panel/facilitator/buddy-teams',
      icon: IconChalkboard,
      links: [
        { label: 'View Buddy Team', link: '/panel/facilitator/buddy-teams/my' },
        { label: 'Attendance', link: '/panel/facilitator/buddy-teams/attendance' },
        { label: 'List All Buddy Teams', link: '/panel/facilitator/buddy-teams/all' },
      ],
    },
    {
      label: 'Campaigns',
      link: '/panel/facilitator/campaigns',
      icon: IconBrandCampaignmonitor,
      links: [
        { label: 'List Current Campaigns', link: '/panel/facilitator/campaigns/current' },
        { label: 'List All Campaigns', link: '/panel/facilitator/campaigns/all' },
      ],
    },
    {
      label: 'Universities',
      link: '/panel/facilitator/universities',
      icon: IconSchool,
      links: [
        { label: 'List Universities', link: '/panel/facilitator/universities/list' },
      ],
    },
    {
      label: 'Cities',
      link: '/panel/facilitator/cities',
      icon: IconTree,
      links: [
        { label: 'List Cities', link: '/panel/facilitator/cities/list' },
      ],
    },
    {
      label: 'Countries',
      link: '/panel/facilitator/countries',
      icon: IconFlag,
      links: [
        { label: 'List Countries', link: '/panel/facilitator/countries/list' },
      ],
    },
    {
      label: 'Questions',
      link: '/panel/facilitator/questions',
      icon: IconQuestionMark,
      links: [
        { label: 'List Questions', link: '/panel/facilitator/questions/list' },
        { label: 'Ask a Question', link: '/panel/facilitator/questions/ask' },
      ],
    },
    {
      label: 'Settings',
      link: '/panel/facilitator/settings',
      icon: IconAdjustments,
      links: [
        { label: 'Account Settings', link: '/panel/facilitator/settings/account' },
        { label: 'Ask a Question', link: '/panel/facilitator/settings/profile' },
        { label: 'Preferences', link: '/panel/facilitator/settings/preferences' },
      ],
    },
  ];