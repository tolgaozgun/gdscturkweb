import { Container, SimpleGrid, Skeleton, Stack, px, useMantineTheme } from "@mantine/core";

const getChild = (height: number) => <Skeleton height={height} radius="md" animate={false} />;
const BASE_HEIGHT = 500;
const getSubHeight = (children: number, spacing: number) =>
  BASE_HEIGHT / children - spacing * ((children - 1) / children);

const VerticalImageGrid = () => {
  const theme = useMantineTheme();
  return (
    <Container size="xl" my="md">
      <SimpleGrid cols={4} breakpoints={[{ maxWidth: 'xs', cols: 1 }]}>
        {getChild(BASE_HEIGHT)}
        <Stack>
          {getChild(getSubHeight(2, px(theme.spacing.md)))}
          {getChild(getSubHeight(2, px(theme.spacing.md)))}
        </Stack>
        <Stack>
          {getChild(getSubHeight(3, px(theme.spacing.md)))}
          {getChild(getSubHeight(3, px(theme.spacing.md)))}
          {getChild(getSubHeight(3, px(theme.spacing.md)))}
        </Stack>
        {getChild(BASE_HEIGHT)}
      </SimpleGrid>
    </Container>
  );

}

export default VerticalImageGrid;