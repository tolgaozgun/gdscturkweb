import { MapContainer, Marker, Popup, TileLayer } from "react-leaflet"
import 'leaflet/dist/leaflet.css';
import './Map.css'
import { AxiosInstance } from "axios";
import LoadingPage from "../../pages/LoadingPage";
import FacilitatorCard from "../cards/FacilitatorCard";
import useGetFacilitators from "../../hooks/user/useGetFacilitators";


interface FacilitatorMapProps {
  axiosSecure: AxiosInstance
}

const FacilitatorMap = ({axiosSecure}: FacilitatorMapProps) => {

	const {
		data: allFacilitators,
		isLoading: isFacilitatorsLoading,
		// isError: isLeadsError,
	} = useGetFacilitators(axiosSecure);

  if (isFacilitatorsLoading || !allFacilitators) {
    return <LoadingPage/>
  }

  return (
      <MapContainer center={[39.015118, 35.0278237]} zoom={5.00} scrollWheelZoom={false}>
      <TileLayer
        attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
      />
        {allFacilitators?.data?.map((facilitator) => {
          return (
            <Marker key={facilitator.facilitatorId} position={[facilitator.university.latitude, facilitator.university.longitude]}>
              <Popup>
                <FacilitatorCard padding="none" key={facilitator.facilitatorId} facilitator={facilitator} />
              </Popup>
            </Marker>
          );
        })}
    </MapContainer>
  )
}

export default FacilitatorMap;