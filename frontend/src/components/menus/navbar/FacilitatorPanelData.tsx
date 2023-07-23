
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
      initiallyOpened: true,
      icon: IconChalkboard,
      links: [
        { label: 'View Buddy Team', link: '/panel/facilitator/buddy-teams/list' },
        { label: 'Attendance', link: '/panel/facilitator/buddy-teams/attendance' },
        { label: 'List All Buddy Teams', link: '/panel/facilitator/buddy-teams/add' },
      ],
    },
    {
      label: 'Campaigns',
      icon: IconBrandCampaignmonitor,
      links: [
        { label: 'List Current Campaigns', link: '/panel/facilitator/campaigns/list' },
        { label: 'List All Campaigns', link: '/panel/facilitator/campaigns/list' },
        { label: 'Add a Campaign', link: '/panel/facilitator/campaigns/add' },
      ],
    },
    {
      label: 'Universities',
      icon: IconSchool,
      links: [
        { label: 'List Universities', link: '/panel/facilitator/universities/list' },
      ],
    },
    {
      label: 'Cities',
      icon: IconTree,
      links: [
        { label: 'List Cities', link: '/panel/facilitator/cities/list' },
      ],
    },
    {
      label: 'Countries',
      icon: IconFlag,
      links: [
        { label: 'List Countries', link: '/panel/facilitator/countries/list' },
      ],
    },
    {
      label: 'Questions',
      icon: IconQuestionMark,
      links: [
        { label: 'List Questions', link: '/panel/facilitator/questions/list' },
        { label: 'Ask a Question', link: '/panel/facilitator/questions/add' },
      ],
    },
    { label: 'Settings', icon: IconAdjustments, link: '/panel/facilitator/settings' },
  ];