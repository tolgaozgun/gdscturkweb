import { MapContainer, Marker, Popup, TileLayer } from "react-leaflet"
import 'leaflet/dist/leaflet.css';
import './Map.css'
import { AxiosInstance } from "axios";
import LoadingPage from "../../pages/LoadingPage";
import useGetGooglers from "../../hooks/user/useGetGooglers";
import GooglerCard from "../cards/GooglerCard";


interface GooglerMapProps {
  axiosSecure: AxiosInstance
}

const GooglerMap = ({axiosSecure}: GooglerMapProps) => {

	const {
		data: allGooglers,
		isLoading: isGooglersLoading,
		// isError: isLeadsError,
	} = useGetGooglers(axiosSecure);

  if (isGooglersLoading || !allGooglers) {
    return <LoadingPage/>
  }

  return (
      <MapContainer center={[39.015118, 35.0278237]} zoom={5.00} scrollWheelZoom={false}>
      <TileLayer
        attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
      />
        {allGooglers?.data?.map((googler) => {
          return (
            <Marker key={googler.googlerId} position={[googler.city.latitude, googler.city.longitude]}>
              <Popup>
                <GooglerCard padding="none" key={googler.googlerId} googler={googler} />
              </Popup>
            </Marker>
          );
        })}
    </MapContainer>
  )
}

export default GooglerMap;