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
import { City } from '../../../../types/CityTypes';
import useGetCountries from '../../../../hooks/country/useGetCountries';
import useAxiosSecure from '../../../../hooks/auth/useAxiosSecure';
import LoadingPage from '../../../../pages/LoadingPage';
import { isErrorResponse } from '../../../../utils/utils';
import CustomElevatedButton from '../../../buttons/CustomElevatedButton';
import { CreateUniversity } from '../../../../types/UniversityTypes';
import { createUniversity } from '../../../../services/university/UniversityService';
import useGetCities from '../../../../hooks/city/useGetCities';

interface AddUniversityFormProps {
	padding?: number;
	mt?: number;
}

interface CountrySelectItemProps extends React.ComponentPropsWithoutRef<'div'> {
	name: string;
	countryId: number;
	label: string;
	flag: string;
}

const CountrySelectItem = forwardRef<HTMLDivElement, CountrySelectItemProps>(
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


interface CitySelectItemProps extends React.ComponentPropsWithoutRef<'div'> {
	name: string;
    city: City;
    country: Country;
}

const CitySelectItem = forwardRef<HTMLDivElement, CitySelectItemProps>(
	({ name, city, country, ...others }: CitySelectItemProps, ref) => (
		<div ref={ref} {...others}>
			<Group noWrap>
				<IconFlag />
				{/* {vehicleType === 'PLANE' ? <IconPlane /> : <IconBus />} */}
				<div>
					<Text size="sm">{name}</Text>
					<Text size="xs" opacity={0.65}>
						{country.name}
					</Text>
				</div>
			</Group>
		</div>
	),
);

const AddUniversityForm = ({padding, mt}: AddUniversityFormProps) => {
	const axiosSecure = useAxiosSecure();

	const form = useForm({
		initialValues: {
			name: "",
			countryId: 0,
            cityId: 0,
			latitude: 0,
			longitude: 0,
		},
		validate: {
			name: (value) => (value.length > 0 ? null : 'Name is required'),
            cityId: (value) => (value > 0 ? null : 'City must be selected'),
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

	const {
		data: allCities,
		isLoading: isCitiesLoading,
		isError: isCitiesError,
	} = useGetCities(axiosSecure);

	const { mutateAsync: addUniversity } = useMutation({
		mutationKey: ['addUniversity'],
		mutationFn: (addUniversity: CreateUniversity) => createUniversity(axiosSecure, addUniversity),
		onError: (error: any) =>
			notifications.show({
				id: 'university-create-fail',
				title: 'Create University failed!',
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


	const countryList: Array<Country> = allCountries ? allCountries?.data!: [];
	const countryData: Array<SelectItem> = countryList!
		.map((country) => {
			return {
				name: country.name,
				countryId: country.countryId,
				flag: country.flagImage,
				value: String(country.countryId),
				label: country.name,
			};
		}).sort((a, b) => a.name.localeCompare(b.name));

	const cityList: Array<City> = allCities? allCities?.data! : [];
	const cityData: Array<SelectItem> = cityList!
		.map((city) => {
			return {
				name: city.name,
                city: city,
                country: city.country,
                label: city.name,
                value: String(city.cityId),
			};
		}).sort((a, b) => a.name.localeCompare(b.name));

	const handleAddUniversity = async () => {
		const validation = form.validate();
		if (validation.hasErrors) {
			return;
		}

		const universityDetails: CreateUniversity = {
			name: form.values.name,
            cityId: form.values.cityId,
			countryId: form.values.countryId,
			latitude: form.values.latitude,
			longitude: form.values.longitude,
		};

		const res = await addUniversity(universityDetails);
		if (isErrorResponse(res)) {
			notifications.show({
				message: res?.msg || "Something went wrong. Couldn't add the city",
				color: 'red',
			});
		} else {
			notifications.show({
				message: 'University added successfully.',
				color: 'green',
			});
		}
	};


	if (isCountriesLoading || !allCountries || isCitiesLoading || !allCities) {
		return <LoadingPage />;
	}
	if (isCountriesError || isCitiesError) {
		return <div>Error</div>;
	}


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
                        itemComponent={CountrySelectItem}
                        data={countryData}
                        searchable
                        withAsterisk
                        {...form.getInputProps('countryId')}
                    />
                    <Select
                        label="City"
                        placeholder="Pick one"
                        itemComponent={CitySelectItem}
                        data={cityData}
                        searchable
                        withAsterisk
                        {...form.getInputProps('cityId')}
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
					<CustomElevatedButton text={'Add University'} onClick={handleAddUniversity} />
				</Flex>		
			</Affix>
        </Flex>
	);
};

export default AddUniversityForm;
