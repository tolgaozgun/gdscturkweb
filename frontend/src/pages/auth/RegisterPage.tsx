import { Image, Card, Center, Grid, SelectItem } from '@mantine/core';
import { IconBrandGoogle, IconClipboardText, IconFriends, IconUser } from '@tabler/icons-react';
import useGetUniversities from '../../hooks/university/useGetUniversities';
import { University } from '../../types/UniversityTypes';
import LoadingPage from '../LoadingPage';
import { UserType } from '../../types';
import RegisterForm from '../../components/forms/auth/RegisterForm';
import Banner from '../../assets/banner.png';

const RegisterPage = () => {

	const {
		data: allUniversities,
		isLoading: isUniversitiesLoading,
		// isError: isUniversitiesError,
	} = useGetUniversities();

	if (isUniversitiesLoading || !allUniversities) {
		return <LoadingPage />;
	}

	console.log(allUniversities);

	const universitiesList: Array<University> = allUniversities?.data!;
	const universityData: Array<SelectItem> = universitiesList!
		.map((university) => {
			return {
				value: String(university.universityId),
				label: university.name,
				description: university.city.name + ', ' + university.country.name,
			};
		});

	const userTypeIcons = [<IconUser size="1rem"/>, <IconFriends size="1rem"/>, <IconClipboardText size="1rem"/>, <IconBrandGoogle size="1rem"/>]
	const allUserTypes = [UserType.Lead, UserType.CoreTeamMember, UserType.Facilitator, UserType.Googler]
	const userTypeData: Array<SelectItem> = allUserTypes.map((userType, index) => {
		return {
			icon: userTypeIcons[index],
			value: String(userType),
			label: userType,
			description: userType,
		};
	});

	return (		
			<Center miw={400}>
				<Card radius="md">

				<Image
					src={Banner}
				/>
				<RegisterForm universityData={universityData} userTypeData={userTypeData} />
				</Card>
			</Center>
	);
};

export default RegisterPage;
