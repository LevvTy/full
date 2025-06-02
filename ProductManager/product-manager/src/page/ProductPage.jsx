import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import {
  fetchProductsRequest,
  addProductRequest,
  updateProductRequest,
  deleteProductRequest,
} from "../redux/product/action";
import { Button, Input, Table, Space, message } from "antd";
import { useNavigate } from "react-router-dom";
import { logout } from "../redux/auth/action";
const ProductPage = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { list, loading } = useSelector((state) => state.product);
  const { token, role } = useSelector((state) => state.auth);
  const [inputValue, setInputValue] = useState("");
  const [selectedProduct, setSelectedProduct] = useState(null);
  const [maSP, setMaSP] = useState("");
  const [tenSP, setTenSP] = useState("");
  const [dvt, setDvt] = useState("");
  const [nuocSX, setNuocSX] = useState("");
  const [gia, setGia] = useState("");

  useEffect(() => {
    dispatch(fetchProductsRequest());
  }, [dispatch]);

  const handleSubmit = () => {
    const newProduct = {
      maSP,
      tenSP,
      dvt,
      nuocSX,
      gia,
    };
    if (selectedProduct) {
      // update
      dispatch(updateProductRequest(newProduct));
    } else {
      // add new
      dispatch(addProductRequest(newProduct));
    }
    console.log("Dữ liệu gửi lên saga:", newProduct);
    dispatch(addProductRequest(newProduct));
    // clear form
    setMaSP("");
    setTenSP("");
    setDvt("");
    setNuocSX("");
    setGia("");
  };

  const handleLogut = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("role");
    dispatch(logout());
    navigate("/login");
  };

  const columns = [
    { title: "Mã SP", dataIndex: "maSP", key: "maSP" },
    { title: "Tên sản phẩm", dataIndex: "tenSP", key: "tenSP" },
    { title: "Đơn vị tính", dataIndex: "dvt", key: "dvt" },
    { title: "Nước SX", dataIndex: "nuocSX", key: "nuocSX" },
    { title: "Giá", dataIndex: "gia", key: "gia" },
    {
      title: "Thao tác",
      render: (_, record) => (
        <Space>
          {role === "ROLE_ADMIN" && (
            <Button
              onClick={() => {
                setMaSP(record.maSP);
                setTenSP(record.tenSP);
                setDvt(record.dvt);
                setNuocSX(record.nuocSX);
                setGia(record.gia);
                setSelectedProduct(record);
              }}
            >
              Sửa
            </Button>
          )}
          {role === "ROLE_ADMIN" && (
            <Button
              danger
              onClick={() => dispatch(deleteProductRequest(record.id))}
            >
              Xoá
            </Button>
          )}
        </Space>
      ),
    },
  ];

  return (
    <div style={{ padding: 20 }}>
      <h2>Danh sách sản phẩm</h2>
      {role === "ROLE_ADMIN" && (
        <Space>
          <Input
            value={maSP}
            onChange={(e) => setMaSP(e.target.value)}
            placeholder="Mã sản phẩm"
          />
          <Input
            value={tenSP}
            onChange={(e) => setTenSP(e.target.value)}
            placeholder="Tên sản phẩm"
          />
          <Input
            value={dvt}
            onChange={(e) => setDvt(e.target.value)}
            placeholder="Đơn vị tính"
          />
          <Input
            value={nuocSX}
            onChange={(e) => setNuocSX(e.target.value)}
            placeholder="Nước sản xuất"
          />
          <Input
            value={gia}
            onChange={(e) => setGia(e.target.value)}
            placeholder="Giá"
          />
          <Button type="primary" onClick={handleSubmit}>
            {selectedProduct ? "Cập nhật" : "Thêm"}
          </Button>
        </Space>
      )}

      <Table
        columns={columns}
        dataSource={list}
        loading={loading}
        rowKey="maSP"
        style={{ marginTop: 20 }}
      />
      <Button onClick={handleLogut}>Logout</Button>
    </div>
  );
};

export default ProductPage;
