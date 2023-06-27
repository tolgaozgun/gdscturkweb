import {
	Button,
	Card,
	Flex,
	Group,
	PasswordInput,
	Select,
	SelectItem,
	Stack,
	TextInput,
	Title,
	Text,
} from '@mantine/core';
import { useForm } from '@mantine/form';
import { useNavigate } from 'react-router-dom';
import { primaryButtonColor } from '../../../constants/colors';
import { notifications } from '@mantine/notifications';
import { LeadRegisterModel, RegisterLead, UserRegisterModel } from '../../../types';
import SubtleLinkButton from '../../buttons/SubtleLinkButton';
import { isErrorResponse } from '../../../utils/utils';
import { useRegisterLead } from '../../../hooks/auth/useRegisterLead';
import userGetUniversities from '../../../hooks/university/useGetUniversities';
import useGetUniversities from '../../../hooks/university/useGetUniversities';
import useAxiosSecure from '../../../hooks/auth/useAxiosSecure';
import LoadingPage from '../../../pages/LoadingPage';
import { University } from '../../../types/UniversityTypes';
import { forwardRef } from 'react';
import { IconSchool } from '@tabler/icons-react';



interface UniversitySelectItemProps extends React.ComponentPropsWithoutRef<'div'> {
	label: string;
	description: string;
}

const CustomUniversitySelectItem = forwardRef<HTMLDivElement, UniversitySelectItemProps>(
	({ label, description, ...others }: UniversitySelectItemProps, ref) => (
		<div ref={ref} {...others}>
			<Group noWrap>
				<IconSchool />
				<div>
					<Text size="sm">{label}</Text>
					<Text size="xs" opacity={0.65}>
						{description}
					</Text>
				</div>
			</Group>
		</div>
	),
);

const RegisterLeadForm = () => {
	const form = useForm({
		initialValues: {
			name: '',
			surname: '',
			username:'',
			email: '',
			password: '',
			confirmPassword: '',
			universityId: 0,
		},
		validate: {
			name: (value) =>
				value === '' ? 'Name cannot be left empty.' : null,
			surname: (value) =>
				value === '' ? 'Surname cannot be left empty.' : null,
			username: (value) =>
				value === '' ? 'Username cannot be left empty.' : null,
			email: (value) => (/^\S+@\S+$/.test(value) ? null : 'Invalid email.'),
			password: (value) => (value === '' ? 'Password cannot be left empty.' : null),
			confirmPassword: (value, values) =>
				value !== values.password ? 'Passwords did not match' : null,
			universityId: (value) =>
				Number(value) > 0 ? null : 'University must be selected',
		},
	});
	const navigate = useNavigate();
	const { register } = useRegisterLead();


	const {
		data: allUniversities,
		isLoading: isUniversitiesLoading,
		isError: isUniversitiesError,
	} = useGetUniversities();


	const onRegister = async () => {
		const validation = form.validate();
		if (validation.hasErrors) {
			return;
		}

		const user: UserRegisterModel = {
			name: form.values.name,
			surname: form.values.surname,
			username: form.values.username,
			email: form.values.email,
			password: form.values.password,
		};
		const lead: LeadRegisterModel = {
			universityId: form.values.universityId
		};
		const registerInfo: RegisterLead = {
			user,
			lead,
		};
		const res = await register(registerInfo);
		if (isErrorResponse(res)) {
			notifications.show({
				id: 'registration-fail',
				title: 'Registration failed!',
				message: res.msg,
				autoClose: 5000,
				withCloseButton: true,
				style: { backgroundColor: 'red' },
				styles: (theme) => ({
					title: { color: theme.white },
					description: { color: theme.white }
				})
			});
			return;
		}

		notifications.show({
			id: 'registration-success',
			title: 'Registration successful!',
			message:
				'You have successfully registered! We are redirecting you to the main page...',
			autoClose: 5000,
			withCloseButton: true,
			style: { backgroundColor: 'green' },
			styles: (theme) => ({
				title: { color: theme.white },
				description: { color: theme.white }
			})
		});
		navigate('/login');
	};

	if (isUniversitiesLoading) {
		return <LoadingPage />;
	}
	if (isUniversitiesError) {
		return <div>Error</div>;
	}


	const universitiesList: Array<University> = allUniversities?.data!;
	const universityData: Array<SelectItem> = universitiesList!
		.map((university) => {
			return {
				value: String(university.universityId),
				label: university.name,
				description: university.city.name,
			};
		});

	return (
		<Card withBorder radius="xl" shadow="xl" p={48} sx={{ minWidth: 400 }} mx="auto">
			<Stack spacing={'xl'}>
				<Title size="32px" align="center">
					Register
				</Title>
				<form>
					<Flex direction={'column'} gap={'xs'}>
						<Flex direction={'row'} gap={'xs'}>
							<TextInput
								label="Name"
								{...form.getInputProps('name')}
							/>
							<TextInput
								label="Surname"
								{...form.getInputProps('surname')}
							/>
						</Flex>
						<TextInput
							label="Username"
							{...form.getInputProps('username')}
						/>
						<TextInput
							label="Email"
							{...form.getInputProps('email')}
						/>
						<PasswordInput
							label="Password"
							{...form.getInputProps('password')}
						/>
						<PasswordInput
							label="Confirm Password"
							{...form.getInputProps('confirmPassword')}
						/>
						<Select
							label="University"
							placeholder="Pick your university"
							itemComponent={CustomUniversitySelectItem}
							data={universityData}
							searchable
							withAsterisk
							{...form.getInputProps('universityId')}
						/>
						<Button onClick={onRegister} bg={primaryButtonColor}>
							Register
						</Button>
						<SubtleLinkButton to="/login" size="sm">
							Already have an account? Login
						</SubtleLinkButton>
					</Flex>
				</form>
			</Stack>
		</Card>
	);
};

export default RegisterLeadForm;
