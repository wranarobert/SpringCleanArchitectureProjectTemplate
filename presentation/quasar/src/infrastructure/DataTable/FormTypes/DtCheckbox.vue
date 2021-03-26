<template>
  <q-checkbox dense :value="value[field]" @input="update" v-bind="qAttrs" />
</template>

<script>
import { QCheckbox } from 'quasar';

export default {
  inheritAttrs: false,
  props: {
    value: {
      type: Object,
      required: true,
    },
    field: {
      type: String,
      required: true,
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
    QCheckbox,
  },
};
</script>

<style>
</style>
