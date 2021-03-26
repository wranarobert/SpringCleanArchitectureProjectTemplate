import axios from 'axios';

const api = '/api/users';

export function getAll() {
  return axios.get(api).then(({ data }) => data);
}

export function addUser(entityData) {
  console.log(entityData);
  return axios.post(api, entityData).then(({ data }) => data);
}

export function editUser(id, entityData) {
  console.log(entityData);
  return axios.put(`${api}/${id}`, entityData).then(({ data }) => data);
}

export function deleteUser(id) {
  return axios.delete(`${api}/${id}`).then(({ data }) => data);
}

export default {
  getAll,
  addUser,
  editUser,
  deleteUser,
};
