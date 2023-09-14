import React, { createContext, useContext, useReducer, ReactNode } from 'react';
import { User } from '../types';
import { useToken } from '../hooks/auth/useToken';
import { useAxiosSecureWithToken } from '../hooks/auth/useAxiosSecure';
import useGetUser from '../hooks/auth/useGetUser';

type UserAction =
  | { type: 'LOGIN'; payload: User }
  | { type: 'LOGOUT' }
  | { type: 'UPDATE_USER'; payload: Partial<User> };

// Create an initial state for the user
const initialUserState: User | null = null;

// Create the context
const UserContext = createContext<{
  user: User | null;
  dispatch: React.Dispatch<UserAction>;
}>({
  user: initialUserState,
  dispatch: () => {},
});

// Create a reducer function to manage user state
const userReducer = (state: User | null, action: UserAction): User | null => {
  switch (action.type) {
    case 'LOGIN':
        state = action.payload;
      return state;
    case 'LOGOUT':
      return null;
    case 'UPDATE_USER':
      if (state) {
        return { ...state, ...action.payload };
      }
      return null;
    default:
      return state;
  }
};

// Create a UserProvider component to wrap your app with the context
export const UserProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
  const [user, dispatch] = useReducer(userReducer, initialUserState);

  return (
    <UserContext.Provider value={{ user, dispatch }}>{children}</UserContext.Provider>
  );
};

// Create a custom hook to access the user context
export const useUser = () => {
  const context = useContext(UserContext);
  if (!context) {
    throw new Error('useUser must be used within a UserProvider');
  }

  if (!context. user) {
    const token = useToken();
    
    if (token && token.accessToken && token.accessToken.length > 0 && token.refreshToken && token.refreshToken.length > 0) {
       let accessToken = token.accessToken ? token.accessToken : "";
       let refreshToken = token.refreshToken ? token.refreshToken : "";

        const axiosSecure = useAxiosSecureWithToken(true, accessToken, refreshToken);
        const {data} = useGetUser(axiosSecure);
        if (data?.data) {
          let user = data.data;
          user = {
            ...user,
            accessToken: token.accessToken ? token.accessToken : "",
            refreshToken: token.refreshToken ? token.refreshToken : "",
          }
            context.dispatch({type: "LOGIN", payload: data!.data});
        }
    }
  }


  return context;
};
