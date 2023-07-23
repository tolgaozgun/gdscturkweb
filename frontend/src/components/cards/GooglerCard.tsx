import { Badge, Card, Grid, Image, Text } from '@mantine/core';
import { GooglerModel } from '../../types';


interface GooglerCardProps {
    googler: GooglerModel;
    padding: string;
}

const GooglerCard = ({googler, padding}: GooglerCardProps) => {
    // const navigate = useNavigate();

    const handleClick = () => {
        // Navigate to Googler profile
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
                    src={googler.user.profileImage}
                    alt="No way!"
                    />
                </Grid.Col>
                <Grid.Col span={8}>
                    <Text color="black" weight={500} size="lg">
                        {googler.user.name} {googler.user.surname}
                    </Text>
                    <Text color="black" weight={300} size="sm" mt={1}>
                        {googler.city.name}, {googler.country.name}
                    </Text>
                </Grid.Col>
            </Grid>
          </Card.Section>
          {googler.user.interests?.map((topic) => {
            return (
                <Badge>
                    {topic.name}
                </Badge>
            );
          })}
        </Card>
      );
}

export default GooglerCard;