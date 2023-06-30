import Lottie from 'react-lottie-player';
import loadingLottie from '../../../assets/loading_lottie.json';
import { useState } from 'react';

const LoadingLottie = () => {
	const [segments, setSegments] = useState<any>([0, 220]);

	const toggleSegments = () => {
		setSegments(segments[0] === 0 ? [220, 0] : [0, 220]);
	}

	return (
		<Lottie
			style={{ width: 600, height: 600 }}
			animationData={loadingLottie}
			play
			segments={segments}
			onLoad={() => {
				console.log('loaded');
			}}
			onLoadStart={() => {
				console.log('load start');
			}}
			onLoadedData={() => {
				console.log('loaded data');
			}}
			onLoopComplete={() => {
				toggleSegments();
			}}
		/>
	);
};

export default LoadingLottie;
