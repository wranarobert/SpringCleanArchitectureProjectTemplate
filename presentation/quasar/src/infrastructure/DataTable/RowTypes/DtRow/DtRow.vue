<template>
  <q-tr :props="props">
    <q-td
      v-for="column in props.cols"
      :key="column.name"
      :props="props"
      :auto-width="isActionColumn(column)"
    >
      <template v-if="isActionColumn(column)">
        <slot :name="`row-cell-${column.name}-prepend`" :props="props"></slot>
        <slot :name="`row-cell-${column.name}-print`" :props="props">
          <q-btn
            v-if="metadata.showPrint"
            @click="printRowClick"
            round
            size="sm"
            color="positive"
            icon="print"
            class="q-mx-xs"
          />
        </slot>
        <slot :name="`row-cell-${column.name}-between`" :props="props"></slot>
        <slot :name="`row-cell-${column.name}-edit`" :props="props">
          <dt-edit-btn v-if="metadata.showEdit" @click="editRowClick"></dt-edit-btn>
        </slot>
        <slot :name="`row-cell-${column.name}-delete`" :props="props">
          <q-btn
            v-if="metadata.showDelete"
            @click="deleteRowClick"
            round
            size="sm"
            color="negative"
            icon="delete"
            class="q-mx-xs"
          ></q-btn>
        </slot>
        <slot :name="`row-cell-${column.name}-append`" :props="props"></slot>
      </template>
      <template v-else-if="column.name === 'badge'">
        <slot :name="`row-cell-${column.name}`" :props="props">
          <q-badge :style="`background-color:${props.row[column.color]}`">{{ column.value }}</q-badge>
        </slot>
      </template>
      <template v-else>
        <slot :name="`row-cell-${column.name}`" :props="props">{{ column.value }}</slot>
      </template>
    </q-td>
  </q-tr>
</template>

<script>
import DtEditBtn from './DtEditBtn';
import deleteDialog from '../../Dialogs/deleteDialog';

export default {
  props: {
    props: {
      type: Object,
      required: true,
    },
    metadata: {
      type: Object,
    },
  },
  methods: {
    isActionColumn(column) {
      return column.name === 'actions';
    },
    printRowClick() {
      this.$emit('printRowClick', this.props);
    },
    editRowClick() {
      this.$emit('editRowClick', this.props);
    },
    deleteRowClick() {
      this.$q.dialog(deleteDialog).onOk(() => {
        this.$emit('deleteRowClick', this.props);
      });
    },
  },
  components: {
    DtEditBtn,
  },
};
</script>

<style></style>
