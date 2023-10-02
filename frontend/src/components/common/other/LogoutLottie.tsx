import Lottie from 'react-lottie-player';
import loadingLottie from '../../../assets/logout_lottie.json';

const LogoutLottie = () => {

	return (
		<Lottie
			style={{ width: 600, height: 600 }}
			animationData={loadingLottie}
			play
			loop={true}
		/>
	);
};

export default LogoutLottie;
