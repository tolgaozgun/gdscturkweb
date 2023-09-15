import { Center } from "@mantine/core";
import { useParams } from "react-router";
import VerifyEmailForm from "../../components/forms/auth/VerifyEmailForm";

const LoginPage = () => {
  const param = useParams();

  // Get email and token param from url
  const email = param.email;
  const token = param.token;


  return (
    <Center sx={{ height: "60vh" }}>
      <VerifyEmailForm email={email} token={token} />
    </Center>
  );
};

export default LoginPage;
