import Lottie from 'react-lottie-player';
import loadingLottie from '../../../assets/check_lottie.json';

interface CheckLottieProps {
	loop?: boolean;
}

const CheckLottie = ({loop = false}: CheckLottieProps) => {

	return (
		<Lottie
			style={{ width: 600, height: 600 }}
			animationData={loadingLottie}
			play
			loop={loop}
		/>
	);
};

export default CheckLottie;
