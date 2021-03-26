import axios from 'axios';

const apiPrefix = '/api/codebook';

export function getAllEntities(Entity) {
  return axios
    .get(`${apiPrefix}/${Entity}/getAll`)
    .then(({ data }) => data);
}

export function addEntity(Entity, entityData) {
  console.log(Entity, entityData);
  return axios
    .post(`${apiPrefix}/${Entity}/add`, entityData)
    .then(({ data }) => data);
}

export function editEntity(Entity, id, entityData) {
  return axios
    .put(`${apiPrefix}/${Entity}/edit/${id}`, entityData)
    .then(({ data }) => data);
}

export function deleteEntity(Entity, id) {
  return axios
    .delete(`${apiPrefix}/${Entity}/deleteById/${id}`)
    .then(({ data }) => data);
}

export default {
  getAllEntities,
  addEntity,
  editEntity,
  deleteEntity,
};
