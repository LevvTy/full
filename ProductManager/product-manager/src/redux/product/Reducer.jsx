const initialState = {
  list: [],
  loading: false,
  error: null,
};

export default function ProductReducer(state = initialState, action) {
  switch (action.type) {
    case "FETCH_PRODUCTS_REQUEST":
      return { ...state, loading: true };
    case "FETCH_PRODUCTS_SUCCESS":
      return { ...state, loading: false, list: action.payload };
    case "FETCH_PRODUCTS_FAILURE":
      return { ...state, loading: false, error: action.error };
    default:
      return state;
  }
}
