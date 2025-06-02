import { call, put, takeLatest, all } from "redux-saga/effects";
import axios from "axios";

import { act } from "react";
import {
  fetchProductsFailure,
  fetchProductsRequest,
  fetchProductsSuccess,
} from "./action";

const api = axios.create({
  baseURL: "http://localhost:8282/api",
  headers: { "Content-Type": "application/json" },
  withCredentials: true,
});

function authHeader() {
  return { Authorization: `Bearer ${localStorage.getItem("token")}` };
}

function* fetchProducts() {
  try {
    const res = yield call(api.get, "/product", { headers: authHeader() });
    yield put(fetchProductsSuccess(res.data));
  } catch (error) {
    yield put(fetchProductsFailure(error.message));
  }
}

function* addProduct(action) {
  yield call(api.post, "/product", action.payload, { headers: authHeader() });
  yield put(fetchProductsRequest());
}

function* updateProduct(action) {
  yield call(api.put, `/product/${action.payload.id}`, action.payload, {
    headers: authHeader(),
  });
  yield put(fetchProductsRequest());
}

function* deleteProduct(action) {
  yield call(api.delete, `/product/${action.payload}`, {
    headers: authHeader(),
  });
  yield put(fetchProductsRequest());
}

export default function* ProductSaga() {
  yield all([
    takeLatest("FETCH_PRODUCTS_REQUEST", fetchProducts),
    takeLatest("ADD_PRODUCT_REQUEST", addProduct),
    takeLatest("UPDATE_PRODUCT_REQUEST", updateProduct),
    takeLatest("DELETE_PRODUCT_REQUEST", deleteProduct),
  ]);
}
