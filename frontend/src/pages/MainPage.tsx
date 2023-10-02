import HeroComponent from "../components/HeroComponent"
import HeroBg from "../assets/heroBg.jpg"
import UniversitiesMap from "../components/maps/UniversitiesMap"
import { Card, Center, Container, Title, Text, Flex, Stack, useMantineTheme } from "@mantine/core"
import VerticalImageGrid from "../components/VerticalImageGrid"
import ActivitiesCarousel from "../components/ActivitesCarousel"
import useGetUniversities from "../hooks/university/useGetUniversities"
import { University } from "../types/UniversityTypes"


const MainPage = () => {

  const theme = useMantineTheme();
  const {
    data: universitiesData,
    isLoading: isUniversitiesLoading,

  } = useGetUniversities();

  let universities: University[] = [] as University[];
    if (universitiesData && universitiesData?.data){
        universities = universitiesData?.data!
    }

  return (
        < >
            <HeroComponent 
                bg={HeroBg}
                title="Google Developer Student Clubs Türkiye"
                description="Google Developer Student Clubs are university based community groups for students interested in Google developer technologies. Students from all undergraduate or graduate programs with an interest in growing as a developer are welcome. By joining a GDSC, students grow their knowledge in a peer-to-peer learning environment and build solutions for local businesses and their community."
                buttons={["Sign In", "Learn More"]}
            />

            <Container mt={30} mb={30} size="xl">
                <Center>
                    <Stack>
                        <Title mb={10} align="center" weight={700} size={40}>
                            Universities
                        </Title>
                        <Text size={20} mb={20}>
                            Explore the universities that have Google Developer Student Clubs in Türkiye.
                        </Text>
                    </Stack>
                </Center>
                <UniversitiesMap isLoading={isUniversitiesLoading} universities={universities} />
            </Container>
            
            <Container mt={30} mb={30}>
                <Center>
                    <Stack>
                        <Title mb={20} align="center" weight={700} size={40}>
                            Upcoming Activities
                        </Title>
                    </Stack>
                </Center>
                <ActivitiesCarousel/>
            </Container>


            <Container mt={30} mb={30}>
                <Center>
                    <Stack>
                        <Title mb={20} align="center" weight={700} size={40}>
                            Gallery
                        </Title>
                    </Stack>
                </Center>
                <VerticalImageGrid/>
            </Container>

        </>
    )
}

export default MainPage