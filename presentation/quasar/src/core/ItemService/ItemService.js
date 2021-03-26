import axios from 'axios';

const apiPrefix = '/api';

export function uploadFile({ file }) {
  const uploadData = new FormData();
  uploadData.append('file', file);

  return axios.post(`${apiPrefix}/storage`, uploadData).then(({ data }) => data);
}

export default {
  uploadFile,
};
