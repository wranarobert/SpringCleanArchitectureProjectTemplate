<template>
  <q-tr :props="props">
    <q-td v-for="column in props.cols" :key="column.name">
      <template v-if="column.name === 'actions'">
        <slot :name="`form-row-cell-${column.name}`" :formData="formData">
          <q-btn
            @click="submitRowClick"
            round
            size="sm"
            color="positive"
            icon="check"
            class="q-mx-xs"
          />
          <q-btn
            @click="cancelRowClick"
            round
            size="sm"
            color="negative"
            icon="clear"
            class="q-mx-xs"
          />
        </slot>
      </template>
      <slot v-else-if="!column.form" :name="`form-row-cell-${column.name}`" :formData="formData" />
      <dt-input
        v-else-if="column.form.type === 'input'"
        :value="formData"
        @input="onFormDataChange"
        :field="column.form.controlField"
        :qAttrs="column.form.qAttrs"
      />
      <dt-select
        v-else-if="column.form.type === 'select'"
        :value="formData"
        @input="onFormDataChange"
        :field="column.form.controlField"
        :options="column.form.options"
        :optionValue="column.form.optionValue"
        :optionLabel="column.form.optionLabel"
        :qAttrs="column.form.qAttrs"
      />
      <dt-checkbox
        v-else-if="column.form.type === 'checkbox'"
        :value="formData"
        @input="onFormDataChange"
        :field="column.form.controlField"
        :qAttrs="column.form.qAttrs"
      />
      <dt-toggle
        v-else-if="column.form.type === 'toggle'"
        :value="formData"
        @input="onFormDataChange"
        :field="column.form.controlField"
        :qAttrs="column.form.qAttrs"
      />
      <dt-date-time-picker
        v-else-if="column.form.type === 'dateTimePicker'"
        :value="formData"
        @input="onFormDataChange"
        :field="column.form.controlField"
        :qAttrs="column.form.qAttrs"
      />
      <dt-color-picker
        v-else-if="column.form.type === 'colorPicker'"
        :value="formData"
        @input="onFormDataChange"
        :field="column.form.controlField"
        :qAttrs="column.form.qAttrs"
      />
      <template v-else>
        <slot :name="`form-row-cell-${column.name}`" :formData="formData"></slot>
      </template>
    </q-td>
  </q-tr>
</template>

<script>
import DtInput from '../../FormTypes/DtInput';
import DtSelect from '../../FormTypes/DtSelect';
import DtCheckbox from '../../FormTypes/DtCheckbox';
import DtToggle from '../../FormTypes/DtToggle';
import DtDateTimePicker from '../../FormTypes/DtDateTimePicker';
import DtColorPicker from '../../FormTypes/DtColorPicker';

export default {
  props: {
    props: {
      type: Object,
      required: true,
    },
    formData: {
      type: Object,
    },
  },
  watch: {
    formData(current, previous) {
      this.$emit('formChange', current, previous);
    },
  },
  methods: {
    onFormDataChange(formData) {
      this.$emit('update:formData', formData);
    },
    submitRowClick() {
      this.$emit('submitRowClick', { ...this.formData });
    },
    cancelRowClick() {
      this.$emit('cancelRowClick', { ...this.formData });
    },
  },
  components: {
    DtInput,
    DtSelect,
    DtCheckbox,
    DtToggle,
    DtDateTimePicker,
    DtColorPicker,
  },
};
</script>

<style></style>
