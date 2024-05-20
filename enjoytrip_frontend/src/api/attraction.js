import http from "./http.js";

const api = http;

const getSido = async (success, fail) => {
  await api.get(`/attraction/areacode/`).then(success).catch(fail);
};

const getGungu = async (code, success, fail) => {
  await api.get(`/attraction/areacode/${code}`).then(success).catch(fail);
};

const getContent = async (
  sidoCode,
  gugunCode,
  contentTypeId,
  success,
  fail
) => {
  const queryString = contentTypeId.reduce((acc, curr) => {
    return (acc += `&contenttypeid=${curr}`);
  }, "");

  await api
    .get(
      `attraction/?sidocode=${sidoCode}&guguncode=${gugunCode}${queryString}`
    )
    .then(success)
    .catch(fail);
};

export { getSido, getGungu, getContent };
