import { Button, DefaultMantineColor } from '@mantine/core';

interface CustomElevatedButtonProps {
	text: string;
	leftIcon?: React.ReactNode | undefined;
	onClick?: React.MouseEventHandler<HTMLButtonElement> | undefined;
	isLoading?: boolean | undefined;
	color?: DefaultMantineColor | undefined;
	size?: 'xs' | 'sm' | 'md' | 'lg' | 'xl' | undefined;
}
const CustomElevatedButton = ({
	text,
	leftIcon,
	onClick,
	isLoading,
	size,
}: CustomElevatedButtonProps) => {
	return (
		<Button
			loading={isLoading}
			leftIcon={leftIcon}
			onClick={onClick}
			size={size}
		>
			{text}
		</Button>
	);
};
export default CustomElevatedButton;
