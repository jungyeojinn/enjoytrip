import http from "./http.js";
import { useCookies } from "vue3-cookies";

const api = http;
const { cookies } = useCookies();

const addPlan = async (title, startDate, endDate, userId, formData) => {
  const { data } = await api.post(
    `/attrplan/?title=${title}&start_date=${startDate}&end_date=${endDate}&user_id=${userId}`,
    formData,
    {
      headers: {
        "Content-Type": "multipart/form-data",
        "Authorization ": `Bearer ${cookies.get("accessToken")}`,
      },
    }
  );

  return data;
};

const addPlanItem = async (planId, item) => {
  await api.post(`/attrplan/${planId}/add`, item, {
    headers: {
      "Authorization ": `Bearer ${cookies.get("accessToken")}`,
    },
  });
};

const addPlans = async () => {
  const { data } = await api.get(`/attrplan/`);
  return data;
};

const getDetailPlan = async (id) => {
  const { data } = await api.get(`/attrplan/${id}`);
  return data;
};

const getPlanOrder = async (id) => {
  const { data } = await api.get(`/attrplan/${id}/plans`);
  return data;
};

export { addPlan, addPlanItem, addPlans, getDetailPlan, getPlanOrder };
