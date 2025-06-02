export const loginRequest = (payload) => ({ type: "LOGIN_REQUEST", payload });
export const loginSuccess = (payload) => ({ type: "LOGIN_SUCCESS", payload });
export const loginFailure = (error) => ({ type: "LOGIN_FAILURE", error });
// action.js
export const logout = () => ({ type: "LOGOUT" });
