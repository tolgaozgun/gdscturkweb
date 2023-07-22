import { Center } from "@mantine/core";
import LoginForm from "../../components/forms/auth/LoginForm";

const LoginPage = () => {
  return (
    <Center sx={{ height: "60vh" }}>
      <LoginForm />
    </Center>
  );
};

export default LoginPage;
