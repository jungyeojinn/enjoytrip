import http from "./http.js";
import { useCookies } from "vue3-cookies";

const { cookies } = useCookies();
const api = http;

const addHotplace = async (formData) => {
  try {
    await api.post(`/hotplace/`, formData, {
      headers: {
        "Content-Type": "multipart/form-data",
        "Authorization ": `Bearer ${cookies.get("accessToken")}`,
      },
    });

    return true;
  } catch (error) {
    console.log(error);
    return false;
  }
};

const getHotplace = async () => {
  try {
    let { data } = await api.get(`/hotplace/`);
    return data;
  } catch (error) {
    console.log(error);
    return null;
  }
};

const getHotplaceDetail = async (id) => {
  try {
    let { data } = await api.get(`/hotplace/${id}`);
    return data;
  } catch (error) {
    console.log(error);
    return null;
  }
};

const deleteHotPlace = async (hotplaceId) => {
  try {
    await api.delete(`/hotplace/${hotplaceId}`, {
      headers: {
        "Authorization ": `Bearer ${cookies.get("accessToken")}`,
      },
    });
    return true;
  } catch (error) {
    console.log(error);
    return false;
  }
};

const updateHotPlace = async (hotplaceId, formData) => {
  try {
    await api.patch(`/hotplace/${hotplaceId}`, formData, {
      headers: {
        "Content-Type": "multipart/form-data",
        "Authorization ": `Bearer ${cookies.get("accessToken")}`,
      },
    });
    return true;
  } catch (error) {
    console.log(error);
    return false;
  }
};

export {
  addHotplace,
  getHotplace,
  getHotplaceDetail,
  deleteHotPlace,
  updateHotPlace,
};
