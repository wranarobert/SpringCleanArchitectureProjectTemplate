import downloadjs from 'downloadjs';
import contentDisposition from 'content-disposition';

export default function(axiosResponse) {
  downloadjs(
    new Blob([axiosResponse.data]),
    contentDisposition.parse(axiosResponse.headers['content-disposition']).parameters.filename,
  );
}
