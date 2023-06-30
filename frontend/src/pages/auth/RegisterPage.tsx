import { Center, SelectItem, Tabs } from '@mantine/core';
import { IconBus, IconUser } from '@tabler/icons-react';
import RegisterLeadForm from '../../components/forms/auth/RegisterLeadForm';
import RegisterCoreTeamForm from '../../components/forms/auth/RegisterCoreTeamForm';
import useGetUniversities from '../../hooks/university/useGetUniversities';
import { University } from '../../types/UniversityTypes';
import LoadingPage from '../LoadingPage';
import RegisterFacilitatorForm from '../../components/forms/auth/RegisterFacilitatorForm';
import RegisterGooglerForm from '../../components/forms/auth/RegisterGooglerForm';

const RegisterPage = () => {

	const {
		data: allUniversities,
		isLoading: isUniversitiesLoading,
		// isError: isUniversitiesError,
	} = useGetUniversities();

	if (isUniversitiesLoading || !allUniversities) {
		return <LoadingPage />;
	}

	const universitiesList: Array<University> = allUniversities?.data!;
	const universityData: Array<SelectItem> = universitiesList!
		.map((university) => {
			return {
				value: String(university.universityId),
				label: university.name,
				description: university.city.name + ', ' + university.country.name,
			};
		});

	return (
		<Center miw={400}>
			<Tabs defaultValue="lead">
				<Tabs.List>
					<Tabs.Tab icon={<IconUser size="1rem" />} value="lead">
						Lead
					</Tabs.Tab>
					<Tabs.Tab icon={<IconBus size="1rem" />} value="core_team">
						Core Team
					</Tabs.Tab>
					<Tabs.Tab icon={<IconBus size="1rem" />} value="facilitator">
						Facilitator
					</Tabs.Tab>
					<Tabs.Tab icon={<IconBus size="1rem" />} value="googler">
						Googler
					</Tabs.Tab>
					<Tabs.Tab icon={<IconBus size="1rem" />} value="admin">
						Admin
					</Tabs.Tab>
				</Tabs.List>

				<Tabs.Panel value="lead" pt="md">
					<RegisterLeadForm universityData={universityData} />
				</Tabs.Panel>
				<Tabs.Panel value="core_team" pt="md">
					<RegisterCoreTeamForm universityData={universityData} />
				</Tabs.Panel>
				<Tabs.Panel value="facilitator" pt="md">
					<RegisterFacilitatorForm universityData={universityData} />
				</Tabs.Panel>
				<Tabs.Panel value="googler" pt="md">
					<RegisterGooglerForm />
				</Tabs.Panel>
				<Tabs.Panel value="admin" pt="md">
					{/* <RegisterLeadForm universityData={universityData} /> */}
				</Tabs.Panel>
			</Tabs>
		</Center>
	);
};

export default RegisterPage;
