import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate,
} from "react-router-dom";
import { useSelector } from "react-redux";
import "./App.css";
import LoginPage from "./page/LoginPage";
import ProductPage from "./page/ProductPage";

function App() {
  const token = useSelector((state) => state.auth.token);

  return (
    <Router>
      <Routes>
        <Route
          path="/"
          element={<Navigate to={token ? "/product" : "/login"} />}
        />
        <Route
          path="/login"
          element={token ? <Navigate to="/product" /> : <LoginPage />}
        />
        <Route
          path="/product"
          element={token ? <ProductPage /> : <Navigate to="/login" />}
        />
      </Routes>
    </Router>
  );
}

export default App;
