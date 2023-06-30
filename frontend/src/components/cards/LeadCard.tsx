import { AspectRatio, Badge, Card, Grid, Group, Image, Text } from '@mantine/core';
import { LeadModel } from '../../types';
import { useNavigate } from 'react-router';
import DefaultProfilePicture from "../../assets/default_profile_picture.png";

interface LeadCardProps {
    lead: LeadModel;
    padding: string;
}

const LeadCard = ({lead, padding}: LeadCardProps) => {
    const navigate = useNavigate();

    const handleClick = () => {
        // Navigate to Lead profile
    }

    return (
        <Card
          miw={300}
              shadow="sm"
          padding="xl"
          component="a"
          onClick={handleClick}
    
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
          <Group mt="md">
            {lead.user.interests?.map((topic) => {
                return (
                    <Badge key={topic.topicId}>
                        {topic.name}
                    </Badge>
                );
            })}
          </Group>
        </Card>
      );
}

export default LeadCard;