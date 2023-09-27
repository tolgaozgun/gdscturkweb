import { AspectRatio, Card, Grid, Image, Text } from '@mantine/core';
import { useNavigate } from 'react-router';
import { useContextMenu } from 'mantine-contextmenu';
import { University } from '../../types/UniversityTypes';
import GDSCLogo from "../../assets/gdsc-logo.png"

interface UniversityCardProps {
    university: University;
    padding?: string;
}

const UniversityCard = ({university, padding}: UniversityCardProps) => {
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


    return (
        <Card
          miw={300}
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
                        src={GDSCLogo}
                        withPlaceholder
                        />
                    </AspectRatio>
                </Grid.Col>
                <Grid.Col span={8}>
                    <Text color="black" weight={300} size="md" mt={2}>
                        GDSC {university.name} 
                    </Text>
                    <Text color="black" weight={300} size="sm" mt={1}>
                        {university.city.name}, {university.country.name}
                    </Text>
                </Grid.Col>
            </Grid>
          </Card.Section>
          {/* <Group mt="md">
            {lead.user.interests?.map((topic) => {
                return (
                    <Badge key={topic.topicId}>
                        {topic.name}
                    </Badge>
                );
            })}
          </Group> */}
        </Card>
      );
}

export default UniversityCard;