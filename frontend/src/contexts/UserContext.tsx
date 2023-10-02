import React, { createContext, useContext, useReducer, ReactNode } from 'react';
import { User } from '../types';
import { useToken } from '../hooks/auth/useToken';
import useGetUser from '../hooks/auth/useGetUser';
import useAxiosSecure, { useAxiosSecureWithToken } from '../hooks/auth/useAxiosSecure';


// Create an initial state for the user
const initialUserState: User | null = null;

// Create the context
const UserContext = createContext<{
  user: User | null;
  isUserLoading: boolean;
  isUserError: boolean;
}>({
  user: initialUserState,
  isUserLoading: false,
  isUserError: false,
});

// Create a UserProvider component to wrap your app with the context
export const UserProvider: React.FC<{ children: ReactNode }> = ({ children }) => {

  const axiosSecure = useAxiosSecure(false);
  const {
    data: data,
    isLoading: isLoading,
    isError: isError,
  } = useGetUser(axiosSecure);

  let user = data? data.data : null;

  return (
    <UserContext.Provider value={{ user, isUserLoading: isLoading, isUserError: isError }}>{children}</UserContext.Provider>
  );
};

// Create a custom hook to access the user context
export const useUser = () => {
  const context = useContext(UserContext);
  if (!context) {
    throw new Error('useUser must be used within a UserProvider');
  }


  return context;
};
