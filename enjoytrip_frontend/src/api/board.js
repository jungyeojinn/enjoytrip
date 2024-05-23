import http from "./http.js";
import { useCookies } from "vue3-cookies";

const api = http;
const { cookies } = useCookies();

const getBoardList = async (offset, pageSize) => {
  try {
    const { data } = await api.get(
      `/board/?offset=${offset}&pageSize=${pageSize}`
    );
    return data;
  } catch (error) {
    console.log(error);
    return null;
  }
};

const getBoard = async (boardId) => {
  try {
    const { data } = await api.get(`/board/${boardId}`);
    return data;
  } catch (error) {
    console.log(error);
    return null;
  }
};

const addBoard = async (formData) => {
  console.log(formData);
  try {
    await api.post(`/board/`, formData, {
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

export { getBoardList, addBoard, getBoard };
