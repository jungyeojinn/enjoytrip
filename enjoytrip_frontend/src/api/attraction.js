import http from "./http.js";

const api = http;

const getSido = async (success, fail) => {
  await api.get(``).then(success).catch(fail);
};

const getGungu = async (success, fail) => {
  await api.get(``).then(success).catch(fail);
};

export { getSido, getGungu };
