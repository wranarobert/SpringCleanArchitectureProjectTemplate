<template>
  <q-input dense outlined :value="value[field]" @input="update($event)" v-bind="qAttrs" />
</template>

<script>
import { QInput } from 'quasar';

export default {
  inheritAttrs: false,
  props: {
    field: {
      type: String,
      required: true,
    },
    value: {
      type: Object,
      required: true,
    },
    validation: {
      type: Function,
    },
    qAttrs: {
      type: Object,
    },
  },
  methods: {
    update(value) {
      this.$emit('input', { ...this.value, [this.field]: value });
    },
  },
  components: {
    QInput,
  },
};
</script>

<style></style>
