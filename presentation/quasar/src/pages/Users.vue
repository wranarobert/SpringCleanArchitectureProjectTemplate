<template>
  <q-page padding>
    <data-table
      ref="userTable"
      class="sticky-action-column-table"
      title="Users"
      :columns="columns"
      :data="userList"
      :loading="loading"
      @addRow="addUser"
      @editRow="editUser"
      @deleteRow="deleteUser"
    >
      <template v-slot:search-append>
        <q-btn @click="openAddForm">Add</q-btn>
      </template>

      <template v-slot:row-cell-password>*****</template>
      <template v-slot:row-cell-roles="{ props }">{{ getRoleLabels(props.row) }}</template>
      <template v-slot:row-cell-actions-delete="{ props }">
        <template v-if="props.row.username === 'admin'">{{ '' }}</template>
      </template>
    </data-table>
  </q-page>
</template>

<script>
import DataTable from 'src/infrastructure/DataTable/DataTable';

import InputSettings from 'src/infrastructure/DataTable/Settings/InputSettings';
import SelectSettings from 'src/infrastructure/DataTable/Settings/SelectSettings';

import UserModel from 'src/core/UserService/UserModel';
import UserValidatorMixin from 'src/core/UserService/UserValidatorMixin';

import QErrorDialog from 'src/infrastructure/Status/Dialogs/QErrorDialog';

export default {
  data() {
    return {
      columns: [
        {
          name: 'username',
          field: 'username',
          label: 'Username',
          form: new InputSettings({
            controlField: 'username',
            qAttrs: {
              autofocus: true,
              debounce: '100',
              'lazy-rules': true,
              rules: [this.usernameValidation],
            },
          }),
          required: true,
          align: 'left',
          sortable: true,
        },
        {
          name: 'password',
          field: 'password',
          label: 'Password',
          form: new InputSettings({
            controlField: 'password',
            qAttrs: {
              type: 'password',
              debounce: '100',
              'lazy-rules': true,
              rules: [this.passwordValidation],
            },
          }),
          required: true,
          align: 'left',
          sortable: true,
        },
        {
          name: 'roles',
          field: 'roles',
          label: 'Roles',
          form: new SelectSettings({
            controlField: 'roles',
            optionValue: 'name',
            optionLabel: 'name',
            options: () => Promise.resolve(this.$store.getters['roles/getList']),
            qAttrs: {
              multiple: true,
              clearable: true,
            },
          }),
          required: true,
          align: 'left',
          sortable: true,
        },
        {
          name: 'actions',
          align: 'center',
        },
      ],
    };
  },
  created() {
    this.getAllData();
  },
  computed: {
    userList() {
      return this.$store.getters['users/getAll'];
    },
    loading() {
      return this.$store.getters['users/isStatusLoading'];
    },
  },
  methods: {
    openAddForm() {
      this.$refs.userTable.openAddForm(new UserModel());
    },
    closeAddForm() {
      this.$refs.userTable.closeAddForm();
    },
    addUser(userData) {
      const validation = this.userValidation(userData);

      if (!validation.valid) {
        QErrorDialog('Validation Error', validation.resultMessages);
        return;
      }

      this.$store
        .dispatch('users/[Users Page] Add User', userData)
        .then(() => {
          this.closeAddForm();
        })
        .catch(error => error);
    },
    editUser(userData) {
      const validation = this.userValidation(userData);

      if (!validation.valid) {
        QErrorDialog('Validation Error', validation.resultMessages);
        return;
      }

      this.$store
        .dispatch('users/[Users Page] Edit User', userData)
        .then(() => {
          this.$refs.userTable.removeEditForm(userData);
        })
        .catch(error => error);
    },
    deleteUser({ row }) {
      this.$store.dispatch('users/[Users Page] Delete User', row.id).catch(error => error);
    },
    getAllData() {
      this.$store.dispatch('users/[Users Page] Get Data').then(userList => console.log(userList));
    },
    getRoleLabels(row) {
      console.log(row);
      return row.roles.join(', ');
    },
  },
  components: {
    DataTable,
  },
  mixins: [UserValidatorMixin],
};
</script>

<style></style>
