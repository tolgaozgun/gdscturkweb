import { MapContainer, Marker, Popup, TileLayer } from "react-leaflet"
import 'leaflet/dist/leaflet.css';
import './Map.css'
import LoadingPage from "../../pages/LoadingPage";
import FacilitatorCard from "../cards/FacilitatorCard";
import { FacilitatorModel } from "../../types";


interface FacilitatorMapProps {
  data: FacilitatorModel[];
  isLoading: boolean;
}

const FacilitatorMap = ({data, isLoading}: FacilitatorMapProps) => {


  if (isLoading || !data) {
    return <LoadingPage/>
  }

  return (
      <MapContainer center={[39.015118, 35.0278237]} zoom={5.00} scrollWheelZoom={false}>
      <TileLayer
        attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
      />
        {data.map((facilitator) => {
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