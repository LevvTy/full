export const fetchProductsRequest = () => ({ type: "FETCH_PRODUCTS_REQUEST" });
export const fetchProductsSuccess = (payload) => ({
  type: "FETCH_PRODUCTS_SUCCESS",
  payload,
});
export const fetchProductsFailure = (error) => ({
  type: "FETCH_PRODUCTS_FAILURE",
  error,
});

export const addProductRequest = (payload) => ({
  type: "ADD_PRODUCT_REQUEST",
  payload,
});
export const updateProductRequest = (payload) => ({
  type: "UPDATE_PRODUCT_REQUEST",
  payload,
});
export const deleteProductRequest = (payload) => ({
  type: "DELETE_PRODUCT_REQUEST",
  payload,
});
