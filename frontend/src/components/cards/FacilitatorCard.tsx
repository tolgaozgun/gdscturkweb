import { Badge, Card, Grid, Image, Text } from '@mantine/core';
import { useNavigate } from 'react-router';
import { FacilitatorModel } from '../../types';


interface FacilitatorCardProps {
    facilitator: FacilitatorModel;
    padding: string;
}

const FacilitatorCard = ({facilitator, padding}: FacilitatorCardProps) => {
    const navigate = useNavigate();

    const handleClick = () => {
        // Navigate to Facilitator profile
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
                    <Image
                    src={facilitator.user.profileImage}
                    alt="No way!"
                    />
                </Grid.Col>
                <Grid.Col span={8}>
                    <Text color="black" weight={500} size="lg">
                        {facilitator.user.name} {facilitator.user.surname}
                    </Text>
                    <Text color="black" weight={300} size="md" mt={2}>
                        {facilitator.university.name}
                    </Text>
                    <Text color="black" weight={300} size="sm" mt={1}>
                        {facilitator.university.city.name}, {facilitator.university.country.name}
                    </Text>
                </Grid.Col>
            </Grid>
          </Card.Section>
          {facilitator.user.interests?.map((topic) => {
            return (
                <Badge>
                    {topic.name}
                </Badge>
            );
          })}
        </Card>
      );
}

export default FacilitatorCard;