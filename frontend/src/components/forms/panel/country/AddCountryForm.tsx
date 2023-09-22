import {
	Affix,
	Flex,
	TextInput,
	rem,
} from '@mantine/core';
import { useForm } from '@mantine/form';
import { notifications } from '@mantine/notifications';
import { useMutation } from '@tanstack/react-query';
import { CreateCountry } from '../../../../types/CountryTypes';
import useAxiosSecure from '../../../../hooks/auth/useAxiosSecure';
import { isErrorResponse } from '../../../../utils/utils';
import CustomElevatedButton from '../../../buttons/CustomElevatedButton';
import { createCountry } from '../../../../services/country/CountryService';

interface AddCountryFormProps {
	padding?: number;
	mt?: number;
}

const AddCountryForm = ({padding, mt}: AddCountryFormProps) => {
	const axiosSecure = useAxiosSecure();

	const form = useForm({
		initialValues: {
			name: "",
			flagImage: "",
		},
		validate: {
			name: (value) => (value.length > 0 ? null : 'Name is required'),
			flagImage: (value) => (value.length > 0 ? null : 'Flag Image is required'),
		},
	});

	const { mutateAsync: addCountry } = useMutation({
		mutationKey: ['addCountry'],
		mutationFn: (addCountry: CreateCountry) => createCountry(axiosSecure, addCountry),
		onError: (error: any) =>
			notifications.show({
				id: 'country-create-fail',
				title: 'Create Country failed!',
				message: error.response ? error.response.data.msg : 'Something went wrong',
				autoClose: 5000,
				withCloseButton: true,
				style: { backgroundColor: 'red' },
				styles: (theme) => ({
					title: { color: theme.white },
					description: { color: theme.white }
				})
			}),
	});

	const handleAddCountry = async () => {
		const validation = form.validate();
		if (validation.hasErrors) {
			return;
		}

		const countryDetails: CreateCountry = {
			name: form.values.name,
			flagImage: form.values.flagImage,
		};
		const res = await addCountry(countryDetails);
		if (isErrorResponse(res)) {
			notifications.show({
				message: res?.msg || "Something went wrong. Couldn't add the country",
				color: 'red',
			});
		} else {
			notifications.show({
				message: 'Country added successfully.',
				color: 'green',
			});
		}
	};

	return (
		<Flex p={padding} mt={mt} direction={'column'} gap={'xl'}>
			<form>
				<Flex direction={'column'} gap={'xl'}>
					<TextInput
						withAsterisk
						label="Name"
						{...form.getInputProps('name')}
					/>
					<TextInput
						withAsterisk
						label="Flag Image"
						{...form.getInputProps('flagImage')}
					/>
				</Flex>
			</form>
			<Affix position={{ bottom: rem(20), right: rem(20) }}>	
				<Flex gap="md">
					<CustomElevatedButton text={'Cancel'} color="red" />
					<CustomElevatedButton text={'Add Country'} onClick={handleAddCountry} />
				</Flex>		
			</Affix>
		</Flex>
	);
};

export default AddCountryForm;
