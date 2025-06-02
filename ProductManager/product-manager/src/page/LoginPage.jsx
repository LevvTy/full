import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { loginRequest } from "../redux/auth/action";
import { Form, Input, Button } from "antd";

const LoginPage = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { loading, token } = useSelector((state) => state.auth);

  useEffect(() => {
    if (token) navigate("/product");
  }, [token, navigate]);

  const onFinish = (values) => {
    dispatch(loginRequest(values));
  };

  return (
    <Form onFinish={onFinish} style={{ maxWidth: 300, margin: "100px auto" }}>
      <Form.Item name="username" rules={[{ required: true }]}>
        <Input placeholder="Username" />
      </Form.Item>
      <Form.Item name="password" rules={[{ required: true }]}>
        <Input placeholder="Password" />
      </Form.Item>
      <Button type="primary" htmlType="submit" loading={loading} block>
        {" "}
        Login{" "}
      </Button>
    </Form>
  );
};

export default LoginPage;
