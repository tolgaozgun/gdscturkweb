import {
    createStyles,
    Paper,
    Title,
    Text,
    TextInput,
    Button,
    Container,
    Group,
    Anchor,
    Center,
    Box,
    rem,
  } from '@mantine/core';
  import { IconArrowLeft } from '@tabler/icons-react';
import { useResendEmailVerification } from '../../../hooks/verification/useResendEmailVerification';
import { EmailResendRequest } from '../../../types/VerificationTypes';
import { useForm } from '@mantine/form';
import { isErrorResponse } from '../../../utils/utils';
import { notifications } from '@mantine/notifications';
import { useNavigate } from 'react-router';
  
  const useStyles = createStyles((theme) => ({
    title: {
      fontSize: rem(26),
      fontWeight: 900,
      fontFamily: `Greycliff CF, ${theme.fontFamily}`,
    },
  
    controls: {
      [theme.fn.smallerThan('xs')]: {
        flexDirection: 'column-reverse',
      },
    },
  
    control: {
      [theme.fn.smallerThan('xs')]: {
        width: '100%',
        textAlign: 'center',
      },
    },
  }));

  interface SendEmailVerificationFormProps {
    email?: string | null;
  }
  
  export function SendEmailVerificationForm({email}: SendEmailVerificationFormProps) {
    const { classes } = useStyles();
    const { resendVerification } = useResendEmailVerification();
    const navigate = useNavigate();

	  const form = useForm({
		initialValues: {
			email: email ? email : '',
		},

		validate: {
			email: (value) => (/^\S+@\S+$/.test(value) ? null : 'Invalid email.'),
		},
	});

    const sendVerification = async () => {
		const validation = form.validate();
		if (validation.hasErrors) {
			return;
		}

		const emailResendRequest: EmailResendRequest = {
			email: form.values.email,
		};

		const res = await resendVerification(emailResendRequest);
        
		if (isErrorResponse(res)) {
			notifications.show({
				id: 'resend-fail',
				title: 'Resend email failed!',
				message: res.msg,
				autoClose: 5000,
				withCloseButton: true,
				style: { backgroundColor: 'red' },
				styles: (theme) => ({
					title: { color: theme.white },
					description: { color: theme.white }
				})
			});
			return;
		}

		notifications.show({
			id: 'resend-success',
			title: 'Resend successful!',
			message:
				'We have successfully resent your verification email! We are redirecting you to the verification page...',
			autoClose: 5000,
			withCloseButton: true,
			style: { backgroundColor: 'green' },
			styles: (theme) => ({
				title: { color: theme.white },
				description: { color: theme.white }
			})
		});
		navigate('/verify-email/step-2?email=' + form.values.email);
	};

    const onBackToLogin = () => {
        navigate('/login');
    }

    const onForwardToVerify = () => {
        navigate('/verify-email/step-2');
    }
  
    return (
      <Container size={460} my={30}>
        <Title className={classes.title} align="center">
          Haven't verified your email?
        </Title>
        <Text c="dimmed" fz="sm" ta="center">
          Enter your email to get a verification code
        </Text>
  
        <Paper withBorder shadow="md" p={30} radius="md" mt="xl">
            <form>
          <TextInput 
            label="Your email" 
            placeholder="me@mantine.dev"
            {...form.getInputProps("email")} />
          <Group position="apart" mt="lg" className={classes.controls}>
            <Anchor onClick={onBackToLogin} color="dimmed" size="sm" className={classes.control}>
              <Center inline>
                <IconArrowLeft size={rem(12)} stroke={1.5} />
                <Box ml={5}>Back to the login page</Box>
              </Center>
            </Anchor>
            <Anchor onClick={onForwardToVerify} color="dimmed" size="sm" className={classes.control}>
              <Center inline>
                <Box ml={5}>I already have a code</Box>
              </Center>
            </Anchor>
            <Button onClick={sendVerification} className={classes.control}>Send verification code</Button>
          </Group>
          </form>
        </Paper>
      </Container>
    );
  }
  export default SendEmailVerificationForm;
  