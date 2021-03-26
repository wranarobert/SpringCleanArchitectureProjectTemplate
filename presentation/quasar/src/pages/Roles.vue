<template>
  <q-page class="flex flex-center">
    <data-table
      ref="roleTable"
      title="Roles"
      :columns="columns"
      :data="roleList"
      :loading="loading"
    ></data-table>
  </q-page>
</template>

<style>
</style>

<script>
import DataTable from 'src/infrastructure/DataTable/DataTable';

export default {
  data() {
    return {
      columns: [
        {
          name: 'name',
          field: 'name',
          label: 'Name',
          align: 'left',
          sortable: true,
        },
      ],
    };
  },
  created() {
    this.getRoles();
  },
  computed: {
    roleList() {
      return this.$store.getters['roles/getList'];
    },
    loading() {
      return this.$store.getters['roles/isStatusLoading'];
    },
  },
  methods: {
    getRoles() {
      this.$store.dispatch('roles/[API] Get Entities');
    },
  },
  components: {
    DataTable,
  },
};
</script>
