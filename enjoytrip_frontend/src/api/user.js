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

const signUp = async (userData) => {
  try {
    await api.post(`/user/`, {
      ...userData,
    });
    return true;
  } catch (error) {
    console.log(error);
    return false;
  }
};

export { login, signUp };
