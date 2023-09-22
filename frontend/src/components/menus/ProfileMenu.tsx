import { useUser } from '../../contexts/UserContext';
import { UserType } from '../../types/UserTypes';
import AdminMenu from './AdminMenu';
import CoreTeamMemberMenu from './CoreTeamMemberMenu';
import FacilitatorMenu from './FacilitatorMenu';
import GooglerMenu from './GooglerMenu';
import LeadMenu from './LeadMenu';

const ProfileMenu = () => {
	const {user} = useUser();
	if (!user) {
		return null;
	}

	if (user.userType === UserType.Lead) {
		return <LeadMenu />;
	} else if (user.userType === UserType.Googler) {
		return <GooglerMenu />;
	} else if (user.userType === UserType.Admin) {
		return <AdminMenu />;
	} else if (user.userType === UserType.CoreTeamMember) {
        return <CoreTeamMemberMenu />;
    } else if (user.userType === UserType.Facilitator) {
        return <FacilitatorMenu />;
    }
	return null;
};

export default ProfileMenu;
