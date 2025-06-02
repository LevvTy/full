import React from "react";
import { combineReducers } from "redux";
import AuthReducer from "./auth/reducer";
import ProductReducer from "./product/Reducer";

export default combineReducers({
  auth: AuthReducer,
  product: ProductReducer,
});
