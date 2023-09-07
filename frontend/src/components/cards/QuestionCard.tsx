import { Avatar, Text, Paper } from '@mantine/core';
import { LeadModel } from '../../types';

interface UserInfoActionProps {
  lead: LeadModel;
  isUsedInMap?: boolean
}

const QuestionCard = ({ lead, isUsedInMap }: UserInfoActionProps) => {

    const topics = lead.user.interests?.map((topic) => (topic.name + ", "))

    // remove the last two characters from the last element on the array if exists
    if (topics?.length > 0) {
      topics[topics.length - 1] = topics[topics.length - 1].slice(0, -2);
    }


  return (
    <Paper
      radius="md"
      withBorder
      h={isUsedInMap ? 170 : 250}
      p="lg"
      sx={(theme) => ({
        backgroundColor: theme.colorScheme === 'dark' ? theme.colors.dark[8] : theme.white,
      })}
    >
      <Avatar src={lead.user.profileImage} size={120} radius={120} mx="auto" />
      <Text ta="center" fz="lg" weight={500} mt="md">
        {lead.user.name} {lead.user.surname}
      </Text>
      <Text ta="center" c="dimmed" fz="sm">
        {lead.university.name} • {lead.university.city.name}, {lead.university.country.name} 
      </Text>

        <Text ta="center" fz="sm" mt="md">
            {topics}
        </Text>

{/* 
      <Button variant="default" fullWidth mt="md">
        Send message
      </Button> */}
    </Paper>
  );
}

export default QuestionCard