import axios from "axios";
import { loginFailure, loginSuccess } from "./action";
import { call, put, takeLatest } from "redux-saga/effects";

function parseJwt(token) {
  try {
    // Lấy phần payload (phần thứ 2 trong chuỗi token)
    const base64Url = token.split(".")[1];
    // Thay thế ký tự base64url sang base64 chuẩn
    const base64 = base64Url.replace(/-/g, "+").replace(/_/g, "/");
    // Giải mã base64
    const jsonPayload = decodeURIComponent(
      atob(base64)
        .split("")
        .map((c) => "%" + ("00" + c.charCodeAt(0).toString(16)).slice(-2))
        .join("")
    );

    return JSON.parse(jsonPayload);
  } catch (e) {
    return null;
  }
}

function* loginSaga(action) {
  try {
    const res = yield call(
      axios.post,
      "http://localhost:8282/api/auth/token",
      action.payload
    );

    // const { token, role } = res.data;
    const token = res.data.token;

    const decoded = parseJwt(token);
    const role = decoded?.roles?.length > 0 ? decoded.roles[0] : null;
    console.log("Extracted role:", role);
    localStorage.setItem("token", token);
    localStorage.setItem("role", role);
    yield put(loginSuccess({ token, role }));
  } catch (e) {
    yield put(loginFailure("Sai tài khoản hoặc mật khẩu"));
  }
}

export default function* AuthSaga() {
  yield takeLatest("LOGIN_REQUEST", loginSaga);
}
