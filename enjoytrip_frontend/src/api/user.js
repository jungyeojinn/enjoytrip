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

const logout = async (id) => {
  try {
    await api.get(`/user/logout/${id}`);
    return true;
  } catch (error) {
    console.log(error);
    return false;
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

const getUserInfo = async (userId) => {
  try {
    let { data } = await api.get(`/user/${userId}`);
    return data;
  } catch (error) {
    console.log(error);
    return null;
  }
};

const updateUserInfo = async (userId, formData) => {
  try {
    await api.patch(`/user/${userId}`, formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
    return true;
  } catch (error) {
    console.log(error);
    return null;
  }
};
export { login, logout, signUp, getUserInfo, updateUserInfo };
