import { Badge, Card, Grid, Image, Text } from '@mantine/core';
import { useNavigate } from 'react-router';
import { CoreTeamMemberModel } from '../../types';


interface CoreTeamMemberCardProps {
    coreTeamMember: CoreTeamMemberModel;
    padding: string;
}

const CoreTeamMemberCard = ({coreTeamMember, padding}: CoreTeamMemberCardProps) => {
    const navigate = useNavigate();

    const handleClick = () => {
        // Navigate to Core Team Member profile
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
                    src={coreTeamMember.user.profileImage}
                    alt="No way!"
                    />
                </Grid.Col>
                <Grid.Col span={8}>
                    <Text color="black" weight={500} size="lg">
                        {coreTeamMember.user.name} {coreTeamMember.user.surname}
                    </Text>
                    <Text color="black" weight={300} size="md" mt={2}>
                        {coreTeamMember.university.name} Core Team Member
                    </Text>
                    <Text color="black" weight={300} size="sm" mt={1}>
                        {coreTeamMember.university.city.name}, {coreTeamMember.university.country.name}
                    </Text>
                </Grid.Col>
            </Grid>
          </Card.Section>
          {coreTeamMember.user.interests?.map((topic) => {
            return (
                <Badge>
                    {topic.name}
                </Badge>
            );
          })}
        </Card>
      );
}

export default CoreTeamMemberCard;