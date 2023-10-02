import { AspectRatio, Badge, Card, Grid, Group, Image, Text } from '@mantine/core';
import { LeadModel } from '../../types';
import { useNavigate } from 'react-router';
import DefaultProfilePicture from "../../assets/default_profile_picture.png";
import { useContextMenu } from 'mantine-contextmenu';

interface LeadCardProps {
    lead: LeadModel;
    padding: string;
    isUsedInMap?: boolean
}

const LeadCard = ({lead, padding, isUsedInMap}: LeadCardProps) => {
    const navigate = useNavigate();
    const showContextMenu = useContextMenu();

    const handleClick = () => {
        // Navigate to Lead profile
    }

    const copyImageToClipboard = () => {
        // Copy image to clipboard
    }

    const downloadImage = () => {
        // Download image
    }

    const topics = lead.user.interests?.map((topic) => (topic.name + ", "))

    // remove the last two characters from the last element on the array if exists
    if (topics?.length > 0) {
      topics[topics.length - 1] = topics[topics.length - 1].slice(0, -2);
    }

    return (
        <Card
        withBorder
          miw={300}
          h={isUsedInMap ? 170 : 250}
          shadow="sm"
          padding="xl"
          component="a"
          onClick={handleClick}
          onContextMenu={showContextMenu(
            [
              {
                key: 'View Profile',
                onClick: () => copyImageToClipboard(),
              },
              {
                key: 'download',
                onClick: () => downloadImage(),
              },
            ],
            { zIndex: 1000, shadow: 'md', borderRadius: 'md' }
          )}
    
        >
          <Card.Section>
            
            <Grid p={padding}>
                <Grid.Col span={4}>
                    <AspectRatio ratio={1/1} mx="auto">
                        <Image
                        src={lead.user.profileImage}
                        alt={lead.user.name} 
                        withPlaceholder
                        placeholder={
                            <Image
                                src={DefaultProfilePicture}
                                />
                        }
                        />
                    </AspectRatio>
                </Grid.Col>
                <Grid.Col span={8}>
                    <Text color="black" weight={500} size="lg">
                        {lead.user.name} {lead.user.surname}
                    </Text>
                    <Text color="black" weight={300} size="md" mt={2}>
                        {lead.university.name} Lead
                    </Text>
                    <Text color="black" weight={300} size="sm" mt={1}>
                        {lead.university.city.name}, {lead.university.country.name}
                    </Text>
                </Grid.Col>
            </Grid>
          </Card.Section>
          <Group 
            mt={isUsedInMap ? "md" : "none"}
            >
            Interests: {topics}
            {/* <Text color="black" w={100}>{topics}</Text> */}
            
            {/* {lead.user.interests?.map((topic) => {
                return (
                  <Text color="black" weight={300} mr={0}>
                        {topic.name}, 
                    </Text>
                );
            })} */}
          </Group>
        </Card>
      );
}

export default LeadCard;