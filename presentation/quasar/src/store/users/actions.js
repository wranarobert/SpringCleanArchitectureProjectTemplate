import UserService from 'src/core/UserService/UserService';

export default {
  '[Users Page] Get Data': ({ dispatch }) => Promise.all([
    dispatch('roles/[API] Get Entities', null, { root: true }),
    dispatch('[API] Get All Users'),
  ]),
  '[Users Page] Add User': ({ dispatch }, userData) => dispatch('[API] Add User', userData),
  '[Users Page] Edit User': ({ dispatch }, userData) => dispatch('[API] Edit User', userData),
  '[Users Page] Delete User': ({ dispatch }, id) => dispatch('[API] Delete User', id),
  '[API] Get All Users': ({ commit }) => {
    commit('statusLoading');
    return UserService.getAll()
      .then((userList) => {
        commit('saveUserList', userList);
        commit('statusSuccess');
        return userList;
      })
      .catch((error) => {
        commit('statusError', error);
        throw error;
      });
  },
  '[API] Add User': ({ commit }, userData) => {
    commit('statusLoading');
    return UserService.addUser(userData)
      .then((userResponse) => {
        commit('addUserToList', userResponse);
        commit('statusSuccess', userResponse);
        return userResponse;
      })
      .catch((error) => {
        commit('statusError', error);
        throw error;
      });
  },
  '[API] Edit User': ({ commit }, userData) => {
    commit('statusLoading');
    return UserService.editUser(userData.id, userData)
      .then((userResponse) => {
        commit('editUserFromList', userResponse);
        commit('statusSuccess', userResponse);
        return userResponse;
      })
      .catch((error) => {
        commit('statusError', error);
        throw error;
      });
  },
  '[API] Delete User': ({ commit }, id) => {
    commit('statusLoading');
    return UserService.deleteUser(id)
      .then((userResponse) => {
        commit('deleteUserFromList', id);
        commit('statusSuccess', userResponse);
        return userResponse;
      })
      .catch((error) => {
        commit('statusError', error);
        throw error;
      });
  },
};
