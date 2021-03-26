import ErrorResponseModel from '../Models/ErrorResponseModel';

export default function (error) {
  if (error.response) {
    if (error.response.status === 401) {
      return null;
    }
    return new ErrorResponseModel({
      title: error.response.data.error,
      message: error.response.data.message,
    });
  } if (error.request) {
    return new ErrorResponseModel({
      title: 'No Response Error',
      message: 'The request was made but no response was received.',
    });
  }

  return new ErrorResponseModel({
    title: 'Client Error',
    message: 'Something happened in setting up the request.',
  });
}
