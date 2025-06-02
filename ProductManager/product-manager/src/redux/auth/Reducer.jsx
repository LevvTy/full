const initialState = {
  token: localStorage.getItem("Token") || null,
  role: localStorage.getItem("role") || null,
  loading: false,
  error: null,
};

export default function AuthReducer(state = initialState, action) {
  switch (action.type) {
    case "LOGIN_REQUEST":
      return { ...state, loading: true };
    case "LOGIN_SUCCESS":
      return {
        ...state,
        loading: false,
        token: action.payload.token,
        role: action.payload.role,
      };
    case "LOGIN_FAILURE":
      return { ...state, loading: false, error: action.error };
    case "LOGOUT":
      return {
        ...state,
        token: null,
        role: null,
      };

    default:
      return state;
  }
}
