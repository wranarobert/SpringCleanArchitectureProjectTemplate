<template>
  <q-page padding>
    <q-uploader @added="addFile" @removed="removeFile" />
    <q-btn @click="uploadFile" :disable="loading">{{ this.loading ? 'Loading' : 'Upload' }}</q-btn>
    <data-table
      title="Files"
      :columns="columns"
      :data="uploadedFiles"
      :loading="loading"
      @deleteRow="deleteFile"
    >
      <template v-slot:row-cell-actions-prepend="{ props }">
        <q-btn @click="downloadFile(props)" :disable="loading">Download</q-btn>
      </template>
    </data-table>
  </q-page>
</template>

<script>
import axios from 'axios';

import ItemService from 'src/core/ItemService/ItemService';

import DataTable from 'src/infrastructure/DataTable/DataTable';

import DownloadFile from 'src/infrastructure/DownloadFile/DownloadFile';

export default {
  data() {
    return {
      file: null,
      loading: false,
      columns: [
        {
          name: 'originalFilename',
          field: 'originalFilename',
          label: 'Filename',
          align: 'left',
          sortable: true,
        },
        {
          name: 'actions',
          align: 'center',
        },
      ],
      uploadedFiles: [],
    };
  },
  methods: {
    addFile([firstFile]) {
      this.file = firstFile;
    },
    removeFile() {
      this.file = null;
    },
    uploadFile() {
      this.loading = true;

      ItemService.uploadFile({ file: this.file })
        .then(response => {
          console.log(response);
          this.uploadedFiles.push(response);
        })
        .catch(error => console.log(error.response))
        .finally(() => {
          this.loading = false;
        });
    },
    deleteFile({ row }) {
      this.loading = true;

      axios
        .delete(`api/storage/${row.id}`)
        .then(({ data }) => data)
        .then(() => {
          this.uploadedFiles = [];
        })
        .finally(() => {
          this.loading = false;
        });
    },
    downloadFile({ row }) {
      this.loading = true;

      axios
        .get(`api/storage/${row.id}`, { responseType: 'blob' })
        .then(response => {
          console.log(response);
          DownloadFile(response);
        })
        .catch(response => {
          console.error('Could not download a file.', response);
        })
        .finally(() => {
          this.loading = false;
        });
    },
  },
  components: {
    DataTable,
  },
};
</script>

<style></style>
