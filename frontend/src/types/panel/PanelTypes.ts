type PanelLink = {
    label: string;
    link: string;
  }
  
type PanelItem = {
    label: string;
    icon: React.ElementType;
    link?: string;
    initiallyOpened?: boolean;
    links?: PanelLink[];
  }
