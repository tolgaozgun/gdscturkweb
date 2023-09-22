import {
	Affix,
	Card,
	Flex,
	Group,
	NumberInput,
	Select,
	SelectItem,
	Text,
	TextInput,
	Title,
	rem,
} from '@mantine/core';
import { useForm } from '@mantine/form';
import { notifications } from '@mantine/notifications';
import { IconFlag } from '@tabler/icons-react';
import { useMutation } from '@tanstack/react-query';
import { forwardRef } from 'react';
import { Country } from '../../../../types/CountryTypes';
import { AddCity } from '../../../../types/CityTypes';
import { createCity } from '../../../../services/city/CItyService';
import useGetCountries from '../../../../hooks/country/useGetCountries';
import useAxiosSecure from '../../../../hooks/auth/useAxiosSecure';
import LoadingPage from '../../../../pages/LoadingPage';
import { isErrorResponse } from '../../../../utils/utils';
import CustomElevatedButton from '../../../buttons/CustomElevatedButton';

interface AddCityFormProps {
	padding?: number;
	mt?: number;
}

interface CountrySelectItemProps extends React.ComponentPropsWithoutRef<'div'> {
	name: string;
	countryId: number;
	label: string;
	flag: string;
}

const CustomCountrySelectItem = forwardRef<HTMLDivElement, CountrySelectItemProps>(
	({ name, countryId, flag, ...others }: CountrySelectItemProps, ref) => (
		<div ref={ref} {...others}>
			<Group noWrap>
				<IconFlag />
				{/* {vehicleType === 'PLANE' ? <IconPlane /> : <IconBus />} */}
				<div>
					<Text size="sm">{name}</Text>
				</div>
			</Group>
		</div>
	),
);

const AddCityForm = ({padding, mt}: AddCityFormProps) => {
	const axiosSecure = useAxiosSecure();

	const form = useForm({
		initialValues: {
			name: "",
			countryId: 0,
			latitude: 0,
			longitude: 0,
		},
		validate: {
			name: (value) => (value.length > 0 ? null : 'Name is required'),
			countryId: (value) => (value > 0 ? null : 'Country must be selected'),
			latitude: (value) => (value > 0 ? null : 'Latitude must be greater than 0'),
			longitude: (value) => (value > 0 ? null : 'Longitude must be greater than 0'),
		},
	});
	const {
		data: allCountries,
		isLoading: isCountriesLoading,
		isError: isCountriesError,
	} = useGetCountries(axiosSecure);

	const { mutateAsync: addCity } = useMutation({
		mutationKey: ['addCity'],
		mutationFn: (addCity: AddCity) => createCity(axiosSecure, addCity),
		onError: (error: any) =>
			notifications.show({
				id: 'fare-create-fail',
				title: 'Create Fare failed!',
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

	if (isCountriesLoading || !allCountries) {
		return <LoadingPage />;
	}
	if (isCountriesError) {
		return <div>Error</div>;
	}


	const countryList: Array<Country> = allCountries?.data!;
	const countryData: Array<SelectItem> = countryList!
		.map((country) => {
			return {
				name: country.name,
				countryId: country.countryId,
				flag: country.flagImage,
				value: String(country.countryId),
				label: country.name,
			};
		});

	const handleAddCity = async () => {
		const validation = form.validate();
		if (validation.hasErrors) {
			return;
		}

		const cityDetails: AddCity = {
			name: form.values.name,
			countryId: form.values.countryId,
			latitude: form.values.latitude,
			longitude: form.values.longitude,
		};
		const res = await addCity(cityDetails);
		if (isErrorResponse(res)) {
			notifications.show({
				message: res?.msg || "Something went wrong. Couldn't add the city",
				color: 'red',
			});
		} else {
			notifications.show({
				message: 'City added successfully.',
				color: 'green',
			});
		}
	};

	return (
		<Flex direction={'column'} gap={'xl'} p={padding} mt={mt}>
			<form>
				<Flex direction={'column'} gap={'xl'}>
					<TextInput
						withAsterisk
						label="Name"
						{...form.getInputProps('name')}
					/>
					<Select
						label="Country"
						placeholder="Pick one"
						itemComponent={CustomCountrySelectItem}
						data={countryData}
						searchable
						withAsterisk
						{...form.getInputProps('countryId')}
					/>
					<NumberInput
						withAsterisk
						label="Latitude"
						precision={6}
						{...form.getInputProps('latitude')}
					/>
					<NumberInput
						withAsterisk
						label="Longitude"
						precision={6}
						{...form.getInputProps('longitude')}
					/>
				</Flex>
			</form>
			<Affix position={{ bottom: rem(20), right: rem(20) }}>	
				<Flex gap="md">
					<CustomElevatedButton text={'Cancel'} color="red" />
					<CustomElevatedButton text={'Add City'} onClick={handleAddCity} />
				</Flex>		
			</Affix>
		</Flex>
	);
};

export default AddCityForm;
