import { MapContainer, Marker, Popup, TileLayer } from "react-leaflet"
import 'leaflet/dist/leaflet.css';
import './Map.css'
import LoadingPage from "../../pages/LoadingPage";
import LeadCard from "../cards/LeadCard";
import { LeadModel } from "../../types";
import UserCard from "../cards/UserCard";


interface LeadMapProps {
  data: LeadModel[];
  isLoading: boolean;
  
}

const LeadMap = ({data, isLoading}: LeadMapProps) => {

  if (isLoading || !data) {
    return <LoadingPage/>
  }

  return (
      <MapContainer center={[39.015118, 35.0278237]} zoom={5.00} scrollWheelZoom={false}>
      <TileLayer
        attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
      />
        {data.map((lead) => {
          return (
            <Marker key={lead.leadId} position={[lead.university.latitude, lead.university.longitude]}>
              <Popup>
                <UserCard key={lead.leadId} lead={lead} />
                {/* <LeadCard key={lead.leadId} padding="none" lead={lead} isUsedInMap={true} /> */}
              </Popup>
            </Marker>
          );
        })}
    </MapContainer>
  )
}

export default LeadMap;