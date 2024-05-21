import http from "./http.js";

const api = http;

const login = async (userData) => {
  try {
    const { data } = await api.post(`/user/login`, {
      ...userData,
    });
    return data;
  } catch (error) {
    console.log(error);
    return null;
  }
};

export { login };
