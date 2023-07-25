import { MapContainer, Marker, Popup, TileLayer } from "react-leaflet"
import 'leaflet/dist/leaflet.css';
import './Map.css'
import LoadingPage from "../../pages/LoadingPage";
import CoreTeamMemberCard from "../cards/CoreTeamMemberCard";
import { CoreTeamMemberModel } from "../../types";


interface CoreTeamMapProps {
  data: CoreTeamMemberModel[];
  isLoading: boolean;
}

const CoreTeamMap = ({data, isLoading}: CoreTeamMapProps) => {

  if (isLoading || !data) {
    return <LoadingPage/>
  }

  return (
      <MapContainer center={[39.015118, 35.0278237]} zoom={5.00} scrollWheelZoom={false}>
      <TileLayer
        attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
      />
        {data.map((coreTeamMember) => {
          return (
            <Marker key={coreTeamMember.coreTeamMemberId} position={[coreTeamMember.university.latitude, coreTeamMember.university.longitude]}>
              <Popup>
                <CoreTeamMemberCard padding="none" key={coreTeamMember.coreTeamMemberId} coreTeamMember={coreTeamMember} />
              </Popup>
            </Marker>
          );
        })}
    </MapContainer>
  )
}

export default CoreTeamMap;