import { Center } from "@mantine/core";
import { useParams } from "react-router";
import VerifyEmailForm from "../../components/forms/auth/VerifyEmailForm";
import { decodeEmail } from "../../utils/utils";
import { useSearchParams } from "react-router-dom";

const EmailVerifyPage = () => {
  const [searchParams] = useSearchParams();
  const email = searchParams.get("email");
  const token = searchParams.get("token");


  return (
    <Center sx={{ height: "60vh" }}>
      <VerifyEmailForm email={email} token={token} />
    </Center>
  );
};

export default EmailVerifyPage;
