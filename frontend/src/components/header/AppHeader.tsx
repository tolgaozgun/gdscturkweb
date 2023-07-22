import { Flex, Header, Image } from '@mantine/core';
import ProfileMenu from '../menus/ProfileMenu';
import GDSCLogo from '../../assets/gdsc-logo.png';

const AppHeader = () => {
	return (
		<Header w="100%" height={100} p="lg">
			<Flex align="center" justify="space-between">
				<Image src={GDSCLogo} style={{marginLeft: 20}} />
				{/* <Title color="blue" ml={20}>
					GDSC Turkiye
				</Title> */}
				<ProfileMenu />
			</Flex>
		</Header>
	);
};

export default AppHeader;