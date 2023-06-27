import { Center, Tabs } from '@mantine/core';
import { IconBus, IconUser } from '@tabler/icons-react';
import RegisterLeadForm from '../../components/forms/auth/RegisterLeadForm';
import RegisterCoreTeamForm from '../../components/forms/auth/RegisterCoreTeamForm';
import useGetUniversities from '../../hooks/university/useGetUniversities';
import { useMemo } from 'react';

const RegisterPage = () => {

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
					<RegisterLeadForm />
				</Tabs.Panel>
				<Tabs.Panel value="core_team" pt="md">
					<RegisterCoreTeamForm />
				</Tabs.Panel>
				<Tabs.Panel value="facilitator" pt="md">
					<RegisterLeadForm />
				</Tabs.Panel>
				<Tabs.Panel value="googler" pt="md">
					<RegisterLeadForm />
				</Tabs.Panel>
				<Tabs.Panel value="admin" pt="md">
					<RegisterLeadForm />
				</Tabs.Panel>
			</Tabs>
		</Center>
	);
};

export default RegisterPage;
