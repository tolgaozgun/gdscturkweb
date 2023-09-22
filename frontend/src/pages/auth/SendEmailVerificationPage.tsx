import {
  Center
} from '@mantine/core';
import SendEmailVerificationForm from '../../components/forms/auth/SendEmailVerificationForm';
import { useParams } from 'react-router';


export function SendEmailVerificationPage() {
  const param = useParams();
  
  // Get email and token param from url
  const email = param.email;

  return (
    <Center sx={{ height: "60vh" }}>
      <SendEmailVerificationForm email={email} />
    </Center>
  );
}
export default SendEmailVerificationPage;
