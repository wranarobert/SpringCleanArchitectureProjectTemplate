import axios from 'axios';

const api = '/api/roles';

export function getRoles() {
  return axios.get(api).then(({ data }) => data);
}

export default {
  getRoles,
};
