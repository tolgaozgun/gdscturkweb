import { MapContainer, Marker, Popup, TileLayer } from "react-leaflet"
import 'leaflet/dist/leaflet.css';
import './Map.css'
import { AxiosInstance } from "axios";
import useGetLeads from "../../hooks/user/useGetLeads";
import LoadingPage from "../../pages/LoadingPage";
import LeadCard from "../cards/LeadCard";


interface LeadMapProps {
  axiosSecure: AxiosInstance
}

const LeadMap = ({axiosSecure}: LeadMapProps) => {

	const {
		data: allLeads,
		isLoading: isLeadsLoading,
		// isError: isLeadsError,
	} = useGetLeads(axiosSecure);

  if (isLeadsLoading || !allLeads) {
    return <LoadingPage/>
  }

  return (
      <MapContainer center={[39.015118, 35.0278237]} zoom={5.00} scrollWheelZoom={false}>
      <TileLayer
        attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
      />
        {allLeads?.data?.map((lead) => {
          return (
            <Marker key={lead.leadId} position={[lead.university.latitude, lead.university.longitude]}>
              <Popup>
                <LeadCard key={lead.leadId} padding="none" lead={lead} />
              </Popup>
            </Marker>
          );
        })}
    </MapContainer>
  )
}

export default LeadMap;