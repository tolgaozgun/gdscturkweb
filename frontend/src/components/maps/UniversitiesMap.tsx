import { MapContainer, Marker, Popup, TileLayer } from "react-leaflet"
import 'leaflet/dist/leaflet.css';
import './Map.css'
import LoadingPage from "../../pages/LoadingPage";
import useGetUniversities from "../../hooks/university/useGetUniversities";
import UniversityCard from "../cards/UniversityCard";

const UniversitiesMap = () => {

	const {
		data: allUniversities,
		isLoading: isUniversitiesLoading,
	} = useGetUniversities();

  if (isUniversitiesLoading || !allUniversities) {
    return <LoadingPage/>
  }

  return (
      <MapContainer center={[39.015118, 35.0278237]} zoom={5.00} scrollWheelZoom={false}>
      <TileLayer
        attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
      />
        {allUniversities?.data?.map((university) => {
          return (
            <Marker key={university.universityId} position={[university.latitude, university.longitude]}>
              <Popup>
                <UniversityCard key={university.universityId} padding="none" university={university}/>
              </Popup>
            </Marker>
          );
        })}
    </MapContainer>
  )
}

export default UniversitiesMap;