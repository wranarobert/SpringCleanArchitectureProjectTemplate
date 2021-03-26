<script>
import { QTable } from 'quasar';

import { removeObjectKey, patchObjectValue, hasObjectKey } from './Utils/objectOperators';

import DtFormRow from './RowTypes/DtFormRow/DtFormRow';
import DtRow from './RowTypes/DtRow/DtRow';

import DtSearch from './Components/DtSearch';

export default {
  inheritAttrs: false,
  props: {
    rowKey: {
      type: String,
      default: 'id',
    },
    search: {
      type: Boolean,
      default: true,
    },
    editingRowStrategy: {
      type: String,
      validator: prop => ['single', 'multiple'].includes(prop),
      default: 'single',
    },
  },
  data() {
    return {
      addForm: null,
      editRowForms: {},
      searchText: '',
    };
  },
  computed: {
    isAllowedOpenForm() {
      if (this.editingRowStrategy === 'multiple') {
        return true;
      }

      return !this.addForm && !Object.keys(this.editRowForms).length;
    },
    attrs() {
      return this.$attrs;
    },
    listeners() {
      return Object.keys(this.$listeners).filter(
        listener =>
          !listener.includes(['addRow', 'editRow', 'openEditForm', 'deleteRow', 'formChange']),
      );
    },
    hasPrintRowEvent() {
      return !!this.$listeners.printRow;
    },
    hasEditRowEvent() {
      return !!this.$listeners.editRow;
    },
    hasOpenEditFormEvent() {
      return !!this.$listeners.openEditForm;
    },
    hasDeleteRowEvent() {
      return !!this.$listeners.deleteRow;
    },
    dtRowMetadata() {
      return {
        showPrint: this.hasPrintRowEvent,
        showEdit: this.hasEditRowEvent || this.hasOpenEditFormEvent,
        showDelete: this.hasDeleteRowEvent,
      };
    },
    rowCellSlots() {
      return Object.keys(this.$scopedSlots).filter(slotName => slotName.startsWith('row-cell'));
    },
    formRowCellSlots() {
      return Object.keys(this.$scopedSlots).filter(slotName =>
        slotName.startsWith('form-row-cell'),
      );
    },
  },
  methods: {
    updateSearch(value) {
      this.searchText = value;
    },
    printRow(props) {
      this.$emit('printRow', props);
    },
    addRow(formData) {
      this.$emit('addRow', formData);
    },
    editRow(formData) {
      this.$emit('editRow', formData);
    },
    deleteRow(props) {
      this.$emit('deleteRow', props);
    },
    updateAddForm(formData) {
      this.addForm = formData;
    },
    openAddForm(formData) {
      if (!this.isAllowedOpenForm) {
        return;
      }

      this.updateAddForm(formData);
    },
    closeAddForm() {
      this.addForm = null;
    },
    isEditable({ row }) {
      return hasObjectKey(this.editRowForms, row[this.rowKey]);
    },
    openEditForm({ row }) {
      if (!this.isAllowedOpenForm) {
        return;
      }

      if (this.hasOpenEditFormEvent) {
        this.$emit('openEditForm', row);
      } else {
        this.addEditForm(row);
      }
    },
    closeEditForm(formData) {
      this.removeEditForm(formData);
    },
    getEditForm({ row }) {
      return this.editRowForms[row[this.rowKey]];
    },
    addEditForm(formData) {
      this.editRowForms = patchObjectValue(this.editRowForms, formData[this.rowKey], formData);
    },
    updateEditForm(formData) {
      if (!hasObjectKey(this.editRowForms, formData[this.rowKey])) {
        return;
      }

      this.addEditForm(formData);
    },
    removeEditForm(formData) {
      if (!hasObjectKey(this.editRowForms, formData[this.rowKey])) {
        return;
      }

      this.editRowForms = removeObjectKey(formData[this.rowKey], this.editRowForms);
    },
    onFormChange(current, previous) {
      this.$emit('formChange', current, previous);
    },
  },
  components: {
    QTable,
    DtSearch,
    DtFormRow,
    DtRow,
  },
};
</script>

<template src="./DataTable.html"></template>

<style lang="sass">
.sticky-action-column-table

  thead tr:last-child th:last-child
    background-color: #fff

  td:last-child
    background-color: #fff

  th:last-child,
  td:last-child
    position: sticky
    right: 0
    z-index: 1
</style>
