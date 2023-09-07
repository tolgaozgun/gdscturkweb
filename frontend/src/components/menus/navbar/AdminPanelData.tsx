
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
    IconHome,
  } from '@tabler/icons-react';

export const AdminPanelData: PanelItem[] = [
    { label: 'Back to Home Page', icon: IconHome, link: '/' },
    { label: 'Dashboard', icon: IconGauge, link: '/panel/admin/dashboard' },
    {
      label: 'Users',
      initiallyOpened: true,
      icon: IconUsersGroup,
      links: [
        { label: 'List Users', link: '/panel/admin/users/list' },
        { label: 'Verification List', link: '/panel/admin/users/verification' },
      ],
    },
    {
      label: 'Buddy Teams',
      initiallyOpened: true,
      icon: IconChalkboard,
      links: [
        { label: 'List Buddy Teams', link: '/panel/admin/buddy-teams/list' },
        { label: 'All Attendance', link: '/panel/admin/buddy-teams/attendance' },
        { label: 'Add a Buddy Team', link: '/panel/admin/buddy-teams/add' },
      ],
    },
    {
      label: 'Campaigns',
      icon: IconBrandCampaignmonitor,
      links: [
        { label: 'List Campaigns', link: '/panel/admin/campaigns/list' },
        { label: 'Add a Campaign', link: '/panel/admin/campaigns/add' },
      ],
    },
    {
      label: 'Universities',
      icon: IconSchool,
      links: [
        { label: 'List Universities', link: '/panel/admin/universities/list' },
        { label: 'Add a University', link: '/panel/admin/universities/add' },
      ],
    },
    {
      label: 'Cities',
      icon: IconTree,
      links: [
        { label: 'List Cities', link: '/panel/admin/cities/list' },
        { label: 'Add a City', link: '/panel/admin/cities/add' },
      ],
    },
    {
      label: 'Countries',
      icon: IconFlag,
      links: [
        { label: 'List Countries', link: '/panel/admin/countries/list' },
        { label: 'Add a Country', link: '/panel/admin/countries/add' },
      ],
    },
    {
      label: 'Questions',
      icon: IconQuestionMark,
      links: [
        { label: 'List Questions', link: '/panel/admin/questions/list' },
        { label: 'List Question Categories', link: '/panel/admin/questions/categories/list' },
        { label: 'Add a Question Category', link: '/panel/admin/questions/categories/add' },
        { label: 'Add a Question', link: '/panel/admin/questions/add' },
      ],
    },
    { label: 'Settings', icon: IconAdjustments, link: '/panel/admin/settings' },
  ];