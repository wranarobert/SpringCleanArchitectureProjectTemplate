export default {
  saveUserList: (state, userList) => (state.users = userList.slice()),
  addUserToList: (state, userData) => (state.users = state.users.concat(userData)),
  editUserFromList: (state, userData) => {
    // Slice copies the list to new reference
    const entityList = state.users.slice();

    const index = entityList.findIndex(entity => entity.id === userData.id);

    const editedObject = { ...userData };

    // Replace data by index
    const list = entityList.splice(index, 1, editedObject);

    state.users = list;
  },
  deleteUserFromList: (state, userId) =>
    (state.users = state.users.filter(entity => userId !== entity.id)),
};
